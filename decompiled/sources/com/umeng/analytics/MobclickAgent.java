package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.UMLogAnalytics;
import com.umeng.common.ISysListener;
import com.umeng.commonsdk.debug.UMLog;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONObject;

public class MobclickAgent {

    public enum PageMode {
        AUTO,
        MANUAL,
        LEGACY_AUTO,
        LEGACY_MANUAL
    }

    private static void init(Context context) {
        InternalAgent.m328a().mo213a(context);
    }

    public static void setLocation(double d, double d2) {
        InternalAgent.m328a().mo211a(d, d2);
    }

    public static void setLatencyWindow(long j) {
    }

    public static void enableEncrypt(boolean z) {
    }

    public static void setCatchUncaughtExceptions(boolean z) {
        InternalAgent.m328a().mo232a(z);
    }

    public static void setSecret(Context context, String str) {
        InternalAgent.m328a().mo240c(context, str);
    }

    public static void setScenarioType(Context context, EScenarioType eScenarioType) {
    }

    public static void setSessionContinueMillis(long j) {
        if (j <= 30000) {
            j = 30000;
        }
        InternalAgent.m328a().mo212a(j);
    }

    public static InternalAgent getAgent() {
        return InternalAgent.m328a();
    }

    public static void setCheckDevice(boolean z) {
    }

    public static void setOpenGLContext(GL10 gl10) {
        InternalAgent.m328a().mo231a(gl10);
    }

    public static void setPageCollectionMode(PageMode pageMode) {
        InternalAgent.m328a().mo225a(pageMode);
    }

    public static void onPageStart(String str) {
        if (!TextUtils.isEmpty(str)) {
            InternalAgent.m328a().mo228a(str);
        } else {
            UMLog.m1136aq(UMLogAnalytics.f774C, 0, "\\|");
        }
    }

    public static void onPageEnd(String str) {
        if (!TextUtils.isEmpty(str)) {
            InternalAgent.m328a().mo237b(str);
        } else {
            UMLog.m1136aq(UMLogAnalytics.f775D, 0, "\\|");
        }
    }

    public static void setDebugMode(boolean z) {
    }

    public static void onPause(Context context) {
        InternalAgent.m328a().mo239c(context);
    }

    public static void onResume(Context context) {
        if (context == null) {
            UMLog.m1136aq(UMLogAnalytics.f829n, 0, "\\|");
        } else {
            InternalAgent.m328a().mo234b(context);
        }
    }

    public static void reportError(Context context, String str) {
        try {
            Class<?> cls = Class.forName("com.umeng.umcrash.UMCrash");
            Method declaredMethod = cls.getDeclaredMethod("generateCustomLog", String.class, String.class);
            if (declaredMethod != null) {
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(cls, str, "DEFAULT");
            }
        } catch (Throwable th) {
        }
    }

    public static void reportError(Context context, Throwable th) {
        try {
            Class<?> cls = Class.forName("com.umeng.umcrash.UMCrash");
            Method declaredMethod = cls.getDeclaredMethod("generateCustomLog", Throwable.class, String.class);
            if (declaredMethod != null) {
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(cls, th, "DEFAULT");
            }
        } catch (Throwable th2) {
        }
    }

    public static void onEvent(Context context, String str) {
        InternalAgent.m328a().mo218a(context, str, (String) null, -1, 1);
    }

    public static void onEvent(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            UMLog.m1136aq(UMLogAnalytics.f826k, 0, "\\|");
        } else {
            InternalAgent.m328a().mo218a(context, str, str2, -1, 1);
        }
    }

    public static void onEvent(Context context, String str, Map map) {
        if (map == null) {
            UMLog.m1136aq(UMLogAnalytics.f798a, 0, "\\|");
        } else {
            InternalAgent.m328a().mo221a(context, str, new HashMap(map), -1);
        }
    }

    public static void onEventObject(Context context, String str, Map map) {
        if (map == null) {
            UMLog.m1136aq(UMLogAnalytics.f798a, 0, "\\|");
        } else {
            InternalAgent.m328a().mo221a(context, str, map, -1);
        }
    }

    public static void onEventValue(Context context, String str, Map map, int i) {
        HashMap hashMap;
        if (map == null) {
            hashMap = new HashMap();
        } else {
            hashMap = new HashMap(map);
        }
        hashMap.put("__ct__", Integer.valueOf(i));
        InternalAgent.m328a().mo221a(context, str, hashMap, -1);
    }

    public static void onKillProcess(Context context) {
        InternalAgent.m328a().mo242d(context);
    }

    public static void onProfileSignIn(String str) {
        onProfileSignIn("_adhoc", str);
    }

    public static void onProfileSignIn(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            UMLog.m1136aq(UMLogAnalytics.f835t, 0, "\\|");
        } else if (str2.length() > 64) {
            UMLog.m1136aq(UMLogAnalytics.f836u, 0, "\\|");
        } else if (TextUtils.isEmpty(str)) {
            InternalAgent.m328a().mo229a("_adhoc", str2);
        } else if (str.length() > 32) {
            UMLog.m1136aq(UMLogAnalytics.f837v, 0, "\\|");
        } else {
            InternalAgent.m328a().mo229a(str, str2);
        }
    }

    public static void onProfileSignOff() {
        InternalAgent.m328a().mo255j();
    }

    public enum EScenarioType {
        E_UM_NORMAL(0),
        E_UM_GAME(1);
        

        /* renamed from: a */
        private int f192a;

        private EScenarioType(int i) {
            this.f192a = i;
        }

        public int toValue() {
            return this.f192a;
        }
    }

    public static void setFirstLaunchEvent(Context context, List list) {
        getAgent().mo223a(context, list);
    }

    public static void registerPreProperties(Context context, JSONObject jSONObject) {
        getAgent().mo224a(context, jSONObject);
    }

    public static void unregisterPreProperty(Context context, String str) {
        getAgent().mo249f(context, str);
    }

    public static void clearPreProperties(Context context) {
        getAgent().mo251g(context);
    }

    public static JSONObject getPreProperties(Context context) {
        return getAgent().mo252h(context);
    }

    private static void disableExceptionCatch() {
        InternalAgent.m328a().mo232a(false);
        AnalyticsConfig.CHANGE_CATCH_EXCEPTION_NOTALLOW = true;
    }

    private static void setGameScenarioType(Context context) {
        InternalAgent.m328a().mo215a(context, EScenarioType.E_UM_GAME);
    }

    public static void disable() {
        AnalyticsConfig.enable = false;
    }

    private static void setSysListener(ISysListener iSysListener) {
        InternalAgent.m328a().mo226a(iSysListener);
    }

    private static void onGKVEvent(Context context, String str, HashMap hashMap) {
        InternalAgent.m328a().mo219a(context, str, hashMap);
    }
}
