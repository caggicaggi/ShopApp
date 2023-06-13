// ignore_for_file: prefer_const_constructors, file_names

import 'dart:async';
import 'package:flutter/cupertino.dart';
import '../services/add_cart.dart';
import '../services/remove_cart.dart';

class CartList {
  Map<int, int> productQuantities = {};
  Map<int, int> addedQuantities = {};
  Map<int, int> removedQuantities = {};

  Timer? _debounceTimer;
  final Duration _debounceDuration = Duration(seconds: 2);

  void initializeFromMap(Map<int, int> initialMap) {
    productQuantities = Map.from(initialMap);
    addedQuantities = {};
    removedQuantities = {};
  }

  Future<int> addProduct(int productId, int quantity) async {
    if (quantity <= 0) {
      debugPrint('Invalid quantity. Quantity must be greater than 0.');
      return -1; // Return a custom error code or handle the error accordingly
    }

    if (productQuantities.containsKey(productId)) {
      addedQuantities[productId] = (addedQuantities[productId] ?? 0) + quantity;
    } else {
      addedQuantities[productId] = quantity;
    }

    return _triggerDebounce();
  }

  Future<int> removeProduct(int productId, int quantity) async {
    if (productQuantities.containsKey(productId)) {
      removedQuantities[productId] =
          (removedQuantities[productId] ?? 0) + quantity;
    }

    return _triggerDebounce();
  }

  Future<int> _triggerDebounce() {
    if (_debounceTimer != null && _debounceTimer!.isActive) {
      _debounceTimer!.cancel();
    }

    final completer = Completer<int>();
    _debounceTimer = Timer(_debounceDuration, () async {
      final statusCode = await _updateBackend();
      completer.complete(statusCode);
    });

    return completer.future;
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

  Future<int> _updateBackend() async {
    if (addedQuantities.isEmpty && removedQuantities.isEmpty) {
      debugPrint('No changes since the last update');
      return 0;
    }

    if (addedQuantities.isNotEmpty) {
      debugPrint('Added quantities in DB: $addedQuantities');
      _applyDeltaUpdate();
      final statusCode = await updateDbAdd(addedQuantities);
      addedQuantities.clear();
      debugPrint(productQuantities.toString());
      return statusCode;
    }

    if (removedQuantities.isNotEmpty) {
      debugPrint('Removed quantities in DB: $removedQuantities');
      final statusCode = await updateDbRemove(removedQuantities);
      _applyDeltaUpdate();
      removedQuantities.clear();
      return statusCode;
    }

    return 0;
  }
}
