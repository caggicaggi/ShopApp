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
  Duration _debounceDuration = Duration(seconds: 2);

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

  void _triggerDebounce() {
    if (_debounceTimer != null && _debounceTimer!.isActive) {
      _debounceTimer!.cancel();
    }

    _debounceTimer = Timer(_debounceDuration, () {
      _updateBackend();
    });
  }

  void _cancelDebounce() {
    if (_debounceTimer != null && _debounceTimer!.isActive) {
      _debounceTimer!.cancel();
    }
  }

  void _applyDeltaUpdate() {
    for (var entry in addedQuantities.entries) {
      var productId = entry.key;
      var addedQuantity = entry.value;
      var currentQuantity = productQuantities[productId] ?? 0;
      var newQuantity = currentQuantity + addedQuantity;
      productQuantities[productId] = newQuantity;
      debugPrint('q: $addedQuantity x id: $productId added to the cart');
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
      debugPrint('q: $removedQuantity x id: $productId removed from the cart');
    }
  }

  void _updateBackend() {
    if (addedQuantities.isEmpty && removedQuantities.isEmpty) {
      debugPrint('No changes since the last update');
      return;
    }

    if (addedQuantities.isNotEmpty) {
      debugPrint('Added quantities in DB: $addedQuantities');
      _applyDeltaUpdate();
      updateDbAdd(addedQuantities, currentUser.id);
      addedQuantities.clear();
      debugPrint(productQuantities.toString());
    }

    if (removedQuantities.isNotEmpty) {
      debugPrint('Removed quantities in DB: $removedQuantities');
      updateDbRemove(removedQuantities, currentUser.id);
      _applyDeltaUpdate();
      removedQuantities.clear();
    }

    _cancelDebounce();
  }

  Future<int> updateDbAdd(Map<int, int> quantities, int userId) async {
    String completeUrl =
        '$url/cart/add'; // API endpoint for adding or removing items

    List<Map<String, dynamic>> requestBody = quantities.entries.map((entry) {
      int idProduct = entry.key;
      int quantity = entry.value;
      debugPrint(idProduct.toString());
      debugPrint(currentUser.id.toString());
      debugPrint(quantity.toString());
      return {
        'idProduct': idProduct,
        'idUtente': currentUser.id,
        'quantity': quantity,
      };
    }).toList();
    final headers = {
      'Authorization': 'Bearer $tokenJWT',
      'Content-Type': 'application/json',
    };

    try {
      http.Response response = await http.put(
        Uri.parse(completeUrl),
        headers: headers,
        body: jsonEncode(requestBody),
      );

      if (response.statusCode == 200) {
        debugPrint('Request succes: ${response.statusCode}');
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

  Future<int> updateDbRemove(Map<int, int> quantities, int userId) async {
    String completeUrl =
        '$url/cart/remove'; // API endpoint for adding or removing items

    List<Map<String, dynamic>> requestBody = quantities.entries.map((entry) {
      int idProduct = entry.key;
      int quantity = entry.value;
      return {
        'idProduct': idProduct,
        'idUtente': userId,
        'quantity': quantity,
      };
    }).toList();
    final headers = {
      'Authorization': 'Bearer $tokenJWT',
      'Content-Type': 'application/json',
    };

    try {
      http.Response response = await http.delete(
        Uri.parse(completeUrl),
        headers: headers,
        body: jsonEncode(requestBody),
      );

      if (response.statusCode == 200) {
        debugPrint('Request succes: ${response.statusCode}');
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
