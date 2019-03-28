import 'dart:async';

import 'package:flutter/services.dart';

class Policy {
  static final int REALTIME = 0;
  static final int BATCH = 1;
  static final int SEND_INTERVAL = 6;
  static final int SMART_POLICY = 8;
}

class UmengAnalytics {
  static const DEVICE_TYPE_PHONE = 1;
  static const DEVICE_TYPE_BOX = 2;

  static const MethodChannel _channel =
      const MethodChannel('umeng_analytics');

  static Future<bool> init(
    String key, {
    int deviceType = DEVICE_TYPE_PHONE,
    String channel = 'default',
    bool logEnable,
  }) async {
    Map<String, dynamic> args = {
      'key': key,
      'deviceType': deviceType,
      'channel': channel,
    };

    if (logEnable != null) args['logEnable'] = logEnable;

    await _channel.invokeMethod('init', args);
    return true;
  }
}

