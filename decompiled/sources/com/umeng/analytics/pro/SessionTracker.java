package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.CoreProtocol;
import com.umeng.analytics.InternalAgent;
import com.umeng.analytics.pro.CoreProtocolImpl;
import com.umeng.analytics.pro.SessionIdManager;
import com.umeng.analytics.pro.UMDBConfig;
import com.umeng.analytics.pro.UMStoreManager;
import com.umeng.analytics.process.UMProcessDBDatasSender;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMConstant;
import com.umeng.commonsdk.utils.UMUtils;
import java.lang.reflect.Method;
import org.json.JSONObject;

/* renamed from: com.umeng.analytics.pro.t */
public class SessionTracker implements SessionIdManager.AbstractC0124a {

    /* renamed from: a */
    public static final String f952a = "session_start_time";

    /* renamed from: b */
    public static final String f953b = "session_end_time";

    /* renamed from: c */
    public static final String f954c = "session_id";

    /* renamed from: d */
    public static final String f955d = "pre_session_id";

    /* renamed from: e */
    public static final String f956e = "a_start_time";

    /* renamed from: f */
    public static final String f957f = "a_end_time";

    /* renamed from: g */
    public static final String f958g = "fg_count";

    /* renamed from: h */
    private static String f959h = null;

    /* renamed from: i */
    private static Context f960i = null;

    /* renamed from: j */
    private static boolean f961j = false;

    /* renamed from: k */
    private static long f962k = 0;

    /* renamed from: l */
    private static boolean f963l = true;

    /* renamed from: m */
    private static long f964m = 0;

