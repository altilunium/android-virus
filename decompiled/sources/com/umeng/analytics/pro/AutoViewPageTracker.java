package com.umeng.analytics.pro;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import com.umeng.analytics.InternalAgent;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.pro.UMDBConfig;
import com.umeng.analytics.pro.UMStoreManager;
import com.umeng.analytics.vshelper.PageLifeCycle;
import com.umeng.analytics.vshelper.PageNameMonitor;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.utils.UMConstant;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.umeng.analytics.pro.k */
public class AutoViewPageTracker {

    /* renamed from: a */
    public static String f842a = null;

    /* renamed from: b */
    public static MobclickAgent.PageMode f843b = MobclickAgent.PageMode.AUTO;

    /* renamed from: e */
    static String f844e = null;

    /* renamed from: f */
    static int f845f = -1;

    /* renamed from: j */
    private static JSONArray f846j = new JSONArray();

    /* renamed from: k */
    private static Object f847k = new Object();

    /* renamed from: l */
    private static Application f848l = null;

    /* renamed from: p */
    private static boolean f849p = true;

    /* renamed from: q */
    private static Object f850q = new Object();

    /* renamed from: c */
    boolean f851c;

    /* renamed from: d */
    boolean f852d;

    /* renamed from: g */
    PageLifeCycle f853g;

    /* renamed from: h */
    Application.ActivityLifecycleCallbacks f854h;

    /* renamed from: i */
    private final Map f855i;

    /* renamed from: m */
    private boolean f856m;

    /* renamed from: n */
    private int f857n;

    /* renamed from: o */
    private int f858o;

    /* renamed from: a */
    static /* synthetic */ int m889a(AutoViewPageTracker kVar) {
        int i = kVar.f858o;
        kVar.f858o = i - 1;
        return i;
    }

    /* renamed from: b */
    static /* synthetic */ int m895b(AutoViewPageTracker kVar) {
        int i = kVar.f857n;
        kVar.f857n = i - 1;
        return i;
    }

    /* renamed from: e */
    static /* synthetic */ int m903e(AutoViewPageTracker kVar) {
        int i = kVar.f858o;
        kVar.f858o = i + 1;
        return i;
    }

    /* renamed from: f */
    static /* synthetic */ int m905f(AutoViewPageTracker kVar) {
        int i = kVar.f857n;
        kVar.f857n = i + 1;
        return i;
    }

