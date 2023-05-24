import 'dart:async';

class Wishlist {
  List<int> productIds = []; // List to store product IDs
  List<int> addedProductIds = []; // List to track added product IDs
  List<int> removedProductIds = []; // List to track removed product IDs

  Timer? _debounceTimer; // Timer for debounce functionality
  Duration _debounceDuration = Duration(seconds: 2); // Debounce duration

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

  void addProduct(int productId) {
    if (!productIds.contains(productId)) {
      // Check if product ID is not already present in wishlist
      productIds.add(productId); // Add product ID to the wishlist
      addedProductIds.add(productId); // Track the added product ID

      _triggerDebounce(); // Trigger debounce timer
    }
  }

  void removeProduct(int productId) {
    if (productIds.contains(productId)) {
      // Check if product ID is present in the wishlist
      productIds.remove(productId); // Remove product ID from the wishlist
      removedProductIds.add(productId); // Track the removed product ID

      _triggerDebounce(); // Trigger debounce timer
    }
  }

  void _applyDeltaUpdate() {
    for (var productId in addedProductIds) {
      print(
          '$productId added to the wishlist'); // Print message for each added product ID
    }

    for (var productId in removedProductIds) {
      print(
          '$productId removed from the wishlist'); // Print message for each removed product ID
    }
  }

  void _triggerDebounce() {
    if (_debounceTimer != null && _debounceTimer!.isActive) {
      _debounceTimer!.cancel(); // Cancel any existing debounce timer
    }

    _debounceTimer = Timer(_debounceDuration, () {
      _updateBackend(); // Schedule the backend update after debounce duration
    });
  }

  void _cancelDebounce() {
    if (_debounceTimer != null && _debounceTimer!.isActive) {
      _debounceTimer!.cancel(); // Cancel any existing debounce timer
    }
  }

  void _updateBackend() {
    if (addedProductIds.isNotEmpty || removedProductIds.isNotEmpty) {
      // Check if there are any changes
      _applyDeltaUpdate(); // Apply the delta update (print messages for added/removed product IDs)
      print('Updated wishlist: $productIds'); // Print the updated wishlist

      // Perform backend call to update the wishlist in the database

      addedProductIds.clear(); // Clear the addedProductIds list
      removedProductIds.clear(); // Clear the removedProductIds list
      _cancelDebounce(); // Cancel debounce after manual backend update
    } else {
      print(
          'No changes since the last update'); // Print message if there are no changes
    }
  }
}
