import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:shop_app/main.dart';
import '../../../constant.dart';
import '../../../models/Product.dart';

Future<int> sendUserInfo(Map<String, String> userInfo) async {
  String completeUrl = '$url/utente/signup'; // API endpoint for sign-in
  // If the above URL doesn't work, try the following URL
  // String completeUrl = 'http://10.0.2.2:8000/SOMETHING';
  try {
    http.Response response = await http.post(
      Uri.parse(completeUrl),
      body: userInfo,
    );

    debugPrint('Response status code: ${response.statusCode}');

    if (response.statusCode == 200) {
      // Decode the response body
      List<dynamic> responseData = jsonDecode(response.body);
      Map<String, dynamic> userIdData = responseData[0];
      int userId = userIdData['idUtente'];
      currentUser.setId(userId);

      List<Product> listProd = responseData.sublist(1).map((item) {
        List<String> images = [
          item['images1'],
          item['images2'],
          item['images3'],
        ];

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
          description: item['description'],
          images: images,
          rating: (item['rating'] as num).toDouble(),
          price: (item['price'] as num).toDouble(),
          isPopular: isPopular,
          isAvailable: isAvailable,
          category: item['category'],
        );
      }).toList();

      listOfProduct = listProd;

      // Print the data for testing
      debugPrint('User ID: ${currentUser.getId()}');
      debugPrint('Lista generata: ');
      listProd.forEach((product) => debugPrint(product.toString()));
      debugPrint('Lista nel main: ');
      listOfProduct.forEach((product) => debugPrint(product.toString()));

      return response.statusCode;
    } else {
      print('Request failed with status: $response.statusCode');
      return response.statusCode;
    }
  } catch (e) {
    print('Error: $e');
    return -1; // Return a default error status code
  }
}
