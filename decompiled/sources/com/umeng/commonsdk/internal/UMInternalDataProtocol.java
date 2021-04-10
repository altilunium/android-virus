package com.umeng.commonsdk.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.umeng.analytics.CoreProtocol;
import com.umeng.analytics.pro.CacheDBHelper;
import com.umeng.analytics.pro.CacheData;
import com.umeng.analytics.pro.Constants;
import com.umeng.analytics.pro.CoreProtocolImpl;
import com.umeng.analytics.pro.OpenDeviceId;
import com.umeng.analytics.pro.UMCommonContent;
import com.umeng.commonsdk.UMConfigureImpl;
import com.umeng.commonsdk.UMInnerManager;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.listener.OnGetOaidListener;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.EnvelopeManager;
import com.umeng.commonsdk.statistics.UMServerURL;
import com.umeng.commonsdk.statistics.idtracking.OaidTracking;
import com.umeng.commonsdk.utils.UMConstant;
import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.commonsdk.utils.onMessageSendListener;
import java.io.File;
import java.lang.reflect.Method;
import org.json.JSONObject;

/* renamed from: com.umeng.commonsdk.internal.c */
public class UMInternalDataProtocol implements UMLogDataProtocol {

    /* renamed from: b */
    private static int f1235b = 1;

    /* renamed from: c */
    private static final String f1236c = "info";

    /* renamed from: d */
    private static final String f1237d = "stat";

    /* renamed from: e */
    private static Class f1238e = null;

    /* renamed from: f */
    private static Method f1239f = null;

    /* renamed from: g */
    private static Method f1240g = null;

    /* renamed from: h */
    private static Method f1241h = null;

    /* renamed from: i */
    private static boolean f1242i = false;

    /* renamed from: a */
    private Context f1243a;

    static {
        m1187c();
    }

