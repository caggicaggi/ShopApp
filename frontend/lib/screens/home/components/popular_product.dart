import 'package:flutter/material.dart';
import 'package:shop_app/main.dart';

import '../../../components/product_card.dart';
import '../../../models/Product.dart';
import '../../../size_config.dart';
import 'section_title.dart';

class PopularProducts extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Padding(
          padding:
              EdgeInsets.symmetric(horizontal: getProportionateScreenWidth(20)),
          child: SectionTitle(title: "Popular products", press: () {}),
        ),
        SizedBox(height: getProportionateScreenWidth(20)),
        SingleChildScrollView(
          scrollDirection: Axis.horizontal,
          child: Row(
            children: [
              /*
              The List.generate() function is called with two arguments:
              a. The first argument is listOfProduct.length, which represents the length of the listOfProduct list.
              b. The second argument is a callback function that takes an index parameter.
              
              Inside the callback function, there is a conditional statement:
              a. It checks if the isPopular property of the listOfProduct[index] element is true.
              b. If the condition is true, it returns a ProductCard widget with the current listOfProduct[index] as the product parameter.
              c. If the condition is false, it returns a SizedBox.shrink() widget.
              
              The List.generate() function generates a new list by executing the callback function for each index from 0 to listOfProduct.length - 1.
              a. If the condition in the callback function is true, it includes a ProductCard widget with the corresponding listOfProduct[index] element in the generated list.
              b. If the condition is false, it includes a SizedBox.shrink() widget in the generated list.
             */
              ...List.generate(
                listOfProduct.length,
                (index) {
                  if (listOfProduct[index].isPopular)
                    return ProductCard(product: listOfProduct[index]);

                  return SizedBox
                      .shrink(); // here by default width and height is 0
                },
              ),
              SizedBox(width: getProportionateScreenWidth(20)),
            ],
          ),
        )
      ],
    );
  }
}
