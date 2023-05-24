import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import '../../../constant.dart';
import '../../../main.dart';
import '../../../models/Product.dart';

Future<int> fetchDataFromSignIn(String email, String password) async {
  String completeUrl = '$url/login'; // API endpoint for sign-in
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

// Extract the values from the response data
      String result = responseData[
          'result']; // Get the value associated with the 'result' key
      int idUtente = int.parse(responseData[
          'idUtente']); // Parse the value associated with the 'idUtente' key as an integer

// Extract the list of products from the response data
      List<Map<String, dynamic>> productList =
          List<Map<String, dynamic>>.from(responseData['listOfProduct']);
      List<Product> listProd =
          productList.map((item) => Product.fromJson(item)).toList();

// Extract the list of product IDs from the wishlistDTO
      List<int> wishlistDTO =
          List<Map<String, dynamic>>.from(responseData['wishListDTO'])
              .map<int>((item) => item['idProduct'])
              .toList();

// Extract the cart items from the response data and create a map of product IDs to quantities
      Map<int, int> cartDTO = {};
      for (var item in responseData['cartDTO']) {
        int idProduct = item['idProduct'];
        int quantity = item['quantity'];
        cartDTO[idProduct] = quantity;
      }

      /* Utilize the retrieved data as necessary */
      demoCartList.initializeFromMap(cartDTO);
      wishlist.initializeWishlist(wishlistDTO);
      listOfProduct.addAll(listProd);
      currentUser.setId(idUtente);

      debugPrint(demoCartList.toString());
      debugPrint(wishlist.toString());
      debugPrint(listOfProduct.toString());
      debugPrint(currentUser.id.toString());

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
