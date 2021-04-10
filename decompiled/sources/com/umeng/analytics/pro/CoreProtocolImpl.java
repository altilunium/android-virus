package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.CoreProtocol;
import com.umeng.analytics.InternalAgent;
import com.umeng.analytics.InternalConfig;
import com.umeng.analytics.process.UMProcessDBHelper;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.SdkVersion;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ReportPolicy;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.internal.StatTracer;
import com.umeng.commonsdk.statistics.noise.ABTest;
import com.umeng.commonsdk.statistics.noise.Defcon;
import com.umeng.commonsdk.utils.JSONArraySortUtil;
import com.umeng.commonsdk.utils.UMConstant;
import com.umeng.commonsdk.utils.UMUtils;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.umeng.analytics.pro.n */
public class CoreProtocolImpl {

    /* renamed from: a */
    private static Context f869a = null;

    /* renamed from: l */
    private static final String f870l = "first_activate_time";

    /* renamed from: m */
    private static final String f871m = "ana_is_f";

    /* renamed from: n */
    private static final String f872n = "thtstart";

    /* renamed from: o */
    private static final String f873o = "dstk_last_time";

    /* renamed from: p */
    private static final String f874p = "dstk_cnt";

    /* renamed from: q */
    private static final String f875q = "gkvc";

    /* renamed from: r */
    private static final String f876r = "ekvc";

    /* renamed from: t */
    private static final String f877t = "-1";

    /* renamed from: x */
    private static final String f878x = "com.umeng.umcrash.UMCrashUtils";

    /* renamed from: y */
    private static Class f879y = null;

    /* renamed from: z */
    private static Method f880z = null;

    /* renamed from: b */
    private C0115c f881b;

    /* renamed from: c */
    private SharedPreferences f882c;

    /* renamed from: d */
    private String f883d;

    /* renamed from: e */
    private String f884e;

    /* renamed from: f */
    private int f885f;

    /* renamed from: g */
    private JSONArray f886g;

    /* renamed from: h */
    private final int f887h;

    /* renamed from: i */
    private int f888i;

    /* renamed from: j */
    private int f889j;

    /* renamed from: k */
    private long f890k;

    /* renamed from: s */
    private final long f891s;

    /* renamed from: u */
    private boolean f892u;

    /* renamed from: v */
    private boolean f893v;

    /* renamed from: w */
    private Object f894w;

    /* renamed from: com.umeng.analytics.pro.n$a */
    /* compiled from: CoreProtocolImpl */
    public class C0113a {

        /* renamed from: A */
        public static final int f895A = 8211;

        /* renamed from: B */
        public static final int f896B = 8212;

        /* renamed from: C */
        public static final int f897C = 8213;

        /* renamed from: a */
        public static final int f898a = 4097;

        /* renamed from: b */
        public static final int f899b = 4098;

        /* renamed from: c */
        public static final int f900c = 4099;

        /* renamed from: d */
        public static final int f901d = 4100;

        /* renamed from: e */
        public static final int f902e = 4101;

        /* renamed from: f */
        public static final int f903f = 4102;

        /* renamed from: g */
        public static final int f904g = 4103;

        /* renamed from: h */
        public static final int f905h = 4104;

        /* renamed from: i */
        public static final int f906i = 4105;

        /* renamed from: j */
        public static final int f907j = 4106;

        /* renamed from: k */
        public static final int f908k = 4352;

        /* renamed from: l */
        public static final int f909l = 4353;

        /* renamed from: m */
        public static final int f910m = 4354;

        /* renamed from: n */
        public static final int f911n = 4355;

        /* renamed from: o */
        public static final int f912o = 4356;

        /* renamed from: p */
        public static final int f913p = 8193;

        /* renamed from: q */
        public static final int f914q = 8194;

        /* renamed from: r */
        public static final int f915r = 8195;

        /* renamed from: s */
        public static final int f916s = 8196;

        /* renamed from: t */
        public static final int f917t = 8197;

        /* renamed from: u */
        public static final int f918u = 8199;

        /* renamed from: v */
        public static final int f919v = 8200;

        /* renamed from: w */
        public static final int f920w = 8201;

        /* renamed from: x */
        public static final int f921x = 8208;

        /* renamed from: y */
        public static final int f922y = 8209;

        /* renamed from: z */
        public static final int f923z = 8210;
    }

    static {
        m940h();
    }

    /* renamed from: h */
    private static void m940h() {
        try {
            Class<?> cls = Class.forName(f878x);
            f879y = cls;
            Method declaredMethod = cls.getDeclaredMethod("setPuidAndProvider", String.class, String.class);
            if (declaredMethod != null) {
                f880z = declaredMethod;
            }
        } catch (Throwable th) {
        }
    }

