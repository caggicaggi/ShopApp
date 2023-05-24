import 'dart:async';
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:flutter/cupertino.dart';
import 'package:shop_app/main.dart';

import '../constant.dart';

class CartList {
  Map<int, int> productQuantities =
      {}; // Map to store product IDs and their respective quantities
  Map<int, int> addedQuantities =
      {}; // Map to track added quantities for each product
  Map<int, int> removedQuantities =
      {}; // Map to track removed quantities for each product

  Timer? _debounceTimer;
  Duration _debounceDuration = Duration(seconds: 0);

  // Method to initialize the cart with an existing map
  void initializeFromMap(Map<int, int> initialMap) {
    productQuantities = Map.from(initialMap);
    addedQuantities = {};
    removedQuantities = {};
  }

  void addProduct(int productId, int quantity) {
    if (quantity <= 0) {
      debugPrint('Invalid quantity. Quantity must be greater than 0.');
      return;
    }
    if (productQuantities.containsKey(productId)) {
      // If the product ID is already present in the cart, update the added quantity
      addedQuantities[productId] = (addedQuantities[productId] ?? 0) + quantity;
    } else {
      // If the product ID is not present, add it to the cart with the added quantity
      addedQuantities[productId] = quantity;
    }

    _triggerDebounce();
  }

  void removeProduct(int productId, int quantity) {
    if (productQuantities.containsKey(productId)) {
      // If the product ID is present in the cart, update the removed quantity
      removedQuantities[productId] =
          (removedQuantities[productId] ?? 0) + quantity;
    }

    _triggerDebounce();
  }

  void _applyDeltaUpdate() {
    for (var entry in addedQuantities.entries) {
      var productId = entry.key;
      var addedQuantity = entry.value;
      var currentQuantity = productQuantities[productId] ?? 0;
      var newQuantity = currentQuantity + addedQuantity;
      productQuantities[productId] = newQuantity;
      debugPrint('$addedQuantity x $productId added to the cart');
    }

    for (var entry in removedQuantities.entries) {
      var productId = entry.key;
      var removedQuantity = entry.value;
      var currentQuantity = productQuantities[productId] ?? 0;
      var newQuantity = currentQuantity - removedQuantity;
      productQuantities[productId] = newQuantity;
      // Check if the new quantity is 0, and remove the key-value pair
      if (newQuantity == 0) {
        productQuantities.remove(productId);
      } else {
        productQuantities[productId] = newQuantity;
      }
      debugPrint('$removedQuantity x $productId removed from the cart');
    }

    addedQuantities.clear();
    removedQuantities.clear();
  }

  void _triggerDebounce() {
    if (_debounceTimer != null && _debounceTimer!.isActive) {
      _debounceTimer!.cancel();
    }

    _debounceTimer = Timer(_debounceDuration, () {
      _updateBackend();
    });
  }

  void _updateBackend() {
  if (addedQuantities.isNotEmpty) {
    _applyDeltaUpdate();
    debugPrint('Added quantities: $addedQuantities');
    updateDb(addedQuantities, 'add', currentUser.id);
  }

  if (removedQuantities.isNotEmpty) {
    _applyDeltaUpdate();
    debugPrint('Removed quantities: $removedQuantities');
    updateDb(removedQuantities, 'remove', currentUser.id);
  }

  if (addedQuantities.isEmpty && removedQuantities.isEmpty) {
    debugPrint('No changes since the last update');
  }

  _cancelDebounce();
}


  void _cancelDebounce() {
    if (_debounceTimer != null && _debounceTimer!.isActive) {
      _debounceTimer!.cancel();
    }
  }

  Future<int> updateDb(Map<int, int> quantities, String mode, int userId) async {
  String completeUrl = '$url/cart/$mode'; // API endpoint for adding or removing items

  List<Map<String, dynamic>> requestBody = quantities.entries.map((entry) {
    int idProduct = entry.key;
    int quantity = entry.value;
    return {
      'idProduct': idProduct,
      'quantity': quantity,
      'idUtente': userId,
    };
  }).toList();

  try {
    http.Response response = await http.post(
      Uri.parse(completeUrl),
      body: jsonEncode(requestBody),
    );

    if (response.statusCode == 200) {
      return response.statusCode;
    } else {
      debugPrint('Request failed with status: ${response.statusCode}');
      return response.statusCode;
    }
  } catch (e) {
    debugPrint('Error: $e');
    return -1; // Return a custom error code or handle the error accordingly
  }
}

}
