import 'package:flutter/material.dart';
import 'package:shop_app/screens/details/details_screen.dart';
import 'package:shop_app/screens/product_display/components/product_list_item.dart';

import '../../../models/Product.dart';

class Body extends StatelessWidget {
  final List<Product> productList;

  Body({required this.productList});

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Padding(
        padding: const EdgeInsets.symmetric(
          horizontal: 20.0,
          vertical: 100,
        ),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const SizedBox(height: 20),
            // Create a ProductListItem for each product in the productList
            for (final product in productList)
              InkWell(
                onTap: () => Navigator.pushNamed(
                  context,
                  DetailsScreen.routeName,
                  arguments: ProductDetailsArguments(product: product),
                ),
                child: ProductListItem(
                  imageUrl: product.images[0],
                  name: product.title,
                  information: '${product.price} € | ${product.category} | ${product.rating}',
                ),
              ),
          ],
        ),
      ),
    );
  }
}
