import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:shop_app/main.dart';
import '../../../constant.dart';
import '../../../models/Product.dart';

Future<int> sendUserInfo(Map<String, String> userInfo) async {
  String completeUrl = '$url/utente/signup'; // API endpoint for sign-in

  try {
    http.Response response = await http.post(
      Uri.parse(completeUrl),
      body: userInfo,
    );

    debugPrint('Response status code: ${response.statusCode}');

    if (response.statusCode == 200) {
      // Decode the response body
      dynamic responseBody = json.decode(response.body);

      tokenJWT = responseBody['tokenJWT'][0]['value'];
      int userId = responseBody['idUtente'][0]['value'];

      currentUser.setId(userId);

      List<Product> productList = (responseBody['ProductDTO'] as List)
          .map((item) => Product.fromJson(item))
          .toList();

      listOfProduct = productList;

      // Print the data for testing
      debugPrint('User ID: ${currentUser.getId()}');
      debugPrint('Token: $tokenJWT');
      debugPrint('Product List: ');
      productList.forEach((product) => debugPrint(product.toString()));
      debugPrint('List in the main: ');
      listOfProduct.forEach((product) => debugPrint(product.toString()));

      return response.statusCode;
    } else {
      print('Request failed with status: ${response.statusCode}');
      return response.statusCode;
    }
  } catch (e) {
    print('Error: $e');
    return -1; // Return a default error status code
  }
}
