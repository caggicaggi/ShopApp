// ignore_for_file: use_key_in_widget_constructors, prefer_const_constructors_in_immutables, library_private_types_in_public_api

import 'package:flutter/material.dart';
import 'package:shop_app/screens/details/details_screen.dart';
import 'package:shop_app/components/product_list_item.dart';

import '../../../models/Product.dart';

class Body extends StatefulWidget {
  final List<Product> productList;

  Body({required this.productList});

  @override
  _BodyState createState() => _BodyState();
}

class _BodyState extends State<Body> {
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
            for (final product in widget.productList)
              InkWell(
                onTap: () => Navigator.pushNamed(
                  context,
                  DetailsScreen.routeName,
                  arguments: ProductDetailsArguments(product: product),
                ),
                child: ProductListItem(
                  imageUrl: product.images[0],
                  name: product.title,
                  information:
                      '${product.price} € | ${product.category} | ${product.rating}',
                ),
              ),
          ],
        ),
      ),
    );
  }
}
