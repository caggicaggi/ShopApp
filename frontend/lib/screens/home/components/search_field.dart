// ignore_for_file: library_private_types_in_public_api

import 'package:flutter/material.dart';
import '../../../constant.dart';
import '../../../main.dart';
import '../../../size_config.dart';
import '../../../models/Product.dart';
import '../../product_display/product_display.dart';

class SearchField extends StatefulWidget {
  const SearchField({
    Key? key,
  }) : super(key: key);

  @override
  _SearchFieldState createState() => _SearchFieldState();
}

class _SearchFieldState extends State<SearchField> {
  List<Product> searchResults = [];

  void searchProducts(String query) {
    // Clear previous search results
    searchResults.clear();

    // Perform the search based on the entered query
    for (Product product in listOfProduct) {
      if (product.title.toLowerCase().contains(query.toLowerCase())) {
        searchResults.add(product);
      }
    }

    if (searchResults.isEmpty) {
      showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            title: const Text('No Results Found'),
            content: const Text(
                'No products matching your search query were found.'),
            actions: <Widget>[
              TextButton(
                child: const Text('OK'),
                onPressed: () {
                  Navigator.of(context).pop();
                },
              ),
            ],
          );
        },
      );
    } else {
      Navigator.push(
        context,
        MaterialPageRoute(
          builder: (context) =>
              ProductDisplayScreen(productList: searchResults),
        ),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      width: SizeConfig.screenWidth * 0.7,
      decoration: BoxDecoration(
        color: kSecondaryColor.withOpacity(0.1),
        borderRadius: BorderRadius.circular(15),
      ),
      child: TextField(
        onSubmitted: searchProducts,
        decoration: InputDecoration(
          contentPadding: EdgeInsets.symmetric(
            horizontal: getProportionateScreenWidth(20),
            vertical: getProportionateScreenWidth(9),
          ),
          border: InputBorder.none,
          focusedBorder: InputBorder.none,
          enabledBorder: InputBorder.none,
          hintText: "Search product",
          prefixIcon: const Icon(Icons.search),
        ),
      ),
    );
  }
}
