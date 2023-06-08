import 'package:flutter/material.dart';

import 'components/body.dart';

class NewPasswordScreen extends StatelessWidget {
  static String routeName = "/new_password";
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("New Password"),
      ),
      body: Body(),
    );
  }
}
