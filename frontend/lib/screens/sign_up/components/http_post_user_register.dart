import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:shop_app/main.dart';
import '../../../constant.dart';
import '../../../models/Product.dart';

Future<int> sendUserInfo(Map<String, String> userInfo) async {
  String completeUrl = '$url/singin'; // API endpoint for sign-in
  // If the above URL doesn't work, try the following URL
  // String completeUrl = 'http://10.0.2.2:8000/SOMETHING';
  try {
    http.Response response = await http.post(
      Uri.parse(completeUrl),
      body: userInfo,
    );

    int statusCode = response.statusCode;

    if (statusCode == 200) {
      // Decode the response body
      Map<String, dynamic> responseData = jsonDecode(response.body);
      int? userId = responseData['id'];
      currentUser.setId(userId!);

      List<dynamic> productData = responseData['listOfProduct'];
      List<Product> listProd = productData.map((item) {
        List<String> images = [
          item['images1'],
          item['images2'],
          item['images3'],
        ];
        return Product(
          idProduct: item['idProduct'],
          title: item['title'],
          description: item['description'],
          images: images,
          rating: item['rating'],
          price: item['price'],
          isPopular: item['popular'],
          isAvailable: item['isAvailable'],
          category: item['category'],
        );
      }).toList();

      // Print the data for testing
      print('User ID: ${currentUser.getId()}');
      listOfProduct.forEach((product) => print(product.toString()));

      return response.statusCode;
    } else {
      print('Request failed with status: $statusCode');
      return response.statusCode;
    }
  } catch (e) {
    print('Error: $e');
    return -1; // Return a default error status code
  }
}
