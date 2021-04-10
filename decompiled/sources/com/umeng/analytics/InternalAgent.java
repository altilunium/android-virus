package com.umeng.analytics;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.filter.EventBlackList;
import com.umeng.analytics.filter.EventWhiteList;
import com.umeng.analytics.pro.ActivityManualPageTracker;
import com.umeng.analytics.pro.AutoViewPageTracker;
import com.umeng.analytics.pro.BackgroundMonitor;
import com.umeng.analytics.pro.BackgroundWatcher;
import com.umeng.analytics.pro.C0117o;
import com.umeng.analytics.pro.CoreProtocolImpl;
import com.umeng.analytics.pro.EventTracker;
import com.umeng.analytics.pro.OnAppCrashHandler;
import com.umeng.analytics.pro.SessionIdManager;
import com.umeng.analytics.pro.SessionTracker;
import com.umeng.analytics.pro.UContent;
import com.umeng.analytics.pro.UMLogAnalytics;
import com.umeng.analytics.pro.UMStoreManager;
import com.umeng.analytics.pro.ViewPageTracker;
import com.umeng.common.ISysListener;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMConstant;
import com.umeng.commonsdk.utils.UMUtils;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.umeng.analytics.b */
public class InternalAgent implements BackgroundWatcher, OnAppCrashHandler {

    /* renamed from: A */
    private static final String f202A = "umsp_2";

    /* renamed from: B */
    private static final String f203B = "umsp_3";

    /* renamed from: C */
    private static final String f204C = "umsp_4";

    /* renamed from: D */
    private static final String f205D = "umsp_5";

    /* renamed from: a */
    private static Context f206a = null;

    /* renamed from: h */
    private static final String f207h = "sp_uapp";

    /* renamed from: i */
    private static final String f208i = "prepp_uapp";

    /* renamed from: o */
    private static final int f209o = 128;

    /* renamed from: p */
    private static final int f210p = 256;

    /* renamed from: q */
    private static String f211q = "";

    /* renamed from: r */
    private static String f212r = "";

    /* renamed from: s */
    private static final String f213s = "ekv_bl";

    /* renamed from: t */
    private static final String f214t = "ekv_bl_ver";

    /* renamed from: v */
    private static final String f215v = "ekv_wl";

    /* renamed from: w */
    private static final String f216w = "ekv_wl_ver";

    /* renamed from: z */
    private static final String f217z = "umsp_1";

    /* renamed from: b */
    private ISysListener f218b;

    /* renamed from: c */
    private C0117o f219c;

    /* renamed from: d */
    private ViewPageTracker f220d;

    /* renamed from: e */
    private ActivityManualPageTracker f221e;

    /* renamed from: f */
    private SessionTracker f222f;

    /* renamed from: g */
    private AutoViewPageTracker f223g;

    /* renamed from: j */
    private boolean f224j;

    /* renamed from: k */
    private volatile JSONObject f225k;

    /* renamed from: l */
    private volatile JSONObject f226l;

    /* renamed from: m */
    private volatile JSONObject f227m;

    /* renamed from: n */
    private boolean f228n;

    /* renamed from: u */
    private EventBlackList f229u;

    /* renamed from: x */
    private EventWhiteList f230x;

    /* renamed from: y */
    private BackgroundMonitor f231y;

    static {
        f206a = null;
        Context appContext = UMGlobalContext.getAppContext();
        if (appContext != null) {
            f206a = appContext.getApplicationContext();
        }
    }

