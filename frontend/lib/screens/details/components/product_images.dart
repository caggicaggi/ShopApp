import 'package:flutter/material.dart';
import 'package:shop_app/models/Product.dart';
import '../../../constant.dart';
import '../../../size_config.dart';

class ProductImages extends StatefulWidget {
  const ProductImages({
    Key? key,
    required this.product,
  }) : super(key: key);

  final Product product;

  @override
  _ProductImagesState createState() => _ProductImagesState();
}

class _ProductImagesState extends State<ProductImages> {
  int selectedImage = 0;
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        SizedBox(
          width: getProportionateScreenWidth(358),
          child: AspectRatio(
            aspectRatio: 1,
            child: Hero(
              tag: widget.product.idProduct.toString(),
              child: Image.network(
                widget.product.images[selectedImage],
                fit: BoxFit.cover,
              ),
            ),
          ),
        ),
        SizedBox(
          height: 15,
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ...List.generate(widget.product.images.length,
                (index) => buildSmallProductPreview(index)),
          ],
        )
      ],
    );
  }

  GestureDetector buildSmallProductPreview(int index) {
    String imageUrl = widget.product.images[index];

    if (imageUrl.isEmpty) {
      // Return an empty container if the image URL is empty
      return GestureDetector(
        onTap: () {
          setState(() {
            selectedImage = index;
          });
        },
        child: Container(),
      );
    }

    return GestureDetector(
      onTap: () {
        setState(() {
          selectedImage = index;
        });
      },
      child: AnimatedContainer(
        duration: defaultDuration,
        margin: EdgeInsets.only(right: 15),
        padding: EdgeInsets.all(8),
        height: getProportionateScreenWidth(48),
        width: getProportionateScreenWidth(48),
        decoration: BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.circular(10),
          border: Border.all(
            color: kPrimaryColor.withOpacity(selectedImage == index ? 1 : 0),
          ),
        ),
        child: Image.network(
          imageUrl,
          fit: BoxFit.cover,
        ),
      ),
    );
  }
}