    /* renamed from: c */
    private static void m1187c() {
        try {
            Class<?> cls = Class.forName("com.umeng.umzid.ZIDManager");
            f1238e = cls;
            Method declaredMethod = cls.getDeclaredMethod("getInstance", new Class[0]);
            if (declaredMethod != null) {
                f1239f = declaredMethod;
            }
            Method declaredMethod2 = f1238e.getDeclaredMethod("getZID", Context.class);
            if (declaredMethod2 != null) {
                f1240g = declaredMethod2;
            }
            Method declaredMethod3 = f1238e.getDeclaredMethod("getSDKVersion", new Class[0]);
            if (declaredMethod3 != null) {
                f1241h = declaredMethod3;
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public String mo678a() {
        Method method;
        Class cls = f1238e;
        if (cls == null || (method = f1239f) == null || f1240g == null) {
            return "";
        }
        try {
            Object invoke = method.invoke(cls, new Object[0]);
            if (invoke != null) {
                return (String) f1240g.invoke(invoke, this.f1243a);
            }
            return "";
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: b */
    public static String m1185b() {
        Method method;
        Class cls = f1238e;
        if (cls == null || (method = f1239f) == null || f1241h == null) {
            return "";
        }
        try {
            Object invoke = method.invoke(cls, new Object[0]);
            if (invoke != null) {
                return (String) f1241h.invoke(invoke, new Object[0]);
            }
            return "";
        } catch (Throwable th) {
            return "";
        }
    }

    public UMInternalDataProtocol(Context context) {
        if (context != null) {
            this.f1243a = context.getApplicationContext();
        }
    }

    /* renamed from: a */
    private void m1183a(Context context) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appkey", UMGlobalContext.getInstance(context).getAppkey());
            jSONObject.put("app_version", UMGlobalContext.getInstance(context).getAppVersion());
            jSONObject.put(UMCommonContent.f375x, "Android");
            JSONObject buildZeroEnvelopeWithExtHeader = UMEnvelopeBuild.buildZeroEnvelopeWithExtHeader(context, jSONObject, null, UMServerURL.ZCFG_PATH);
            if (buildZeroEnvelopeWithExtHeader == null || !buildZeroEnvelopeWithExtHeader.has("exception")) {
                UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 构建零号报文 成功!!!");
            } else {
                UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 构建零号报文失败.");
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: d */
    private void m1189d() {
        CacheDBHelper a = CacheDBHelper.m506a(this.f1243a);
        CacheData a2 = a.mo364a(Constants.f436c);
        if (a2 != null) {
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> [有状态]二级缓存记录构建成真正信封。");
            try {
                String str = a2.f428a;
                String str2 = a2.f429b;
                JSONObject a3 = new EnvelopeManager().mo718a(this.f1243a.getApplicationContext(), new JSONObject(a2.f430c), new JSONObject(a2.f431d), a2.f432e, str2, a2.f433f);
                if (a3 == null || !a3.has("exception")) {
                    UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> [有状态]二级缓存记录构建真正信封 成功! 删除二级缓存记录。");
                } else {
                    UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> [有状态]二级缓存记录构建真正信封 失败。删除二级缓存记录");
                }
                a.mo366a(Constants.f436c, str);
                a.mo368b();
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: b */
    private static void m1186b(final Context context) {
        new Thread(new Runnable() {
            /* class com.umeng.commonsdk.internal.UMInternalDataProtocol.RunnableC01611 */

            public void run() {
                try {
                    SharedPreferences sharedPreferences = context.getSharedPreferences(OaidTracking.f1453a, 0);
                    long currentTimeMillis = System.currentTimeMillis();
                    String a = OpenDeviceId.m1067a(context);
                    long currentTimeMillis2 = System.currentTimeMillis();
                    if (!TextUtils.isEmpty(a) && sharedPreferences != null) {
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString(OaidTracking.f1455c, (currentTimeMillis2 - currentTimeMillis) + "");
                        edit.commit();
                    }
                    if (sharedPreferences != null) {
                        try {
                            SharedPreferences.Editor edit2 = sharedPreferences.edit();
                            edit2.putString(OaidTracking.f1454b, a);
                            edit2.commit();
                        } catch (Throwable th) {
                            return;
                        }
                    }
                    if (Build.VERSION.SDK_INT > 28) {
                        UMConfigureImpl.removeInterruptFlag();
                    }
                } catch (Exception e) {
                }
            }
        }).start();
    }

    /* renamed from: a */
    private static void m1184a(Context context, final OnGetOaidListener onGetOaidListener) {
        if (context != null) {
            final Context applicationContext = context.getApplicationContext();
            new Thread(new Runnable() {
                /* class com.umeng.commonsdk.internal.UMInternalDataProtocol.RunnableC01622 */

                public void run() {
                    String a = OpenDeviceId.m1067a(applicationContext);
                    OnGetOaidListener onGetOaidListener = onGetOaidListener;
                    if (onGetOaidListener != null) {
                        onGetOaidListener.onGetOaid(a);
                    }
                }
            }).start();
        }
    }

    /* renamed from: c */
    private static void m1188c(final Context context) {
        if (FieldManager.allow(UMConstant.f1602G) && Build.VERSION.SDK_INT > 28) {
            m1184a(context, new OnGetOaidListener() {
                /* class com.umeng.commonsdk.internal.UMInternalDataProtocol.C01633 */

                @Override // com.umeng.commonsdk.listener.OnGetOaidListener
                public void onGetOaid(String str) {
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            SharedPreferences sharedPreferences = context.getSharedPreferences(OaidTracking.f1453a, 0);
                            if (sharedPreferences != null && !sharedPreferences.getString(OaidTracking.f1454b, "").equalsIgnoreCase(str)) {
                                UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 更新本地缓存OAID");
                                SharedPreferences.Editor edit = sharedPreferences.edit();
                                edit.putString(OaidTracking.f1454b, str);
                                edit.commit();
                            }
                        } catch (Throwable th) {
                        }
                    }
                }
            });
        }
    }

    /* renamed from: e */
    private void m1191e() {
        if (!f1242i) {
            if (FieldManager.allow(UMConstant.f1602G) && Build.VERSION.SDK_INT > 28) {
                f1242i = true;
                m1184a(this.f1243a, new OnGetOaidListener() {
                    /* class com.umeng.commonsdk.internal.UMInternalDataProtocol.C01644 */

                    @Override // com.umeng.commonsdk.listener.OnGetOaidListener
                    public void onGetOaid(String str) {
                        UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> OAID云控参数更新(不采集->采集)：采集完成");
                        if (TextUtils.isEmpty(str)) {
                            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> oaid返回null或者空串，不需要 伪冷启动。");
                            return;
                        }
                        try {
                            SharedPreferences sharedPreferences = UMInternalDataProtocol.this.f1243a.getSharedPreferences(OaidTracking.f1453a, 0);
                            if (sharedPreferences != null) {
                                SharedPreferences.Editor edit = sharedPreferences.edit();
                                edit.putString(OaidTracking.f1454b, str);
                                edit.commit();
                            }
                        } catch (Throwable th) {
                        }
                        UMWorkDispatch.sendEvent(UMInternalDataProtocol.this.f1243a, UMInternalConfig.f1228w, UMInternalData.m1179a(UMInternalDataProtocol.this.f1243a).mo677a(), null);
                    }
                });
            }
        } else if (!FieldManager.allow(UMConstant.f1602G)) {
            f1242i = false;
        }
    }

    /* renamed from: f */
    private void m1193f() {
        if (FieldManager.allow(UMConstant.f1602G) && Build.VERSION.SDK_INT > 28) {
            f1242i = true;
            UMConfigureImpl.registerInterruptFlag();
            UMConfigureImpl.init(this.f1243a);
            f1235b++;
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 要读取 oaid，需等待读取结果.");
            UMConfigureImpl.registerMessageSendListener(new onMessageSendListener() {
                /* class com.umeng.commonsdk.internal.UMInternalDataProtocol.C01655 */

                @Override // com.umeng.commonsdk.utils.onMessageSendListener
                public void onMessageSend() {
                    if (UMInternalDataProtocol.this.f1243a != null) {
                        UMWorkDispatch.sendEvent(UMInternalDataProtocol.this.f1243a, UMInternalConfig.f1229x, UMInternalData.m1179a(UMInternalDataProtocol.this.f1243a).mo677a(), null);
                    }
                    UMConfigureImpl.removeMessageSendListener(this);
                }
            });
            m1186b(this.f1243a);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00d6 A[Catch:{ all -> 0x00f4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00ed A[Catch:{ all -> 0x00f4 }] */
    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void workEvent(java.lang.Object r9, int r10) {
        /*
        // Method dump skipped, instructions count: 776
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.UMInternalDataProtocol.workEvent(java.lang.Object, int):void");
    }

    /* renamed from: a */
    private static Class m1182a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    /* renamed from: d */
    private void m1190d(Context context) {
        Object invoke;
        Method declaredMethod;
        Context applicationContext = context.getApplicationContext();
        String appkey = UMUtils.getAppkey(context);
        try {
            Class a = m1182a("com.umeng.umzid.ZIDManager");
            Method declaredMethod2 = a.getDeclaredMethod("getInstance", new Class[0]);
            if (declaredMethod2 != null && (invoke = declaredMethod2.invoke(a, new Object[0])) != null && (declaredMethod = a.getDeclaredMethod("init", Context.class, String.class, m1182a("com.umeng.umzid.IZIDCompletionCallback"))) != null) {
                try {
                    declaredMethod.invoke(invoke, applicationContext, appkey, null);
                } catch (Throwable th) {
                }
            }
        } catch (Throwable th2) {
        }
    }

    /* renamed from: g */
    private void m1194g() {
        if (f1235b <= 0) {
            m1195h();
            m1190d(this.f1243a);
        }
    }

    /* renamed from: e */
    private static void m1192e(Context context) {
        File filesDir = context.getFilesDir();
        File file = new File(filesDir.getAbsolutePath() + File.separator + Constants.f445l);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: h */
    private void m1195h() {
        UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 真实构建条件满足，开始构建业务信封。");
        if (UMUtils.isMainProgress(this.f1243a)) {
            m1192e(this.f1243a);
            UMInnerManager.m1111a(this.f1243a);
            Context context = this.f1243a;
            UMWorkDispatch.sendEvent(context, CoreProtocolImpl.C0113a.f921x, CoreProtocol.getInstance(context), null);
            Context context2 = this.f1243a;
            UMWorkDispatch.sendEvent(context2, UMInternalConfig.f1225t, UMInternalData.m1179a(context2).mo677a(), null);
        }
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void removeCacheData(Object obj) {
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public JSONObject setupReportData(long j) {
        return null;
    }
}
