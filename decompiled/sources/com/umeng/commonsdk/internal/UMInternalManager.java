package com.umeng.commonsdk.internal;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodInfo;
import com.umeng.analytics.pro.UMCommonContent;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.internal.utils.ApplicationLayerUtil;
import com.umeng.commonsdk.internal.utils.CpuUtil;
import com.umeng.commonsdk.internal.utils.UMInternalUtils;
import com.umeng.commonsdk.internal.utils.UMProbe;
import com.umeng.commonsdk.statistics.UMServerURL;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.utils.UMConstant;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.umeng.commonsdk.internal.d */
public class UMInternalManager {
    /* renamed from: a */
    public static void m1199a(Context context) {
        try {
            ULog.m1396i("walle", "[internal] workEvent send envelope");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(UMCommonContent.f301aK, UMInternalConfig.f1210e);
            JSONObject buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(context, jSONObject, m1206d(context), UMServerURL.PATH_ANALYTICS, UMCommonContent.f291aA, UMInternalConfig.f1210e);
            if (buildEnvelopeWithExtHeader != null && !buildEnvelopeWithExtHeader.has("exception")) {
                ULog.m1396i("walle", "[internal] workEvent send envelope back, result is ok");
                ApplicationLayerUtil.m1229e(context);
            }
        } catch (Exception e) {
            UMCrashManager.reportCrash(context, e);
        }
    }

    /* renamed from: b */
    public static void m1203b(Context context) {
        ULog.m1396i("walle", "[internal] begin by stateful--->>>");
        if (context != null) {
            m1212j(context);
        }
    }

    /* renamed from: c */
    public static void m1205c(Context context) {
        ULog.m1396i("walle", "[internal] begin by stateful--->>>");
        if (context != null && UMEnvelopeBuild.getTransmissionSendFlag()) {
            m1212j(context);
        }
    }

    /* renamed from: j */
    private static void m1212j(Context context) {
        try {
            if (UMEnvelopeBuild.isReadyBuild(context, UMLogDataProtocol.UMBusinessType.U_INTERNAL)) {
                UMWorkDispatch.sendEvent(context, UMInternalConfig.f1211f, UMInternalData.m1179a(context).mo677a(), null);
            }
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 冷启动：5秒后触发2号数据仓遗留信封检查动作。");
            UMWorkDispatch.sendEvent(context, UMInternalConfig.f1227v, UMInternalData.m1179a(context).mo677a(), null, 5000);
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
        }
    }

