// ignore_for_file: file_names

import 'dart:async';
import 'package:flutter/material.dart';
import 'package:shop_app/services/remove_wishlist.dart';
import '../services/add_wishlist.dart';

class Wishlist {
  List<int> productIds = []; // List to store product IDs
  List<int> addedProductIds = []; // List to track added product IDs
  List<int> removedProductIds = []; // List to track removed product IDs

  Timer? _debounceTimer; // Timer for debounce functionality
  final Duration _debounceDuration =
      const Duration(seconds: 2); // Debounce duration

  bool isIdInWishList(int id) {
    return productIds.contains(id);
  }

  //Method to initialize the wishlist with an existing list
  void initializeWishlist(List<int> initialProductIds) {
    productIds = List<int>.from(
        initialProductIds); // Copy initial product IDs to the wishlist
    addedProductIds = []; // Initialize added product IDs list as empty
    removedProductIds = []; // Initialize removed product IDs list as empty
  }

  Future<int> addProduct(int productId) async {
    if (!productIds.contains(productId)) {
      // Check if product ID is not already present in wishlist
      productIds.add(productId); // Add product ID to the wishlist
      addedProductIds.add(productId); // Track the added product ID

      return _triggerDebounce(); // Trigger debounce timer and return the future
    }
    return -1; // Return a custom error code or handle the error accordingly
  }

  Future<int> removeProduct(int productId) async {
    if (productIds.contains(productId)) {
      // Check if product ID is present in the wishlist
      productIds.remove(productId); // Remove product ID from the wishlist
      removedProductIds.add(productId); // Track the removed product ID

      return _triggerDebounce(); // Trigger debounce timer and return the future
    }
    return -1; // Return a custom error code or handle the error accordingly
  }

  void _applyDeltaUpdate() {
    for (var productId in addedProductIds) {
      debugPrint(
          '$productId added to the wishlist'); // Print message for each added product ID
    }

    for (var productId in removedProductIds) {
      debugPrint(
          '$productId removed from the wishlist'); // Print message for each removed product ID
    }
  }

  Future<int> _triggerDebounce() {
    if (_debounceTimer != null && _debounceTimer!.isActive) {
      _debounceTimer!.cancel(); // Cancel any existing debounce timer
    }

    final completer = Completer<int>();
    _debounceTimer = Timer(_debounceDuration, () async {
      final statusCode = await _updateBackend(); // Await the backend update
      completer.complete(statusCode);
    });

    return completer.future;
  }

  void _cancelDebounce() {
    if (_debounceTimer != null && _debounceTimer!.isActive) {
      _debounceTimer!.cancel(); // Cancel any existing debounce timer
    }
  }

  Future<int> _updateBackend() async {
    if (addedProductIds.isNotEmpty) {
      _applyDeltaUpdate(); // Apply the delta update (print messages for added product IDs)

      final statusCode = await updateDbAddWishList(addedProductIds);
      addedProductIds.clear(); // Clear the addedProductIds list
      _cancelDebounce();
      return statusCode;

      // Cancel debounce after manual backend update
    }

    if (removedProductIds.isNotEmpty) {
      _applyDeltaUpdate(); // Apply the delta update (print messages for removed product IDs)
      final statusCode = await updateDbRemoveWishList(removedProductIds);

      removedProductIds.clear(); // Clear the removedProductIds list
      _cancelDebounce(); // Cancel debounce after manual backend update
      return statusCode;
    }

    if (addedProductIds.isEmpty && removedProductIds.isEmpty) {
      debugPrint('No changes since the last update');
      // Print message if there are no changes
      return -1;
    }
    return -1;
  }
}