    private InternalAgent() {
        this.f219c = new C0117o();
        this.f220d = new ViewPageTracker();
        this.f221e = new ActivityManualPageTracker();
        this.f222f = SessionTracker.m1011a();
        this.f223g = null;
        this.f224j = false;
        this.f225k = null;
        this.f226l = null;
        this.f227m = null;
        this.f228n = false;
        this.f229u = null;
        this.f230x = null;
        this.f231y = null;
        this.f219c.mo582a(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.analytics.b$a */
    /* compiled from: InternalAgent */
    public class C0065a {

        /* renamed from: a */
        private static final InternalAgent f232a = new InternalAgent();

        private C0065a() {
        }
    }

    /* renamed from: a */
    public static InternalAgent m328a() {
        return C0065a.f232a;
    }

    /* renamed from: a */
    public void mo213a(Context context) {
        if (context != null) {
            try {
                if (f206a == null) {
                    f206a = context.getApplicationContext();
                }
                if (this.f229u == null) {
                    EventBlackList aVar = new EventBlackList("ekv_bl", "ekv_bl_ver");
                    this.f229u = aVar;
                    aVar.register(f206a);
                }
                if (this.f230x == null) {
                    EventWhiteList bVar = new EventWhiteList("ekv_wl", "ekv_wl_ver");
                    this.f230x = bVar;
                    bVar.register(f206a);
                }
                if (UMUtils.isMainProgress(f206a)) {
                    if (!this.f224j) {
                        this.f224j = true;
                        m333i(f206a);
                    }
                    synchronized (this) {
                        if (!this.f228n) {
                            AutoViewPageTracker a = AutoViewPageTracker.m890a(context);
                            this.f223g = a;
                            if (a.mo535a()) {
                                this.f228n = true;
                            }
                            this.f231y = BackgroundMonitor.m912a();
                            try {
                                BackgroundMonitor.m913a(context);
                                this.f231y.mo546a(this);
                            } catch (Throwable th) {
                            }
                        }
                    }
                    if (UMConfigure.isDebugLog()) {
                        UMLog.mutlInfo(UMLogAnalytics.f773B, 3, "", null, null);
                    }
                    UMWorkDispatch.registerConnStateObserver(CoreProtocol.getInstance(f206a));
                }
            } catch (Throwable th2) {
            }
        }
    }

    /* renamed from: i */
    private void m333i(Context context) {
        if (context == null) {
            try {
                MLog.m1357e("unexpected null context in getNativeSuperProperties");
            } catch (Throwable th) {
            }
        } else {
            if (f206a == null) {
                f206a = context.getApplicationContext();
            }
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            if (this.f225k == null) {
                this.f225k = new JSONObject();
            }
            if (this.f226l == null) {
                this.f226l = new JSONObject();
            }
            String string = sharedPreferences.getString(f208i, null);
            if (!TextUtils.isEmpty(string)) {
                try {
                    this.f227m = new JSONObject(string);
                } catch (JSONException e) {
                }
            }
            if (this.f227m == null) {
                this.f227m = new JSONObject();
            }
        }
    }

    /* renamed from: b */
    public JSONObject mo233b() {
        return this.f225k;
    }

    /* renamed from: c */
    public JSONObject mo238c() {
        return this.f227m;
    }

    /* renamed from: d */
    public JSONObject mo241d() {
        return this.f226l;
    }

    /* renamed from: e */
    public void mo246e() {
        this.f226l = null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo228a(String str) {
        if (!UMUtils.isMainProgress(f206a)) {
            MLog.m1357e("onPageStart can not be called in child process");
            return;
        }
        try {
            if (AutoViewPageTracker.f843b != MobclickAgent.PageMode.LEGACY_AUTO) {
                this.f220d.mo603a(str);
            }
        } catch (Throwable th) {
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo237b(String str) {
        if (!UMUtils.isMainProgress(f206a)) {
            MLog.m1357e("onPageEnd can not be called in child process");
            return;
        }
        try {
            if (AutoViewPageTracker.f843b != MobclickAgent.PageMode.LEGACY_AUTO) {
                this.f220d.mo605b(str);
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public void mo226a(ISysListener iSysListener) {
        if (!UMUtils.isMainProgress(f206a)) {
            MLog.m1357e("setSysListener can not be called in child process");
        } else {
            this.f218b = iSysListener;
        }
    }

    /* renamed from: a */
    public void mo214a(Context context, int i) {
        if (context == null) {
            MLog.m1357e("unexpected null context in setVerticalType");
            return;
        }
        if (f206a == null) {
            f206a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f206a)) {
            MLog.m1357e("setVerticalType can not be called in child process");
            return;
        }
        if (!this.f224j || !this.f228n) {
            mo213a(f206a);
        }
        AnalyticsConfig.m324a(f206a, i);
    }

    /* renamed from: f */
    public String mo247f() {
        if (UMUtils.isMainProgress(f206a)) {
            return f211q;
        }
        MLog.m1357e("getOnResumedActivityName can not be called in child process");
        return null;
    }

    /* renamed from: g */
    public String mo250g() {
        if (UMUtils.isMainProgress(f206a)) {
            return f212r;
        }
        MLog.m1357e("getOnPausedActivityName can not be called in child process");
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo234b(Context context) {
        if (context == null) {
            MLog.m1357e("unexpected null context in onResume");
        } else if (AutoViewPageTracker.f843b != MobclickAgent.PageMode.AUTO) {
            if (f206a == null) {
                f206a = context.getApplicationContext();
            }
            if (!UMUtils.isMainProgress(f206a)) {
                MLog.m1357e("onResume can not be called in child process");
                return;
            }
            if (UMConfigure.isDebugLog() && !(context instanceof Activity)) {
                UMLog.m1136aq(UMLogAnalytics.f830o, 2, "\\|");
            }
            try {
                if (!this.f224j || !this.f228n) {
                    mo213a(context);
                }
                if (AutoViewPageTracker.f843b != MobclickAgent.PageMode.LEGACY_MANUAL) {
                    this.f221e.mo603a(context.getClass().getName());
                }
                mo253h();
                if (UMConfigure.isDebugLog() && (context instanceof Activity)) {
                    f211q = context.getClass().getName();
                }
            } catch (Throwable th) {
                MLog.m1359e("Exception occurred in Mobclick.onResume(). ", th);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo239c(Context context) {
        if (context == null) {
            UMLog.m1136aq(UMLogAnalytics.f831p, 0, "\\|");
        } else if (AutoViewPageTracker.f843b != MobclickAgent.PageMode.AUTO) {
            if (f206a == null) {
                f206a = context.getApplicationContext();
            }
            if (!UMUtils.isMainProgress(f206a)) {
                MLog.m1357e("onPause can not be called in child process");
                return;
            }
            if (UMConfigure.isDebugLog() && !(context instanceof Activity)) {
                UMLog.m1136aq(UMLogAnalytics.f832q, 2, "\\|");
            }
            try {
                if (!this.f224j || !this.f228n) {
                    mo213a(context);
                }
                if (AutoViewPageTracker.f843b != MobclickAgent.PageMode.LEGACY_MANUAL) {
                    this.f221e.mo605b(context.getClass().getName());
                }
                mo254i();
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.m1359e("Exception occurred in Mobclick.onRause(). ", th);
                }
            }
            if (UMConfigure.isDebugLog() && (context instanceof Activity)) {
                f212r = context.getClass().getName();
            }
        }
    }

    /* renamed from: a */
    public void mo219a(Context context, String str, HashMap hashMap) {
        if (context != null) {
            try {
                if (f206a == null) {
                    f206a = context.getApplicationContext();
                }
                if (!UMUtils.isMainProgress(f206a)) {
                    MLog.m1357e("onGKVEvent can not be called in child process");
                    return;
                }
                if (!this.f224j || !this.f228n) {
                    mo213a(f206a);
                }
                String str2 = "";
                if (this.f225k == null) {
                    this.f225k = new JSONObject();
                } else {
                    str2 = this.f225k.toString();
                }
                EventTracker.m993a(f206a).mo589a(str, hashMap, str2);
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.m1361e(th);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo216a(Context context, String str) {
        if (context == null) {
            UMLog.m1136aq(UMLogAnalytics.f838w, 0, "\\|");
            return;
        }
        if (f206a == null) {
            f206a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f206a)) {
            MLog.m1357e("reportError can not be called in child process");
        } else if (!TextUtils.isEmpty(str)) {
            try {
                if (!this.f224j || !this.f228n) {
                    mo213a(f206a);
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ts", System.currentTimeMillis());
                jSONObject.put(UContent.f595Q, 2);
                jSONObject.put(UContent.f596R, str);
                jSONObject.put("__ii", this.f222f.mo600c());
                Context context2 = f206a;
                UMWorkDispatch.sendEvent(context2, CoreProtocolImpl.C0113a.f907j, CoreProtocol.getInstance(context2), jSONObject);
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.m1361e(th);
                }
            }
        } else if (UMConfigure.isDebugLog()) {
            UMLog.m1136aq(UMLogAnalytics.f839x, 0, "\\|");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo222a(Context context, Throwable th) {
        if (context == null || th == null) {
            UMLog.m1136aq(UMLogAnalytics.f840y, 0, "\\|");
            return;
        }
        if (f206a == null) {
            f206a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f206a)) {
            MLog.m1357e("reportError can not be called in child process");
            return;
        }
        try {
            if (!this.f224j || !this.f228n) {
                mo213a(f206a);
            }
            mo216a(f206a, DataHelper.convertExceptionToString(th));
        } catch (Exception e) {
            if (MLog.DEBUG) {
                MLog.m1361e(e);
            }
        }
    }

    /* renamed from: h */
    public void mo253h() {
        try {
            Context context = f206a;
            if (context != null) {
                if (!UMUtils.isMainProgress(context)) {
                    MLog.m1357e("onStartSessionInternal can not be called in child process");
                    return;
                }
                if (UMConfigure.isDebugLog() && !UMConfigure.getInitStatus()) {
                    UMLog.mutlInfo(UMLogAnalytics.f779H, 3, "", null, null);
                }
                long currentTimeMillis = System.currentTimeMillis();
                Context context2 = f206a;
                UMWorkDispatch.sendEvent(context2, CoreProtocolImpl.C0113a.f908k, CoreProtocol.getInstance(context2), Long.valueOf(currentTimeMillis));
                Context context3 = f206a;
                UMWorkDispatch.sendEvent(context3, CoreProtocolImpl.C0113a.f904g, CoreProtocol.getInstance(context3), Long.valueOf(currentTimeMillis));
            }
            ISysListener iSysListener = this.f218b;
            if (iSysListener != null) {
                iSysListener.onAppResume();
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: i */
    public void mo254i() {
        try {
            Context context = f206a;
            if (context != null) {
                if (!UMUtils.isMainProgress(context)) {
                    MLog.m1357e("onEndSessionInternal can not be called in child process");
                    return;
                }
                Context context2 = f206a;
                UMWorkDispatch.sendEvent(context2, CoreProtocolImpl.C0113a.f905h, CoreProtocol.getInstance(context2), Long.valueOf(System.currentTimeMillis()));
                Context context3 = f206a;
                UMWorkDispatch.sendEvent(context3, CoreProtocolImpl.C0113a.f901d, CoreProtocol.getInstance(context3), null);
                Context context4 = f206a;
                UMWorkDispatch.sendEvent(context4, CoreProtocolImpl.C0113a.f900c, CoreProtocol.getInstance(context4), null);
                Context context5 = f206a;
                UMWorkDispatch.sendEvent(context5, CoreProtocolImpl.C0113a.f906i, CoreProtocol.getInstance(context5), null);
            }
        } catch (Throwable th) {
        }
        ISysListener iSysListener = this.f218b;
        if (iSysListener != null) {
            iSysListener.onAppPause();
        }
    }

    /* renamed from: b */
    public void mo235b(Context context, String str) {
        if (context == null) {
            try {
                UMLog.m1136aq(UMLogAnalytics.f785N, 0, "\\|");
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.m1361e(th);
                }
            }
        } else {
            if (f206a == null) {
                f206a = context.getApplicationContext();
            }
            if (!UMUtils.isMainProgress(f206a)) {
                MLog.m1357e("onDeepLinkReceived can not be called in child process");
                return;
            }
            if (!this.f224j || !this.f228n) {
                mo213a(f206a);
            }
            if (!TextUtils.isEmpty(str)) {
                HashMap hashMap = new HashMap();
                hashMap.put(UContent.f610aE, str);
                m329a(f206a, UContent.f609aD, (Map) hashMap, -1L, false);
                return;
            }
            UMLog.m1136aq(UMLogAnalytics.f786O, 0, "\\|");
        }
    }

    /* renamed from: c */
    private boolean m332c(String str) {
        if (this.f229u.enabled() && this.f229u.matchHit(str)) {
            return true;
        }
        if (!this.f230x.enabled()) {
            return false;
        }
        if (!this.f230x.matchHit(str)) {
            return true;
        }
        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> white list match! id = " + str);
        return false;
    }

    /* renamed from: a */
    public void mo218a(Context context, String str, String str2, long j, int i) {
        String str3;
        if (context != null) {
            try {
                if (f206a == null) {
                    f206a = context.getApplicationContext();
                }
                if (!this.f224j || !this.f228n) {
                    mo213a(f206a);
                }
                if (m332c(str)) {
                    UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> filter ekv [" + str + "].");
                    return;
                }
                if (this.f225k == null) {
                    this.f225k = new JSONObject();
                    str3 = "";
                } else {
                    str3 = this.f225k.toString();
                }
                EventTracker.m993a(f206a).mo587a(str, str2, j, i, str3);
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.m1361e(th);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo221a(Context context, String str, Map map, long j) {
        try {
            if (TextUtils.isEmpty(str)) {
                UMLog.m1136aq(UMLogAnalytics.f818c, 0, "\\|");
            } else if (Arrays.asList(UContent.f611aF).contains(str)) {
                UMLog.m1136aq(UMLogAnalytics.f817b, 0, "\\|");
            } else if (map.isEmpty()) {
                UMLog.m1136aq(UMLogAnalytics.f819d, 0, "\\|");
            } else {
                for (Map.Entry entry : map.entrySet()) {
                    if (Arrays.asList(UContent.f611aF).contains(entry.getKey())) {
                        UMLog.m1136aq(UMLogAnalytics.f820e, 0, "\\|");
                        return;
                    }
                }
                m329a(context, str, map, j, false);
            }
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.m1361e(th);
            }
        }
    }

    /* renamed from: a */
    public void mo220a(Context context, String str, Map map) {
        m329a(context, str, map, -1L, true);
    }

    /* renamed from: a */
    private void m329a(Context context, String str, Map map, long j, boolean z) {
        String str2;
        if (context == null) {
            try {
                MLog.m1357e("context is null in onEventNoCheck, please check!");
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.m1361e(th);
                }
            }
        } else {
            if (f206a == null) {
                f206a = context.getApplicationContext();
            }
            if (!this.f224j || !this.f228n) {
                mo213a(f206a);
            }
            if (m332c(str)) {
                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> filter ekv [" + str + "].");
                return;
            }
            if (this.f225k == null) {
                this.f225k = new JSONObject();
                str2 = "";
            } else {
                str2 = this.f225k.toString();
            }
            EventTracker.m993a(f206a).mo588a(str, map, j, str2, z);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo242d(Context context) {
        if (context != null) {
            try {
                if (f206a == null) {
                    f206a = context.getApplicationContext();
                }
                if (!UMUtils.isMainProgress(f206a)) {
                    MLog.m1357e("onKillProcess can not be called in child process");
                    return;
                }
                AutoViewPageTracker kVar = this.f223g;
                if (kVar != null) {
                    kVar.mo538c();
                }
                AutoViewPageTracker.m892a(context, "onKillProcess");
                ActivityManualPageTracker jVar = this.f221e;
                if (jVar != null) {
                    jVar.mo604b();
                }
                ViewPageTracker uVar = this.f220d;
                if (uVar != null) {
                    uVar.mo604b();
                }
                Context context2 = f206a;
                if (context2 != null) {
                    SessionTracker tVar = this.f222f;
                    if (tVar != null) {
                        tVar.mo602c(context2, Long.valueOf(System.currentTimeMillis()));
                    }
                    CoreProtocolImpl.m921a(f206a).mo569d();
                    ViewPageTracker.m1031a(f206a);
                    if (AutoViewPageTracker.f843b == MobclickAgent.PageMode.AUTO) {
                        AutoViewPageTracker.m900c(f206a);
                    }
                    PreferenceWrapper.getDefault(f206a).edit().commit();
                }
            } catch (Throwable th) {
            }
        }
    }

    @Override // com.umeng.analytics.pro.OnAppCrashHandler
    /* renamed from: a */
    public void mo230a(Throwable th) {
        try {
            Context context = f206a;
            if (context != null) {
                if (!UMUtils.isMainProgress(context)) {
                    MLog.m1357e("onAppCrash can not be called in child process");
                } else if (AnalyticsConfig.enable) {
                    ViewPageTracker uVar = this.f220d;
                    if (uVar != null) {
                        uVar.mo604b();
                    }
                    AutoViewPageTracker.m892a(f206a, "onAppCrash");
                    ActivityManualPageTracker jVar = this.f221e;
                    if (jVar != null) {
                        jVar.mo604b();
                    }
                    AutoViewPageTracker kVar = this.f223g;
                    if (kVar != null) {
                        kVar.mo538c();
                    }
                    SessionTracker tVar = this.f222f;
                    if (tVar != null) {
                        tVar.mo602c(f206a, Long.valueOf(System.currentTimeMillis()));
                    }
                    if (th != null) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("ts", System.currentTimeMillis());
                        jSONObject.put(UContent.f595Q, 1);
                        jSONObject.put(UContent.f596R, DataHelper.convertExceptionToString(th));
                        UMStoreManager.m853a(f206a).mo518a(this.f222f.mo600c(), jSONObject.toString(), 1);
                    }
                    CoreProtocolImpl.m921a(f206a).mo569d();
                    ViewPageTracker.m1031a(f206a);
                    if (AutoViewPageTracker.f843b == MobclickAgent.PageMode.AUTO) {
                        AutoViewPageTracker.m900c(f206a);
                    }
                    PreferenceWrapper.getDefault(f206a).edit().commit();
                }
            }
        } catch (Exception e) {
            if (MLog.DEBUG) {
                MLog.m1359e("Exception in onAppCrash", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo229a(String str, String str2) {
        try {
            Context context = f206a;
            if (context != null) {
                if (!UMUtils.isMainProgress(context)) {
                    MLog.m1357e("onProfileSignIn can not be called in child process");
                    return;
                }
                long currentTimeMillis = System.currentTimeMillis();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(UContent.f591M, str);
                jSONObject.put("uid", str2);
                jSONObject.put("ts", currentTimeMillis);
                Context context2 = f206a;
                UMWorkDispatch.sendEvent(context2, CoreProtocolImpl.C0113a.f902e, CoreProtocol.getInstance(context2), jSONObject);
                Context context3 = f206a;
                UMWorkDispatch.sendEvent(context3, CoreProtocolImpl.C0113a.f912o, CoreProtocol.getInstance(context3), jSONObject);
            }
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.m1359e(" Excepthon  in  onProfileSignIn", th);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: j */
    public void mo255j() {
        try {
            Context context = f206a;
            if (context != null) {
                if (!UMUtils.isMainProgress(context)) {
                    MLog.m1357e("onProfileSignOff can not be called in child process");
                    return;
                }
                long currentTimeMillis = System.currentTimeMillis();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ts", currentTimeMillis);
                Context context2 = f206a;
                UMWorkDispatch.sendEvent(context2, CoreProtocolImpl.C0113a.f903f, CoreProtocol.getInstance(context2), jSONObject);
                Context context3 = f206a;
                UMWorkDispatch.sendEvent(context3, CoreProtocolImpl.C0113a.f912o, CoreProtocol.getInstance(context3), jSONObject);
            }
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.m1359e(" Excepthon  in  onProfileSignOff", th);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo232a(boolean z) {
        Context context = f206a;
        if (context != null) {
            if (!UMUtils.isMainProgress(context)) {
                MLog.m1357e("setCatchUncaughtExceptions can not be called in child process");
            } else if (!AnalyticsConfig.CHANGE_CATCH_EXCEPTION_NOTALLOW) {
                AnalyticsConfig.CATCH_EXCEPTION = z;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo231a(GL10 gl10) {
        String[] gpu = UMUtils.getGPU(gl10);
        if (gpu.length == 2) {
            AnalyticsConfig.GPU_VENDER = gpu[0];
            AnalyticsConfig.GPU_RENDERER = gpu[1];
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo225a(MobclickAgent.PageMode pageMode) {
        Context context = f206a;
        if (context != null) {
            if (!UMUtils.isMainProgress(context)) {
                MLog.m1357e("setPageCollectionMode can not be called in child process");
            } else {
                AutoViewPageTracker.f843b = pageMode;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo211a(double d, double d2) {
        Context context = f206a;
        if (context != null) {
            if (!UMUtils.isMainProgress(context)) {
                MLog.m1357e("setLocation can not be called in child process");
                return;
            }
            if (AnalyticsConfig.f182a == null) {
                AnalyticsConfig.f182a = new double[2];
            }
            double[] dArr = AnalyticsConfig.f182a;
            dArr[0] = d;
            dArr[1] = d2;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo215a(Context context, MobclickAgent.EScenarioType eScenarioType) {
        if (context == null) {
            MLog.m1357e("unexpected null context in setScenarioType");
            return;
        }
        if (f206a == null) {
            f206a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f206a)) {
            MLog.m1357e("setScenarioType can not be called in child process");
            return;
        }
        if (eScenarioType != null) {
            mo214a(f206a, eScenarioType.toValue());
        }
        if (!this.f224j || !this.f228n) {
            mo213a(f206a);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo240c(Context context, String str) {
        if (context == null) {
            UMLog.m1136aq(UMLogAnalytics.f841z, 0, "\\|");
            return;
        }
        if (f206a == null) {
            f206a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f206a)) {
            MLog.m1357e("setSecret can not be called in child process");
            return;
        }
        if (!this.f224j || !this.f228n) {
            mo213a(f206a);
        }
        AnalyticsConfig.m325a(f206a, str);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo212a(long j) {
        Context context = f206a;
        if (context != null) {
            if (!UMUtils.isMainProgress(context)) {
                MLog.m1357e("setSessionContinueMillis can not be called in child process");
                return;
            }
            AnalyticsConfig.kContinueSessionMillis = j;
            SessionIdManager.m1047a().mo613a(AnalyticsConfig.kContinueSessionMillis);
        }
    }

    /* renamed from: a */
    public synchronized void mo217a(Context context, String str, Object obj) {
        int i = 0;
        if (context == null) {
            try {
                UMLog.m1136aq(UMLogAnalytics.f804af, 0, "\\|");
            } catch (Throwable th) {
            }
        } else {
            if (f206a == null) {
                f206a = context.getApplicationContext();
            }
            if (!UMUtils.isMainProgress(f206a)) {
                MLog.m1357e("registerSuperProperty can not be called in child process");
                return;
            }
            if (!this.f224j || !this.f228n) {
                mo213a(f206a);
            }
            if (TextUtils.isEmpty(str) || obj == null) {
                UMLog.m1136aq(UMLogAnalytics.f805ag, 0, "\\|");
            } else if (!str.equals(f217z) && !str.equals(f202A) && !str.equals(f203B) && !str.equals(f204C) && !str.equals(f205D)) {
                MLog.m1357e("property name is " + str + ", please check key, must be correct!");
            } else if (!(obj instanceof String) || HelperUtils.checkStrLen(obj.toString(), f210p)) {
                try {
                    if (this.f225k == null) {
                        this.f225k = new JSONObject();
                    }
                    if (obj.getClass().isArray()) {
                        if (obj instanceof String[]) {
                            String[] strArr = (String[]) obj;
                            if (strArr.length > 10) {
                                MLog.m1357e("please check value, size is " + strArr.length + ", overstep 10!");
                                return;
                            }
                            JSONArray jSONArray = new JSONArray();
                            while (i < strArr.length) {
                                if (strArr[i] == null || !HelperUtils.checkStrLen(strArr[i], f210p)) {
                                    MLog.m1357e("please check value, length is " + strArr[i].length() + ", overlength 256!");
                                    return;
                                }
                                jSONArray.put(strArr[i]);
                                i++;
                            }
                            this.f225k.put(str, jSONArray);
                        } else if (obj instanceof long[]) {
                            long[] jArr = (long[]) obj;
                            if (jArr.length > 10) {
                                MLog.m1357e("please check value, size is " + jArr.length + ", overstep 10!");
                                return;
                            }
                            JSONArray jSONArray2 = new JSONArray();
                            while (i < jArr.length) {
                                jSONArray2.put(jArr[i]);
                                i++;
                            }
                            this.f225k.put(str, jSONArray2);
                        } else if (obj instanceof int[]) {
                            int[] iArr = (int[]) obj;
                            if (iArr.length > 10) {
                                MLog.m1357e("please check value, size is " + iArr.length + ", overstep 10!");
                                return;
                            }
                            JSONArray jSONArray3 = new JSONArray();
                            while (i < iArr.length) {
                                jSONArray3.put(iArr[i]);
                                i++;
                            }
                            this.f225k.put(str, jSONArray3);
                        } else if (obj instanceof float[]) {
                            float[] fArr = (float[]) obj;
                            if (fArr.length > 10) {
                                MLog.m1357e("please check value, size is " + fArr.length + ", overstep 10!");
                                return;
                            }
                            JSONArray jSONArray4 = new JSONArray();
                            while (i < fArr.length) {
                                jSONArray4.put((double) fArr[i]);
                                i++;
                            }
                            this.f225k.put(str, jSONArray4);
                        } else if (obj instanceof double[]) {
                            double[] dArr = (double[]) obj;
                            if (dArr.length > 10) {
                                MLog.m1357e("please check value, size is " + dArr.length + ", overstep 10!");
                                return;
                            }
                            JSONArray jSONArray5 = new JSONArray();
                            while (i < dArr.length) {
                                jSONArray5.put(dArr[i]);
                                i++;
                            }
                            this.f225k.put(str, jSONArray5);
                        } else if (obj instanceof short[]) {
                            short[] sArr = (short[]) obj;
                            if (sArr.length > 10) {
                                MLog.m1357e("please check value, size is " + sArr.length + ", overstep 10!");
                                return;
                            }
                            JSONArray jSONArray6 = new JSONArray();
                            while (i < sArr.length) {
                                jSONArray6.put((int) sArr[i]);
                                i++;
                            }
                            this.f225k.put(str, jSONArray6);
                        } else {
                            MLog.m1357e("please check value, illegal type!");
                            return;
                        }
                    } else if ((obj instanceof String) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof Short)) {
                        this.f225k.put(str, obj);
                    } else {
                        MLog.m1357e("please check value, illegal type!");
                        return;
                    }
                } catch (Throwable th2) {
                }
                Context context2 = f206a;
                UMWorkDispatch.sendEvent(context2, CoreProtocolImpl.C0113a.f915r, CoreProtocol.getInstance(context2), this.f225k.toString());
            } else {
                MLog.m1357e("property value is " + obj + ", please check value, lawless!");
            }
        }
    }

    /* renamed from: a */
    private void m330a(String str, Object obj) {
        try {
            if (this.f225k == null) {
                this.f225k = new JSONObject();
            }
            int i = 0;
            if (!obj.getClass().isArray()) {
                if (obj instanceof List) {
                    List list = (List) obj;
                    JSONArray jSONArray = new JSONArray();
                    while (i < list.size()) {
                        Object obj2 = list.get(i);
                        if ((obj2 instanceof String) || (obj2 instanceof Long) || (obj2 instanceof Integer) || (obj2 instanceof Float) || (obj2 instanceof Double) || (obj2 instanceof Short)) {
                            jSONArray.put(list.get(i));
                        }
                        i++;
                    }
                    this.f225k.put(str, jSONArray);
                } else if ((obj instanceof String) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof Short)) {
                    this.f225k.put(str, obj);
                }
            } else if (obj instanceof String[]) {
                String[] strArr = (String[]) obj;
                if (strArr.length <= 10) {
                    JSONArray jSONArray2 = new JSONArray();
                    while (i < strArr.length) {
                        if (strArr[i] != null && !HelperUtils.checkStrLen(strArr[i], f210p)) {
                            jSONArray2.put(strArr[i]);
                        }
                        i++;
                    }
                    this.f225k.put(str, jSONArray2);
                }
            } else if (obj instanceof long[]) {
                long[] jArr = (long[]) obj;
                JSONArray jSONArray3 = new JSONArray();
                while (i < jArr.length) {
                    jSONArray3.put(jArr[i]);
                    i++;
                }
                this.f225k.put(str, jSONArray3);
            } else if (obj instanceof int[]) {
                int[] iArr = (int[]) obj;
                JSONArray jSONArray4 = new JSONArray();
                while (i < iArr.length) {
                    jSONArray4.put(iArr[i]);
                    i++;
                }
                this.f225k.put(str, jSONArray4);
            } else if (obj instanceof float[]) {
                float[] fArr = (float[]) obj;
                JSONArray jSONArray5 = new JSONArray();
                while (i < fArr.length) {
                    jSONArray5.put((double) fArr[i]);
                    i++;
                }
                this.f225k.put(str, jSONArray5);
            } else {
                if (obj instanceof double[]) {
                    double[] dArr = (double[]) obj;
                    JSONArray jSONArray6 = new JSONArray();
                    while (i < dArr.length) {
                        jSONArray6.put(dArr[i]);
                        i++;
                    }
                    this.f225k.put(str, jSONArray6);
                } else if (obj instanceof short[]) {
                    short[] sArr = (short[]) obj;
                    JSONArray jSONArray7 = new JSONArray();
                    while (i < sArr.length) {
                        jSONArray7.put((int) sArr[i]);
                        i++;
                    }
                    this.f225k.put(str, jSONArray7);
                }
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public synchronized void mo227a(Object obj) {
        try {
            Context context = f206a;
            if (context != null) {
                if (!UMUtils.isMainProgress(context)) {
                    MLog.m1357e("registerSuperPropertyByCoreProtocol can not be called in child process");
                    return;
                }
                if (obj != null) {
                    String str = (String) obj;
                    SharedPreferences.Editor edit = PreferenceWrapper.getDefault(f206a).edit();
                    if (edit != null && !TextUtils.isEmpty(str)) {
                        edit.putString(f207h, this.f225k.toString()).commit();
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: d */
    public synchronized void mo243d(Context context, String str) {
        if (context == null) {
            try {
                UMLog.m1136aq(UMLogAnalytics.f806ah, 0, "\\|");
                return;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            if (f206a == null) {
                f206a = context.getApplicationContext();
            }
            if (!UMUtils.isMainProgress(f206a)) {
                MLog.m1357e("unregisterSuperProperty can not be called in child process");
                return;
            }
            if (!this.f224j || !this.f228n) {
                mo213a(f206a);
            }
            if (TextUtils.isEmpty(str)) {
                UMLog.m1136aq(UMLogAnalytics.f805ag, 0, "\\|");
                return;
            } else if (str.equals(f217z) || str.equals(f202A) || str.equals(f203B) || str.equals(f204C) || str.equals(f205D)) {
                if (this.f225k == null) {
                    this.f225k = new JSONObject();
                }
                if (this.f225k.has(str)) {
                    this.f225k.remove(str);
                    Context context2 = f206a;
                    UMWorkDispatch.sendEvent(context2, CoreProtocolImpl.C0113a.f917t, CoreProtocol.getInstance(context2), str);
                }
                return;
            } else {
                MLog.m1357e("please check key or value, must be correct!");
                return;
            }
        }
    }

    /* renamed from: k */
    public synchronized void mo256k() {
        try {
            Context context = f206a;
            if (context != null) {
                if (!UMUtils.isMainProgress(context)) {
                    MLog.m1357e("unregisterSuperPropertyByCoreProtocol can not be called in child process");
                    return;
                }
                if (this.f225k != null) {
                    SharedPreferences.Editor edit = PreferenceWrapper.getDefault(f206a).edit();
                    edit.putString(f207h, this.f225k.toString());
                    edit.commit();
                } else {
                    this.f225k = new JSONObject();
                }
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: e */
    public synchronized Object mo244e(Context context, String str) {
        if (context == null) {
            try {
                UMLog.m1136aq(UMLogAnalytics.f807ai, 0, "\\|");
                return null;
            } catch (Throwable th) {
            }
        } else {
            if (f206a == null) {
                f206a = context.getApplicationContext();
            }
            if (!UMUtils.isMainProgress(f206a)) {
                MLog.m1357e("getSuperProperty can not be called in child process");
                return null;
            } else if (TextUtils.isEmpty(str)) {
                UMLog.m1136aq(UMLogAnalytics.f805ag, 0, "\\|");
                return null;
            } else if (str.equals(f217z) || str.equals(f202A) || str.equals(f203B) || str.equals(f204C) || str.equals(f205D)) {
                if (this.f225k == null) {
                    this.f225k = new JSONObject();
                } else if (this.f225k.has(str)) {
                    return this.f225k.opt(str);
                }
                return null;
            } else {
                MLog.m1357e("please check key or value, must be correct!");
                return null;
            }
        }
    }

    /* renamed from: e */
    public synchronized String mo245e(Context context) {
        if (context == null) {
            try {
                UMLog.m1136aq(UMLogAnalytics.f807ai, 0, "\\|");
                return null;
            } catch (Throwable th) {
            }
        } else {
            if (f206a == null) {
                f206a = context.getApplicationContext();
            }
            if (!UMUtils.isMainProgress(f206a)) {
                MLog.m1357e("getSuperProperties can not be called in child process");
                return null;
            } else if (this.f225k != null) {
                return this.f225k.toString();
            } else {
                this.f225k = new JSONObject();
                return null;
            }
        }
    }

    /* renamed from: l */
    public synchronized JSONObject mo257l() {
        try {
            Context context = f206a;
            if (context == null) {
                return null;
            }
            if (!UMUtils.isMainProgress(context)) {
                MLog.m1357e("getSuperPropertiesJSONObject can not be called in child process");
                return null;
            }
            if (this.f225k == null) {
                this.f225k = new JSONObject();
            }
            return this.f225k;
        } catch (Throwable th) {
        }
    }

    /* renamed from: f */
    public synchronized void mo248f(Context context) {
        if (context == null) {
            UMLog.m1136aq(UMLogAnalytics.f806ah, 0, "\\|");
            return;
        }
        if (f206a == null) {
            f206a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f206a)) {
            MLog.m1357e("clearSuperProperties can not be called in child process");
            return;
        }
        if (!this.f224j || !this.f228n) {
            mo213a(f206a);
        }
        this.f225k = new JSONObject();
        Context context2 = f206a;
        UMWorkDispatch.sendEvent(context2, CoreProtocolImpl.C0113a.f916s, CoreProtocol.getInstance(context2), null);
    }

    /* renamed from: m */
    public synchronized void mo258m() {
        try {
            Context context = f206a;
            if (context != null) {
                if (!UMUtils.isMainProgress(context)) {
                    MLog.m1357e("clearSuperPropertiesByCoreProtocol can not be called in child process");
                    return;
                }
                SharedPreferences.Editor edit = PreferenceWrapper.getDefault(f206a).edit();
                edit.remove(f207h);
                edit.commit();
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public synchronized void mo223a(Context context, List list) {
        if (context == null) {
            try {
                UMLog.m1136aq(UMLogAnalytics.f808aj, 0, "\\|");
                return;
            } catch (Throwable th) {
                MLog.m1361e(th);
            }
        } else {
            if (f206a == null) {
                f206a = context.getApplicationContext();
            }
            if (!UMUtils.isMainProgress(f206a)) {
                MLog.m1357e("setFirstLaunchEvent can not be called in child process");
                return;
            }
            if (!this.f224j || !this.f228n) {
                mo213a(f206a);
            }
            EventTracker.m993a(f206a).mo590a(list);
            return;
        }
    }

    /* renamed from: a */
    public synchronized void mo224a(Context context, JSONObject jSONObject) {
        if (context == null) {
            UMLog.m1136aq(UMLogAnalytics.f810al, 0, "\\|");
            return;
        }
        if (f206a == null) {
            f206a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f206a)) {
            MLog.m1357e("registerPreProperties can not be called in child process");
            return;
        }
        if (!this.f224j || !this.f228n) {
            mo213a(f206a);
        }
        if (this.f227m == null) {
            this.f227m = new JSONObject();
        }
        if (jSONObject == null || jSONObject.length() <= 0) {
            UMLog.m1136aq(UMLogAnalytics.f811am, 0, "\\|");
            return;
        }
        JSONObject jSONObject2 = null;
        try {
            jSONObject2 = new JSONObject(this.f227m.toString());
        } catch (Exception e) {
        }
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        Iterator<String> keys = jSONObject.keys();
        if (keys != null) {
            while (keys.hasNext()) {
                try {
                    String obj = keys.next().toString();
                    Object obj2 = jSONObject.get(obj);
                    if (m331b(obj, obj2)) {
                        jSONObject2.put(obj, obj2);
                        if (jSONObject2.length() > 10) {
                            MLog.m1357e("please check propertics, size overlength!");
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Exception e2) {
                }
            }
        }
        this.f227m = jSONObject2;
        if (this.f227m.length() > 0) {
            Context context2 = f206a;
            UMWorkDispatch.sendEvent(context2, CoreProtocolImpl.C0113a.f918u, CoreProtocol.getInstance(context2), this.f227m.toString());
        }
    }

    /* renamed from: f */
    public synchronized void mo249f(Context context, String str) {
        if (context == null) {
            UMLog.m1136aq(UMLogAnalytics.f812an, 0, "\\|");
            return;
        }
        if (f206a == null) {
            f206a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f206a)) {
            MLog.m1357e("unregisterPreProperty can not be called in child process");
            return;
        }
        if (!this.f224j || !this.f228n) {
            mo213a(f206a);
        }
        if (this.f227m == null) {
            this.f227m = new JSONObject();
        }
        if (str == null || str.length() <= 0) {
            MLog.m1357e("please check propertics, property is null!");
            return;
        }
        if (this.f227m.has(str)) {
            this.f227m.remove(str);
            Context context2 = f206a;
            UMWorkDispatch.sendEvent(context2, CoreProtocolImpl.C0113a.f919v, CoreProtocol.getInstance(context2), this.f227m.toString());
        } else if (UMConfigure.isDebugLog()) {
            UMLog.m1136aq(UMLogAnalytics.f813ao, 0, "\\|");
        }
    }

    /* renamed from: g */
    public synchronized void mo251g(Context context) {
        if (context == null) {
            UMLog.m1136aq(UMLogAnalytics.f814ap, 0, "\\|");
            return;
        }
        if (f206a == null) {
            f206a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f206a)) {
            MLog.m1357e("clearPreProperties can not be called in child process");
            return;
        }
        if (!this.f224j || !this.f228n) {
            mo213a(f206a);
        }
        if (this.f227m.length() > 0) {
            Context context2 = f206a;
            UMWorkDispatch.sendEvent(context2, CoreProtocolImpl.C0113a.f920w, CoreProtocol.getInstance(context2), null);
        }
        this.f227m = new JSONObject();
    }

    /* renamed from: h */
    public synchronized JSONObject mo252h(Context context) {
        if (context == null) {
            UMLog.m1136aq(UMLogAnalytics.f815aq, 0, "\\|");
            return null;
        }
        if (f206a == null) {
            f206a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f206a)) {
            MLog.m1357e("getPreProperties can not be called in child process");
            return null;
        }
        if (!this.f224j || !this.f228n) {
            mo213a(f206a);
        }
        if (this.f227m == null) {
            this.f227m = new JSONObject();
        }
        JSONObject jSONObject = new JSONObject();
        if (this.f227m.length() > 0) {
            try {
                jSONObject = new JSONObject(this.f227m.toString());
            } catch (JSONException e) {
            }
        }
        return jSONObject;
    }

    /* renamed from: b */
    public synchronized void mo236b(Object obj) {
        try {
            Context context = f206a;
            if (context != null) {
                if (!UMUtils.isMainProgress(context)) {
                    MLog.m1357e("updateNativePrePropertiesByCoreProtocol can not be called in child process");
                    return;
                }
                SharedPreferences.Editor edit = PreferenceWrapper.getDefault(f206a).edit();
                if (obj != null) {
                    String str = (String) obj;
                    if (edit != null && !TextUtils.isEmpty(str)) {
                        edit.putString(f208i, str).commit();
                    }
                } else if (edit != null) {
                    edit.remove(f208i).commit();
                }
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    private boolean m331b(String str, Object obj) {
        int i;
        try {
            if (TextUtils.isEmpty(str)) {
                MLog.m1357e("key is " + str + ", please check key, illegal");
                return false;
            }
            try {
                i = str.getBytes("UTF-8").length;
            } catch (UnsupportedEncodingException e) {
                i = 0;
            }
            if (i > f209o) {
                MLog.m1357e("key length is " + i + ", please check key, illegal");
                return false;
            } else if (obj instanceof String) {
                if (((String) obj).getBytes("UTF-8").length <= f210p) {
                    return true;
                }
                MLog.m1357e("value length is " + ((String) obj).getBytes("UTF-8").length + ", please check value, illegal");
                return false;
            } else if ((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof Float)) {
                return true;
            } else {
                MLog.m1357e("value is " + obj + ", please check value, type illegal");
                return false;
            }
        } catch (Throwable th) {
        }
    }

    @Override // com.umeng.analytics.pro.BackgroundWatcher
    /* renamed from: n */
    public void mo259n() {
        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> onIntoBackground triggered.");
        if (AnalyticsConfig.enable && FieldManager.m1114b()) {
            if (!FieldManager.allow(UMConstant.f1599D)) {
                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> 退出发送策略: 云控控制字关闭。功能不生效");
            } else if (!UMWorkDispatch.eventHasExist(CoreProtocolImpl.C0113a.f923z)) {
                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> 退出时发送策略 被触发！");
                Context context = f206a;
                UMWorkDispatch.sendEvent(context, CoreProtocolImpl.C0113a.f923z, CoreProtocol.getInstance(context), null);
            }
        }
    }
}
