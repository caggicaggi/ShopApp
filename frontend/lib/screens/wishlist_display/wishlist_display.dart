import 'package:flutter/material.dart';
import '../../components/coustom_bottom_nav_bar.dart';
import '../../constant.dart';
import '../../enums.dart';
import '../../main.dart';
import '../../models/Product.dart';
import 'components/body.dart';

class WishListDisplayScreen extends StatefulWidget {
  static String routeName = "/product_display";

  @override
  _WishListDisplayScreenState createState() => _WishListDisplayScreenState();

  static void refreshWishlist() {
    _WishListDisplayScreenState? state =
        _WishListDisplayScreenState.currentState;
    state?.updateProductList();
  }
}

class _WishListDisplayScreenState extends State<WishListDisplayScreen> {
  List<Product> productList = [];
  static _WishListDisplayScreenState? currentState;

  @override
  void initState() {
    super.initState();
    currentState = this;
    updateProductList();
  }

  void updateProductList() {
    setState(() {
      productList = getInWishlistProducts(listOfProduct, wishlist.productIds);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
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
      bottomNavigationBar: const CustomBottomNavBar(selectedMenu: MenuState.heart),
    );
  }
}
