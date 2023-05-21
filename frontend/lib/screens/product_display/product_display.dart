// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';
import '../../components/coustom_bottom_nav_bar.dart';
import '../../constant.dart';
import '../../enums.dart';
import 'components/body.dart';

class ProductDisplayScreen extends StatelessWidget {
  static String routeName = "/product_display";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "Products",
          style: TextStyle(color: kPrimaryColor),
        ),
      ),
      extendBodyBehindAppBar: true,
      body: Body(),
      bottomNavigationBar: CustomBottomNavBar(selectedMenu: MenuState.home),
    );
  }
}
