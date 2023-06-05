// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';
import '../../components/coustom_bottom_nav_bar.dart';
import '../../constant.dart';
import '../../enums.dart';
import '../../models/Product.dart';
import 'components/body.dart';

class WishListDisplayScreen extends StatelessWidget {
  static String routeName = "/product_display";

  final List<Product> productList;

  WishListDisplayScreen({required this.productList});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "Your Wishlist",
          style: TextStyle(
            color: kPrimaryColor,
            fontSize: 22,
            fontWeight: FontWeight.bold,
          ),
        ),
      ),
      extendBodyBehindAppBar: true,
      body: Body(productList: productList),
      bottomNavigationBar: CustomBottomNavBar(selectedMenu: MenuState.heart),
    );
  }
}
