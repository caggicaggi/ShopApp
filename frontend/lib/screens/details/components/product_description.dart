import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:shop_app/components/show_dialog.dart';
import 'package:shop_app/main.dart';
import 'package:shop_app/models/Product.dart';
import '../../../constant.dart';
import '../../../models/CartList.dart';
import '../../../models/User.dart';
import '../../../models/Wishlist.dart';
import '../../../size_config.dart';
import '../../sign_in/sing_in_screen.dart';

class ProductDescription extends StatefulWidget {
  const ProductDescription({
    Key? key,
    required this.product,
    this.pressOnSeeMore,
  }) : super(key: key);

  final Product product;
  final GestureTapCallback? pressOnSeeMore;

  @override
  _ProductDescriptionState createState() => _ProductDescriptionState();
}

class _ProductDescriptionState extends State<ProductDescription> {
  bool isFavorite = false;

  @override
  void initState() {
    super.initState();
    isFavorite = wishlist.isIdInWishList(widget.product.idProduct);
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Align(
          alignment: Alignment.centerRight,
          child: GestureDetector(
            onTap: () {
              setState(() {
                if (isFavorite) {
                  wishlist
                      .removeProduct(widget.product.idProduct)
                      .then((int statusCode) {
                    switch (statusCode) {
                      case 403:
                        showSessionExpiredDialog(context);
                        break;
                      default:
                        // Perform other actions for different status codes
                        debugPrint('Status code: $statusCode');
                    }
                  });
                  isFavorite = false;
                } else {
                  wishlist
                      .addProduct(widget.product.idProduct)
                      .then((int statusCode) {
                    switch (statusCode) {
                      case 403:
                        showSessionExpiredDialog(context);
                        break;
                      default:
                        // Perform other actions for different status codes
                        debugPrint('Status code: $statusCode');
                    }
                  });
                  isFavorite = true;
                }
              });
            },
            child: Container(
              padding: EdgeInsets.all(getProportionateScreenWidth(15)),
              width: getProportionateScreenWidth(64),
              decoration: BoxDecoration(
                color: isFavorite ? Color(0xFFFFE6E6) : Color(0xFFF5F6F9),
                borderRadius: BorderRadius.only(
                  topLeft: Radius.circular(20),
                  bottomLeft: Radius.circular(20),
                ),
              ),
              child: SvgPicture.asset(
                "assets/icons/Heart Icon_2.svg",
                color: isFavorite ? Color(0xFFFF4848) : Color(0xFFDBDEE4),
                height: getProportionateScreenWidth(16),
              ),
            ),
          ),
        ),
        Padding(
          padding:
              EdgeInsets.symmetric(horizontal: getProportionateScreenWidth(20)),
          child: Text(
            widget.product.title,
            style: Theme.of(context).textTheme.headline6,
          ),
        ),
        Padding(
          padding: EdgeInsets.only(
            left: getProportionateScreenWidth(20),
            right: getProportionateScreenWidth(64),
          ),
          child: Text(
            widget.product.price.toString() + '€',
            style: TextStyle(fontSize: 20),
          ),
        ),
        Padding(
          padding: EdgeInsets.only(
            left: getProportionateScreenWidth(20),
            right: getProportionateScreenWidth(64),
          ),
          child: Text(
            widget.product.description,
            maxLines: 2,
          ),
        ),
      ],
    );
  }
}
