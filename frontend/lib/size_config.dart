import 'package:flutter/material.dart';

class SizeConfig {
  static late MediaQueryData _mediaQueryData;
  static late double screenWidth;
  static late double screenHeight;
  static double? defaultSize;
  static Orientation? orientation;

  // Initialize the SizeConfig using the provided BuildContext
  void init(BuildContext context) {
    _mediaQueryData = MediaQuery.of(context);
    screenWidth = _mediaQueryData.size.width; // Get the screen width
    screenHeight = _mediaQueryData.size.height; // Get the screen height
    orientation = _mediaQueryData.orientation; // Get the screen orientation
  }
}

// Get the proportionate height as per screen size
double getProportionateScreenHeight(double inputHeight) {
  double screenHeight = SizeConfig.screenHeight;
  // 812 is the layout height that the designer uses
  return (inputHeight / 812.0) * screenHeight;
}

// Get the proportionate width as per screen size
double getProportionateScreenWidth(double inputWidth) {
  double screenWidth = SizeConfig.screenWidth;
  // 375 is the layout width that the designer uses
  return (inputWidth / 375.0) * screenWidth;
}
