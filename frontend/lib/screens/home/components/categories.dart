import 'package:flutter/material.dart';
import 'package:shop_app/screens/categoties_menu/categories_menu.dart';
import '../../../main.dart';
import '../../../models/Product.dart';
import '../../../size_config.dart';
import '../../categoties_menu/components/body.dart';
import '../../product_display/product_display.dart';
import 'section_title.dart';

class Categories extends StatelessWidget {
  const Categories({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Padding(
          padding:
              EdgeInsets.symmetric(horizontal: getProportionateScreenWidth(20)),
          child: SectionTitle(
            title: "Categories",
            press: () {
              Navigator.pushNamed(context, CategoriesMenuScreen.routeName);
            },
          ),
        ),
        SizedBox(height: getProportionateScreenWidth(20)),
        SingleChildScrollView(
          scrollDirection: Axis.horizontal,
          child: Row(
            children: [
              SpecialOfferCard(
                image: 'assets/images/electronics.jpg',
                category: "Electronics",
                press: () {
                  List<Product> listCategoryProducts =
                      getCategoryProducts(listOfProduct, 'Electronics');
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => ProductDisplayScreen(
                          productList: listCategoryProducts),
                    ),
                  );
                },
              ),
              SpecialOfferCard(
                image: 'assets/images/fashion.jpg',
                category: "Fashion",
                press: () {
                  List<Product> listCategoryProducts =
                      getCategoryProducts(listOfProduct, 'Fashion');
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => ProductDisplayScreen(
                          productList: listCategoryProducts),
                    ),
                  );
                },
              ),
              SpecialOfferCard(
                image: 'assets/images/home.jpg',
                category: "Home",
                press: () {
                  List<Product> listCategoryProducts =
                      getCategoryProducts(listOfProduct, 'Home');
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => ProductDisplayScreen(
                          productList: listCategoryProducts),
                    ),
                  );
                },
              ),
              SizedBox(width: getProportionateScreenWidth(20)),
            ],
          ),
        ),
      ],
    );
  }
}

class SpecialOfferCard extends StatelessWidget {
  const SpecialOfferCard({
    Key? key,
    required this.category,
    required this.image,
    required this.press,
  }) : super(key: key);

  final String category, image;
  final GestureTapCallback press;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.only(left: getProportionateScreenWidth(20)),
      child: GestureDetector(
        onTap: press,
        child: SizedBox(
          width: getProportionateScreenWidth(242),
          height: getProportionateScreenWidth(100),
          child: ClipRRect(
            borderRadius: BorderRadius.circular(20),
            child: Stack(
              children: [
                Image.asset(
                  image,
                  fit: BoxFit.fill,
                  width: double.infinity,
                  height: double.infinity,
                ),
                Container(
                  decoration: BoxDecoration(
                    gradient: LinearGradient(
                      begin: Alignment.topCenter,
                      end: Alignment.bottomCenter,
                      colors: [
                        const Color(0xFF343434).withOpacity(0.4),
                        const Color(0xFF343434).withOpacity(0.15),
                      ],
                    ),
                  ),
                ),
                Padding(
                  padding: EdgeInsets.symmetric(
                    horizontal: getProportionateScreenWidth(15.0),
                    vertical: getProportionateScreenWidth(10),
                  ),
                  child: Text.rich(
                    TextSpan(
                      style: const TextStyle(color: Colors.white),
                      children: [
                        TextSpan(
                          text: "$category\n",
                          style: TextStyle(
                            fontSize: getProportionateScreenWidth(18),
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
