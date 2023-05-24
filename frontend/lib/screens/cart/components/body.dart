import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import '../../../main.dart';
import '../../../size_config.dart';
import 'cart_card.dart';

class Body extends StatefulWidget {
  @override
  _BodyState createState() => _BodyState();
}

class _BodyState extends State<Body> {
  Map<int, int> localProductQuantities = {};

  @override
  void initState() {
    super.initState();
    localProductQuantities = Map.from(demoCartList.productQuantities);
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.symmetric(horizontal: getProportionateScreenWidth(20)),
      child: ListView.builder(
        itemCount: localProductQuantities.length,
        itemBuilder: (context, index) {
          int productId = localProductQuantities.keys.elementAt(index);
          int quantity = localProductQuantities[productId] ?? 0;

          return Padding(
            padding: EdgeInsets.symmetric(vertical: 10),
            child: Dismissible(
              key: UniqueKey(),
              direction: DismissDirection.endToStart,
              onDismissed: (direction) {
                setState(() {
                  localProductQuantities.remove(productId);
                });

                demoCartList.removeProduct(productId, quantity);
              },
              background: Container(
                padding: EdgeInsets.symmetric(horizontal: 20),
                decoration: BoxDecoration(
                  color: Color(0xFFFFE6E6),
                  borderRadius: BorderRadius.circular(15),
                ),
                child: Row(
                  children: [
                    Spacer(),
                    SvgPicture.asset("assets/icons/Trash.svg"),
                  ],
                ),
              ),
              child: CartCard(
                productId: productId,
                quantity: quantity,
              ),
            ),
          );
        },
      ),
    );
  }
}
