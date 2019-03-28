package io.github.zileyuan.umeng_analytics;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import android.content.Context;

/** UmengAnalyticsPlugin */
public class UmengAnalyticsPlugin implements MethodCallHandler {
  private Context context;

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "umeng_analytics");
    channel.setMethodCallHandler(new UmengAnalyticsPlugin(registrar.context()));
  }

  private UmengAnalyticsPlugin(Context context) {
    this.context = context;
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
    UMConfigure.init(this.context.getApplicationContext(), appKey, channel, deviceType, null);

    if (call.hasArgument("logEnable"))
      UMConfigure.setLogEnabled((Boolean) call.argument("logEnable"));

    if (call.hasArgument("encrypt"))
      UMConfigure.setEncryptEnabled((Boolean) call.argument("encrypt"));

    if (call.hasArgument("reportCrash"))
      MobclickAgent.setCatchUncaughtExceptions((Boolean) call.argument("reportCrash"));

    MobclickAgent.openActivityDurationTrack(false);

    return true;
  }
}
