// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';
import '../../components/coustom_bottom_nav_bar.dart';
import '../../constant.dart';
import '../../enums.dart';
import '../../models/Product.dart';
import 'components/body.dart';

class ProductDisplayScreen extends StatelessWidget {
  static String routeName = "/product_display";
  
  final List<Product> productList;

  ProductDisplayScreen({required this.productList});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "Products",
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
