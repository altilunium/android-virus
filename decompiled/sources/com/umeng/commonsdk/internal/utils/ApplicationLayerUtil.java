package com.umeng.commonsdk.internal.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;

/* renamed from: com.umeng.commonsdk.internal.utils.a */
public class ApplicationLayerUtil {

    /* renamed from: com.umeng.commonsdk.internal.utils.a$a */
    /* compiled from: ApplicationLayerUtil */
    public class C0166a {

        /* renamed from: a */
        public String f1250a;

        /* renamed from: b */
        public String f1251b;
    }

    /* renamed from: com.umeng.commonsdk.internal.utils.a$b */
    /* compiled from: ApplicationLayerUtil */
    public class C0167b {

        /* renamed from: a */
        public int f1252a;

        /* renamed from: b */
        public String f1253b;

        /* renamed from: c */
        public String f1254c;

        /* renamed from: d */
        public int f1255d;

        /* renamed from: e */
        public int f1256e;

        /* renamed from: f */
        public int f1257f;

        /* renamed from: g */
        public int f1258g;

        /* renamed from: h */
        public String f1259h;

        /* renamed from: i */
        public int f1260i;

        /* renamed from: j */
        public int f1261j;

        /* renamed from: k */
        public int f1262k;

        /* renamed from: l */
        public long f1263l;
    }

    /* renamed from: a */
    public static long m1216a(Context context, String str) {
        if (context == null) {
            return 0;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0).firstInstallTime;
        } catch (PackageManager.NameNotFoundException e) {
            UMCrashManager.reportCrash(context, e);
            ULog.m1387e("getAppFirstInstallTime" + e.getMessage());
            return 0;
        }
    }

    /* renamed from: b */
    public static long m1218b(Context context, String str) {
        if (context == null) {
            return 0;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0).lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            UMCrashManager.reportCrash(context, e);
            ULog.m1387e("getAppLastUpdateTime:" + e.getMessage());
            return 0;
        }
    }

    /* renamed from: c */
    public static String m1222c(Context context, String str) {
        try {
            return context.getPackageManager().getInstallerPackageName(str);
        } catch (Exception e) {
            UMCrashManager.reportCrash(context, e);
            ULog.m1387e("getAppInstaller:" + e.getMessage());
            return null;
        }
    }

    /* renamed from: d */
    public static int m1224d(Context context, String str) {
        if (context == null) {
            return 0;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getPackageInfo(str, 0).applicationInfo;
            if (applicationInfo != null) {
                return applicationInfo.uid;
            }
            return 0;
        } catch (PackageManager.NameNotFoundException e) {
            UMCrashManager.reportCrash(context, e);
            ULog.m1387e("getAppUid:" + e.getMessage());
            return 0;
        }
    }

    /* renamed from: a */
    public static boolean m1217a() {
        return Root.m1270a();
    }

    /* renamed from: b */
    public static String m1220b() {
        return new SimpleDateFormat().format(new Date());
    }

    /* renamed from: a */
    public static float m1215a(Context context) {
        if (context == null) {
            return 0.0f;
        }
        Configuration configuration = new Configuration();
        try {
            configuration.updateFrom(context.getResources().getConfiguration());
            return configuration.fontScale;
        } catch (Exception e) {
            ULog.m1387e("getFontSize:" + e.getMessage());
            return 0.0f;
        }
    }

    /* renamed from: b */
    public static WifiInfo m1219b(Context context) {
        WifiManager wifiManager;
        if (context == null || !DeviceConfig.checkPermission(context, "android.permission.ACCESS_WIFI_STATE") || (wifiManager = (WifiManager) context.getSystemService("wifi")) == null) {
            return null;
        }
        return wifiManager.getConnectionInfo();
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a2 A[Catch:{ Exception -> 0x00a6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m1223c(android.content.Context r8) {
        /*
        // Method dump skipped, instructions count: 193
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.utils.ApplicationLayerUtil.m1223c(android.content.Context):void");
    }

    /* renamed from: d */
    public static JSONArray m1226d(Context context) {
        if (context == null) {
            return null;
        }
        return InfoPreference.m1260a(context);
    }

    /* renamed from: e */
    public static void m1229e(Context context) {
        if (context != null) {
            InfoPreference.m1263b(context);
        }
    }

    /* renamed from: f */
    public static int m1230f(Context context) {
        WifiManager wifiManager;
        if (context == null || !DeviceConfig.checkPermission(context, "android.permission.ACCESS_WIFI_STATE") || (wifiManager = (WifiManager) context.getSystemService("wifi")) == null) {
            return -1;
        }
        return wifiManager.getWifiState();
    }

    /* renamed from: g */
    public static int m1231g(Context context) {
        if (context == null) {
            return 0;
        }
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    /* renamed from: h */
    public static int m1232h(Context context) {
        if (context == null) {
            return 0;
        }
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
    }

    /* renamed from: i */
    public static DisplayMetrics m1233i(Context context) {
        if (context == null) {
            return null;
        }
        return context.getResources().getDisplayMetrics();
    }

    /* renamed from: c */
    public static String m1221c() {
        return ProcessUtil.m1265a("df");
    }

    /* renamed from: j */
    public static List m1234j(Context context) {
        InputMethodManager inputMethodManager;
        if (context == null || (inputMethodManager = (InputMethodManager) context.getSystemService("input_method")) == null) {
            return null;
        }
        return inputMethodManager.getInputMethodList();
    }

    /* renamed from: k */
    public static List m1235k(Context context) {
        String[] list;
        if (context == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            File file = new File(Environment.getExternalStorageDirectory() + "/Android/data/");
            if (file.isDirectory() && (list = file.list()) != null && list.length > 0) {
                for (String str : list) {
                    if (str != null && !str.startsWith(".")) {
                        C0166a aVar = new C0166a();
                        aVar.f1250a = str;
                        aVar.f1251b = m1228e(context, str);
                        arrayList.add(aVar);
                    }
                }
            }
        } catch (Exception e) {
            ULog.m1387e("getAppList:" + e.getMessage());
        }
        return arrayList;
    }

    /* renamed from: e */
    private static String m1228e(Context context, String str) {
        if (context == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
            if (applicationInfo != null) {
                return (String) applicationInfo.loadLabel(context.getPackageManager());
            }
            return null;
        } catch (Exception e) {
            ULog.m1387e("getLabel:" + e.getMessage());
            return null;
        }
    }

    /* renamed from: l */
    public static ActivityManager.MemoryInfo m1236l(Context context) {
        ActivityManager activityManager;
        if (context == null || (activityManager = (ActivityManager) context.getSystemService("activity")) == null) {
            return null;
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo;
    }

    /* renamed from: d */
    public static long m1225d() {
        return System.currentTimeMillis() - SystemClock.elapsedRealtime();
    }

    /* renamed from: m */
    public static String m1237m(Context context) {
        return context == null ? null : null;
    }

    /* renamed from: n */
    public static String m1238n(Context context) {
        return null;
    }

    /* renamed from: e */
    public static String m1227e() {
        try {
            Method declaredMethod = Build.class.getDeclaredMethod("getString", String.class);
            declaredMethod.setAccessible(true);
            String obj = declaredMethod.invoke(null, "net.hostname").toString();
            if (obj == null || obj.equalsIgnoreCase("")) {
                return obj;
            }
            return HelperUtils.getUmengMD5(obj);
        } catch (Exception e) {
            ULog.m1387e("getHostName:" + e.getMessage());
            return null;
        }
    }
}
