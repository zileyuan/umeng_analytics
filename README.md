# umeng_analytics

Flutter plugin for [umeng:analytics](http://mobile.umeng.com/analytics)

## Usage

### Init

```dart
import 'package:umeng_analytics/umeng_analytics.dart';

UMengAnalytics.init('AppKey', channel: 'default', policy: Policy.BATCH,
    encrypt: true, reportCrash: false);
```
