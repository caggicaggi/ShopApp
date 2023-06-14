// ignore_for_file: library_private_types_in_public_api, prefer_const_constructors, sort_child_properties_last, deprecated_member_use

import 'package:flutter/material.dart';
import 'package:shop_app/components/rounded_icon_btn.dart';
import 'package:shop_app/models/Product.dart';
import '../../../constant.dart';
import '../../../size_config.dart';

class ColorDots extends StatefulWidget {
  final Product product;

  const ColorDots({
    Key? key,
    required this.product,
  }) : super(key: key);

  @override
  _ColorDotsState createState() => _ColorDotsState();
}

class _ColorDotsState extends State<ColorDots> {
  int quantity = 0;
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding:
          EdgeInsets.symmetric(horizontal: getProportionateScreenWidth(20)),
      child: Row(
        children: [
          Spacer(),
          RoundedIconBtn(
            icon: Icons.remove,
            press: () {
              setState(() {
                if (quantity > 0) {
                  quantity--;
                }
              });
            },
          ),
          SizedBox(width: getProportionateScreenWidth(20)),
          SizedBox(
            width: getProportionateScreenWidth(40),
            height: getProportionateScreenHeight(50),
            child: ElevatedButton(
              onPressed: () {},
              child: Text(
                '$quantity',
                style: TextStyle(
                  fontSize: 20,
                  color: kPrimaryColor,
                  fontWeight: FontWeight.bold,
                ),
              ),
              style: ElevatedButton.styleFrom(
                shape: CircleBorder(),
                primary: Colors.white,
                padding: EdgeInsets.all(getProportionateScreenWidth(1)),
              ),
            ),
          ),
          SizedBox(width: getProportionateScreenWidth(20)),
          RoundedIconBtn(
            icon: Icons.add,
            showShadow: true,
            press: () {
              setState(() {
                quantity++;
              });
            },
          ),
        ],
      ),
    );
  }
}