    /* renamed from: a */
    public boolean mo535a() {
        return this.f856m;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.analytics.pro.k$a */
    /* compiled from: AutoViewPageTracker */
    public class C0110a {

        /* renamed from: a */
        private static final AutoViewPageTracker f860a = new AutoViewPageTracker();

        private C0110a() {
        }
    }

    /* renamed from: a */
    public static synchronized AutoViewPageTracker m890a(Context context) {
        AutoViewPageTracker kVar;
        synchronized (AutoViewPageTracker.class) {
            if (f848l == null && context != null) {
                if (context instanceof Activity) {
                    f848l = ((Activity) context).getApplication();
                } else if (context instanceof Application) {
                    f848l = (Application) context;
                }
            }
            kVar = C0110a.f860a;
        }
        return kVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0019, code lost:
        r0 = r5.getLocalClassName();
        com.umeng.commonsdk.debug.UMRTLog.m1142e(com.umeng.commonsdk.debug.UMRTLog.RTLOG_TAG, "--->>> init触发onResume: 补救成功，前台Activity名：" + r0);
        m891a(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0036, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000b, code lost:
        r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getGlobleActivity(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        if (r5 != null) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0011, code lost:
        com.umeng.commonsdk.debug.UMRTLog.m1142e(com.umeng.commonsdk.debug.UMRTLog.RTLOG_TAG, "--->>> init触发onResume: 无前台Activity，直接退出。");
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo537b(android.content.Context r5) {
        /*
            r4 = this;
            java.lang.Object r0 = com.umeng.analytics.pro.AutoViewPageTracker.f850q
            monitor-enter(r0)
            boolean r1 = com.umeng.analytics.pro.AutoViewPageTracker.f849p     // Catch:{ all -> 0x0040 }
            if (r1 == 0) goto L_0x0037
            r1 = 0
            com.umeng.analytics.pro.AutoViewPageTracker.f849p = r1     // Catch:{ all -> 0x0040 }
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            android.app.Activity r5 = com.umeng.commonsdk.statistics.common.DeviceConfig.getGlobleActivity(r5)
            if (r5 != 0) goto L_0x0019
            java.lang.String r5 = "MobclickRT"
            java.lang.String r0 = "--->>> init触发onResume: 无前台Activity，直接退出。"
            com.umeng.commonsdk.debug.UMRTLog.m1142e(r5, r0)
            return
        L_0x0019:
            java.lang.String r0 = r5.getLocalClassName()
            java.lang.String r1 = "MobclickRT"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "--->>> init触发onResume: 补救成功，前台Activity名："
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.umeng.commonsdk.debug.UMRTLog.m1142e(r1, r0)
            r4.m891a(r5)
            return
        L_0x0037:
            java.lang.String r5 = "MobclickRT"
            java.lang.String r1 = "--->>> init触发onResume: firstResumeCall = false，直接返回。"
            com.umeng.commonsdk.debug.UMRTLog.m1142e(r5, r1)
            monitor-exit(r0)
            return
        L_0x0040:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.AutoViewPageTracker.mo537b(android.content.Context):void");
    }

    private AutoViewPageTracker() {
        this.f855i = new HashMap();
        this.f856m = false;
        this.f851c = false;
        this.f852d = false;
        this.f857n = 0;
        this.f858o = 0;
        this.f853g = PageNameMonitor.getInstance();
        this.f854h = new Application.ActivityLifecycleCallbacks() {
            /* class com.umeng.analytics.pro.AutoViewPageTracker.C01091 */

            public void onActivityStopped(Activity activity) {
                MobclickAgent.PageMode pageMode = AutoViewPageTracker.f843b;
                MobclickAgent.PageMode pageMode2 = MobclickAgent.PageMode.AUTO;
                if (activity == null) {
                    return;
                }
                if (activity.isChangingConfigurations()) {
                    AutoViewPageTracker.m889a(AutoViewPageTracker.this);
                    return;
                }
                AutoViewPageTracker.m895b(AutoViewPageTracker.this);
                if (AutoViewPageTracker.this.f857n > 0) {
                    return;
                }
                if (AutoViewPageTracker.f845f != 0 || !UMUtils.isMainProgress(activity)) {
                    int i = AutoViewPageTracker.f845f;
                    if (i == 1 || (i == 0 && !UMUtils.isMainProgress(activity))) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("pairUUID", AutoViewPageTracker.f844e);
                        hashMap.put("reason", "Normal");
                        hashMap.put("pid", Integer.valueOf(Process.myPid()));
                        hashMap.put("isMainProcess", Integer.valueOf(UMUtils.isMainProgress(activity) ? 1 : 0));
                        hashMap.put("activityName", activity.toString());
                        InternalAgent.m328a().mo220a((Context) activity, "$$_onUMengEnterBackground", (Map) hashMap);
                        if (AutoViewPageTracker.f844e != null) {
                            AutoViewPageTracker.f844e = null;
                        }
                    }
                }
            }

            public void onActivityStarted(Activity activity) {
                if (activity != null) {
                    if (AutoViewPageTracker.this.f857n <= 0) {
                        if (AutoViewPageTracker.f844e == null) {
                            AutoViewPageTracker.f844e = UUID.randomUUID().toString();
                        }
                        if (AutoViewPageTracker.f845f == -1) {
                            AutoViewPageTracker.f845f = activity.isTaskRoot() ? 1 : 0;
                        }
                        if (AutoViewPageTracker.f845f == 0 && UMUtils.isMainProgress(activity)) {
                            HashMap hashMap = new HashMap();
                            hashMap.put("activityName", activity.toString());
                            hashMap.put("pid", Integer.valueOf(Process.myPid()));
                            hashMap.put("isMainProcess", Integer.valueOf(UMUtils.isMainProgress(activity) ? 1 : 0));
                            InternalAgent.m328a().mo220a((Context) activity, "$$_onUMengEnterForegroundInitError", (Map) hashMap);
                            AutoViewPageTracker.f845f = -2;
                            if (UMConfigure.isDebugLog()) {
                                UMLog.mutlInfo(2, UMLogAnalytics.f816ar);
                            }
                        } else if (AutoViewPageTracker.f845f == 1 || !UMUtils.isMainProgress(activity)) {
                            HashMap hashMap2 = new HashMap();
                            hashMap2.put("pairUUID", AutoViewPageTracker.f844e);
                            hashMap2.put("pid", Integer.valueOf(Process.myPid()));
                            hashMap2.put("isMainProcess", Integer.valueOf(UMUtils.isMainProgress(activity) ? 1 : 0));
                            hashMap2.put("activityName", activity.toString());
                            InternalAgent.m328a().mo220a((Context) activity, "$$_onUMengEnterForeground", (Map) hashMap2);
                        }
                    }
                    if (AutoViewPageTracker.this.f858o < 0) {
                        AutoViewPageTracker.m903e(AutoViewPageTracker.this);
                    } else {
                        AutoViewPageTracker.m905f(AutoViewPageTracker.this);
                    }
                }
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onActivityResumed(Activity activity) {
                if (FieldManager.allow(UMConstant.f1601F)) {
                    UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> onActivityResumed: FirstResumeTrigger enabled.");
                    synchronized (AutoViewPageTracker.f850q) {
                        if (AutoViewPageTracker.f849p) {
                            boolean unused = AutoViewPageTracker.f849p = false;
                        }
                    }
                    AutoViewPageTracker.this.m891a((AutoViewPageTracker) activity);
                    return;
                }
                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> onActivityResumed: FirstResumeTrigger disabled.");
                AutoViewPageTracker.this.m891a((AutoViewPageTracker) activity);
            }

            public void onActivityPaused(Activity activity) {
                if (FieldManager.allow(UMConstant.f1601F)) {
                    UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> onActivityPaused: FirstResumeTrigger enabled.");
                    synchronized (AutoViewPageTracker.f850q) {
                        if (AutoViewPageTracker.f849p) {
                            return;
                        }
                    }
                } else {
                    UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> onActivityPaused: FirstResumeTrigger disabled.");
                }
                if (AutoViewPageTracker.f843b == MobclickAgent.PageMode.AUTO) {
                    AutoViewPageTracker.this.m899c((AutoViewPageTracker) activity);
                    InternalAgent.m328a().mo254i();
                    AutoViewPageTracker.this.f851c = false;
                }
            }

            public void onActivityDestroyed(Activity activity) {
            }

            public void onActivityCreated(Activity activity, Bundle bundle) {
            }
        };
        synchronized (this) {
            if (f848l != null) {
                m906f();
            }
        }
    }

    /* renamed from: f */
    private void m906f() {
        if (!this.f856m) {
            this.f856m = true;
            if (f848l != null) {
                f848l.registerActivityLifecycleCallbacks(this.f854h);
            }
        }
    }

    /* renamed from: b */
    public void mo536b() {
        this.f856m = false;
        if (f848l != null) {
            f848l.unregisterActivityLifecycleCallbacks(this.f854h);
            f848l = null;
        }
    }

    /* renamed from: c */
    public void mo538c() {
        m899c((Activity) null);
        mo536b();
    }

    /* renamed from: a */
    public static void m892a(Context context, String str) {
        if (f845f == 1 && UMUtils.isMainProgress(context)) {
            HashMap hashMap = new HashMap();
            hashMap.put("pairUUID", f844e);
            hashMap.put("reason", str);
            if (f844e != null) {
                f844e = null;
            }
            if (context != null) {
                hashMap.put("pid", Integer.valueOf(Process.myPid()));
                hashMap.put("isMainProcess", Integer.valueOf(UMUtils.isMainProgress(context) ? 1 : 0));
                hashMap.put("Context", context.toString());
                InternalAgent.m328a().mo220a(context, "$$_onUMengEnterBackground", (Map) hashMap);
            }
        }
    }

    /* renamed from: c */
    public static void m900c(Context context) {
        String jSONArray;
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                synchronized (f847k) {
                    jSONArray = f846j.toString();
                    f846j = new JSONArray();
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put(UMDBConfig.C0098d.C0099a.f722c, new JSONArray(jSONArray));
                    UMStoreManager.m853a(context).mo519a(SessionTracker.m1011a().mo600c(), jSONObject, UMStoreManager.EnumC0107a.AUTOPAGE);
                }
            } catch (Throwable th) {
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m891a(Activity activity) {
        if (f843b == MobclickAgent.PageMode.AUTO && activity != null) {
            String str = activity.getPackageName() + "." + activity.getLocalClassName();
            this.f853g.activityResume(str);
            if (this.f851c) {
                this.f851c = false;
                if (TextUtils.isEmpty(f842a)) {
                    f842a = str;
                } else if (!f842a.equals(str)) {
                    m896b(activity);
                    synchronized (f850q) {
                        InternalAgent.m328a().mo253h();
                    }
                }
            } else {
                m896b(activity);
                synchronized (f850q) {
                    InternalAgent.m328a().mo253h();
                }
            }
        }
    }

    /* renamed from: b */
    private void m896b(Activity activity) {
        f842a = activity.getPackageName() + "." + activity.getLocalClassName();
        synchronized (this.f855i) {
            this.f855i.put(f842a, Long.valueOf(System.currentTimeMillis()));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m899c(Activity activity) {
        long j;
        long j2;
        try {
            synchronized (this.f855i) {
                if (f842a == null && activity != null) {
                    f842a = activity.getPackageName() + "." + activity.getLocalClassName();
                }
                j = 0;
                if (TextUtils.isEmpty(f842a) || !this.f855i.containsKey(f842a)) {
                    j2 = 0;
                } else {
                    long longValue = ((Long) this.f855i.get(f842a)).longValue();
                    this.f855i.remove(f842a);
                    j = System.currentTimeMillis() - longValue;
                    j2 = longValue;
                }
            }
            synchronized (f847k) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(UContent.f660v, f842a);
                    jSONObject.put("duration", j);
                    jSONObject.put(UContent.f662x, j2);
                    jSONObject.put(UContent.f663y, 0);
                    f846j.put(jSONObject);
                } catch (Throwable th) {
                }
            }
        } catch (Throwable th2) {
        }
    }
}
