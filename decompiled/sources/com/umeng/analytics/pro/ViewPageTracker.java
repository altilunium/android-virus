package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.CoreProtocol;
import com.umeng.analytics.pro.CoreProtocolImpl;
import com.umeng.analytics.pro.UMStoreManager;
import com.umeng.analytics.vshelper.PageLifeCycle;
import com.umeng.analytics.vshelper.PageNameMonitor;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.umeng.analytics.pro.u */
public class ViewPageTracker {

    /* renamed from: c */
    private static final int f966c = 5;

    /* renamed from: d */
    private static JSONArray f967d = new JSONArray();

    /* renamed from: e */
    private static Object f968e = new Object();

    /* renamed from: a */
    Stack f969a = new Stack();

    /* renamed from: b */
    PageLifeCycle f970b = PageNameMonitor.getInstance();

    /* renamed from: f */
    private final Map f971f = new HashMap();

    /* renamed from: a */
    public static void m1031a(Context context) {
        String jSONArray;
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                synchronized (f968e) {
                    jSONArray = f967d.toString();
                    f967d = new JSONArray();
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("__a", new JSONArray(jSONArray));
                    if (jSONObject.length() > 0) {
                        UMStoreManager.m853a(context).mo519a(SessionTracker.m1011a().mo600c(), jSONObject, UMStoreManager.EnumC0107a.PAGE);
                    }
                }
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: a */
    public void mo603a(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (UMConfigure.isDebugLog() && this.f969a.size() != 0) {
                UMLog.m1138aq(UMLogAnalytics.f777F, 0, "\\|", new String[]{"@"}, new String[]{(String) this.f969a.peek()}, null, null);
            }
            this.f970b.customPageBegin(str);
            synchronized (this.f971f) {
                this.f971f.put(str, Long.valueOf(System.currentTimeMillis()));
                if (UMConfigure.isDebugLog()) {
                    this.f969a.push(str);
                }
            }
        }
    }

    /* renamed from: b */
    public void mo605b(String str) {
        Long l;
        Context appContext;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.f971f.containsKey(str)) {
            synchronized (this.f971f) {
                l = (Long) this.f971f.get(str);
            }
            if (l != null) {
                if (UMConfigure.isDebugLog() && this.f969a.size() > 0 && str.equals(this.f969a.peek())) {
                    this.f969a.pop();
                }
                long currentTimeMillis = System.currentTimeMillis() - l.longValue();
                synchronized (f968e) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(UContent.f660v, str);
                        jSONObject.put("duration", currentTimeMillis);
                        jSONObject.put(UContent.f662x, l);
                        jSONObject.put(UContent.f663y, mo534a());
                        f967d.put(jSONObject);
                        if (f967d.length() >= 5 && (appContext = UMGlobalContext.getAppContext(null)) != null) {
                            UMWorkDispatch.sendEvent(appContext, CoreProtocolImpl.C0113a.f900c, CoreProtocol.getInstance(appContext), null);
                        }
                    } catch (Throwable th) {
                    }
                }
                if (UMConfigure.isDebugLog() && this.f969a.size() != 0) {
                    UMLog.m1138aq(UMLogAnalytics.f776E, 0, "\\|", new String[]{"@"}, new String[]{str}, null, null);
                }
            }
        } else if (UMConfigure.isDebugLog() && this.f969a.size() == 0) {
            UMLog.m1138aq(UMLogAnalytics.f778G, 0, "\\|", new String[]{"@"}, new String[]{str}, null, null);
        }
    }

    /* renamed from: b */
    public void mo604b() {
        String str;
        synchronized (this.f971f) {
            str = null;
            long j = 0;
            for (Map.Entry entry : this.f971f.entrySet()) {
                if (((Long) entry.getValue()).longValue() > j) {
                    long longValue = ((Long) entry.getValue()).longValue();
                    str = (String) entry.getKey();
                    j = longValue;
                }
            }
        }
        if (str != null) {
            mo605b(str);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public int mo534a() {
        return 2;
    }
}
