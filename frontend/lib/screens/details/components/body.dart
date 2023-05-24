import 'package:flutter/material.dart';
import 'package:shop_app/components/default_button.dart';
import 'package:shop_app/main.dart';
import 'package:shop_app/models/Product.dart';
import 'package:shop_app/size_config.dart';
import '../../../components/rounded_icon_btn.dart';
import '../../../constant.dart';
import 'color_dots.dart';
import 'product_description.dart';
import 'top_rounded_container.dart';
import 'product_images.dart';

class Body extends StatefulWidget {
  final Product product;

  const Body({Key? key, required this.product}) : super(key: key);

  @override
  _BodyState createState() => _BodyState();
}

class _BodyState extends State<Body> {
  int quantity = 0;

  @override
  Widget build(BuildContext context) {
    return ListView(
      children: [
        ProductImages(product: widget.product),
        TopRoundedContainer(
          color: Colors.white,
          child: Column(
            children: [
              ProductDescription(
                product: widget.product,
                pressOnSeeMore: () {},
              ),

              /*ColorDots(
                      product: widget.product,
                    ),*/
              TopRoundedContainer(
                color: Colors.white,
                child: Padding(
                  padding: EdgeInsets.only(
                    left: SizeConfig.screenWidth * 0.15,
                    right: SizeConfig.screenWidth * 0.15,
                    bottom: getProportionateScreenWidth(40),
                    top: getProportionateScreenWidth(15),
                  ),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
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
                      Text(
                        '$quantity',
                        style: TextStyle(
                          fontSize: 20,
                          color: kPrimaryColor,
                          fontWeight: FontWeight.bold,
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
                ),
              ),
              TopRoundedContainer(
                color: Colors.white,
                child: Padding(
                  padding: EdgeInsets.only(
                    left: SizeConfig.screenWidth * 0.15,
                    right: SizeConfig.screenWidth * 0.15,
                    bottom: getProportionateScreenWidth(40),
                    top: getProportionateScreenWidth(15),
                  ),
                  child: DefaultButton(
                    text: "Add To Cart",
                    press: () {
                      demoCartList.addProduct(
                          widget.product.idProduct, quantity);
                    },
                  ),
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }
}
