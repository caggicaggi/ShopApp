import 'package:flutter/material.dart';
import 'package:google_sign_in/google_sign_in.dart';
import 'package:shop_app/models/Wishlist.dart';
import 'package:shop_app/routs.dart';
import 'package:shop_app/screens/splash/splash_screen.dart';
import 'package:shop_app/theme.dart';
import 'models/CartList.dart';
import 'models/Product.dart';
import 'models/User.dart';

// Global variables
List<Product> listOfProduct = [];
Wishlist wishlist = Wishlist();
CartList demoCartList = CartList();
User currentUser = User();
String tokenJWT = '';
final GoogleSignIn googleSignIn = GoogleSignIn();

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of the application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Demo',
      theme: theme(), // Set the theme of the application
      initialRoute:
          SplashScreen.routeName, // Set the initial route to the splash screen
      routes: routes, // Set the routes for different screens
    );
  }
}
