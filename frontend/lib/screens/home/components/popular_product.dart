import 'package:flutter/material.dart';
import 'package:shop_app/main.dart';
import '../../../components/product_card.dart';
import '../../../models/Product.dart';
import '../../../size_config.dart';
import '../../product_display/product_display.dart';
import 'section_title.dart';

class PopularProducts extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    List<Product> popularProducts = listOfProduct
        .where((product) => product.isPopular)
        .toList(); // Create a list of popular products

    int maxProductCount = 3; // Maximum number of random product cards to display

    popularProducts.shuffle(); // Shuffle the list of popular products

    return Column(
      children: [
        Padding(
          padding:
              EdgeInsets.symmetric(horizontal: getProportionateScreenWidth(20)),
          child: SectionTitle(
              title: "Popular products",
              press: () {
                List<Product> filteredProducts = listOfProduct
                    .where((product) => product.isPopular)
                    .toList(); // Create a filtered list of all popular products

                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) =>
                        ProductDisplayScreen(productList: filteredProducts),
                  ),
                );
              }),
        ),
        SizedBox(height: getProportionateScreenWidth(20)),
        SingleChildScrollView(
          scrollDirection: Axis.horizontal,
          child: Row(
            children: [
              ...popularProducts.take(maxProductCount).map((product) {
                return ProductCard(product: product);
              }),
              SizedBox(width: getProportionateScreenWidth(20)),
            ],
          ),
        )
      ],
    );
  }
}
