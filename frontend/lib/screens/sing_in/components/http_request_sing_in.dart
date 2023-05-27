import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import 'package:shop_app/models/CartList.dart';
import 'package:shop_app/models/Wishlist.dart';
import '../../../constant.dart';
import '../../../main.dart';
import '../../../models/Product.dart';

Future<int> fetchDataFromSignIn(String email, String password) async {
  String completeUrl = '$url/utente/signin'; // API endpoint for sign-in
  // If the above URL doesn't work, try the following URL
  // String completeUrl = 'http://10.0.2.2:8000/SOMETHING';

  Map<String, String> requestBody = {
    'email': email,
    'password': password,
  };

  try {
    http.Response response = await http.post(
      Uri.parse(completeUrl),
      body: requestBody,
    );

    if (response.statusCode == 200) {
      // Decode the response body from JSON to a Map<String, dynamic>
      Map<String, dynamic> responseData = jsonDecode(response.body);

      debugPrint(responseData.toString());

      // Extract the values from the response data
      List<dynamic> tokenList = responseData['tokenJWT'];
      tokenJWT = tokenList[0]['value'] as String;

      // Extract the list of products from the response data
      List<dynamic> productList = responseData['ProductDTO'];
      List<Product> listProd = productList.map<Product>((item) {
        String image1 = item['images1'] != null ? item['images1'] : '';
        String image2 = item['images2'] != null ? item['images2'] : '';
        String image3 = item['images3'] != null ? item['images3'] : '';
        bool isPopular = item['isPopular'] == "true";
        bool isAvailable = item['isAvailable'] == "true";

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

      // Extract the list of product IDs from the wishlistDTO
      if (responseData.containsKey('WishListDTO')) {
        List<dynamic> wishListData = responseData['WishListDTO'];
        if (wishListData != null &&
            wishListData is List &&
            wishListData.isNotEmpty) {
          List<int> wishlistDTO =
              wishListData.map<int>((item) => item['idProduct'] ?? 0).toList();
          wishlist.initializeWishlist(wishlistDTO);
        } else {
          wishlist = Wishlist(); // Initialize as an empty wishlist
        }
      }

      // Extract the cart items from the response data and create a map of product IDs to quantities
      Map<int, int> cartDTO = {};
      if (responseData.containsKey('CartDTO')) {
        List<dynamic> cartData = responseData['CartDTO'];
        if (cartData != null && cartData is List && cartData.isNotEmpty) {
          for (var item in cartData) {
            int idProduct = item['idProduct'] ?? 0;
            int quantity = item['quantity'] ?? 0;
            cartDTO[idProduct] = quantity;
          }
          demoCartList.initializeFromMap(cartDTO);
        } else {
          demoCartList = CartList(); // Initialize as an empty cart list
        }
      }

      /* Utilize the retrieved data as necessary */
      listOfProduct = listProd;

      return response.statusCode;
    } else {
      print('Request failed with status: ${response.statusCode}');
      return response.statusCode;
    }
  } catch (e) {
    print('Error: $e');
    return -1; // Return a custom error code or handle the error accordingly
  }
}