package io.github.zileyuan.umeng_analytics;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.analytics.MobclickAgent;
import android.app.Activity;
import android.content.Context;

/** UmengAnalyticsPlugin */
public class UmengAnalyticsPlugin implements MethodCallHandler {
  private Activity activity;
  private Context context;

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "umeng_analytics");
    channel.setMethodCallHandler(new UmengAnalyticsPlugin(registrar.activity(), registrar.context()));
  }

  private UmengAnalyticsPlugin(Activity activity, Context context) {
    this.activity = activity;
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
    UMConfigure.init(this.activity.getApplicationContext(), appKey, channel, deviceType, null);

    if (call.hasArgument("logEnable"))
      UMConfigure.setLogEnabled((Boolean) call.argument("logEnable"));

    MobclickAgent.setSessionContinueMillis(20 * 1000);
    MobclickAgent.setScenarioType(this.context, MobclickAgent.EScenarioType.E_UM_NORMAL);

    return true;
  }
}
