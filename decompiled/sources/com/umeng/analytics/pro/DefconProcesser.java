package com.umeng.analytics.pro;

import android.content.Context;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.service.UMGlobalContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.umeng.analytics.pro.q */
public class DefconProcesser {

    /* renamed from: a */
    private static final int f937a = 0;

    /* renamed from: b */
    private static final int f938b = 1;

    /* renamed from: c */
    private static final int f939c = 2;

    /* renamed from: d */
    private static final int f940d = 3;

    /* renamed from: e */
    private final long f941e;

    private DefconProcesser() {
        this.f941e = 60000;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.analytics.pro.q$a */
    /* compiled from: DefconProcesser */
    public class C0119a {

        /* renamed from: a */
        public static final DefconProcesser f942a = new DefconProcesser();

        private C0119a() {
        }
    }

    /* renamed from: a */
    public static DefconProcesser m986a() {
        return C0119a.f942a;
    }

    /* renamed from: a */
    public int mo584a(Context context) {
        return Integer.valueOf(UMEnvelopeBuild.imprintProperty(context, "defcon", String.valueOf(0))).intValue();
    }

    /* renamed from: a */
    private void m987a(JSONObject jSONObject, boolean z) {
        if (!z && jSONObject.has(UContent.f652n)) {
            jSONObject.remove(UContent.f652n);
        }
        if (jSONObject.has(UContent.f590L)) {
            jSONObject.remove(UContent.f590L);
        }
        if (jSONObject.has(UContent.f593O)) {
            jSONObject.remove(UContent.f593O);
        }
        if (jSONObject.has("ekv")) {
            jSONObject.remove("ekv");
        }
        if (jSONObject.has(UContent.f598T)) {
            jSONObject.remove(UContent.f598T);
        }
        if (jSONObject.has(UContent.f590L)) {
            jSONObject.remove(UContent.f590L);
        }
        if (jSONObject.has("userlevel")) {
            jSONObject.remove("userlevel");
        }
    }

    /* renamed from: a */
    public void mo585a(JSONObject jSONObject, Context context) {
        int a = mo584a(context);
        if (a == 1) {
            m987a(jSONObject, true);
            UMStoreManager.m853a(context).mo523b(false, true);
        } else if (a == 2) {
            jSONObject.remove(UContent.f652n);
            try {
                jSONObject.put(UContent.f652n, m988b());
            } catch (Exception e) {
            }
            m987a(jSONObject, true);
            UMStoreManager.m853a(context).mo523b(false, true);
        } else if (a == 3) {
            m987a(jSONObject, false);
            UMStoreManager.m853a(context).mo523b(false, true);
        }
    }

    /* renamed from: b */
    public void mo586b(JSONObject jSONObject, Context context) {
        int a = mo584a(context);
        if (a == 1) {
            if (jSONObject.has(UContent.f590L)) {
                jSONObject.remove(UContent.f590L);
            }
            if (jSONObject.has(UContent.f652n)) {
                try {
                    JSONArray jSONArray = jSONObject.getJSONArray(UContent.f652n);
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        if (jSONObject2.has(UContent.f634au)) {
                            jSONObject2.remove(UContent.f634au);
                        }
                        if (jSONObject2.has(UContent.f635av)) {
                            jSONObject2.remove(UContent.f635av);
                        }
                    }
                } catch (JSONException e) {
                }
            }
            UMStoreManager.m853a(context).mo517a(false, true);
        } else if (a == 2) {
            if (jSONObject.has(UContent.f590L)) {
                jSONObject.remove(UContent.f590L);
            }
            if (jSONObject.has(UContent.f652n)) {
                jSONObject.remove(UContent.f652n);
            }
            try {
                jSONObject.put(UContent.f652n, m989c());
            } catch (Exception e2) {
            }
            UMStoreManager.m853a(context).mo517a(false, true);
        } else if (a == 3) {
            if (jSONObject.has(UContent.f590L)) {
                jSONObject.remove(UContent.f590L);
            }
            jSONObject.remove(UContent.f652n);
            UMStoreManager.m853a(context).mo517a(false, true);
        }
    }

    /* renamed from: b */
    private JSONArray m988b() {
        JSONArray jSONArray = new JSONArray();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", SessionIdManager.m1047a().mo611a(UMGlobalContext.getAppContext(null)));
            jSONObject.put(UContent.f654p, currentTimeMillis);
            jSONObject.put(UContent.f655q, currentTimeMillis + 60000);
            jSONObject.put("duration", 60000L);
            jSONArray.put(jSONObject);
        } catch (JSONException e) {
        }
        return jSONArray;
    }

    /* renamed from: c */
    private JSONArray m989c() {
        JSONArray jSONArray = new JSONArray();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", SessionIdManager.m1047a().mo619d(UMGlobalContext.getAppContext(null)));
            jSONObject.put(UContent.f654p, currentTimeMillis);
            jSONArray.put(jSONObject);
        } catch (JSONException e) {
        }
        return jSONArray;
    }
}
