import 'package:flutter/services.dart';

class ChatRequest {
  ChatRequest({
    this.firstName,
    this.lastName,
    this.email,
    this.username,
    this.queueStyle = QueueStyle.None,
  });
  QueueStyle queueStyle;
  String? firstName;
  String? lastName;
  String? email;
  String? username;
  Map<String, dynamic> toJson() => {
        'firstName': firstName,
        'lastName': lastName,
        'email': email,
        'username': username,
        'queueStyle': queueStyle,
      };
}

class Credentials {
  Credentials({
    required this.orgId,
    required this.deploymentId,
    required this.buttonId,
    required this.liveAgentPod,
  });
  String orgId;
  String deploymentId;
  String buttonId;
  String liveAgentPod;
  Map<String, dynamic> toJson() => {
        'orgId': orgId,
        'deploymentId': deploymentId,
        'buttonId': buttonId,
        'liveAgentPod': liveAgentPod,
      };
}

enum QueueStyle { EstimatedWaitTime, None, Position }

class FlutterSalesforce {
  static const MethodChannel _channel = MethodChannel('flutter_salesforce');
  FlutterSalesforce({required this.credentials});
  Credentials credentials;

  void initiateChat(ChatRequest chatRequest) async {
    try {
      await _channel.invokeMethod('initiateChat', {
        'credentials': credentials.toJson(),
        'request': chatRequest.toJson()
      }).then((result) {
        print(result);
      });
    } on PlatformException catch (e) {
      print(e.message);
    }
  }
}
