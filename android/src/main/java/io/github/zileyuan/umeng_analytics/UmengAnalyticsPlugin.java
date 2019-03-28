package io.github.zileyuan.umeng_analytics;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import com.umeng.commonsdk.UMConfigure;
import android.app.Activity;

/** UmengAnalyticsPlugin */
public class UmengAnalyticsPlugin implements MethodCallHandler {
  private Activity activity;

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "umeng_analytics");
    channel.setMethodCallHandler(new UmengAnalyticsPlugin(registrar.activity()));
  }

  private UmengAnalyticsPlugin(Activity activity) {
    this.activity = activity;
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("init")) {
      boolean res = init(call, result);
      result.success(res);
    } else {
      result.notImplemented();
    }
  }

  private boolean init(MethodCall call, Result result) {
    // 获取传入的参数
    String appKey = call.argument("key");
    String channel = call.argument("channel");
    Integer deviceType = call.argument("deviceType");
    UMConfigure.init(this.activity, appKey, channel, deviceType, null);

    if (call.hasArgument("logEnable"))
      UMConfigure.setLogEnabled((Boolean) call.argument("logEnable"));

    return true;
  }
}