    private CoreProtocolImpl() {
        this.f881b = null;
        this.f882c = null;
        this.f883d = null;
        this.f884e = null;
        this.f885f = 10;
        this.f886g = new JSONArray();
        this.f887h = 5000;
        this.f888i = 0;
        this.f889j = 0;
        this.f890k = 0;
        this.f891s = 28800000;
        this.f892u = false;
        this.f893v = false;
        this.f894w = new Object();
        try {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f869a);
            this.f890k = sharedPreferences.getLong(f872n, 0);
            this.f888i = sharedPreferences.getInt(f875q, 0);
            this.f889j = sharedPreferences.getInt(f876r, 0);
            this.f881b = new C0115c();
        } catch (Throwable th) {
        }
    }

    /* renamed from: com.umeng.analytics.pro.n$b */
    /* compiled from: CoreProtocolImpl */
    class C0114b {

        /* renamed from: a */
        private static final CoreProtocolImpl f924a = new CoreProtocolImpl();

        private C0114b() {
        }
    }

    /* renamed from: a */
    public static CoreProtocolImpl m921a(Context context) {
        if (f869a == null && context != null) {
            f869a = context.getApplicationContext();
        }
        return C0114b.f924a;
    }

    /* renamed from: a */
    public void mo557a() {
        if (f869a != null) {
            synchronized (this.f894w) {
                if (this.f892u) {
                    UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> network is now available, rebuild instant session data packet.");
                    Context context = f869a;
                    UMWorkDispatch.sendEvent(context, C0113a.f909l, CoreProtocol.getInstance(context), null);
                }
            }
            synchronized (this.f894w) {
                if (this.f893v) {
                    Context context2 = f869a;
                    UMWorkDispatch.sendEvent(context2, C0113a.f910m, CoreProtocol.getInstance(context2), null);
                }
            }
        }
    }

    /* renamed from: b */
    public void mo564b() {
    }

    /* renamed from: c */
    public void mo567c() {
        mo565b(f869a);
        mo569d();
        mo561a(true);
    }

    /* renamed from: a */
    private void m923a(String str, String str2) {
        Method method;
        Class cls = f879y;
        if (cls != null && (method = f880z) != null) {
            try {
                method.invoke(cls, str, str2);
            } catch (Throwable th) {
                UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> reflect call setPuidAndProvider method of crash lib failed.");
            }
        }
    }

    /* renamed from: a */
    public void mo559a(Object obj, int i) {
        if (AnalyticsConfig.enable) {
            if (i != 8213) {
                switch (i) {
                    case C0113a.f898a /*{ENCODED_INT: 4097}*/:
                        try {
                            if (UMUtils.isMainProgress(f869a)) {
                                if (obj != null) {
                                    m933e(obj);
                                }
                                if (!f877t.equals(((JSONObject) obj).optString("__i"))) {
                                    mo561a(false);
                                    return;
                                }
                                return;
                            }
                            UMProcessDBHelper.getInstance(f869a).insertEventsInSubProcess(UMFrUtils.getSubProcessName(f869a), new JSONArray().put(obj));
                            return;
                        } catch (Throwable th) {
                            return;
                        }
                    case C0113a.f899b /*{ENCODED_INT: 4098}*/:
                        if (obj != null) {
                            try {
                                m933e(obj);
                            } catch (Throwable th2) {
                                return;
                            }
                        }
                        if (!f877t.equals(((JSONObject) obj).optString("__i"))) {
                            mo561a(false);
                            return;
                        }
                        return;
                    case C0113a.f900c /*{ENCODED_INT: 4099}*/:
                        ViewPageTracker.m1031a(f869a);
                        return;
                    case C0113a.f901d /*{ENCODED_INT: 4100}*/:
                        AutoViewPageTracker.m900c(f869a);
                        return;
                    case C0113a.f902e /*{ENCODED_INT: 4101}*/:
                        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> PROFILE_SIGNIN");
                        mo560a((Object) null, true);
                        m938g(obj);
                        return;
                    case C0113a.f903f /*{ENCODED_INT: 4102}*/:
                        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> PROFILE_SIGNOFF");
                        mo560a((Object) null, true);
                        m935f(obj);
                        return;
                    case C0113a.f904g /*{ENCODED_INT: 4103}*/:
                        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> START_SESSION");
                        SessionTracker.m1011a().mo593a(f869a, obj);
                        synchronized (this.f894w) {
                            this.f893v = true;
                        }
                        return;
                    case C0113a.f905h /*{ENCODED_INT: 4104}*/:
                        SessionTracker.m1011a().mo602c(f869a, obj);
                        return;
                    case C0113a.f906i /*{ENCODED_INT: 4105}*/:
                        mo569d();
                        return;
                    case C0113a.f907j /*{ENCODED_INT: 4106}*/:
                        m941h(obj);
                        return;
                    default:
                        switch (i) {
                            case C0113a.f908k /*{ENCODED_INT: 4352}*/:
                                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> INSTANT_SESSION_START");
                                SessionTracker.m1011a().mo598b(f869a, obj);
                                synchronized (this.f894w) {
                                    this.f892u = true;
                                }
                                return;
                            case C0113a.f909l /*{ENCODED_INT: 4353}*/:
                                mo560a(obj, true);
                                return;
                            case C0113a.f910m /*{ENCODED_INT: 4354}*/:
                                mo567c();
                                return;
                            case C0113a.f911n /*{ENCODED_INT: 4355}*/:
                                try {
                                    if (!UMUtils.isMainProgress(f869a)) {
                                        UMProcessDBHelper.getInstance(f869a).insertEventsInSubProcess(UMFrUtils.getSubProcessName(f869a), new JSONArray().put(obj));
                                        return;
                                    } else if (obj != null) {
                                        m933e(obj);
                                        mo569d();
                                        return;
                                    } else {
                                        return;
                                    }
                                } catch (Throwable th3) {
                                    return;
                                }
                            case C0113a.f912o /*{ENCODED_INT: 4356}*/:
                                if (obj != null && f879y != null && f880z != null) {
                                    UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> PROFILE_CHANGE_NOTIFY");
                                    String str = "";
                                    String str2 = "";
                                    try {
                                        if (obj instanceof JSONObject) {
                                            JSONObject jSONObject = (JSONObject) obj;
                                            if (jSONObject.has("uid") && jSONObject.has(UContent.f591M)) {
                                                str = jSONObject.getString(UContent.f591M);
                                                str2 = jSONObject.getString("uid");
                                            }
                                            m923a(str2, str);
                                            return;
                                        }
                                        return;
                                    } catch (Throwable th4) {
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            default:
                                switch (i) {
                                    case C0113a.f915r /*{ENCODED_INT: 8195}*/:
                                        InternalAgent.m328a().mo227a(obj);
                                        return;
                                    case C0113a.f916s /*{ENCODED_INT: 8196}*/:
                                        InternalAgent.m328a().mo258m();
                                        return;
                                    case C0113a.f917t /*{ENCODED_INT: 8197}*/:
                                        InternalAgent.m328a().mo256k();
                                        return;
                                    default:
                                        switch (i) {
                                            case C0113a.f918u /*{ENCODED_INT: 8199}*/:
                                            case C0113a.f919v /*{ENCODED_INT: 8200}*/:
                                                InternalAgent.m328a().mo236b(obj);
                                                return;
                                            case C0113a.f920w /*{ENCODED_INT: 8201}*/:
                                                InternalAgent.m328a().mo236b((Object) null);
                                                return;
                                            default:
                                                switch (i) {
                                                    case C0113a.f921x /*{ENCODED_INT: 8208}*/:
                                                        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> receive DELAY_BUILD_ENVELOPE event.");
                                                        Context context = f869a;
                                                        UMWorkDispatch.sendEvent(context, C0113a.f922y, CoreProtocol.getInstance(context), null);
                                                        Context context2 = f869a;
                                                        UMWorkDispatch.sendEvent(context2, C0113a.f910m, CoreProtocol.getInstance(context2), null);
                                                        return;
                                                    case C0113a.f922y /*{ENCODED_INT: 8209}*/:
                                                        mo560a(obj, false);
                                                        return;
                                                    case C0113a.f923z /*{ENCODED_INT: 8210}*/:
                                                        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> recv BUILD_ENVELOPE_IMMEDIATELY.");
                                                        try {
                                                            if (UMUtils.isMainProgress(f869a) && !(this.f881b.mo577c() instanceof ReportPolicy.ReportQuasiRealtime)) {
                                                                mo561a(true);
                                                                return;
                                                            }
                                                            return;
                                                        } catch (Throwable th5) {
                                                            return;
                                                        }
                                                    default:
                                                        return;
                                                }
                                        }
                                }
                        }
                }
            } else if (FieldManager.allow(UMConstant.f1600E)) {
                if (DeviceConfig.getGlobleActivity(f869a) != null) {
                    SessionTracker.m1014b(f869a);
                }
                Context context3 = f869a;
                UMWorkDispatch.sendEventEx(context3, C0113a.f897C, CoreProtocol.getInstance(context3), null, 5000);
            }
        }
    }

    /* renamed from: a */
    public void mo561a(boolean z) {
        if (!m930c(z)) {
            return;
        }
        if (this.f881b.mo577c() instanceof ReportPolicy.ReportQuasiRealtime) {
            if (z) {
                if (UMEnvelopeBuild.isOnline(f869a)) {
                    UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> send session start in policy ReportQuasiRealtime.");
                    m943j();
                }
            } else if (UMEnvelopeBuild.isReadyBuild(f869a, UMLogDataProtocol.UMBusinessType.U_APP)) {
                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> send normal data in policy ReportQuasiRealtime.");
                m943j();
            }
        } else if (UMEnvelopeBuild.isReadyBuild(f869a, UMLogDataProtocol.UMBusinessType.U_APP)) {
            UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> constructMessage()");
            m943j();
        }
    }

    /* renamed from: i */
    private void m942i() {
        JSONObject b = mo562b(UMEnvelopeBuild.maxDataSpace(f869a));
        if (b != null && b.length() >= 1) {
            JSONObject jSONObject = (JSONObject) b.opt("header");
            JSONObject jSONObject2 = (JSONObject) b.opt("content");
            if (f869a != null && jSONObject != null && jSONObject2 != null) {
                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> constructInstantMessage: request build envelope.");
                JSONObject buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(f869a, jSONObject, jSONObject2);
                if (buildEnvelopeWithExtHeader != null) {
                    try {
                        if (buildEnvelopeWithExtHeader.has("exception")) {
                            UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "Build envelope error code: " + buildEnvelopeWithExtHeader.getInt("exception"));
                        }
                    } catch (Throwable th) {
                    }
                    if (UMConfigure.isDebugLog()) {
                        m934e(buildEnvelopeWithExtHeader);
                    }
                    mo566b((Object) buildEnvelopeWithExtHeader);
                }
            }
        }
    }

    /* renamed from: j */
    private void m943j() {
        JSONObject buildEnvelopeWithExtHeader;
        JSONObject a = mo556a(UMEnvelopeBuild.maxDataSpace(f869a));
        if (a != null && a.length() >= 1) {
            JSONObject jSONObject = (JSONObject) a.opt("header");
            JSONObject jSONObject2 = (JSONObject) a.opt("content");
            Context context = f869a;
            if (context != null && jSONObject != null && jSONObject2 != null && (buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(context, jSONObject, jSONObject2)) != null) {
                try {
                    if (buildEnvelopeWithExtHeader.has("exception")) {
                        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "Build envelope error code: " + buildEnvelopeWithExtHeader.getInt("exception"));
                    }
                } catch (Throwable th) {
                }
                if (UMConfigure.isDebugLog()) {
                    m931d(buildEnvelopeWithExtHeader);
                }
                mo558a((Object) buildEnvelopeWithExtHeader);
            }
        }
    }

    /* renamed from: a */
    private boolean m925a(JSONArray jSONArray) {
        int length = jSONArray.length();
        List asList = Arrays.asList("$$_onUMengEnterForeground", "$$_onUMengEnterBackground", "$$_onUMengEnterForegroundInitError");
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            try {
                JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                if (optJSONObject != null && asList.contains(optJSONObject.optString("id"))) {
                    i++;
                }
            } catch (Throwable th) {
            }
        }
        return i >= length;
    }

    /* renamed from: a */
    private boolean m926a(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("ekv");
        int length = optJSONArray.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            try {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                Iterator<String> keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray(keys.next());
                    if (optJSONArray2 != null && m925a(optJSONArray2)) {
                        i++;
                    }
                }
            } catch (Throwable th) {
            }
        }
        if (i >= length) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public JSONObject mo556a(long j) {
        if (TextUtils.isEmpty(SessionIdManager.m1047a().mo619d(f869a))) {
            return null;
        }
        JSONObject b = mo563b(false);
        int a = DefconProcesser.m986a().mo584a(f869a);
        if (b.length() > 0) {
            if (b.length() == 1) {
                if (b.optJSONObject(UContent.f590L) != null && a != 3) {
                    return null;
                }
                if (!TextUtils.isEmpty(b.optString("userlevel")) && a != 3) {
                    return null;
                }
            } else if (b.length() == 2 && b.optJSONObject(UContent.f590L) != null && !TextUtils.isEmpty(b.optString("userlevel")) && a != 3) {
                return null;
            }
            String optString = b.optString(UContent.f652n);
            String optString2 = b.optString(UContent.f598T);
            String optString3 = b.optString("ekv");
            if (TextUtils.isEmpty(optString) && TextUtils.isEmpty(optString2) && !TextUtils.isEmpty(optString3) && m926a(b)) {
                return null;
            }
        } else if (a != 3) {
            return null;
        }
        JSONObject l = m945l();
        if (l != null) {
            m929c(l);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (a == 3) {
                jSONObject2.put("analytics", new JSONObject());
            } else if (b.length() > 0) {
                jSONObject2.put("analytics", b);
            }
            if (l != null && l.length() > 0) {
                jSONObject.put("header", l);
            }
            if (jSONObject2.length() > 0) {
                jSONObject.put("content", jSONObject2);
            }
            return m922a(jSONObject, j);
        } catch (Throwable th) {
            return jSONObject;
        }
    }

    /* renamed from: b */
    private void m928b(JSONObject jSONObject) {
        JSONObject f;
        if (!UMStoreManager.m853a(UMGlobalContext.getAppContext(f869a)).mo525c() && (f = UMStoreManager.m853a(UMGlobalContext.getAppContext(f869a)).mo529f()) != null) {
            String optString = f.optString("__av");
            String optString2 = f.optString("__vc");
            try {
                if (TextUtils.isEmpty(optString)) {
                    jSONObject.put("app_version", UMUtils.getAppVersionName(f869a));
                } else {
                    jSONObject.put("app_version", optString);
                }
                if (TextUtils.isEmpty(optString2)) {
                    jSONObject.put("version_code", UMUtils.getAppVersionCode(f869a));
                } else {
                    jSONObject.put("version_code", optString2);
                }
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: b */
    public JSONObject mo562b(long j) {
        if (TextUtils.isEmpty(SessionIdManager.m1047a().mo619d(UMGlobalContext.getAppContext(f869a)))) {
            return null;
        }
        JSONObject b = UMStoreManager.m853a(UMGlobalContext.getAppContext(f869a)).mo520b(false);
        String[] a = InternalConfig.m385a(f869a);
        if (a != null && !TextUtils.isEmpty(a[0]) && !TextUtils.isEmpty(a[1])) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(UContent.f591M, a[0]);
                jSONObject.put(UContent.f592N, a[1]);
                if (jSONObject.length() > 0) {
                    b.put(UContent.f590L, jSONObject);
                }
            } catch (Throwable th) {
            }
        }
        int a2 = DefconProcesser.m986a().mo584a(f869a);
        if (b.length() == 1 && b.optJSONObject(UContent.f590L) != null && a2 != 3) {
            return null;
        }
        DefconProcesser.m986a().mo586b(b, f869a);
        if (b.length() <= 0 && a2 != 3) {
            return null;
        }
        JSONObject k = m944k();
        if (k != null) {
            m928b(k);
        }
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        if (a2 == 3) {
            try {
                jSONObject3.put("analytics", new JSONObject());
            } catch (Throwable th2) {
                return jSONObject2;
            }
        } else if (b.length() > 0) {
            jSONObject3.put("analytics", b);
        }
        if (k != null && k.length() > 0) {
            jSONObject2.put("header", k);
        }
        if (jSONObject3.length() > 0) {
            jSONObject2.put("content", jSONObject3);
        }
        return m927b(jSONObject2, j);
    }

    /* renamed from: a */
    private JSONObject m922a(JSONObject jSONObject, long j) {
        try {
            if (DataSpliter.m984a(jSONObject) <= j) {
                return jSONObject;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("header");
            jSONObject2.put(UContent.f607aB, DataSpliter.m984a(jSONObject));
            jSONObject.put("header", jSONObject2);
            return DataSpliter.m985a(f869a, j, jSONObject);
        } catch (Throwable th) {
            return jSONObject;
        }
    }

    /* renamed from: b */
    private JSONObject m927b(JSONObject jSONObject, long j) {
        try {
            if (DataSpliter.m984a(jSONObject) <= j) {
                return jSONObject;
            }
            UMStoreManager.m853a(f869a).mo517a(true, false);
            UMStoreManager.m853a(f869a).mo521b();
            UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> Instant session packet overload !!! ");
            return null;
        } catch (Throwable th) {
            return jSONObject;
        }
    }

    /* renamed from: k */
    private JSONObject m944k() {
        JSONObject l = m945l();
        if (l != null) {
            try {
                l.put("st", SdkVersion.MINI_VERSION);
            } catch (Throwable th) {
            }
        }
        return l;
    }

    /* renamed from: c */
    private void m929c(JSONObject jSONObject) {
        if (!UMStoreManager.m853a(f869a).mo528e()) {
            JSONObject g = UMStoreManager.m853a(f869a).mo530g();
            if (g != null) {
                String optString = g.optString("__av");
                String optString2 = g.optString("__vc");
                try {
                    if (TextUtils.isEmpty(optString)) {
                        jSONObject.put("app_version", UMUtils.getAppVersionName(f869a));
                    } else {
                        jSONObject.put("app_version", optString);
                    }
                    if (TextUtils.isEmpty(optString2)) {
                        jSONObject.put("version_code", UMUtils.getAppVersionCode(f869a));
                    } else {
                        jSONObject.put("version_code", optString2);
                    }
                } catch (Throwable th) {
                }
            }
        } else {
            try {
                jSONObject.put("app_version", UMUtils.getAppVersionName(f869a));
                jSONObject.put("version_code", UMUtils.getAppVersionCode(f869a));
            } catch (Throwable th2) {
            }
        }
    }

    /* renamed from: l */
    private JSONObject m945l() {
        String str;
        JSONObject jSONObject = new JSONObject();
        try {
            if (!(AnalyticsConfig.mWrapperType == null || (str = AnalyticsConfig.mWrapperVersion) == null)) {
                jSONObject.put("wrapper_version", str);
                jSONObject.put("wrapper_type", AnalyticsConfig.mWrapperType);
            }
            int verticalType = AnalyticsConfig.getVerticalType(f869a);
            jSONObject.put(UContent.f647i, verticalType);
            String str2 = "9.3.7";
            if (verticalType == 1) {
                String gameSdkVersion = AnalyticsConfig.getGameSdkVersion(f869a);
                if (!TextUtils.isEmpty(gameSdkVersion)) {
                    str2 = gameSdkVersion;
                }
                jSONObject.put("sdk_version", str2);
            } else {
                jSONObject.put("sdk_version", str2);
            }
            String MD5 = HelperUtils.MD5(AnalyticsConfig.getSecretKey(f869a));
            if (!TextUtils.isEmpty(MD5)) {
                jSONObject.put("secret", MD5);
            }
            String imprintProperty = UMEnvelopeBuild.imprintProperty(f869a, "pr_ve", null);
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f869a);
            String imprintProperty2 = UMEnvelopeBuild.imprintProperty(f869a, UContent.f627an, "");
            if (!TextUtils.isEmpty(imprintProperty2)) {
                if (AnalyticsConfig.CLEAR_EKV_BL) {
                    jSONObject.put(UContent.f629ap, "");
                } else {
                    jSONObject.put(UContent.f629ap, imprintProperty2);
                }
            }
            String imprintProperty3 = UMEnvelopeBuild.imprintProperty(f869a, UContent.f628ao, "");
            if (!TextUtils.isEmpty(imprintProperty3)) {
                if (AnalyticsConfig.CLEAR_EKV_WL) {
                    jSONObject.put(UContent.f630aq, "");
                } else {
                    jSONObject.put(UContent.f630aq, imprintProperty3);
                }
            }
            jSONObject.put(UContent.f621ah, "1.0.0");
            if (m952s()) {
                jSONObject.put(UContent.f623aj, SdkVersion.MINI_VERSION);
                if (sharedPreferences != null) {
                    sharedPreferences.edit().putLong(f871m, 0).commit();
                }
            }
            jSONObject.put(UContent.f650l, m946m());
            jSONObject.put(UContent.f651m, m947n());
            if (sharedPreferences != null) {
                String string = sharedPreferences.getString("vers_name", "");
                if (!TextUtils.isEmpty(string)) {
                    String format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                    if (TextUtils.isEmpty(imprintProperty)) {
                        jSONObject.put(UContent.f650l, sharedPreferences.getString("vers_pre_version", "0"));
                        jSONObject.put(UContent.f651m, sharedPreferences.getString("vers_date", format));
                    }
                    sharedPreferences.edit().putString("pre_version", string).putString("cur_version", DeviceConfig.getAppVersionName(f869a)).putString("pre_date", format).remove("vers_name").remove("vers_code").remove("vers_date").remove("vers_pre_version").commit();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    /* renamed from: b */
    public JSONObject mo563b(boolean z) {
        JSONArray jSONArray;
        JSONArray jSONArray2;
        JSONObject jSONObject = null;
        try {
            jSONObject = UMStoreManager.m853a(f869a).mo513a(z);
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            } else {
                try {
                    if (jSONObject.has(UContent.f652n)) {
                        JSONArray jSONArray3 = jSONObject.getJSONArray(UContent.f652n);
                        JSONArray jSONArray4 = new JSONArray();
                        int i = 0;
                        while (i < jSONArray3.length()) {
                            JSONObject jSONObject2 = (JSONObject) jSONArray3.get(i);
                            JSONArray optJSONArray = jSONObject2.optJSONArray(UContent.f658t);
                            JSONArray optJSONArray2 = jSONObject2.optJSONArray(UContent.f659u);
                            if (optJSONArray == null && optJSONArray2 != null) {
                                jSONObject2.put(UContent.f658t, optJSONArray2);
                                jSONObject2.remove(UContent.f659u);
                            }
                            if (!(optJSONArray == null || optJSONArray2 == null)) {
                                ArrayList arrayList = new ArrayList();
                                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                    arrayList.add((JSONObject) optJSONArray.get(i2));
                                }
                                for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                                    arrayList.add((JSONObject) optJSONArray2.get(i3));
                                }
                                JSONArraySortUtil jSONArraySortUtil = new JSONArraySortUtil();
                                jSONArraySortUtil.setCompareKey(UContent.f662x);
                                Collections.sort(arrayList, jSONArraySortUtil);
                                JSONArray jSONArray5 = new JSONArray();
                                Iterator it = arrayList.iterator();
                                while (it.hasNext()) {
                                    jSONArray5.put((JSONObject) it.next());
                                }
                                jSONObject2.put(UContent.f658t, jSONArray5);
                                jSONObject2.remove(UContent.f659u);
                            }
                            if (jSONObject2.has(UContent.f658t)) {
                                JSONArray optJSONArray3 = jSONObject2.optJSONArray(UContent.f658t);
                                int i4 = 0;
                                while (i4 < optJSONArray3.length()) {
                                    JSONObject jSONObject3 = optJSONArray3.getJSONObject(i4);
                                    if (jSONObject3.has(UContent.f662x)) {
                                        jSONArray2 = jSONArray3;
                                        jSONObject3.put("ts", jSONObject3.getLong(UContent.f662x));
                                        jSONObject3.remove(UContent.f662x);
                                    } else {
                                        jSONArray2 = jSONArray3;
                                    }
                                    i4++;
                                    jSONArray3 = jSONArray2;
                                }
                                jSONArray = jSONArray3;
                                jSONObject2.put(UContent.f658t, optJSONArray3);
                                jSONObject2.put(UContent.f664z, optJSONArray3.length());
                            } else {
                                jSONArray = jSONArray3;
                                jSONObject2.put(UContent.f664z, 0);
                            }
                            jSONArray4.put(jSONObject2);
                            i++;
                            jSONArray3 = jSONArray;
                        }
                        jSONObject.put(UContent.f652n, jSONArray4);
                    }
                } catch (Exception e) {
                    MLog.m1357e("merge pages error");
                    e.printStackTrace();
                }
            }
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f869a);
            if (sharedPreferences != null) {
                String string = sharedPreferences.getString("userlevel", "");
                if (!TextUtils.isEmpty(string)) {
                    jSONObject.put("userlevel", string);
                }
            }
            String[] a = InternalConfig.m385a(f869a);
            if (a != null && !TextUtils.isEmpty(a[0]) && !TextUtils.isEmpty(a[1])) {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put(UContent.f591M, a[0]);
                jSONObject4.put(UContent.f592N, a[1]);
                if (jSONObject4.length() > 0) {
                    jSONObject.put(UContent.f590L, jSONObject4);
                }
            }
            if (ABTest.getService(f869a).isInTest()) {
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.put(ABTest.getService(f869a).getTestName(), ABTest.getService(f869a).getGroupInfo());
                jSONObject.put(UContent.f589K, jSONObject5);
            }
            DefconProcesser.m986a().mo585a(jSONObject, f869a);
        } catch (Throwable th) {
        }
        return jSONObject;
    }

    /* renamed from: m */
    private String m946m() {
        String str = null;
        try {
            str = UMEnvelopeBuild.imprintProperty(f869a, "pr_ve", null);
            if (TextUtils.isEmpty(str)) {
                if (!TextUtils.isEmpty(this.f883d)) {
                    return this.f883d;
                }
                if (this.f882c == null) {
                    this.f882c = PreferenceWrapper.getDefault(f869a);
                }
                String string = this.f882c.getString("pre_version", "");
                String appVersionName = DeviceConfig.getAppVersionName(f869a);
                if (TextUtils.isEmpty(string)) {
                    this.f882c.edit().putString("pre_version", "0").putString("cur_version", appVersionName).commit();
                    str = "0";
                } else {
                    String string2 = this.f882c.getString("cur_version", "");
                    if (!appVersionName.equals(string2)) {
                        this.f882c.edit().putString("pre_version", string2).putString("cur_version", appVersionName).commit();
                        str = string2;
                    } else {
                        str = string;
                    }
                }
            }
        } catch (Throwable th) {
        }
        this.f883d = str;
        return str;
    }

    /* renamed from: n */
    private String m947n() {
        String str = null;
        try {
            str = UMEnvelopeBuild.imprintProperty(f869a, "ud_da", null);
            if (TextUtils.isEmpty(str)) {
                if (!TextUtils.isEmpty(this.f884e)) {
                    return this.f884e;
                }
                if (this.f882c == null) {
                    this.f882c = PreferenceWrapper.getDefault(f869a);
                }
                String string = this.f882c.getString("pre_date", "");
                if (TextUtils.isEmpty(string)) {
                    String format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                    this.f882c.edit().putString("pre_date", format).commit();
                    str = format;
                } else {
                    String format2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                    if (!string.equals(format2)) {
                        this.f882c.edit().putString("pre_date", format2).commit();
                        str = format2;
                    } else {
                        str = string;
                    }
                }
            }
        } catch (Throwable th) {
        }
        this.f884e = str;
        return str;
    }

    /* renamed from: d */
    public void mo569d() {
        try {
            if (this.f886g.length() > 0) {
                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>>*** flushMemoryData: 事件落库。");
                UMStoreManager.m853a(f869a).mo515a(this.f886g);
                this.f886g = new JSONArray();
            }
            PreferenceWrapper.getDefault(f869a).edit().putLong(f872n, this.f890k).putInt(f875q, this.f888i).putInt(f876r, this.f889j).commit();
        } catch (Throwable th) {
        }
    }

    /* renamed from: com.umeng.analytics.pro.n$d */
    /* compiled from: CoreProtocolImpl */
    public class C0116d {

        /* renamed from: a */
        private Map f931a = null;

        /* renamed from: b */
        private String f932b = null;

        /* renamed from: c */
        private String f933c = null;

        /* renamed from: d */
        private long f934d = 0;

        private C0116d() {
        }

        public C0116d(String str, Map map, String str2, long j) {
            this.f931a = map;
            this.f932b = str;
            this.f934d = j;
            this.f933c = str2;
        }

        /* renamed from: a */
        public Map mo578a() {
            return this.f931a;
        }

        /* renamed from: b */
        public String mo579b() {
            return this.f933c;
        }

        /* renamed from: c */
        public String mo580c() {
            return this.f932b;
        }

        /* renamed from: d */
        public long mo581d() {
            return this.f934d;
        }
    }

    /* renamed from: e */
    private void m933e(Object obj) {
        try {
            JSONObject jSONObject = (JSONObject) obj;
            if (2050 == jSONObject.getInt("__t")) {
                if (m924a(this.f890k, this.f888i)) {
                    this.f888i++;
                } else {
                    return;
                }
            } else if (2049 == jSONObject.getInt("__t")) {
                if (m924a(this.f890k, this.f889j)) {
                    this.f889j++;
                } else {
                    return;
                }
            }
            if (this.f886g.length() >= this.f885f) {
                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>>*** 超过10个事件，事件落库。");
                UMStoreManager.m853a(f869a).mo515a(this.f886g);
                this.f886g = new JSONArray();
            }
            if (this.f890k == 0) {
                this.f890k = System.currentTimeMillis();
            }
            this.f886g.put(jSONObject);
        } catch (Throwable th) {
            MLog.m1361e(th);
        }
    }

    /* renamed from: a */
    private boolean m924a(long j, int i) {
        if (j == 0) {
            return true;
        }
        if (System.currentTimeMillis() - j > 28800000) {
            m948o();
            return true;
        } else if (i < 5000) {
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: o */
    private void m948o() {
        try {
            this.f888i = 0;
            this.f889j = 0;
            this.f890k = System.currentTimeMillis();
            PreferenceWrapper.getDefault(f869a).edit().putLong(f873o, System.currentTimeMillis()).putInt(f874p, 0).commit();
        } catch (Throwable th) {
        }
    }

    /* renamed from: c */
    private boolean m930c(boolean z) {
        if (m952s()) {
            return true;
        }
        if (this.f881b == null) {
            this.f881b = new C0115c();
        }
        this.f881b.mo574a();
        ReportPolicy.ReportStrategy c = this.f881b.mo577c();
        boolean shouldSendMessage = c.shouldSendMessage(z);
        if (shouldSendMessage) {
            if (((c instanceof ReportPolicy.ReportByInterval) || (c instanceof ReportPolicy.DebugPolicy) || (c instanceof ReportPolicy.ReportQuasiRealtime)) && m949p()) {
                mo569d();
            }
            if ((c instanceof ReportPolicy.DefconPolicy) && m949p()) {
                mo569d();
            }
            if (UMConfigure.isDebugLog()) {
                MLog.m1351d("数据发送策略 : " + c.getClass().getSimpleName());
            }
        }
        return shouldSendMessage;
    }

    /* renamed from: p */
    private boolean m949p() {
        try {
            if (!TextUtils.isEmpty(SessionTracker.m1011a().mo596b())) {
                mo565b(f869a);
            }
            if (this.f886g.length() <= 0) {
                return false;
            }
            for (int i = 0; i < this.f886g.length(); i++) {
                JSONObject optJSONObject = this.f886g.optJSONObject(i);
                if (optJSONObject != null && optJSONObject.length() > 0) {
                    String optString = optJSONObject.optString("__i");
                    if (TextUtils.isEmpty(optString) || f877t.equals(optString)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Throwable th) {
            return true;
        }
    }

    /* renamed from: com.umeng.analytics.pro.n$c */
    /* compiled from: CoreProtocolImpl */
    public class C0115c {

        /* renamed from: a */
        private ReportPolicy.ReportStrategy f925a;

        /* renamed from: b */
        private int f926b;

        /* renamed from: c */
        private int f927c;

        /* renamed from: d */
        private int f928d;

        /* renamed from: e */
        private int f929e;

        /* renamed from: f */
        private ABTest f930f;

        public C0115c() {
            this.f925a = null;
            this.f926b = -1;
            this.f927c = -1;
            this.f928d = -1;
            this.f929e = -1;
            this.f930f = null;
            this.f930f = ABTest.getService(CoreProtocolImpl.f869a);
        }

        /* renamed from: a */
        public void mo574a() {
            try {
                int[] a = mo575a(-1, -1);
                this.f926b = a[0];
                this.f927c = a[1];
            } catch (Throwable th) {
            }
        }

        /* renamed from: a */
        public int[] mo575a(int i, int i2) {
            int intValue = Integer.valueOf(UMEnvelopeBuild.imprintProperty(CoreProtocolImpl.f869a, "report_policy", CoreProtocolImpl.f877t)).intValue();
            int intValue2 = Integer.valueOf(UMEnvelopeBuild.imprintProperty(CoreProtocolImpl.f869a, "report_interval", CoreProtocolImpl.f877t)).intValue();
            if (intValue == -1 || !ReportPolicy.isValid(intValue)) {
                return new int[]{i, i2};
            } else if (6 == intValue) {
                if (intValue2 == -1 || intValue2 < 90 || intValue2 > 86400) {
                    intValue2 = 90;
                }
                return new int[]{intValue, intValue2 * 1000};
            } else if (11 == intValue) {
                if (intValue2 == -1 || intValue2 < 15 || intValue2 > 3600) {
                    intValue2 = 15;
                }
                return new int[]{intValue, intValue2 * 1000};
            } else {
                return new int[]{i, i2};
            }
        }

        /* renamed from: a */
        public int mo573a(int i) {
            int intValue = Integer.valueOf(UMEnvelopeBuild.imprintProperty(CoreProtocolImpl.f869a, "test_report_interval", CoreProtocolImpl.f877t)).intValue();
            if (intValue == -1 || intValue < 90 || intValue > 86400) {
                return i;
            }
            return intValue * 1000;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void mo576b() {
            int i;
            Defcon service = Defcon.getService(CoreProtocolImpl.f869a);
            if (service.isOpen()) {
                ReportPolicy.ReportStrategy reportStrategy = this.f925a;
                this.f925a = (reportStrategy instanceof ReportPolicy.DefconPolicy) && reportStrategy.isValid() ? this.f925a : new ReportPolicy.DefconPolicy(StatTracer.getInstance(CoreProtocolImpl.f869a), service);
            } else {
                boolean z = Integer.valueOf(UMEnvelopeBuild.imprintProperty(CoreProtocolImpl.f869a, "integrated_test", CoreProtocolImpl.f877t)).intValue() == 1;
                if (UMConfigure.isDebugLog() && z && !MLog.DEBUG) {
                    UMLog.mutlInfo(UMLogAnalytics.f782K, 3, "\\|", null, null);
                }
                if (MLog.DEBUG && z) {
                    this.f925a = new ReportPolicy.DebugPolicy(StatTracer.getInstance(CoreProtocolImpl.f869a));
                } else if (!this.f930f.isInTest() || !"RPT".equals(this.f930f.getTestName())) {
                    int i2 = this.f928d;
                    int i3 = this.f929e;
                    int i4 = this.f926b;
                    if (i4 != -1) {
                        i3 = this.f927c;
                        i2 = i4;
                    }
                    this.f925a = m971b(i2, i3);
                } else {
                    if (this.f930f.getTestPolicy() == 6) {
                        if (Integer.valueOf(UMEnvelopeBuild.imprintProperty(CoreProtocolImpl.f869a, "test_report_interval", CoreProtocolImpl.f877t)).intValue() != -1) {
                            i = mo573a(90000);
                        } else {
                            i = this.f927c;
                            if (i <= 0) {
                                i = this.f929e;
                            }
                        }
                    } else {
                        i = 0;
                    }
                    this.f925a = m971b(this.f930f.getTestPolicy(), i);
                }
            }
            if (UMConfigure.isDebugLog()) {
                try {
                    ReportPolicy.ReportStrategy reportStrategy2 = this.f925a;
                    if (reportStrategy2 instanceof ReportPolicy.ReportAtLaunch) {
                        UMLog.mutlInfo(UMLogAnalytics.f780I, 3, "", null, null);
                    } else if (reportStrategy2 instanceof ReportPolicy.ReportByInterval) {
                        UMLog.mutlInfo(UMLogAnalytics.f781J, 3, "", new String[]{"@"}, new String[]{String.valueOf(((ReportPolicy.ReportByInterval) reportStrategy2).getReportInterval() / 1000)});
                    } else if (reportStrategy2 instanceof ReportPolicy.DebugPolicy) {
                        UMLog.mutlInfo(UMLogAnalytics.f783L, 3, "", null, null);
                    } else if (reportStrategy2 instanceof ReportPolicy.ReportQuasiRealtime) {
                        String[] strArr = {String.valueOf(((ReportPolicy.ReportQuasiRealtime) reportStrategy2).getReportInterval() / 1000)};
                        UMLog uMLog = UMConfigure.umDebugLog;
                        UMLog.mutlInfo(UMLogAnalytics.f784M, 3, "", new String[]{"@"}, strArr);
                    } else {
                        boolean z2 = reportStrategy2 instanceof ReportPolicy.DefconPolicy;
                    }
                } catch (Throwable th) {
                }
            }
        }

        /* renamed from: c */
        public ReportPolicy.ReportStrategy mo577c() {
            mo576b();
            return this.f925a;
        }

        /* renamed from: b */
        private ReportPolicy.ReportStrategy m971b(int i, int i2) {
            if (i == 0) {
                ReportPolicy.ReportStrategy reportStrategy = this.f925a;
                if (reportStrategy instanceof ReportPolicy.ReportRealtime) {
                    return reportStrategy;
                }
                return new ReportPolicy.ReportRealtime();
            } else if (i == 1) {
                ReportPolicy.ReportStrategy reportStrategy2 = this.f925a;
                if (reportStrategy2 instanceof ReportPolicy.ReportAtLaunch) {
                    return reportStrategy2;
                }
                return new ReportPolicy.ReportAtLaunch();
            } else if (i == 4) {
                ReportPolicy.ReportStrategy reportStrategy3 = this.f925a;
                if (reportStrategy3 instanceof ReportPolicy.ReportDaily) {
                    return reportStrategy3;
                }
                return new ReportPolicy.ReportDaily(StatTracer.getInstance(CoreProtocolImpl.f869a));
            } else if (i == 5) {
                ReportPolicy.ReportStrategy reportStrategy4 = this.f925a;
                if (reportStrategy4 instanceof ReportPolicy.ReportWifiOnly) {
                    return reportStrategy4;
                }
                return new ReportPolicy.ReportWifiOnly(CoreProtocolImpl.f869a);
            } else if (i == 6) {
                ReportPolicy.ReportStrategy reportStrategy5 = this.f925a;
                if (!(reportStrategy5 instanceof ReportPolicy.ReportByInterval)) {
                    return new ReportPolicy.ReportByInterval(StatTracer.getInstance(CoreProtocolImpl.f869a), (long) i2);
                }
                ((ReportPolicy.ReportByInterval) reportStrategy5).setReportInterval((long) i2);
                return reportStrategy5;
            } else if (i == 8) {
                ReportPolicy.ReportStrategy reportStrategy6 = this.f925a;
                if (reportStrategy6 instanceof ReportPolicy.SmartPolicy) {
                    return reportStrategy6;
                }
                return new ReportPolicy.SmartPolicy(StatTracer.getInstance(CoreProtocolImpl.f869a));
            } else if (i != 11) {
                ReportPolicy.ReportStrategy reportStrategy7 = this.f925a;
                if (reportStrategy7 instanceof ReportPolicy.ReportAtLaunch) {
                    return reportStrategy7;
                }
                return new ReportPolicy.ReportAtLaunch();
            } else {
                ReportPolicy.ReportStrategy reportStrategy8 = this.f925a;
                if (reportStrategy8 instanceof ReportPolicy.ReportQuasiRealtime) {
                    ((ReportPolicy.ReportQuasiRealtime) reportStrategy8).setReportInterval((long) i2);
                    return reportStrategy8;
                }
                ReportPolicy.ReportQuasiRealtime reportQuasiRealtime = new ReportPolicy.ReportQuasiRealtime();
                reportQuasiRealtime.setReportInterval((long) i2);
                return reportQuasiRealtime;
            }
        }
    }

    /* renamed from: d */
    private void m931d(JSONObject jSONObject) {
        String str;
        JSONObject jSONObject2;
        if (jSONObject != null) {
            try {
                if (jSONObject.length() > 0) {
                    JSONObject jSONObject3 = new JSONObject();
                    if (jSONObject.has("analytics")) {
                        JSONObject jSONObject4 = jSONObject.getJSONObject("analytics");
                        if (jSONObject4.has("ekv")) {
                            str = "version_code";
                            jSONObject3.put("ekv", jSONObject4.getJSONArray("ekv"));
                            if (jSONObject3.length() > 0) {
                                MLog.m1351d("事件:" + jSONObject3.toString());
                                jSONObject3 = new JSONObject();
                            }
                        } else {
                            str = "version_code";
                        }
                        if (jSONObject4.has(UContent.f598T)) {
                            jSONObject3.put(UContent.f598T, jSONObject4.getJSONArray(UContent.f598T));
                            if (jSONObject3.length() > 0) {
                                MLog.m1351d("游戏事件:" + jSONObject3.toString());
                                jSONObject3 = new JSONObject();
                            }
                        }
                        if (jSONObject4.has(UContent.f593O)) {
                            jSONObject3.put(UContent.f593O, jSONObject4.getJSONArray(UContent.f593O));
                            if (jSONObject3.length() > 0) {
                                MLog.m1351d("错误:" + jSONObject3.toString());
                                jSONObject3 = new JSONObject();
                            }
                        }
                        if (jSONObject4.has(UContent.f652n)) {
                            JSONArray jSONArray = jSONObject4.getJSONArray(UContent.f652n);
                            JSONArray jSONArray2 = new JSONArray();
                            for (int i = 0; i < jSONArray.length(); i++) {
                                JSONObject jSONObject5 = jSONArray.getJSONObject(i);
                                if (jSONObject5 != null && jSONObject5.length() > 0) {
                                    if (jSONObject5.has(UContent.f659u)) {
                                        jSONObject5.remove(UContent.f659u);
                                    }
                                    jSONArray2.put(jSONObject5);
                                }
                            }
                            jSONObject3.put(UContent.f652n, jSONArray2);
                            if (jSONObject3.length() > 0) {
                                MLog.m1351d("会话:" + jSONObject3.toString());
                                jSONObject3 = new JSONObject();
                            }
                        }
                        if (jSONObject4.has(UContent.f587I)) {
                            jSONObject3.put(UContent.f587I, jSONObject4.getJSONObject(UContent.f587I));
                        }
                        if (jSONObject4.has(UContent.f590L)) {
                            jSONObject3.put(UContent.f590L, jSONObject4.getJSONObject(UContent.f590L));
                            if (jSONObject3.length() > 0) {
                                MLog.m1351d("账号:" + jSONObject3.toString());
                                jSONObject3 = new JSONObject();
                            }
                        }
                    } else {
                        str = "version_code";
                    }
                    if (jSONObject.has("dplus")) {
                        jSONObject3.put("dplus", jSONObject.getJSONObject("dplus"));
                    }
                    if (jSONObject.has("header") && jSONObject.has("header") && (jSONObject2 = jSONObject.getJSONObject("header")) != null && jSONObject2.length() > 0) {
                        if (jSONObject2.has("sdk_version")) {
                            jSONObject3.put("sdk_version", jSONObject2.getString("sdk_version"));
                        }
                        if (jSONObject2.has("device_id")) {
                            jSONObject3.put("device_id", jSONObject2.getString("device_id"));
                        }
                        if (jSONObject2.has("device_model")) {
                            jSONObject3.put("device_model", jSONObject2.getString("device_model"));
                        }
                        if (jSONObject2.has(str)) {
                            jSONObject3.put("version", jSONObject2.getInt(str));
                        }
                        if (jSONObject2.has("appkey")) {
                            jSONObject3.put("appkey", jSONObject2.getString("appkey"));
                        }
                        if (jSONObject2.has("channel")) {
                            jSONObject3.put("channel", jSONObject2.getString("channel"));
                        }
                        if (jSONObject3.length() > 0) {
                            MLog.m1351d("基础信息:" + jSONObject3.toString());
                            jSONObject3 = new JSONObject();
                        }
                    }
                    jSONObject3.length();
                }
            } catch (Throwable th) {
                MLog.m1361e(th);
            }
        }
    }

    /* renamed from: e */
    private void m934e(JSONObject jSONObject) {
        JSONObject jSONObject2;
        if (jSONObject != null) {
            try {
                if (jSONObject.length() > 0) {
                    JSONObject jSONObject3 = new JSONObject();
                    if (jSONObject.has("analytics")) {
                        JSONObject jSONObject4 = jSONObject.getJSONObject("analytics");
                        if (jSONObject4.has(UContent.f652n)) {
                            JSONArray jSONArray = jSONObject4.getJSONArray(UContent.f652n);
                            JSONArray jSONArray2 = new JSONArray();
                            for (int i = 0; i < jSONArray.length(); i++) {
                                JSONObject jSONObject5 = jSONArray.getJSONObject(i);
                                if (jSONObject5 != null && jSONObject5.length() > 0) {
                                    jSONArray2.put(jSONObject5);
                                }
                            }
                            jSONObject3.put(UContent.f652n, jSONArray2);
                            if (jSONObject3.length() > 0) {
                                MLog.m1351d("本次启动会话:" + jSONObject3.toString());
                                jSONObject3 = new JSONObject();
                            }
                        }
                        if (jSONObject4.has(UContent.f590L)) {
                            jSONObject3.put(UContent.f590L, jSONObject4.getJSONObject(UContent.f590L));
                            if (jSONObject3.length() > 0) {
                                MLog.m1351d("本次启动账号:" + jSONObject3.toString());
                                jSONObject3 = new JSONObject();
                            }
                        }
                    }
                    if (jSONObject.has("header") && jSONObject.has("header") && (jSONObject2 = jSONObject.getJSONObject("header")) != null && jSONObject2.length() > 0) {
                        if (jSONObject2.has("sdk_version")) {
                            jSONObject3.put("sdk_version", jSONObject2.getString("sdk_version"));
                        }
                        if (jSONObject2.has("device_id")) {
                            jSONObject3.put("device_id", jSONObject2.getString("device_id"));
                        }
                        if (jSONObject2.has("device_model")) {
                            jSONObject3.put("device_model", jSONObject2.getString("device_model"));
                        }
                        if (jSONObject2.has("version_code")) {
                            jSONObject3.put("version", jSONObject2.getInt("version_code"));
                        }
                        if (jSONObject2.has("appkey")) {
                            jSONObject3.put("appkey", jSONObject2.getString("appkey"));
                        }
                        if (jSONObject2.has("channel")) {
                            jSONObject3.put("channel", jSONObject2.getString("channel"));
                        }
                        if (jSONObject3.length() > 0) {
                            MLog.m1351d("本次启动基础信息:" + jSONObject3.toString());
                            jSONObject3 = new JSONObject();
                        }
                    }
                    jSONObject3.length();
                }
            } catch (Throwable th) {
                MLog.m1361e(th);
            }
        }
    }

    /* renamed from: a */
    public void mo558a(Object obj) {
        if (obj != null) {
            try {
                JSONObject jSONObject = (JSONObject) obj;
                if (jSONObject.length() <= 0) {
                    return;
                }
                if (!jSONObject.has("exception")) {
                    m939g(jSONObject);
                } else if (101 != jSONObject.getInt("exception")) {
                    m939g(jSONObject);
                }
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: b */
    public void mo566b(Object obj) {
        if (obj != null) {
            try {
                JSONObject jSONObject = (JSONObject) obj;
                if (jSONObject.length() <= 0) {
                    return;
                }
                if (!jSONObject.has("exception")) {
                    m936f(jSONObject);
                } else if (101 != jSONObject.getInt("exception")) {
                    m936f(jSONObject);
                }
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: f */
    private void m936f(JSONObject jSONObject) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        try {
            if (jSONObject.getJSONObject("header").has(UContent.f607aB)) {
                if (jSONObject.has("content")) {
                    jSONObject = jSONObject.getJSONObject("content");
                }
                if (jSONObject.has("analytics")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("analytics");
                    if (jSONObject2.has(UContent.f652n) && (optJSONObject2 = jSONObject2.getJSONArray(UContent.f652n).optJSONObject(0)) != null) {
                        String optString = optJSONObject2.optString("id");
                        if (!TextUtils.isEmpty(optString)) {
                            UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> removeAllInstantData: really delete instant session data");
                            UMStoreManager.m853a(f869a).mo522b(optString);
                        }
                    }
                }
                UMStoreManager.m853a(f869a).mo521b();
                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> removeAllInstantData: send INSTANT_SESSION_START_CONTINUE event because OVERSIZE.");
                Context context = f869a;
                UMWorkDispatch.sendEvent(context, C0113a.f909l, CoreProtocol.getInstance(context), null);
                return;
            }
            if (jSONObject.has("content")) {
                jSONObject = jSONObject.getJSONObject("content");
            }
            if (jSONObject.has("analytics") && (optJSONObject = jSONObject.optJSONObject("analytics")) != null && optJSONObject.length() > 0 && optJSONObject.has(UContent.f652n)) {
                UMStoreManager.m853a(f869a).mo517a(true, false);
            }
            UMStoreManager.m853a(f869a).mo521b();
        } catch (Exception e) {
        }
    }

    /* renamed from: g */
    private void m939g(JSONObject jSONObject) {
        JSONObject optJSONObject;
        try {
            if (jSONObject.getJSONObject("header").has(UContent.f607aB)) {
                if (jSONObject.has("content")) {
                    jSONObject = jSONObject.getJSONObject("content");
                }
                if (!jSONObject.has("analytics")) {
                    return;
                }
                if (jSONObject.getJSONObject("analytics").has(UContent.f652n)) {
                    UMStoreManager.m853a(f869a).mo532i();
                    UMStoreManager.m853a(f869a).mo531h();
                    UMStoreManager.m853a(f869a).mo523b(true, false);
                    UMStoreManager.m853a(f869a).mo514a();
                    return;
                }
                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> Error, Should not go to this branch.");
                return;
            }
            if (jSONObject.has("content")) {
                jSONObject = jSONObject.getJSONObject("content");
            }
            if (jSONObject.has("analytics") && (optJSONObject = jSONObject.optJSONObject("analytics")) != null && optJSONObject.length() > 0) {
                if (optJSONObject.has(UContent.f652n)) {
                    UMStoreManager.m853a(f869a).mo523b(true, false);
                }
                if (optJSONObject.has("ekv") || optJSONObject.has(UContent.f598T)) {
                    UMStoreManager.m853a(f869a).mo531h();
                }
                if (optJSONObject.has(UContent.f593O)) {
                    UMStoreManager.m853a(f869a).mo532i();
                }
            }
            UMStoreManager.m853a(f869a).mo514a();
        } catch (Exception e) {
        }
    }

    /* renamed from: c */
    public void mo568c(Object obj) {
        mo565b(f869a);
        mo569d();
        if (m932d(false)) {
            m943j();
        }
    }

    /* renamed from: b */
    public void mo565b(Context context) {
        try {
            UMStoreManager.m853a(context).mo527d();
            m950q();
        } catch (Throwable th) {
        }
    }

    /* renamed from: e */
    public void mo571e() {
        if (m932d(false)) {
            m943j();
        }
    }

    /* renamed from: d */
    public void mo570d(Object obj) {
        m951r();
        m946m();
        m947n();
        mo561a(true);
    }

    /* renamed from: d */
    private boolean m932d(boolean z) {
        if (this.f881b == null) {
            this.f881b = new C0115c();
        }
        ReportPolicy.ReportStrategy c = this.f881b.mo577c();
        if (!(c instanceof ReportPolicy.DefconPolicy)) {
            return true;
        }
        if (z) {
            return ((ReportPolicy.DefconPolicy) c).shouldSendMessageByInstant();
        }
        return c.shouldSendMessage(false);
    }

    /* renamed from: a */
    public void mo560a(Object obj, boolean z) {
        if (z) {
            if (m932d(true)) {
                m942i();
            }
        } else if (UMEnvelopeBuild.isOnline(f869a) && m932d(true)) {
            m942i();
        }
    }

    /* renamed from: q */
    private void m950q() {
        if (this.f886g.length() > 0) {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.f886g.length(); i++) {
                try {
                    JSONObject jSONObject = this.f886g.getJSONObject(i);
                    if (jSONObject == null || jSONObject.length() <= 0) {
                        jSONArray.put(jSONObject);
                    } else {
                        String optString = jSONObject.optString("__i");
                        boolean isEmpty = TextUtils.isEmpty(optString);
                        String str = f877t;
                        if (isEmpty || str.equals(optString)) {
                            String b = SessionTracker.m1011a().mo596b();
                            if (!TextUtils.isEmpty(b)) {
                                str = b;
                            }
                            jSONObject.put("__i", str);
                        }
                        jSONArray.put(jSONObject);
                    }
                } catch (Throwable th) {
                }
            }
            this.f886g = jSONArray;
        }
    }

    /* renamed from: r */
    private void m951r() {
        Context context;
        SharedPreferences sharedPreferences;
        try {
            if (m952s() && (context = f869a) != null && (sharedPreferences = PreferenceWrapper.getDefault(context)) != null && sharedPreferences.getLong(f870l, 0) == 0) {
                sharedPreferences.edit().putLong(f870l, System.currentTimeMillis()).commit();
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: f */
    public long mo572f() {
        SharedPreferences sharedPreferences;
        try {
            Context context = f869a;
            if (context == null || (sharedPreferences = PreferenceWrapper.getDefault(context)) == null) {
                return 0;
            }
            long j = sharedPreferences.getLong(f870l, 0);
            if (j != 0) {
                return j;
            }
            try {
                long currentTimeMillis = System.currentTimeMillis();
                sharedPreferences.edit().putLong(f870l, currentTimeMillis).commit();
                return currentTimeMillis;
            } catch (Throwable th) {
                return j;
            }
        } catch (Throwable th2) {
            return 0;
        }
    }

    /* renamed from: s */
    private boolean m952s() {
        SharedPreferences sharedPreferences;
        try {
            Context context = f869a;
            if (context == null || (sharedPreferences = PreferenceWrapper.getDefault(context)) == null || sharedPreferences.getLong(f871m, -1) == 0) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    /* renamed from: f */
    private void m935f(Object obj) {
        try {
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject == null) {
                return;
            }
            if (jSONObject.length() > 0) {
                long j = jSONObject.getLong("ts");
                mo565b(f869a);
                mo569d();
                String[] a = InternalConfig.m385a(f869a);
                if (a != null && !TextUtils.isEmpty(a[0]) && !TextUtils.isEmpty(a[1])) {
                    SessionTracker.m1011a().mo592a(f869a, j);
                    String c = SessionIdManager.m1047a().mo618c(f869a);
                    UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> onProfileSignIn: force generate new session: session id = " + c);
                    boolean b = SessionTracker.m1011a().mo599b(f869a, j, false);
                    InternalConfig.m386b(f869a);
                    SessionTracker.m1011a().mo591a(f869a, j, true);
                    if (b) {
                        SessionTracker.m1011a().mo597b(f869a, j);
                    }
                }
            }
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.m1359e(" Excepthon  in  onProfileSignOff", th);
            }
        }
    }

    /* renamed from: g */
    private void m938g(Object obj) {
        try {
            mo565b(f869a);
            mo569d();
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject == null) {
                return;
            }
            if (jSONObject.length() > 0) {
                String string = jSONObject.getString(UContent.f591M);
                String string2 = jSONObject.getString("uid");
                long j = jSONObject.getLong("ts");
                String[] a = InternalConfig.m385a(f869a);
                if (a == null || !string.equals(a[0]) || !string2.equals(a[1])) {
                    SessionTracker.m1011a().mo592a(f869a, j);
                    String c = SessionIdManager.m1047a().mo618c(f869a);
                    boolean b = SessionTracker.m1011a().mo599b(f869a, j, false);
                    InternalConfig.m384a(f869a, string, string2);
                    UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> onProfileSignIn: force generate new session: session id = " + c);
                    SessionTracker.m1011a().mo591a(f869a, j, true);
                    if (b) {
                        SessionTracker.m1011a().mo597b(f869a, j);
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: h */
    private void m941h(Object obj) {
        try {
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject != null && jSONObject.length() > 0 && jSONObject.has("__ii")) {
                String optString = jSONObject.optString("__ii");
                jSONObject.remove("__ii");
                if (!TextUtils.isEmpty(optString)) {
                    UMStoreManager.m853a(f869a).mo518a(optString, obj.toString(), 2);
                }
            }
        } catch (Throwable th) {
        }
    }
}
