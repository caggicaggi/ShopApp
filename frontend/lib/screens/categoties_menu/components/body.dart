// ignore_for_file: use_key_in_widget_constructors, must_be_immutable, prefer_const_constructors

import 'package:flutter/material.dart';
import '../../../components/menu_card.dart';
import '../../../main.dart';
import '../../../models/Product.dart';
import '../../product_display/product_display.dart';

class Body extends StatelessWidget {
  List<String> categories = getCategoryList(listOfProduct);

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      padding: EdgeInsets.symmetric(vertical: 20),
      child: Column(
        children: [
          SizedBox(height: 20),
          ListView.builder(
            shrinkWrap: true,
            physics: NeverScrollableScrollPhysics(),
            itemCount: categories
                .length, // Example value, replace with your desired number of MenuCard items
            itemBuilder: (context, index) {
              return MenuCard(
                text: categories[index],
                icon: "assets/icons/Icon.svg",
                press: () {
                  List<Product> listCategoryProducts =
                      getCategoryProducts(listOfProduct, categories[index]);
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => ProductDisplayScreen(
                          productList: listCategoryProducts),
                    ),
                  );
                },
              );
            },
          ),
        ],
      ),
    );
  }
}

List<String> getCategoryList(List<Product> products) {
  Set<String> categorySet = <String>{};

  for (var product in products) {
    if (!categorySet.contains(product.category)) {
      categorySet.add(product.category);
    }
  }

  return categorySet.toList();
}

List<Product> getCategoryProducts(List<Product> products, String category) {
  return products.where((product) => product.category == category).toList();
}
