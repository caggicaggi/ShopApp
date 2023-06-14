// ignore_for_file: use_key_in_widget_constructors

import 'package:flutter/material.dart';
import 'components/body.dart';

class ResetPassSuccessScreen extends StatelessWidget {
  static String routeName = "/login_success";
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: const SizedBox(),
        title: const Text("Login Success"),
      ),
      body: Body(),
    );
  }
}
