// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';
import 'package:shop_app/main.dart';
import '../../../constant.dart';
import '../../../models/Product.dart';

class CartCard extends StatelessWidget {
  const CartCard({
    Key? key,
    required this.productId,
    required this.quantity,
  }) : super(key: key);

  final int productId;
  final int quantity;

  @override
  Widget build(BuildContext context) {
    // Recupera il prodotto dal suo ID
    Product? prod = getProductById(productId);

    if (prod != null) {
      return Row(
        children: [
          SizedBox(
            width: 88,
            child: AspectRatio(
              aspectRatio: 0.88,
              child: Container(
                decoration: BoxDecoration(
                  color: Color(0xFFF5F6F9),
                  borderRadius: BorderRadius.circular(15),
                ),
                child: Image.network(
                  prod.images[0],
                  fit: BoxFit.cover,
                ),
              ),
            ),
          ),
          SizedBox(width: 20),
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                prod.title,
                style: TextStyle(color: Colors.black, fontSize: 16),
                maxLines: 2,
              ),
              SizedBox(height: 10),
              Text.rich(
                TextSpan(
                  text: "\$${prod.price}",
                  style: TextStyle(
                      fontWeight: FontWeight.w600, color: kPrimaryColor),
                  children: [
                    TextSpan(
                        text: " x$quantity",
                        style: Theme.of(context).textTheme.bodyText1),
                  ],
                ),
              )
            ],
          )
        ],
      );
    } else {
      return Scaffold(
        backgroundColor: Colors.red,
      );
    }
  }
}

Product? getProductById(int productId) {
  for (Product product in listOfProduct) {
    if (product.idProduct == productId) {
      Product pro = product;
      return pro;
    }
  }

  return null; // Se il prodotto con l'ID specificato non viene trovato
}
