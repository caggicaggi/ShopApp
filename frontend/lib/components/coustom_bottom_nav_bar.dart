import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:shop_app/constant.dart';
import 'package:shop_app/screens/home/home_screen.dart';
import 'package:shop_app/screens/product_display/product_display.dart';
import '../enums.dart';
import '../main.dart';
import '../models/Product.dart';
import '../screens/profile/profile_screen.dart';
import '../screens/wishlist_display/wishlist_display.dart';

class CustomBottomNavBar extends StatelessWidget {
  const CustomBottomNavBar({
    Key? key,
    required this.selectedMenu,
  }) : super(key: key);

  final MenuState selectedMenu;

  @override
  Widget build(BuildContext context) {
    final Color inActiveIconColor = Color(0xFFB6B6B6);
    return Container(
      padding: EdgeInsets.symmetric(vertical: 14),
      decoration: BoxDecoration(
        color: Colors.white,
        boxShadow: [
          BoxShadow(
            offset: Offset(0, -15),
            blurRadius: 20,
            color: Color(0xFFDADADA).withOpacity(0.15),
          ),
        ],
        borderRadius: BorderRadius.only(
          topLeft: Radius.circular(40),
          topRight: Radius.circular(40),
        ),
      ),
      child: SafeArea(
          top: false,
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
            children: [
              IconButton(
                icon: SvgPicture.asset(
                  "assets/icons/Shop Icon.svg",
                  color: MenuState.home == selectedMenu
                      ? kPrimaryColor
                      : inActiveIconColor,
                ),
                onPressed: () =>
                    Navigator.pushNamed(context, HomeScreen.routeName),
              ),
              IconButton(
                icon: SvgPicture.asset("assets/icons/Heart Icon.svg",
                color: MenuState.heart == selectedMenu
                      ? kPrimaryColor
                      : inActiveIconColor,),
                onPressed: () {
                  List<Product> wishlistProducts =
                      getInWishlistProducts(listOfProduct, wishlist.productIds);
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) =>
                          WishListDisplayScreen(productList: wishlistProducts),
                    ),
                  );
                },
              ),
              IconButton(
                icon: SvgPicture.asset("assets/icons/Chat bubble Icon.svg"),
                color: MenuState.message == selectedMenu
                    ? kPrimaryColor
                    : inActiveIconColor,
                onPressed: () => Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) =>
                        ProductDisplayScreen(productList: listOfProduct),
                  ),
                ),
              ),
              IconButton(
                icon: SvgPicture.asset(
                  "assets/icons/User Icon.svg",
                  color: MenuState.profile == selectedMenu
                      ? kPrimaryColor
                      : inActiveIconColor,
                ),
                onPressed: () =>
                    Navigator.pushNamed(context, ProfileScreen.routeName),
              ),
            ],
          )),
    );
  }
}

List<Product> getInWishlistProducts(
    List<Product> products, List<int> wishlist) {
  return products
      .where((product) => wishlist.contains(product.idProduct))
      .toList();
}
