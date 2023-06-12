import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import '../constant.dart';
import '../main.dart';

Future<int> updateDbRemoveWishList(List<int> idProduct) async {
  String completeUrl = '$url/wishList/remove';
  debugPrint(idProduct.toString());

  List<Map<String, dynamic>> requestBody = idProduct.map((idProduct) {
    return {
      "idProduct": idProduct,
      "idUtente": currentUser.id,
    };
  }).toList();

  final headers = {
    'Authorization': 'Bearer $tokenJWT',
    'Content-Type': 'application/json',
  };

  try {
    http.Response response = await http.delete(
      Uri.parse(completeUrl),
      headers: headers,
      body: jsonEncode(requestBody),
    );

    if (response.statusCode == 200) {
      debugPrint('Request success: ${response.statusCode}');
      return response.statusCode;
    } else {
      debugPrint('Request failed with status: ${response.statusCode}');
      return response.statusCode;
    }
  } catch (e) {
    debugPrint('Error: $e');
    return -1; // Return a custom error code or handle the error accordingly
  }
}
