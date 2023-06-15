// ignore_for_file: prefer_if_null_operators, unnecessary_null_comparison, unnecessary_type_check

import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import 'package:shop_app/models/CartList.dart';
import 'package:shop_app/models/Wishlist.dart';
import '../../../constant.dart';
import '../../../main.dart';
import '../../../models/Product.dart';

Future<int> requestSignIn(String email, String password) async {
  String completeUrl = '$url/utente/signin';

  Map<String, String> requestBody = {
    'email': email,
    'password': password,
  };

  try {
    http.Response response = await http.post(
      Uri.parse(completeUrl),
      body: requestBody,
    );

    debugPrint(response.statusCode.toString());
    debugPrint(response.body);

    if (response.statusCode == 200) {
      Map<String, dynamic> responseData = jsonDecode(response.body);

      int userId = 0;
      if (responseData.containsKey('idUtente')) {
        List<dynamic> idUtenteList = responseData['idUtente'];
        userId = idUtenteList[0]['value'] as int;
      }

      currentUser.setId(userId);

      List<dynamic> tokenList = responseData['tokenJWT'];
      tokenJWT = tokenList[0]['value'] as String;

      if (responseData.containsKey('ProductDTO')) {
        List<dynamic> productList = responseData['ProductDTO'];
        listOfProduct = productList.map<Product>((item) {
          String image1 = item['images1'] != null ? item['images1'] : '';
          String image2 = item['images2'] != null ? item['images2'] : '';
          String image3 = item['images3'] != null ? item['images3'] : '';
          bool isAvailable = processStatus(item['isAvailable']);
          bool isPopular = processStatus(item['isPopular']);

          return Product(
            idProduct: item['idProduct'],
            title: item['title'],
            description: item['description'],
            images: [image1, image2, image3],
            rating: (item['rating'] as num).toDouble(),
            price: (item['price'] as num).toDouble(),
            isPopular: isPopular,
            isAvailable: isAvailable,
            category: item['category'],
          );
        }).toList();
      }

      if (responseData.containsKey('WishListDTO')) {
        List<dynamic> wishListData = responseData['WishListDTO'];
        if (wishListData != null &&
            wishListData is List &&
            wishListData.isNotEmpty) {
          List<int> wishlistDTO = wishListData
              .map<int>((item) => item['idProduct'] as int)
              .toList();
          wishlist.initializeWishlist(wishlistDTO);
        } else {
          wishlist = Wishlist(); // Initialize as an empty wishlist
        }
      }

      if (responseData.containsKey('CartDTO')) {
        List<dynamic> cartData = responseData['CartDTO'];
        Map<int, int> cartDTO = {};
        if (cartData != null && cartData is List && cartData.isNotEmpty) {
          for (var item in cartData) {
            int idProduct = item['idProduct'] as int;
            int quantity = item['quantity'] as int;
            cartDTO[idProduct] = quantity;
          }
          cart.initializeFromMap(cartDTO);
        } else {
          cart = CartList(); // Initialize as an empty cart list
        }
      }
      debugPrintAllProducts(listOfProduct);
      debugPrintCartContents(cart.productQuantities);
      debugPrintWishlistContents(wishlist.productIds);

      return response.statusCode;
    } else {
      debugPrint('Request failed with status: ${response.statusCode}');
      return response.statusCode;
    }
  } catch (e) {
    debugPrint('Error: $e');
    return -1;
  }
}

bool processStatus(String i) {
  if (i == '1') {
    return true;
  } else {
    return false;
  }
}

void debugPrintAllProducts(List<Product> products) {
  debugPrint('--- List of Products ---');
  for (var product in products) {
    debugPrint('Product: ${product.title} id: ${product.idProduct}');
  }
  debugPrint('-------------------------');
}

void debugPrintCartContents(Map<int, int> cart) {
  debugPrint('--- Cart Contents ---');
  cart.forEach((productId, quantity) {
    debugPrint('Product ID: $productId, Quantity: $quantity');
  });
  debugPrint('---------------------');
}

void debugPrintWishlistContents(List<int> wishlist) {
  debugPrint('--- Wishlist Contents ---');
  for (var productId in wishlist) {
    debugPrint('Product ID: $productId');
  }
  debugPrint('-------------------------');
}
