import 'package:flutter/material.dart';
import 'package:shop_app/main.dart';
import 'package:shop_app/screens/details/details_screen.dart';
import 'package:shop_app/screens/product_display/components/product_list_item.dart';
import '../../../models/Product.dart';

class Body extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Padding(
        padding: const EdgeInsets.symmetric(
          horizontal: 20.0,
          vertical: 100,
        ),
        child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
          const SizedBox(height: 20),
          //we create a Movie list item
          for (final product in listOfProduct)
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
                      '${product.title} | ${product.category} | ${product.rating}'),
            ),
        ]),
      ),
    );
  }
}
