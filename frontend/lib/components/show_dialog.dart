import 'package:flutter/material.dart';

import '../main.dart';
import '../models/CartList.dart';
import '../models/User.dart';
import '../models/Wishlist.dart';
import '../screens/sign_in/sing_in_screen.dart';

Future<dynamic> showSessionExpiredDialog(BuildContext context) {
  return showDialog(
    context: context,
    builder: (BuildContext context) {
      return AlertDialog(
        title: Text('Session Expired'),
        content: Text('Please sign in again.'),
        actions: [
          TextButton(
            child: Text('OK'),
            onPressed: () {
              Navigator.of(context).pop(); // Close the dialog
              currentUser = User();
              wishlist = Wishlist();
              demoCartList = CartList();
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
        title: Text('Product Added'),
        actions: [
          TextButton(
            child: Text('OK'),
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
        title: Text('Product Removed'),
        actions: [
          TextButton(
            child: Text('OK'),
            onPressed: () {
              Navigator.of(context).pop(); // Close the dialog
            },
          ),
        ],
      );
    },
  );
}
