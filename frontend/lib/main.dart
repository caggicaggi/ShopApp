import 'package:flutter/material.dart';
import 'package:shop_app/routs.dart';
import 'package:shop_app/screens/splash/splash_screen.dart';
import 'package:shop_app/theme.dart';

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
      initialRoute: SplashScreen.routeName, // Set the initial route to the splash screen
      routes: routes, // Set the routes for different screens
    );
  }
}
