import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:shop_app/main.dart';
import '../../../constant.dart';
import '../../../models/Product.dart';

Future<int> requestSignUp(Map<String, String> userInfo) async {
  String completeUrl = '$url/utente/signup'; // API endpoint for sign-in

  try {
    http.Response response = await http.post(
      Uri.parse(completeUrl),
      body: userInfo,
    );

    debugPrint('Response status code: ${response.statusCode}');
    debugPrint('Response body: ${response.body}');

    if (response.statusCode == 200) {
      // Decode the response body
      dynamic responseBody = json.decode(response.body);

      dynamic tokenData = responseBody['tokenJWT'];
      dynamic idData = responseBody['idUtente'];
      dynamic productData = responseBody['ProductDTO'];

      if (tokenData is List && tokenData.isNotEmpty) {
        tokenJWT = tokenData[0]['value'];
      }

      if (idData is List && idData.isNotEmpty) {
        int userId = idData[0]['value'];
        currentUser.setId(userId);
      }

      if (productData is List) {
        listOfProduct = productData.map((item) {
          int id = item['idProduct'];
          String title = item['title'];
          String description = item['description'];
          List<String> images = [];
          for (int i = 1; i <= 3; i++) {
            String imageKey = 'images$i';
            if (item.containsKey(imageKey) && item[imageKey] != null) {
              String imageUrl = item[imageKey]?.replaceAll('"', '') ?? '';
              images.add(imageUrl);
            } else {
              images.add('');
            }
          }
          double rating = item['rating'].toDouble();
          double price = item['price'].toDouble();
          bool isAvailable = processStatus(item['isAvailable']);
          bool isPopular = processStatus(item['isPopular']);
          String category = item['category'];

          return Product(
            idProduct: id,
            title: title,
            description: description,
            images: images,
            rating: rating,
            price: price,
            isAvailable: isAvailable,
            isPopular: isPopular,
            category: category,
          );
        }).toList();
      }

      for (var product in listOfProduct) {
        debugPrint('Product ID: ${product.idProduct}');
        debugPrint('Title: ${product.title}');
        debugPrint('Description: ${product.description}');
        debugPrint('Images: ${product.images}');
        debugPrint('Rating: ${product.rating}');
        debugPrint('Price: ${product.price}');
        debugPrint('Is Available: ${product.isAvailable}');
        debugPrint('Is Popular: ${product.isPopular}');
        debugPrint('Category: ${product.category}');
        debugPrint('-------------------------');
      }

      return response.statusCode;
    } else {
      debugPrint('Request failed with status: ${response.statusCode}');
      return response.statusCode;
    }
  } catch (e) {
    debugPrint('Error: $e');
    return -1; // Return a default error status code
  }
}

bool processStatus(String i) {
  if (i == '1') {
    return true;
  } else {
    return false;
  }
}
