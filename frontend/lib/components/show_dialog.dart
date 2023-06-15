import 'package:flutter/material.dart';
import '../main.dart';
import '../models/CartList.dart';
import '../models/Product.dart';
import '../models/User.dart';
import '../models/Wishlist.dart';
import '../screens/cart/cart_screen.dart';
import '../screens/sign_in/sign_in_screen.dart';

Future<dynamic> showSessionExpiredDialog(BuildContext context) {
  return showDialog(
    context: context,
    builder: (BuildContext context) {
      return AlertDialog(
        title: const Text('Session Expired'),
        content: const Text('Please sign in again.'),
        actions: [
          TextButton(
            child: const Text('OK'),
            onPressed: () {
              Navigator.of(context).pop(); // Close the dialog
              currentUser = User();
              wishlist = Wishlist();
              cart = CartList();
              tokenJWT = '';
              Navigator.pushNamed(context, SignInScreen.routeName);
              // Navigate to the sign-in screen
            },
          ),
        ],
      );
    },
  );
}

Future<dynamic> showAddedDialog(BuildContext context) {
  return showDialog(
    context: context,
    builder: (BuildContext context) {
      return AlertDialog(
        title: const Text('Product Added'),
        actions: [
          TextButton(
            child: const Text('OK'),
            onPressed: () {
              Navigator.of(context).pop(); // Close the dialog
              Navigator.of(context).pop(); // Go back to the previous screen
            },
          ),
        ],
      );
    },
  );
}

Future<dynamic> showProductRemoveDialog(BuildContext context) {
  return showDialog(
    context: context,
    builder: (BuildContext context) {
      return AlertDialog(
        title: const Text('Product Removed'),
        actions: [
          TextButton(
            child: const Text('OK'),
            onPressed: () {
              Navigator.of(context).pop(); // Close the dialog
            },
          ),
        ],
      );
    },
  );
}

Future<dynamic> showCheckoutDialog(BuildContext context) {
  return showDialog(
    context: context,
    builder: (BuildContext context) {
      return AlertDialog(
        title: const Text('Proceed to the payment'),
        actions: [
          TextButton(
            child: const Text('OK'),
            onPressed: () {
              Navigator.of(context).pop(); // Close the dialog
              Navigator.pushReplacement(context, MaterialPageRoute(builder: (context) => CartScreen()));
            },
          ),
        ],
      );
    },
  );
}

Future<dynamic> showNoProductCartDialog(BuildContext context) {
  return showDialog(
    context: context,
    builder: (BuildContext context) {
      return AlertDialog(
        title: const Text('There are no products in the cart'),
        actions: [
          TextButton(
            child: const Text('OK'),
            onPressed: () {
              Navigator.of(context).pop(); // Close the dialog
            },
          ),
        ],
      );
    },
  );
}

Future<dynamic> showNoAvailableDialog(BuildContext context, List<int> nonAvailableIds) {
  return showDialog(
    context: context,
    builder: (BuildContext context) {
      List<String> nonAvailableTitles = nonAvailableIds.map((id) {
        Product? product = listOfProduct.firstWhere((p) => p.idProduct== id);
        return product != null ? product.title : 'Product $id';
      }).toList().cast<String>();

      String dialogContent = "The following products are no longer available:\n\n${nonAvailableTitles.join('\n')}";

      return AlertDialog(
        title: const Text('Products Not Available'),
        content: Text(dialogContent),
        actions: [
          TextButton(
            child: const Text('OK'),
            onPressed: () {
              Navigator.of(context).pop(); // Close the dialog
            },
          ),
        ],
      );
    },
  );
}