    private SessionTracker() {
        SessionIdManager.m1047a().mo614a(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.analytics.pro.t$a */
    /* compiled from: SessionTracker */
    public class C0123a {

        /* renamed from: a */
        private static final SessionTracker f965a = new SessionTracker();

        private C0123a() {
        }
    }

    /* renamed from: a */
    public static SessionTracker m1011a() {
        return C0123a.f965a;
    }

    /* renamed from: d */
    private void m1015d(Context context) {
        try {
            SharedPreferences.Editor edit = PreferenceWrapper.getDefault(context).edit();
            edit.putLong(f958g, 0);
            edit.commit();
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public static long m1010a(Context context) {
        try {
            return PreferenceWrapper.getDefault(context).getLong(f958g, 0);
        } catch (Throwable th) {
            return 0;
        }
    }

    /* renamed from: b */
    public static void m1014b(Context context) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f960i);
        if (sharedPreferences != null) {
            long j = sharedPreferences.getLong(f958g, 0);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (edit != null) {
                edit.putLong(f958g, j + 1);
                edit.commit();
            }
        }
    }

    /* renamed from: a */
    public void mo592a(Context context, long j) {
        SharedPreferences.Editor edit;
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f960i);
        if (sharedPreferences != null && (edit = sharedPreferences.edit()) != null) {
            edit.putLong(f952a, j);
            edit.commit();
        }
    }

    /* renamed from: a */
    public void mo593a(Context context, Object obj) {
        SharedPreferences.Editor edit;
        try {
            if (f960i == null && context != null) {
                f960i = context.getApplicationContext();
            }
            long longValue = ((Long) obj).longValue();
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f960i);
            if (sharedPreferences != null && (edit = sharedPreferences.edit()) != null) {
                String string = sharedPreferences.getString(UContent.f639az, "");
                String appVersionName = UMUtils.getAppVersionName(f960i);
                if (TextUtils.isEmpty(string)) {
                    edit.putInt("versioncode", Integer.parseInt(UMUtils.getAppVersionCode(context)));
                    edit.putString(UContent.f639az, appVersionName);
                    edit.commit();
                } else if (!string.equals(appVersionName)) {
                    UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> onStartSessionInternal: upgrade version: " + string + "-> " + appVersionName);
                    int i = sharedPreferences.getInt("versioncode", 0);
                    String string2 = sharedPreferences.getString("pre_date", "");
                    String string3 = sharedPreferences.getString("pre_version", "");
                    String string4 = sharedPreferences.getString(UContent.f639az, "");
                    edit.putInt("versioncode", Integer.parseInt(UMUtils.getAppVersionCode(context)));
                    edit.putString(UContent.f639az, appVersionName);
                    edit.putString("vers_date", string2);
                    edit.putString("vers_pre_version", string3);
                    edit.putString("cur_version", string4);
                    edit.putInt("vers_code", i);
                    edit.putString("vers_name", string);
                    edit.commit();
                    if (f963l) {
                        f963l = false;
                    }
                    if (f961j) {
                        f961j = false;
                        mo599b(f960i, longValue, true);
                        mo597b(f960i, longValue);
                        return;
                    }
                    return;
                }
                if (f961j) {
                    f961j = false;
                    if (f963l) {
                        f963l = false;
                    }
                    f959h = m1016e(context);
                    MLog.m1351d("创建新会话: " + f959h);
                    UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "mSessionChanged flag has been set, Start new session: " + f959h);
                    return;
                }
                f959h = sharedPreferences.getString("session_id", null);
                edit.putLong(f956e, longValue);
                edit.putLong(f957f, 0);
                edit.commit();
                MLog.m1351d("延续上一个会话: " + f959h);
                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "Extend current session: " + f959h);
                if (f963l) {
                    f963l = false;
                    if (FieldManager.allow(UMConstant.f1600E)) {
                        Context context2 = f960i;
                        UMWorkDispatch.sendEventEx(context2, CoreProtocolImpl.C0113a.f897C, CoreProtocol.getInstance(context2), null, 0);
                    }
                }
                m1017f(context);
                CoreProtocolImpl.m921a(f960i).mo561a(false);
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    public void mo598b(Context context, Object obj) {
        long j;
        try {
            if (f960i == null) {
                f960i = UMGlobalContext.getAppContext(context);
            }
            if (obj == null) {
                j = System.currentTimeMillis();
            } else {
                j = ((Long) obj).longValue();
            }
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f960i);
            if (sharedPreferences != null) {
                f962k = sharedPreferences.getLong(f957f, 0);
                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "------>>> lastActivityEndTime: " + f962k);
                String string = sharedPreferences.getString(UContent.f639az, "");
                String appVersionName = UMUtils.getAppVersionName(f960i);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                if (edit != null) {
                    if (!TextUtils.isEmpty(string) && !string.equals(appVersionName)) {
                        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> requestNewInstantSessionIf: version upgrade");
                        edit.putLong(f952a, j);
                        edit.commit();
                        CoreProtocolImpl.m921a(f960i).mo560a((Object) null, true);
                        String c = SessionIdManager.m1047a().mo618c(f960i);
                        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> force generate new session: session id = " + c);
                        f961j = true;
                        mo591a(f960i, j, true);
                    } else if (SessionIdManager.m1047a().mo620e(f960i)) {
                        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> More then 30 sec from last session.");
                        f961j = true;
                        edit.putLong(f952a, j);
                        edit.commit();
                        mo591a(f960i, j, false);
                    } else {
                        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> less then 30 sec from last session, do nothing.");
                        f961j = false;
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public String mo591a(Context context, long j, boolean z) {
        String b = SessionIdManager.m1047a().mo616b(context);
        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> onInstantSessionInternal: current session id = " + b);
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("__e", j);
            JSONObject b2 = InternalAgent.m328a().mo233b();
            if (b2 != null && b2.length() > 0) {
                jSONObject.put("__sp", b2);
            }
            JSONObject c = InternalAgent.m328a().mo238c();
            if (c != null && c.length() > 0) {
                jSONObject.put("__pp", c);
            }
            UMStoreManager.m853a(context).mo519a(b, jSONObject, UMStoreManager.EnumC0107a.INSTANTSESSIONBEGIN);
            CoreProtocolImpl.m921a(context).mo560a(jSONObject, z);
        } catch (Throwable th) {
        }
        return b;
    }

    /* renamed from: c */
    public void mo602c(Context context, Object obj) {
        try {
            if (f960i == null && context != null) {
                f960i = context.getApplicationContext();
            }
            long longValue = ((Long) obj).longValue();
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            if (sharedPreferences != null) {
                if (sharedPreferences.getLong(f956e, 0) == 0) {
                    MLog.m1357e("onPause called before onResume");
                    return;
                }
                SharedPreferences.Editor edit = sharedPreferences.edit();
                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> onEndSessionInternal: write activity end time = " + longValue);
                edit.putLong(f957f, longValue);
                edit.putLong(f953b, longValue);
                edit.commit();
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: e */
    private String m1016e(Context context) {
        if (f960i == null && context != null) {
            f960i = context.getApplicationContext();
        }
        String d = SessionIdManager.m1047a().mo619d(f960i);
        try {
            m1017f(context);
            CoreProtocolImpl.m921a(f960i).mo570d((Object) null);
        } catch (Throwable th) {
        }
        return d;
    }

    /* renamed from: f */
    private void m1017f(Context context) {
        CoreProtocolImpl.m921a(context).mo565b(context);
        CoreProtocolImpl.m921a(context).mo569d();
    }

    /* renamed from: b */
    public boolean mo599b(Context context, long j, boolean z) {
        String a;
        long j2;
        boolean z2 = false;
        try {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            if (sharedPreferences == null || (a = SessionIdManager.m1047a().mo611a(f960i)) == null) {
                return false;
            }
            long j3 = sharedPreferences.getLong(f956e, 0);
            long j4 = sharedPreferences.getLong(f957f, 0);
            if (j3 > 0 && j4 == 0) {
                z2 = true;
                if (z) {
                    j2 = f962k;
                    if (j2 == 0) {
                        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "------>>> lastActivityEndTime = 0, In-app upgrade, use currentTime: = " + j);
                        j2 = j;
                    } else {
                        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "------>>> lastActivityEndTime != 0, app upgrade, use lastActivityEndTime: = " + f962k);
                    }
                    mo602c(f960i, Long.valueOf(j2));
                } else {
                    mo602c(f960i, Long.valueOf(j));
                    j2 = j;
                }
                try {
                    JSONObject jSONObject = new JSONObject();
                    if (z) {
                        jSONObject.put(UMDBConfig.C0098d.C0099a.f726g, j2);
                    } else {
                        jSONObject.put(UMDBConfig.C0098d.C0099a.f726g, j);
                    }
                    JSONObject b = InternalAgent.m328a().mo233b();
                    if (b != null && b.length() > 0) {
                        jSONObject.put("__sp", b);
                    }
                    JSONObject c = InternalAgent.m328a().mo238c();
                    if (c != null && c.length() > 0) {
                        jSONObject.put("__pp", c);
                    }
                    if (FieldManager.allow(UMConstant.f1600E)) {
                        UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>>*** foregroundCount = " + f964m);
                        jSONObject.put(UMDBConfig.C0098d.C0099a.f727h, f964m);
                        f964m = 0;
                    } else {
                        jSONObject.put(UMDBConfig.C0098d.C0099a.f727h, 0L);
                    }
                    UMStoreManager.m853a(context).mo519a(a, jSONObject, UMStoreManager.EnumC0107a.END);
                    CoreProtocolImpl.m921a(f960i).mo571e();
                } catch (Throwable th) {
                }
            }
            return z2;
        } catch (Throwable th2) {
        }
    }

    /* renamed from: b */
    public void mo597b(Context context, long j) {
        if (PreferenceWrapper.getDefault(context) != null) {
            try {
                CoreProtocolImpl.m921a(f960i).mo568c((Object) null);
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: b */
    public String mo596b() {
        return f959h;
    }

    /* renamed from: c */
    public String mo601c(Context context) {
        try {
            if (f959h == null) {
                return PreferenceWrapper.getDefault(context).getString("session_id", null);
            }
        } catch (Throwable th) {
        }
        return f959h;
    }

    /* renamed from: c */
    public String mo600c() {
        return mo601c(f960i);
    }

    @Override // com.umeng.analytics.pro.SessionIdManager.AbstractC0124a
    /* renamed from: a */
    public void mo595a(String str, String str2, long j, long j2, long j3) {
        m1012a(f960i, str2, j, j2, j3);
        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "saveSessionToDB: complete");
        if (AnalyticsConstants.SUB_PROCESS_EVENT) {
            Context context = f960i;
            UMWorkDispatch.sendEvent(context, UMProcessDBDatasSender.UM_PROCESS_EVENT_KEY, UMProcessDBDatasSender.getInstance(context), Long.valueOf(System.currentTimeMillis()));
        }
    }

    @Override // com.umeng.analytics.pro.SessionIdManager.AbstractC0124a
    /* renamed from: a */
    public void mo594a(String str, long j, long j2, long j3) {
        if (!TextUtils.isEmpty(str)) {
            m1013a(str, j);
        }
    }

    /* renamed from: a */
    private void m1012a(Context context, String str, long j, long j2, long j3) {
        if (TextUtils.isEmpty(f959h)) {
            f959h = SessionIdManager.m1047a().mo611a(f960i);
        }
        if (!TextUtils.isEmpty(str) && !str.equals(f959h)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(UMDBConfig.C0098d.C0099a.f726g, j2);
                jSONObject.put(UMDBConfig.C0098d.C0099a.f727h, j3);
                JSONObject b = InternalAgent.m328a().mo233b();
                if (b != null && b.length() > 0) {
                    jSONObject.put("__sp", b);
                }
                JSONObject c = InternalAgent.m328a().mo238c();
                if (c != null && c.length() > 0) {
                    jSONObject.put("__pp", c);
                }
                UMStoreManager.m853a(context).mo519a(f959h, jSONObject, UMStoreManager.EnumC0107a.END);
            } catch (Exception e) {
            }
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("__e", j);
                UMStoreManager.m853a(context).mo519a(str, jSONObject2, UMStoreManager.EnumC0107a.BEGIN);
                if (FieldManager.allow(UMConstant.f1600E)) {
                    f964m = j3;
                    m1015d(context);
                    Context context2 = f960i;
                    UMWorkDispatch.sendEventEx(context2, CoreProtocolImpl.C0113a.f897C, CoreProtocol.getInstance(context2), null, 0);
                }
            } catch (Exception e2) {
            }
            f959h = str;
        }
    }

    /* renamed from: a */
    private void m1013a(String str, long j) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f960i);
        if (sharedPreferences != null) {
            long j2 = sharedPreferences.getLong(f953b, 0);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("__ii", str);
                jSONObject.put("__e", j);
                jSONObject.put(UMDBConfig.C0098d.C0099a.f726g, j2);
                double[] location = AnalyticsConfig.getLocation();
                if (location != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(UContent.f581C, location[0]);
                    jSONObject2.put(UContent.f582D, location[1]);
                    jSONObject2.put("ts", System.currentTimeMillis());
                    jSONObject.put(UMDBConfig.C0098d.C0099a.f724e, jSONObject2);
                }
                Class<?> cls = Class.forName("android.net.TrafficStats");
                Class<?> cls2 = Integer.TYPE;
                Method method = cls.getMethod("getUidRxBytes", cls2);
                Method method2 = cls.getMethod("getUidTxBytes", cls2);
                int i = f960i.getApplicationInfo().uid;
                if (i != -1) {
                    long longValue = ((Long) method.invoke(null, Integer.valueOf(i))).longValue();
                    long longValue2 = ((Long) method2.invoke(null, Integer.valueOf(i))).longValue();
                    if (longValue > 0) {
                        if (longValue2 > 0) {
                            JSONObject jSONObject3 = new JSONObject();
                            jSONObject3.put(UContent.f586H, longValue);
                            jSONObject3.put(UContent.f585G, longValue2);
                            jSONObject.put(UMDBConfig.C0098d.C0099a.f723d, jSONObject3);
                        }
                    }
                    UMStoreManager.m853a(f960i).mo519a(str, jSONObject, UMStoreManager.EnumC0107a.NEWSESSION);
                    ViewPageTracker.m1031a(f960i);
                    AutoViewPageTracker.m900c(f960i);
                }
            } catch (Throwable th) {
            }
        }
    }
}
