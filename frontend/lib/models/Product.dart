import 'package:flutter/material.dart';

class Product {
  int idProduct;
  String title;
  String description;
  List<String> images;
  double rating;
  double price;
  bool isAvailable;
  bool isPopular;
  String category;

  Product({
    required this.idProduct,
    required this.title,
    required this.description,
    required this.images,
    required this.rating,
    required this.price,
    required this.isAvailable,
    required this.isPopular,
    required this.category,
  });

  factory Product.fromJson(Map<String, dynamic> json) {
    return Product(
      idProduct: json['idProduct'],
      title: json['title'],
      description: json['description'],
      images: [
        json['images1'],
        json['images2'],
        json['images3'],
      ],
      rating: json['rating'],
      price: json['price'].toDouble(),
      isPopular: json['isPopular'],
      isAvailable: json['isAvailable'],
      category: json['category'],
    );
  }

}

// Our demo Products

List<Product> demoProducts = [
  Product(
    idProduct: 1,
    images: [
      "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/HPNG2?wid=1144&hei=1144&fmt=jpeg&qlt=90&.v=1665002952548",
    ],
    title: "Wireless Controller for PS4™",
    price: 64.99,
    description: description,
    rating: 4.8,
    isAvailable: true,
    isPopular: true,
    category: "Electronics",
  ),
  Product(
    idProduct: 2,
    images: [
      "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/b47a303d-f770-430f-9b18-d53c5f059638/nikecourt-advantage-mens-tennis-pants-J1NztZ.png",
    ],
    title: "Nike Sport White - Man Pant",
    price: 50.5,
    description: description,
    rating: 4.1,
    isPopular: true,
    category: "Electronics",
    isAvailable: true,
  ),
  Product(
    idProduct: 3,
    images: ["https://cf.shopee.com.my/file/0d95cbfc3e88beeb2296dfdd0c626b1e"],
    title: "Gloves XC Omega - Polygon",
    price: 36.55,
    description: description,
    rating: 4.1,
    isAvailable: true,
    isPopular: true,
    category: "fashion",
  ),
  Product(
    idProduct: 4,
    images: [
      "https://www.elettrovillage.it/26026-large_default/logitech-g-pro-x-71-gaming-headse-connessione-35mm-surround-driver-50mm-colore-nero-rif-981-000818-promo.jpg",
    ],
    title: "Logitech Head",
    price: 20.20,
    description: description,
    rating: 4.1,
    isAvailable: true,
    category: "fashion",
    isPopular: true,
  ),
];

const String description =
    "Wireless Controller for PS4™ gives you what you want in your gaming from over precision control your games to sharing …";
