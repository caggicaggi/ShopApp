// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';
import 'package:shop_app/components/default_button.dart';
import 'package:shop_app/main.dart';
import '../../../components/show_dialog.dart';
import '../../../models/Product.dart';
import '../../../services/checkout_cart.dart';
import '../../../size_config.dart';

class CheckoutCard extends StatelessWidget {
  const CheckoutCard({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.symmetric(
        vertical: getProportionateScreenWidth(15),
        horizontal: getProportionateScreenWidth(30),
      ),
      // height: 174,
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.only(
          topLeft: Radius.circular(30),
          topRight: Radius.circular(30),
        ),
        boxShadow: [
          BoxShadow(
            offset: Offset(0, -15),
            blurRadius: 20,
            color: Color(0xFFDADADA).withOpacity(0.15),
          )
        ],
      ),
      child: SafeArea(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            SizedBox(height: getProportionateScreenHeight(20)),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                SizedBox(
                  width: getProportionateScreenWidth(190),
                  child: DefaultButton(
                    text: "Check Out",
                    press: () {
                      if (cart.productQuantities.isNotEmpty) {
                      ProductCheckResult result = checkAllProductsAvailable(
                          cart.productQuantities, listOfProduct);
                      if (result.allAvailable) {
                        // Empty the cart when all products are available
                        cart.productQuantities.clear();
                        cart.addedQuantities.clear();
                        cart.removedQuantities.clear();
                        updateDbCheckout().then((int statusCode) {
                          switch (statusCode) {
                            case 200:
                              showCheckoutDialog(context);
                              break;
                            case 403:
                              showSessionExpiredDialog(context);
                              break;
                            default:
                              // Perform other actions for different status codes
                              debugPrint('Status code: $statusCode');
                          }
                        });
                      } else {
                        showNoAvailableDialog(context, result.nonAvailableIds);
                      }}else{
                        showNoProductCartDialog(context);
                      }
                    },
                  ),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}

class ProductCheckResult {
  bool allAvailable;
  List<int> nonAvailableIds;

  ProductCheckResult(this.allAvailable, this.nonAvailableIds);
}

ProductCheckResult checkAllProductsAvailable(
    Map<int, int> productQuantities, List<Product> listOfProduct) {
  List<int> nonAvailableIds = [];

  for (var entry in productQuantities.entries) {
    int productId = entry.key;
    int quantity = entry.value;

    Product? product =
        listOfProduct.firstWhere((p) => p.idProduct == productId);

    if (product == null || !product.isAvailable) {
      nonAvailableIds.add(productId);
    }
  }

  bool allAvailable = nonAvailableIds.isEmpty;

  return ProductCheckResult(allAvailable, nonAvailableIds);
}
