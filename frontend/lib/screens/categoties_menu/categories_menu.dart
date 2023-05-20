import 'package:flutter/material.dart';
import 'package:shop_app/components/coustom_bottom_nav_bar.dart';
import 'package:shop_app/constant.dart';
import 'package:shop_app/enums.dart';

import 'components/body.dart';

class CategoriesMenuScreen extends StatelessWidget {
  static String routeName = "/categories_menu";
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "Categories",
          style: TextStyle(color: kPrimaryColor),
        ),
      ),
      body: Body(),
      bottomNavigationBar: CustomBottomNavBar(selectedMenu: MenuState.profile),
    );
  }
}
