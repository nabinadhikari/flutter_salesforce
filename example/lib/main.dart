import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_salesforce/flutter_salesforce.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Column(
          children: [
            TextButton(
              onPressed: () async {
                var s = FlutterSalesforce(
                  credentials: Credentials(
                    buttonId: '<button id>',
                    deploymentId: '<deployment Id>',
                    liveAgentPod: '<live Agent Pod>',
                    orgId: '<org Id>',
                  ),
                );
                var req = ChatRequest(
                  email: 'mr.nabinadhikari@gmail.com',
                  firstName: 'Nabin',
                  lastName: 'Adhikari',
                );
                s.initiateChat(req);
              },
              child: const Text('Salesforce chat'),
            ),
          ],
        ),
      ),
    );
  }
}
