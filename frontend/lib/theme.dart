// ignore_for_file: prefer_const_constructors, deprecated_member_use

import 'package:flutter/material.dart';
import 'package:shop_app/constant.dart';

// Define the theme for the application
ThemeData theme() {
  return ThemeData(
    scaffoldBackgroundColor:
        Colors.white, // Background color for the app's scaffold
    fontFamily: "Muli", // Default font family used throughout the app
    appBarTheme: appBarTheme(), // Customized app bar theme
    textTheme: textTheme(), // Customized text theme
    inputDecorationTheme:
        inputDecorationTheme(), // Customized input decoration theme
    visualDensity: VisualDensity.adaptivePlatformDensity,
  );
}

// Define the input decoration theme
InputDecorationTheme inputDecorationTheme() {
  OutlineInputBorder outlineInputBorder = OutlineInputBorder(
    borderRadius: BorderRadius.circular(28),
    borderSide:
        BorderSide(color: kTextColor), // Color for the input field border
    gapPadding: 10,
  );
  return InputDecorationTheme(
    floatingLabelBehavior: FloatingLabelBehavior.always,
    contentPadding: EdgeInsets.symmetric(horizontal: 42, vertical: 20),
    enabledBorder: outlineInputBorder,
    focusedBorder: outlineInputBorder,
    border: outlineInputBorder,
  );
}

// Define the text theme
TextTheme textTheme() {
  return TextTheme(
    bodyText1: TextStyle(color: kTextColor), // Text style for body text 1
    bodyText2: TextStyle(color: kTextColor), // Text style for body text 2
  );
}

// Define the app bar theme
AppBarTheme appBarTheme() {
  return AppBarTheme(
    color: Colors.white, // Background color for the app bar
    elevation: 0, // Elevation (shadow) of the app bar
    brightness:
        Brightness.light, // Brightness of the app bar (light in this case)
    iconTheme:
        IconThemeData(color: Colors.black), // Color for the app bar icons
    textTheme: TextTheme(
      headline6: TextStyle(
          color: Color(0XFF8B8B8B),
          fontSize: 18), // Text style for the app bar headline
    ),
  );
}
