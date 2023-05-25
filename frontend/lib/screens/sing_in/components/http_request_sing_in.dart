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
      String result = responseData['result'] as String? ?? '';
      int idUtente = int.parse(responseData['idUtente'] as String? ?? '0');

      // Extract the list of products from the response data
      List<Map<String, dynamic>> productList =
          List<Map<String, dynamic>>.from(responseData['listOfProduct']);
      List<Product> listProd = productList.map((item) {
        String image1 = item['images1'] != null ? item['images1'] : '';
        String image2 = item['images2'] != null ? item['images2'] : '';
        String image3 = item['images3'] != null ? item['images3'] : '';
        bool isPopular;
        if (item['isPopular'] == "1") {
          isPopular = true;
        } else {
          isPopular = false;
        }

        bool isAvailable;
        if (item['isAvailable'] == "1") {
          isAvailable = true;
        } else {
          isAvailable = false;
        }

        return Product(
          idProduct: item['idProduct'],
          title: item['title'],
          description: item['descriprtion'], // Corrected key name
          images: [image1, image2, image3],
          rating: (item['rating'] as num).toDouble(),
          price: (item['price'] as num).toDouble(),
          isPopular: isPopular,
          isAvailable: isAvailable,
          category: item['category'],
        );
      }).toList();

      // Extract the list of product IDs from the wishlistDTO
      if (responseData.containsKey('wishListDTO')) {
        dynamic wishListData = responseData['wishListDTO'];
        if (wishListData != null &&
            wishListData is List &&
            wishListData.isNotEmpty) {
          List<int> wishlistDTO = List<Map<String, dynamic>>.from(wishListData)
              .map<int>((item) => item['idProduct'] ?? 0)
              .toList();
          wishlist.initializeWishlist(wishlistDTO);
        } else {
          wishlist = Wishlist(); // Initialize as an empty wishlist
        }
      }

      // Extract the cart items from the response data and create a map of product IDs to quantities
      Map<int, int> cartDTO = {};
      if (responseData.containsKey('cartDTO')) {
        dynamic cartData = responseData['cartDTO'];
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