    /* renamed from: d */
    public static JSONObject m1206d(Context context) {
        JSONArray h;
        JSONArray k;
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            try {
                if (FieldManager.allow(UMConstant.f1604I) && (k = m1213k(applicationContext)) != null && k.length() > 0) {
                    jSONObject2.put("rs", k);
                }
            } catch (Exception e) {
                UMCrashManager.reportCrash(applicationContext, e);
            }
            try {
                JSONArray l = m1214l(applicationContext);
                if (l != null && l.length() > 0) {
                    jSONObject2.put("by", l);
                }
            } catch (Exception e2) {
                UMCrashManager.reportCrash(applicationContext, e2);
            }
            try {
                m1200a(applicationContext, jSONObject2);
            } catch (Exception e3) {
                UMCrashManager.reportCrash(applicationContext, e3);
            }
            try {
                m1204b(applicationContext, jSONObject2);
            } catch (Exception e4) {
                UMCrashManager.reportCrash(applicationContext, e4);
            }
            try {
                JSONObject a = m1198a();
                if (a != null && a.length() > 0) {
                    jSONObject2.put("build", a);
                }
            } catch (Exception e5) {
                UMCrashManager.reportCrash(applicationContext, e5);
            }
            try {
                JSONObject e6 = m1207e(applicationContext);
                if (e6 != null && e6.length() > 0) {
                    jSONObject2.put("scr", e6);
                }
            } catch (Exception e7) {
                UMCrashManager.reportCrash(applicationContext, e7);
            }
            try {
                JSONObject f = m1208f(applicationContext);
                if (f != null && f.length() > 0) {
                    jSONObject2.put("sinfo", f);
                }
            } catch (Exception e8) {
                UMCrashManager.reportCrash(applicationContext, e8);
            }
            try {
                JSONObject jSONObject3 = new JSONObject();
                JSONArray d = ApplicationLayerUtil.m1226d(applicationContext);
                if (d != null && d.length() > 0) {
                    try {
                        jSONObject3.put("wl", d);
                    } catch (JSONException e9) {
                    }
                }
                jSONObject2.put("winfo", jSONObject3);
            } catch (Exception e10) {
                UMCrashManager.reportCrash(applicationContext, e10);
            }
            try {
                JSONArray g = m1209g(applicationContext);
                if (g != null && g.length() > 0) {
                    jSONObject2.put("input", g);
                }
            } catch (Exception e11) {
                UMCrashManager.reportCrash(applicationContext, e11);
            }
            try {
                if (FieldManager.allow(UMConstant.f1628af) && (h = m1210h(applicationContext)) != null && h.length() > 0) {
                    jSONObject2.put("appls", h);
                }
            } catch (Exception e12) {
                UMCrashManager.reportCrash(applicationContext, e12);
            }
            try {
                JSONObject i = m1211i(applicationContext);
                if (i != null && i.length() > 0) {
                    jSONObject2.put("mem", i);
                }
            } catch (Exception e13) {
                UMCrashManager.reportCrash(applicationContext, e13);
            }
            try {
                JSONObject b = m1202b();
                if (b != null && b.length() > 0) {
                    jSONObject2.put(UMCommonContent.f374w, b);
                }
            } catch (Exception e14) {
            }
            try {
                jSONObject.put(UMCommonContent.f335as, jSONObject2);
            } catch (JSONException e15) {
                UMCrashManager.reportCrash(applicationContext, e15);
            }
        }
        return jSONObject;
    }

    /* renamed from: b */
    private static JSONObject m1202b() {
        try {
            CpuUtil.C0172a a = CpuUtil.m1255a();
            if (a == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("pro", a.f1277a);
                jSONObject.put("pla", a.f1278b);
                jSONObject.put("cpus", a.f1279c);
                jSONObject.put("fea", a.f1280d);
                jSONObject.put("imp", a.f1281e);
                jSONObject.put("arc", a.f1282f);
                jSONObject.put("var", a.f1283g);
                jSONObject.put("par", a.f1284h);
                jSONObject.put("rev", a.f1285i);
                jSONObject.put("har", a.f1286j);
                jSONObject.put("rev", a.f1287k);
                jSONObject.put("ser", a.f1288l);
                jSONObject.put("cur_cpu", CpuUtil.m1258d());
                jSONObject.put("max_cpu", CpuUtil.m1256b());
                jSONObject.put("min_cpu", CpuUtil.m1257c());
                jSONObject.put("ts", System.currentTimeMillis());
                return jSONObject;
            } catch (Exception e) {
                return jSONObject;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    /* renamed from: k */
    private static JSONArray m1213k(Context context) {
        Throwable th;
        List<ActivityManager.RunningServiceInfo> runningServices;
        JSONArray jSONArray = null;
        if (context == null) {
            return null;
        }
        try {
            ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService("activity");
            if (activityManager == null || (runningServices = activityManager.getRunningServices(Integer.MAX_VALUE)) == null || runningServices.isEmpty()) {
                return null;
            }
            for (int i = 0; i < runningServices.size(); i++) {
                if (!(runningServices.get(i) == null || runningServices.get(i).service == null || runningServices.get(i).service.getClassName() == null || runningServices.get(i).service.getPackageName() == null)) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("sn", runningServices.get(i).service.getClassName().toString());
                        jSONObject.put("pn", runningServices.get(i).service.getPackageName().toString());
                        if (jSONArray == null) {
                            jSONArray = new JSONArray();
                        }
                        jSONArray.put(jSONObject);
                    } catch (JSONException e) {
                    }
                }
            }
            if (jSONArray == null) {
                return jSONArray;
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("ts", System.currentTimeMillis());
                jSONObject2.put("ls", jSONArray);
            } catch (JSONException e2) {
            }
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put("sers", jSONObject2);
            } catch (JSONException e3) {
            }
            JSONArray jSONArray2 = new JSONArray();
            try {
                jSONArray2.put(jSONObject3);
                return jSONArray2;
            } catch (Throwable th2) {
                th = th2;
                jSONArray = jSONArray2;
                UMCrashManager.reportCrash(context, th);
                return jSONArray;
            }
        } catch (Throwable th3) {
            th = th3;
            UMCrashManager.reportCrash(context, th);
            return jSONArray;
        }
    }

    /* renamed from: l */
    private static JSONArray m1214l(Context context) {
        JSONArray jSONArray = new JSONArray();
        String a = UMInternalUtils.m1275a(context);
        if (!TextUtils.isEmpty(a)) {
            try {
                jSONArray.put(new JSONObject(a));
            } catch (Exception e) {
            }
        }
        return jSONArray;
    }

    /* renamed from: a */
    private static void m1200a(Context context, JSONObject jSONObject) {
        PackageManager packageManager;
        if (context != null && (packageManager = context.getApplicationContext().getPackageManager()) != null) {
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            m1201a(jSONObject, "gp", packageManager.hasSystemFeature("android.hardware.location.gps"));
            m1201a(jSONObject, "to", packageManager.hasSystemFeature("android.hardware.touchscreen"));
            m1201a(jSONObject, "mo", packageManager.hasSystemFeature("android.hardware.telephony"));
            m1201a(jSONObject, "ca", packageManager.hasSystemFeature("android.hardware.camera"));
            m1201a(jSONObject, "fl", packageManager.hasSystemFeature("android.hardware.camera.flash"));
        }
    }

    /* renamed from: a */
    private static void m1201a(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject != null && !TextUtils.isEmpty(str)) {
            if (z) {
                try {
                    jSONObject.put(str, 1);
                } catch (Exception e) {
                }
            } else {
                jSONObject.put(str, 0);
            }
        }
    }

    /* renamed from: b */
    private static void m1204b(Context context, JSONObject jSONObject) {
        if (context != null) {
            String a = UMProbe.m1279a(context);
            if (!TextUtils.isEmpty(a)) {
                try {
                    JSONObject jSONObject2 = new JSONObject(a);
                    if (jSONObject == null) {
                        jSONObject = new JSONObject();
                    }
                    if (jSONObject2.has(UMProbe.f1316d)) {
                        jSONObject.put(UMProbe.f1316d, jSONObject2.opt(UMProbe.f1316d));
                    }
                    if (jSONObject2.has(UMProbe.f1315c)) {
                        jSONObject.put(UMProbe.f1315c, jSONObject2.opt(UMProbe.f1315c));
                    }
                    if (jSONObject2.has(UMProbe.f1314b)) {
                        jSONObject.put(UMProbe.f1314b, jSONObject2.opt(UMProbe.f1314b));
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    /* renamed from: a */
    public static JSONObject m1198a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("a_pr", Build.PRODUCT);
            jSONObject.put("a_bl", Build.BOOTLOADER);
            int i = Build.VERSION.SDK_INT;
            jSONObject.put("a_rv", Build.getRadioVersion());
            jSONObject.put("a_fp", Build.FINGERPRINT);
            jSONObject.put("a_hw", Build.HARDWARE);
            jSONObject.put("a_host", Build.HOST);
            int i2 = 0;
            if (i >= 21) {
                JSONArray jSONArray = new JSONArray();
                int i3 = 0;
                while (true) {
                    String[] strArr = Build.SUPPORTED_32_BIT_ABIS;
                    if (i3 >= strArr.length) {
                        break;
                    }
                    jSONArray.put(strArr[i3]);
                    i3++;
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("a_s32", jSONArray);
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                JSONArray jSONArray2 = new JSONArray();
                int i4 = 0;
                while (true) {
                    String[] strArr2 = Build.SUPPORTED_64_BIT_ABIS;
                    if (i4 >= strArr2.length) {
                        break;
                    }
                    jSONArray2.put(strArr2[i4]);
                    i4++;
                }
                if (jSONArray2.length() > 0) {
                    jSONObject.put("a_s64", jSONArray2);
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                JSONArray jSONArray3 = new JSONArray();
                while (true) {
                    String[] strArr3 = Build.SUPPORTED_ABIS;
                    if (i2 >= strArr3.length) {
                        break;
                    }
                    jSONArray3.put(strArr3[i2]);
                    i2++;
                }
                if (jSONArray3.length() > 0) {
                    jSONObject.put("a_sa", jSONArray3);
                }
            }
            jSONObject.put("a_ta", Build.TAGS);
            jSONObject.put("a_uk", "unknown");
            jSONObject.put("a_user", Build.USER);
            jSONObject.put("a_cpu1", Build.CPU_ABI);
            jSONObject.put("a_cpu2", Build.CPU_ABI2);
            jSONObject.put("a_ra", Build.RADIO);
            if (Build.VERSION.SDK_INT >= 23) {
                jSONObject.put("a_bos", Build.VERSION.BASE_OS);
                jSONObject.put("a_pre", Build.VERSION.PREVIEW_SDK_INT);
                jSONObject.put("a_sp", Build.VERSION.SECURITY_PATCH);
            }
            jSONObject.put("a_cn", Build.VERSION.CODENAME);
            jSONObject.put("a_intl", Build.VERSION.INCREMENTAL);
        } catch (Exception e) {
        }
        return jSONObject;
    }

    /* renamed from: e */
    public static JSONObject m1207e(Context context) {
        DisplayMetrics displayMetrics;
        JSONObject jSONObject = new JSONObject();
        if (context != null) {
            try {
                jSONObject.put("a_st_h", ApplicationLayerUtil.m1231g(context));
                jSONObject.put("a_nav_h", ApplicationLayerUtil.m1232h(context));
                if (!(context.getResources() == null || (displayMetrics = context.getResources().getDisplayMetrics()) == null)) {
                    jSONObject.put("a_den", (double) displayMetrics.density);
                    jSONObject.put("a_dpi", displayMetrics.densityDpi);
                }
            } catch (Exception e) {
                UMCrashManager.reportCrash(context, e);
            }
        }
        return jSONObject;
    }

    /* renamed from: f */
    public static JSONObject m1208f(Context context) {
        JSONObject jSONObject = new JSONObject();
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            String packageName = applicationContext.getPackageName();
            try {
                jSONObject.put("a_fit", ApplicationLayerUtil.m1216a(applicationContext, packageName));
                jSONObject.put("a_alut", ApplicationLayerUtil.m1218b(applicationContext, packageName));
                jSONObject.put("a_c", ApplicationLayerUtil.m1222c(applicationContext, packageName));
                jSONObject.put("a_uid", ApplicationLayerUtil.m1224d(applicationContext, packageName));
                if (ApplicationLayerUtil.m1217a()) {
                    jSONObject.put("a_root", 1);
                } else {
                    jSONObject.put("a_root", 0);
                }
                jSONObject.put("tf", ApplicationLayerUtil.m1220b());
                jSONObject.put("s_fs", (double) ApplicationLayerUtil.m1215a(applicationContext));
                jSONObject.put("a_meid", DeviceConfig.getMeid(applicationContext));
                jSONObject.put("a_imsi", DeviceConfig.getImsi(applicationContext));
                jSONObject.put("st", ApplicationLayerUtil.m1225d());
                String simICCID = DeviceConfig.getSimICCID(applicationContext);
                if (!TextUtils.isEmpty(simICCID)) {
                    try {
                        jSONObject.put("a_iccid", simICCID);
                    } catch (Exception e) {
                    }
                }
                String secondSimIMEi = DeviceConfig.getSecondSimIMEi(applicationContext);
                if (!TextUtils.isEmpty(secondSimIMEi)) {
                    try {
                        jSONObject.put("a_simei", secondSimIMEi);
                    } catch (Exception e2) {
                    }
                }
                jSONObject.put("hn", ApplicationLayerUtil.m1227e());
                jSONObject.put("ts", System.currentTimeMillis());
            } catch (Exception e3) {
                UMCrashManager.reportCrash(applicationContext, e3);
            }
        }
        return jSONObject;
    }

    /* renamed from: g */
    public static JSONArray m1209g(Context context) {
        Context applicationContext;
        List<InputMethodInfo> j;
        JSONArray jSONArray = new JSONArray();
        if (!(context == null || (j = ApplicationLayerUtil.m1234j((applicationContext = context.getApplicationContext()))) == null)) {
            for (InputMethodInfo inputMethodInfo : j) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("a_id", inputMethodInfo.getId());
                    jSONObject.put("a_pn", inputMethodInfo.getPackageName());
                    jSONObject.put("ts", System.currentTimeMillis());
                    jSONArray.put(jSONObject);
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(applicationContext, th);
                }
            }
        }
        return jSONArray;
    }

    /* renamed from: h */
    public static JSONArray m1210h(Context context) {
        Context applicationContext;
        List<ApplicationLayerUtil.C0166a> k;
        JSONArray jSONArray = new JSONArray();
        if (!(context == null || (k = ApplicationLayerUtil.m1235k((applicationContext = context.getApplicationContext()))) == null || k.isEmpty())) {
            for (ApplicationLayerUtil.C0166a aVar : k) {
                if (aVar != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("a_pn", aVar.f1250a);
                        jSONObject.put("a_la", aVar.f1251b);
                        jSONObject.put("ts", System.currentTimeMillis());
                        jSONArray.put(jSONObject);
                    } catch (Exception e) {
                        UMCrashManager.reportCrash(applicationContext, e);
                    }
                }
            }
        }
        return jSONArray;
    }

    /* renamed from: i */
    public static JSONObject m1211i(Context context) {
        Context applicationContext;
        ActivityManager.MemoryInfo l;
        JSONObject jSONObject = new JSONObject();
        if (!(context == null || (l = ApplicationLayerUtil.m1236l((applicationContext = context.getApplicationContext()))) == null)) {
            try {
                jSONObject.put(UMCommonContent.f296aF, l.totalMem);
                jSONObject.put("f", l.availMem);
                jSONObject.put("ts", System.currentTimeMillis());
            } catch (Exception e) {
                UMCrashManager.reportCrash(applicationContext, e);
            }
        }
        return jSONObject;
    }
}
