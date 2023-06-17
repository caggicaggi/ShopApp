import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

import '../constant.dart';

Future<int> checkEmailDb(String email) async {
  final completeUrl = '$url/utente/mailForOtp';
  final headers = {
    'Content-Type': 'application/json',
  };
  final queryParameters = {
    'email': email,
  };

  final requestUri =
      Uri.parse(completeUrl).replace(queryParameters: queryParameters);

  try {
    final response = await http.get(requestUri, headers: headers);

    debugPrint('Request status code: ${response.statusCode}');

    return response.statusCode;
  } catch (e) {
    debugPrint('Error: $e');
    return -1; // Return a custom error code or handle the error accordingly
  }
}
