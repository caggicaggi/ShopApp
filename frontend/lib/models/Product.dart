import 'package:flutter/material.dart';

class Product {
  final int id;
  final String title, description, category;
  final List<String> images;
  final List<Color> colors;
  final double rating, price;
  final bool isFavourite, isPopular;
  final String imagePath;

  Product({
    required this.id,
    required this.images,
    required this.colors,
    this.rating = 0.0,
    this.isFavourite = false,
    this.isPopular = false,
    required this.title,
    required this.price,
    required this.description,
    required this.category,
    required this.imagePath,
  });
}

// Our demo Products

List<Product> demoProducts = [
  Product(
      id: 1,
      images: [
        "assets/images/ps4_console_white_1.png",
        "assets/images/ps4_console_white_2.png",
        "assets/images/ps4_console_white_3.png",
        "assets/images/ps4_console_white_4.png",
      ],
      colors: [
        Color(0xFFF6625E),
        Color(0xFF836DB8),
        Color(0xFFDECB9C),
        Colors.white,
      ],
      title: "Wireless Controller for PS4™",
      price: 64.99,
      description: description,
      rating: 4.8,
      isFavourite: true,
      isPopular: true,
      category: "Electronics",
      imagePath:
          "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/HPNG2?wid=1144&hei=1144&fmt=jpeg&qlt=90&.v=1665002952548"),
  Product(
      id: 2,
      images: [
        "assets/images/Image Popular Product 2.png",
      ],
      colors: [
        Color(0xFFF6625E),
        Color(0xFF836DB8),
        Color(0xFFDECB9C),
        Colors.white,
      ],
      title: "Nike Sport White - Man Pant",
      price: 50.5,
      description: description,
      rating: 4.1,
      isPopular: true,
      category: "Electronics",
      imagePath:
          "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/b47a303d-f770-430f-9b18-d53c5f059638/nikecourt-advantage-mens-tennis-pants-J1NztZ.png"),
  Product(
      id: 3,
      images: [
        "assets/images/glap.png",
      ],
      colors: [
        Color(0xFFF6625E),
        Color(0xFF836DB8),
        Color(0xFFDECB9C),
        Colors.white,
      ],
      title: "Gloves XC Omega - Polygon",
      price: 36.55,
      description: description,
      rating: 4.1,
      isFavourite: true,
      isPopular: true,
      category: "fashion",
      imagePath:
          "https://cf.shopee.com.my/file/0d95cbfc3e88beeb2296dfdd0c626b1e"),
  Product(
      id: 4,
      images: [
        "assets/images/wireless headset.png",
      ],
      colors: [
        Color(0xFFF6625E),
        Color(0xFF836DB8),
        Color(0xFFDECB9C),
        Colors.white,
      ],
      title: "Logitech Head",
      price: 20.20,
      description: description,
      rating: 4.1,
      isFavourite: true,
      category: "fashion",
      imagePath:
          "https://www.elettrovillage.it/26026-large_default/logitech-g-pro-x-71-gaming-headse-connessione-35mm-surround-driver-50mm-colore-nero-rif-981-000818-promo.jpg"),
];

const String description =
    "Wireless Controller for PS4™ gives you what you want in your gaming from over precision control your games to sharing …";
