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
}

class _WishListDisplayScreenState extends State<WishListDisplayScreen> {
  late Future<List<Product>> productListFuture;

  @override
  void initState() {
    super.initState();
    productListFuture = fetchProductList();
  }

  Future<List<Product>> fetchProductList() async {
    List<Product> wishlistProducts =
        getInWishlistProducts(listOfProduct, wishlist.productIds);
    return wishlistProducts;
  }

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
      body: FutureBuilder<List<Product>>(
        future: productListFuture,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            return Center(child: Text('Error: ${snapshot.error}'));
          } else {
            final productList = snapshot.data ?? [];
            return Body(productList: productList);
          }
        },
      ),
      bottomNavigationBar: CustomBottomNavBar(selectedMenu: MenuState.heart),
    );
  }
}
