import 'package:flutter/material.dart';
import '../../constant.dart';
import '../../main.dart';
import 'components/body.dart';
import 'components/cart_card.dart';
import 'components/check_out_card.dart';

class CartScreen extends StatelessWidget {
  static String routeName = "/cart";
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: buildAppBar(context),
      body: Body(),
      bottomNavigationBar: CheckoutCard(),
    );
  }

  AppBar buildAppBar(BuildContext context) {
    return AppBar(
      title: Column(
        children: [
          Text(
            "Your Cart",
            style: TextStyle(color: kPrimaryColor),
          ),
        ],
      ),
    );
  }
  
}