// ignore_for_file: prefer_const_constructors, use_key_in_widget_constructors

import 'package:flutter/material.dart';
import 'package:shop_app/main.dart';
import 'package:shop_app/models/CartList.dart';
import 'package:shop_app/models/User.dart';
import 'package:shop_app/models/Wishlist.dart';
import 'package:shop_app/screens/splash/splash_screen.dart';
import '../../../components/menu_card.dart';
import 'profile_pic.dart';

class Body extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      padding: const EdgeInsets.symmetric(vertical: 20),
      child: Column(
        children: [
          ProfilePic(),
          const SizedBox(height: 20),
          MenuCard(
            text: "My Account",
            icon: "assets/icons/User Icon.svg",
            press: () => {},
          ),
          MenuCard(
            text: "Settings",
            icon: "assets/icons/Settings.svg",
            press: () {},
          ),
          MenuCard(
            text: "Help Center",
            icon: "assets/icons/Question mark.svg",
            press: () {},
          ),
          MenuCard(
            text: "Logout",
            icon: "assets/icons/Log out.svg",
            press: () {
              googleSignIn.signOut();
              currentUser = User();
              wishlist = Wishlist();
              cart = CartList();
              tokenJWT = '';
              Navigator.pushNamed(context, SplashScreen.routeName);
            },
          ),
        ],
      ),
    );
  }
}
