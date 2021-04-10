package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.CoreProtocol;
import com.umeng.analytics.pro.CoreProtocolImpl;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.umeng.analytics.pro.r */
public class EventTracker {

    /* renamed from: a */
    private static final String f943a = "fs_lc_tl_uapp";

    /* renamed from: f */
    private static final String f944f = "-1";

    /* renamed from: g */
    private static Context f945g;

    /* renamed from: b */
    private final int f946b;

    /* renamed from: c */
    private final int f947c;

    /* renamed from: d */
    private final int f948d;

    /* renamed from: e */
    private final int f949e;

    /* renamed from: h */
    private JSONObject f950h;

    private EventTracker() {
        this.f946b = 128;
        this.f947c = 256;
        this.f948d = 1024;
        this.f949e = 10;
        this.f950h = null;
        if (0 == 0) {
            try {
                m999b(f945g);
            } catch (Throwable th) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.analytics.pro.r$a */
    /* compiled from: EventTracker */
    public class C0121a {

        /* renamed from: a */
        private static final EventTracker f951a = new EventTracker();

        private C0121a() {
        }
    }

    /* renamed from: a */
    public static EventTracker m993a(Context context) {
        if (f945g == null && context != null) {
            f945g = context.getApplicationContext();
        }
        return C0121a.f951a;
    }

    /* renamed from: a */
    public void mo587a(String str, String str2, long j, int i, String str3) {
        String str4;
        try {
            if (!m998a(str) || !m1000b(str2)) {
                UMLog.m1136aq(UMLogAnalytics.f827l, 0, "\\|");
            } else if (Arrays.asList(UContent.f612aG).contains(str)) {
                MLog.m1357e("key is " + str + ", please check key, illegal");
                UMLog.m1136aq(UMLogAnalytics.f828m, 0, "\\|");
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", str);
                jSONObject.put("ts", currentTimeMillis);
                if (j > 0) {
                    jSONObject.put(UContent.f601W, j);
                }
                jSONObject.put("__t", UMStoreManager.f752a);
                if (!TextUtils.isEmpty(str2)) {
                    jSONObject.put(str, str2);
                }
                if (UMUtils.isMainProgress(f945g)) {
                    str4 = SessionIdManager.m1047a().mo619d(UMGlobalContext.getAppContext(f945g));
                } else {
                    str4 = SessionIdManager.m1047a().mo612a(UMGlobalContext.getAppContext(f945g), currentTimeMillis);
                }
                if (TextUtils.isEmpty(str4)) {
                    str4 = f944f;
                }
                jSONObject.put("__i", str4);
                if (!TextUtils.isEmpty(str3)) {
                    try {
                        JSONObject jSONObject2 = new JSONObject(str3);
                        if (jSONObject2.length() > 0) {
                            jSONObject.put(UContent.f634au, jSONObject2);
                        }
                    } catch (JSONException e) {
                    }
                }
                jSONObject.put("ds", 0);
                jSONObject.put("pn", UMGlobalContext.getInstance(f945g).getProcessName(f945g));
                m995a();
                JSONObject jSONObject3 = this.f950h;
                if (jSONObject3 != null && jSONObject3.has(str) && !((Boolean) this.f950h.get(str)).booleanValue()) {
                    jSONObject.put(UContent.f603Y, 1);
                    this.f950h.put(str, true);
                    m1002c(f945g);
                }
                Context context = f945g;
                UMWorkDispatch.sendEvent(context, CoreProtocolImpl.C0113a.f898a, CoreProtocol.getInstance(context), jSONObject);
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public void mo588a(String str, Map map, long j, String str2, boolean z) {
        String str3;
        try {
            if (!m998a(str)) {
                UMLog.m1136aq(UMLogAnalytics.f821f, 0, "\\|");
            } else if (m1001b(map)) {
                if (map.size() > 100) {
                    MLog.m1357e("map size is " + map.size() + ", please check");
                } else if (Arrays.asList(UContent.f612aG).contains(str)) {
                    MLog.m1357e("key is " + str + ", please check key, illegal");
                    UMLog.m1136aq(UMLogAnalytics.f817b, 0, "\\|");
                } else {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("id", str);
                    jSONObject.put("ts", System.currentTimeMillis());
                    if (j > 0) {
                        jSONObject.put(UContent.f601W, j);
                    }
                    jSONObject.put("__t", UMStoreManager.f752a);
                    ULog.m1393i("befort ekv map, event is " + jSONObject.toString());
                    for (Map.Entry entry : map.entrySet()) {
                        if (!Arrays.asList(UContent.f612aG).contains(entry.getKey())) {
                            Object value = entry.getValue();
                            if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long) || (value instanceof Short) || (value instanceof Float) || (value instanceof Double)) {
                                jSONObject.put((String) entry.getKey(), value);
                            } else if (!value.getClass().isArray()) {
                                MLog.m1357e("please check key or value, illegal type!");
                                return;
                            } else if (value instanceof int[]) {
                                int[] iArr = (int[]) value;
                                if (iArr.length > 10) {
                                    MLog.m1357e("please check key or value, size overlength!");
                                    return;
                                }
                                JSONArray jSONArray = new JSONArray();
                                for (int i : iArr) {
                                    jSONArray.put(i);
                                }
                                jSONObject.put((String) entry.getKey(), jSONArray);
                            } else if (value instanceof double[]) {
                                double[] dArr = (double[]) value;
                                if (dArr.length > 10) {
                                    MLog.m1357e("please check key or value, size overlength!");
                                    return;
                                }
                                JSONArray jSONArray2 = new JSONArray();
                                for (double d : dArr) {
                                    jSONArray2.put(d);
                                }
                                jSONObject.put((String) entry.getKey(), jSONArray2);
                            } else if (value instanceof long[]) {
                                long[] jArr = (long[]) value;
                                if (jArr.length > 10) {
                                    MLog.m1357e("please check key or value, size overlength!");
                                    return;
                                }
                                JSONArray jSONArray3 = new JSONArray();
                                for (long j2 : jArr) {
                                    jSONArray3.put(j2);
                                }
                                jSONObject.put((String) entry.getKey(), jSONArray3);
                            } else if (value instanceof float[]) {
                                float[] fArr = (float[]) value;
                                if (fArr.length > 10) {
                                    MLog.m1357e("please check key or value, size overlength!");
                                    return;
                                }
                                JSONArray jSONArray4 = new JSONArray();
                                for (float f : fArr) {
                                    jSONArray4.put((double) f);
                                }
                                jSONObject.put((String) entry.getKey(), jSONArray4);
                            } else if (value instanceof short[]) {
                                short[] sArr = (short[]) value;
                                if (sArr.length > 10) {
                                    MLog.m1357e("please check key or value, size overlength!");
                                    return;
                                }
                                JSONArray jSONArray5 = new JSONArray();
                                for (short s : sArr) {
                                    jSONArray5.put((int) s);
                                }
                                jSONObject.put((String) entry.getKey(), jSONArray5);
                            } else if (value instanceof String[]) {
                                String[] strArr = (String[]) value;
                                if (strArr.length > 10) {
                                    MLog.m1357e("please check key or value, size overlength!");
                                    return;
                                }
                                JSONArray jSONArray6 = new JSONArray();
                                for (int i2 = 0; i2 < strArr.length; i2++) {
                                    if (strArr[i2] == null) {
                                        MLog.m1357e("please check array, null item!");
                                        return;
                                    } else if (m1000b(strArr[i2])) {
                                        jSONArray6.put(strArr[i2]);
                                    } else {
                                        return;
                                    }
                                }
                                jSONObject.put((String) entry.getKey(), jSONArray6);
                            } else {
                                MLog.m1357e("please check key or value, illegal type!");
                                return;
                            }
                        } else {
                            UMLog.m1136aq(UMLogAnalytics.f820e, 0, "\\|");
                            return;
                        }
                    }
                    if (UMUtils.isMainProgress(f945g)) {
                        str3 = SessionIdManager.m1047a().mo619d(UMGlobalContext.getAppContext(f945g));
                    } else {
                        str3 = SessionIdManager.m1047a().mo612a(UMGlobalContext.getAppContext(f945g), jSONObject.getLong("ts"));
                    }
                    if (TextUtils.isEmpty(str3)) {
                        str3 = f944f;
                    }
                    jSONObject.put("__i", str3);
                    if (!TextUtils.isEmpty(str2)) {
                        try {
                            JSONObject jSONObject2 = new JSONObject(str2);
                            if (jSONObject2.length() > 0) {
                                jSONObject.put(UContent.f634au, jSONObject2);
                            }
                        } catch (JSONException e) {
                        }
                    }
                    jSONObject.put("ds", 0);
                    jSONObject.put("pn", UMGlobalContext.getInstance(f945g).getProcessName(f945g));
                    m995a();
                    JSONObject jSONObject3 = this.f950h;
                    if (jSONObject3 != null && jSONObject3.has(str) && !((Boolean) this.f950h.get(str)).booleanValue()) {
                        jSONObject.put(UContent.f603Y, 1);
                        this.f950h.put(str, true);
                        m1002c(f945g);
                    }
                    ULog.m1393i("----->>>>>ekv event json is " + jSONObject.toString());
                    if (!z) {
                        Context context = f945g;
                        UMWorkDispatch.sendEvent(context, CoreProtocolImpl.C0113a.f898a, CoreProtocol.getInstance(context), jSONObject);
                        return;
                    }
                    Context context2 = f945g;
                    UMWorkDispatch.sendEvent(context2, CoreProtocolImpl.C0113a.f911n, CoreProtocol.getInstance(context2), jSONObject);
                }
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public void mo589a(String str, Map map, String str2) {
        try {
            if (m998a(str)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", str);
                jSONObject.put("ts", System.currentTimeMillis());
                jSONObject.put(UContent.f601W, 0);
                jSONObject.put("__t", UMStoreManager.f753b);
                ULog.m1393i("befort gkv map, event is " + jSONObject.toString());
                Iterator it = map.entrySet().iterator();
                for (int i = 0; i < 10 && it.hasNext(); i++) {
                    Map.Entry entry = (Map.Entry) it.next();
                    if (!UContent.f603Y.equals(entry.getKey()) && !UContent.f601W.equals(entry.getKey()) && !"id".equals(entry.getKey()) && !"ts".equals(entry.getKey())) {
                        Object value = entry.getValue();
                        if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long)) {
                            jSONObject.put((String) entry.getKey(), value);
                        }
                    }
                }
                String d = SessionIdManager.m1047a().mo619d(UMGlobalContext.getAppContext(f945g));
                if (TextUtils.isEmpty(d)) {
                    d = f944f;
                }
                jSONObject.put("__i", d);
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        JSONObject jSONObject2 = new JSONObject(str2);
                        if (jSONObject2.length() > 0) {
                            jSONObject.put(UContent.f634au, jSONObject2);
                        }
                    } catch (JSONException e) {
                    }
                }
                jSONObject.put("ds", 0);
                jSONObject.put("pn", UMGlobalContext.getInstance(f945g).getProcessName(f945g));
                ULog.m1393i("----->>>>>gkv event json is " + jSONObject.toString());
                Context context = f945g;
                UMWorkDispatch.sendEvent(context, CoreProtocolImpl.C0113a.f899b, CoreProtocol.getInstance(context), jSONObject);
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    private void m999b(Context context) {
        try {
            String string = PreferenceWrapper.getDefault(context).getString(f943a, null);
            if (!TextUtils.isEmpty(string)) {
                this.f950h = new JSONObject(string);
            }
            m995a();
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    private void m995a() {
        try {
            String imprintProperty = UMEnvelopeBuild.imprintProperty(f945g, "track_list", "");
            if (!TextUtils.isEmpty(imprintProperty)) {
                String[] split = imprintProperty.split("!");
                JSONObject jSONObject = new JSONObject();
                int i = 0;
                if (this.f950h != null) {
                    for (String str : split) {
                        String subStr = HelperUtils.subStr(str, 128);
                        if (this.f950h.has(subStr)) {
                            jSONObject.put(subStr, this.f950h.get(subStr));
                        }
                    }
                }
                this.f950h = new JSONObject();
                if (split.length >= 10) {
                    while (i < 10) {
                        m996a(split[i], jSONObject);
                        i++;
                    }
                } else {
                    while (i < split.length) {
                        m996a(split[i], jSONObject);
                        i++;
                    }
                }
                m1002c(f945g);
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    private void m996a(String str, JSONObject jSONObject) {
        String subStr = HelperUtils.subStr(str, 128);
        if (jSONObject.has(subStr)) {
            m997a(subStr, ((Boolean) jSONObject.get(subStr)).booleanValue());
        } else {
            m997a(subStr, false);
        }
    }

    /* renamed from: a */
    private void m997a(String str, boolean z) {
        try {
            if (!UContent.f603Y.equals(str) && !UContent.f601W.equals(str) && !"id".equals(str) && !"ts".equals(str) && !this.f950h.has(str)) {
                this.f950h.put(str, z);
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: c */
    private void m1002c(Context context) {
        try {
            if (this.f950h != null) {
                PreferenceWrapper.getDefault(f945g).edit().putString(f943a, this.f950h.toString()).commit();
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public void mo590a(List list) {
        if (list != null) {
            try {
                if (list.size() > 0) {
                    m995a();
                    JSONObject jSONObject = this.f950h;
                    if (jSONObject == null) {
                        this.f950h = new JSONObject();
                        int size = list.size();
                        int i = 0;
                        while (true) {
                            if (i >= size) {
                                break;
                            }
                            JSONObject jSONObject2 = this.f950h;
                            if (jSONObject2 != null) {
                                if (jSONObject2.length() >= 5) {
                                    break;
                                }
                            } else {
                                this.f950h = new JSONObject();
                            }
                            String str = (String) list.get(i);
                            if (!TextUtils.isEmpty(str)) {
                                m997a(HelperUtils.subStr(str, 128), false);
                            }
                            i++;
                        }
                        m1002c(f945g);
                    } else if (jSONObject.length() >= 5) {
                        MLog.m1351d("already setFistLaunchEvent, igone.");
                        return;
                    } else {
                        for (int i2 = 0; i2 < list.size(); i2++) {
                            if (this.f950h.length() >= 5) {
                                MLog.m1351d(" add setFistLaunchEvent over.");
                                return;
                            } else {
                                m997a(HelperUtils.subStr((String) list.get(i2), 128), false);
                            }
                        }
                        m1002c(f945g);
                    }
                    return;
                }
            } catch (Exception e) {
                return;
            }
        }
        UMLog.m1136aq(UMLogAnalytics.f809ak, 0, "\\|");
    }

    /* renamed from: a */
    private JSONObject m994a(Map map) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry entry : map.entrySet()) {
                try {
                    String str = (String) entry.getKey();
                    if (str != null) {
                        String subStr = HelperUtils.subStr(str, 128);
                        Object value = entry.getValue();
                        if (value != null) {
                            int i = 0;
                            if (value.getClass().isArray()) {
                                if (value instanceof int[]) {
                                    int[] iArr = (int[]) value;
                                    JSONArray jSONArray = new JSONArray();
                                    while (i < iArr.length) {
                                        jSONArray.put(iArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(subStr, jSONArray);
                                } else if (value instanceof double[]) {
                                    double[] dArr = (double[]) value;
                                    JSONArray jSONArray2 = new JSONArray();
                                    while (i < dArr.length) {
                                        jSONArray2.put(dArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(subStr, jSONArray2);
                                } else if (value instanceof long[]) {
                                    long[] jArr = (long[]) value;
                                    JSONArray jSONArray3 = new JSONArray();
                                    while (i < jArr.length) {
                                        jSONArray3.put(jArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(subStr, jSONArray3);
                                } else if (value instanceof float[]) {
                                    float[] fArr = (float[]) value;
                                    JSONArray jSONArray4 = new JSONArray();
                                    while (i < fArr.length) {
                                        jSONArray4.put((double) fArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(subStr, jSONArray4);
                                } else if (value instanceof short[]) {
                                    short[] sArr = (short[]) value;
                                    JSONArray jSONArray5 = new JSONArray();
                                    while (i < sArr.length) {
                                        jSONArray5.put((int) sArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(subStr, jSONArray5);
                                }
                            } else if (value instanceof List) {
                                List list = (List) value;
                                JSONArray jSONArray6 = new JSONArray();
                                while (i < list.size()) {
                                    Object obj = list.get(i);
                                    if ((obj instanceof String) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof Short)) {
                                        jSONArray6.put(list.get(i));
                                    }
                                    i++;
                                }
                                if (jSONArray6.length() > 0) {
                                    jSONObject.put(subStr, jSONArray6);
                                }
                            } else if (value instanceof String) {
                                jSONObject.put(subStr, HelperUtils.subStr(value.toString(), 256));
                            } else {
                                if (!(value instanceof Long) && !(value instanceof Integer) && !(value instanceof Float) && !(value instanceof Double)) {
                                    if (!(value instanceof Short)) {
                                        MLog.m1357e("The param has not support type. please check !");
                                    }
                                }
                                jSONObject.put(subStr, value);
                            }
                        }
                    }
                } catch (Exception e) {
                    MLog.m1361e(e);
                }
            }
        } catch (Exception e2) {
        }
        return jSONObject;
    }

    /* renamed from: a */
    private boolean m998a(String str) {
        if (str != null) {
            try {
                int length = str.trim().getBytes().length;
                if (length > 0 && length <= 128) {
                    return true;
                }
            } catch (Exception e) {
            }
        }
        MLog.m1357e("key is " + str + ", please check key, illegal");
        return false;
    }

    /* renamed from: b */
    private boolean m1000b(String str) {
        if (str == null) {
            return true;
        }
        try {
            if (str.trim().getBytes().length <= 256) {
                return true;
            }
        } catch (Exception e) {
        }
        MLog.m1357e("value is " + str + ", please check value, illegal");
        return false;
    }

    /* renamed from: c */
    private boolean m1003c(String str) {
        if (str == null) {
            return true;
        }
        try {
            if (str.trim().getBytes().length <= 1024) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: b */
    private boolean m1001b(Map map) {
        if (map != null) {
            try {
                if (!map.isEmpty()) {
                    for (Map.Entry entry : map.entrySet()) {
                        if (!m998a((String) entry.getKey())) {
                            UMLog.m1136aq(UMLogAnalytics.f823h, 0, "\\|");
                            return false;
                        } else if (entry.getValue() == null) {
                            UMLog.m1136aq(UMLogAnalytics.f824i, 0, "\\|");
                            return false;
                        } else if (entry.getValue() instanceof String) {
                            if (UContent.f610aE.equals(entry.getKey())) {
                                if (!m1003c(entry.getValue().toString())) {
                                    UMLog.m1136aq(UMLogAnalytics.f787P, 0, "\\|");
                                    return false;
                                }
                            } else if ("_$!url".equals(entry.getKey())) {
                                if (!m1003c(entry.getValue().toString())) {
                                    UMLog.m1136aq("url参数长度超过限制。|参数url长度不能超过1024字符。", 0, "\\|");
                                    return false;
                                }
                            } else if (!m1000b(entry.getValue().toString())) {
                                UMLog.m1136aq(UMLogAnalytics.f825j, 0, "\\|");
                                return false;
                            }
                        }
                    }
                    return true;
                }
            } catch (Exception e) {
                return true;
            }
        }
        UMLog.m1136aq(UMLogAnalytics.f822g, 0, "\\|");
        return false;
    }
}
