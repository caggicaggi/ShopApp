import 'package:flutter/material.dart';
import '../../../components/menu_card.dart';

class Body extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      padding: EdgeInsets.symmetric(vertical: 20),
      child: Column(
        children: [
          SizedBox(height: 20),
          MenuCard(
            text: "My Account",
            icon: "assets/icons/User Icon.svg",
            press: () => {},
          ),
          MenuCard(
            text: "Notifications",
            icon: "assets/icons/Bell.svg",
            press: () {},
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
            text: "Log Out",
            icon: "assets/icons/Log out.svg",
            press: () {},
          ),
        ],
      ),
    );
  }
}
