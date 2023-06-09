// ignore_for_file: prefer_const_literals_to_create_immutables

import 'package:flutter/widgets.dart';
import 'package:shop_app/screens/cart/cart_screen.dart';
import 'package:shop_app/screens/categoties_menu/categories_menu.dart';
import 'package:shop_app/screens/complete_profile/complete_profile_screen.dart';
import 'package:shop_app/screens/details/details_screen.dart';
import 'package:shop_app/screens/forgot_password/forgot_password_screen.dart';
import 'package:shop_app/screens/home/home_screen.dart';
import 'package:shop_app/screens/new_password/new_password_screen.dart';
import 'package:shop_app/screens/product_display/product_display.dart';
import 'package:shop_app/screens/profile/profile_screen.dart';
import 'package:shop_app/screens/reset_pass_success/reset_pass_success_screen.dart';
import 'package:shop_app/screens/sign_up/sign_up_screen.dart';
import 'package:shop_app/screens/splash/splash_screen.dart';
import 'package:shop_app/screens/wishlist_display/wishlist_display.dart';
import 'screens/otp/otp_screen.dart';
import 'screens/sign_in/sign_in_screen.dart';

// All our routes will be available here

// Define the routes for different screens
final Map<String, WidgetBuilder> routes = {
  SplashScreen.routeName: (context) =>
      SplashScreen(), // Route for the splash screen
  SignInScreen.routeName: (context) =>
      SignInScreen(), // Route for the sign-in screen
  ForgotPasswordScreen.routeName: (context) =>
      ForgotPasswordScreen(), // Route for the forgot password screen
  NewPasswordScreen.routeName: (context) =>
      NewPasswordScreen(), // Route for the new password screen
  SignUpScreen.routeName: (context) =>
      SignUpScreen(), // Route for the sign-up screen
  CompleteProfileScreen.routeName: (context) =>
      CompleteProfileScreen(), // Route for the complete profile screen
  OtpScreen.routeName: (context) =>
      OtpScreen(), // Route for the OTP (One-Time Password) screen
  HomeScreen.routeName: (context) => HomeScreen(), // Route for the home screen
  DetailsScreen.routeName: (context) =>
      DetailsScreen(), // Route for the details screen
  CartScreen.routeName: (context) => CartScreen(), // Route for the cart screen
  ProfileScreen.routeName: (context) =>
      ProfileScreen(), // Route for the profile screen
  ResetPassSuccessScreen.routeName: (context) =>
      ResetPassSuccessScreen(), // Route for the reset pass succes screen
  CategoriesMenuScreen.routeName: (context) =>
      CategoriesMenuScreen(), // Route for the Categories Menu screen
  ProductDisplayScreen.routeName: (context) => ProductDisplayScreen(
        productList: [],
      ), // Rout for the Product display screen
  WishListDisplayScreen.routeName: (context) =>
      WishListDisplayScreen(), // Rout for the Wishlist display screen
};
