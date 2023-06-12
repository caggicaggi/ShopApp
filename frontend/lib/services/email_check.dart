import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import '../constant.dart';

Future<int> checkEmailDb(String email) async {
  String completeUrl = '$url/mailFotOtp';

  Map<String, String> requestBody = {
    "email": email,
  };

  try {
    http.Response response = await http.post(
      Uri.parse(completeUrl),
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
