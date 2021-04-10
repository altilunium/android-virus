package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;

/* renamed from: com.umeng.commonsdk.internal.utils.k */
public class UMProbe {

    /* renamed from: a */
    public static final String f1313a = "UM_PROBE_DATA";

    /* renamed from: b */
    public static final String f1314b = "_dsk_s";

    /* renamed from: c */
    public static final String f1315c = "_thm_z";

    /* renamed from: d */
    public static final String f1316d = "_gdf_r";

    /* renamed from: e */
    private static Object f1317e = new Object();

    /* renamed from: a */
    public static String m1279a(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f1313a, 0);
            if (sharedPreferences == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            synchronized (f1317e) {
                jSONObject.put(f1314b, sharedPreferences.getString(f1314b, ""));
                jSONObject.put(f1315c, sharedPreferences.getString(f1315c, ""));
                jSONObject.put(f1316d, sharedPreferences.getString(f1316d, ""));
            }
            return jSONObject.toString();
        } catch (Exception e) {
            UMCrashManager.reportCrash(context, e);
            return null;
        }
    }

    /* renamed from: b */
    public static void m1282b(final Context context) {
        if (!m1285c(context)) {
            final String[] strArr = {"unknown", "unknown", "unknown"};
            new Thread() {
                /* class com.umeng.commonsdk.internal.utils.UMProbe.C01741 */

                public void run() {
                    super.run();
                    try {
                        strArr[0] = UMProbe.m1284c();
                        strArr[1] = UMProbe.m1278a();
                        strArr[2] = UMProbe.m1281b();
                        ULog.m1393i("diskType = " + strArr[0] + "; ThremalZone = " + strArr[1] + "; GoldFishRc = " + strArr[2]);
                        UMProbe.m1283b(context, strArr);
                    } catch (Throwable th) {
                        UMCrashManager.reportCrash(context, th);
                    }
                }
            }.start();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static void m1283b(Context context, String[] strArr) {
        SharedPreferences sharedPreferences;
        if (context != null && (sharedPreferences = context.getApplicationContext().getSharedPreferences(f1313a, 0)) != null) {
            synchronized (f1317e) {
                sharedPreferences.edit().putString(f1314b, strArr[0]).putString(f1315c, strArr[1]).putString(f1316d, strArr[2]).commit();
            }
        }
    }

    /* renamed from: c */
    public static boolean m1285c(Context context) {
        SharedPreferences sharedPreferences;
        if (context == null || (sharedPreferences = context.getApplicationContext().getSharedPreferences(f1313a, 0)) == null || TextUtils.isEmpty(sharedPreferences.getString(f1314b, ""))) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static int m1277a(String str, String str2) {
        int i;
        if (Build.VERSION.SDK_INT > 28) {
            return -1;
        }
        Process exec = Runtime.getRuntime().exec(str);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                if (readLine.contains(str2)) {
                    i = 1;
                    break;
                }
            } else {
                i = -1;
                break;
            }
        }
        try {
            if (exec.waitFor() != 0) {
                return -1;
            }
            return i;
        } catch (InterruptedException e) {
            return -1;
        }
    }

    /* renamed from: a */
    public static String m1278a() {
        int i;
        try {
            i = m1277a("ls /sys/class/thermal", "thermal_zone");
        } catch (Throwable th) {
            i = -1;
        }
        if (i > 0) {
            return "thermal_zone";
        }
        if (i < 0) {
            return "noper";
        }
        return "unknown";
    }

    /* renamed from: b */
    public static String m1281b() {
        int i;
        try {
            i = m1277a("ls /", "goldfish");
        } catch (Throwable th) {
            i = -1;
        }
        if (i > 0) {
            return "goldfish";
        }
        if (i < 0) {
            return "noper";
        }
        return "unknown";
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0046 A[SYNTHETIC, Splitter:B:21:0x0046] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m1284c() {
        /*
            r0 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ all -> 0x0040 }
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ all -> 0x0040 }
            java.lang.String r3 = "/proc/diskstats"
            r2.<init>(r3)     // Catch:{ all -> 0x0040 }
            r1.<init>(r2)     // Catch:{ all -> 0x0040 }
        L_0x0014:
            java.lang.String r0 = r1.readLine()     // Catch:{ all -> 0x003d }
            java.lang.String r2 = "mtd"
            java.lang.String r3 = "sda"
            java.lang.String r4 = "mmcblk"
            if (r0 == 0) goto L_0x003a
            boolean r5 = r0.contains(r4)
            if (r5 == 0) goto L_0x0029
            r2 = r4
            goto L_0x003c
        L_0x0029:
            boolean r4 = r0.contains(r3)
            if (r4 == 0) goto L_0x0032
            r2 = r3
            goto L_0x003c
        L_0x0032:
            boolean r0 = r0.contains(r2)
            if (r0 == 0) goto L_0x0014
            goto L_0x003c
        L_0x003a:
            java.lang.String r2 = "unknown"
        L_0x003c:
            goto L_0x0044
        L_0x003d:
            r0 = move-exception
            r0 = r1
            goto L_0x0041
        L_0x0040:
            r1 = move-exception
        L_0x0041:
            java.lang.String r2 = "noper"
            r1 = r0
        L_0x0044:
            if (r1 == 0) goto L_0x004c
            r1.close()     // Catch:{ all -> 0x004a }
            goto L_0x004c
        L_0x004a:
            r0 = move-exception
            goto L_0x004d
        L_0x004c:
        L_0x004d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.utils.UMProbe.m1284c():java.lang.String");
    }
}
