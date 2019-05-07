import 'package:flutter/material.dart';
import 'package:umeng_analytics/umeng_analytics.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {

  @override
  void initState() {
    super.initState();
    UmengAnalytics.init('5c7cfb8761f56426a50016e6',
        channel: 'default', logEnable: true);
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('UmengAnalytics on\n'),
        ),
      ),
    );
  }
}
