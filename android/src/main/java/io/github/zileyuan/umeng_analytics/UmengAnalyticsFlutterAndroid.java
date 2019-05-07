package io.github.zileyuan.umeng_analytics;

import android.content.Context;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.analytics.MobclickAgent;

public class UmengAnalyticsFlutterAndroid {

    public static void androidInit(Context context, String appKey, String channel, boolean logEnable) {
        UMConfigure.setLogEnabled(logEnable);
        UMConfigure.init(context, appKey, channel, UMConfigure.DEVICE_TYPE_PHONE, "");
    }

    public static void androidOnResume(Context context) {
        MobclickAgent.onResume(context);
    }

    public static void androidOnPausee(Context context) {
        MobclickAgent.onPause(context);
    }
}