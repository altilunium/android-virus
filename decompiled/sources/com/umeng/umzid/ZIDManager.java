package com.umeng.umzid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.UUID;
import org.json.JSONObject;

public class ZIDManager {

    /* renamed from: c */
    public static ZIDManager f1677c;

    /* renamed from: a */
    public boolean f1678a = false;

    /* renamed from: b */
    public boolean f1679b = false;

    /* renamed from: com.umeng.umzid.ZIDManager$a */
    public class RunnableC0242a implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ Context f1680a;

        /* renamed from: b */
        public final /* synthetic */ IZIDCompletionCallback f1681b;

        public RunnableC0242a(Context context, IZIDCompletionCallback iZIDCompletionCallback) {
            this.f1680a = context;
            this.f1681b = iZIDCompletionCallback;
        }

        public void run() {
            String a = ZIDManager.m1784a(ZIDManager.this, this.f1680a);
            if (TextUtils.isEmpty(a)) {
                IZIDCompletionCallback iZIDCompletionCallback = this.f1681b;
                if (iZIDCompletionCallback != null) {
                    iZIDCompletionCallback.onFailure("1002", "获取zid失败");
                    return;
                }
                return;
            }
            IZIDCompletionCallback iZIDCompletionCallback2 = this.f1681b;
            if (iZIDCompletionCallback2 != null) {
                iZIDCompletionCallback2.onSuccess(a);
            }
        }
    }

    /* renamed from: com.umeng.umzid.ZIDManager$b */
    public class RunnableC0243b implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ Context f1683a;

        public RunnableC0243b(Context context) {
            this.f1683a = context;
        }

        public void run() {
            ZIDManager.m1785b(ZIDManager.this, this.f1683a);
        }
    }

    /* renamed from: com.umeng.umzid.ZIDManager$c */
    public class RunnableC0244c implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ Context f1685a;

        public RunnableC0244c(Context context) {
            this.f1685a = context;
        }

        public void run() {
            ZIDManager.m1784a(ZIDManager.this, this.f1685a);
        }
    }

    /* renamed from: com.umeng.umzid.ZIDManager$d */
    public class RunnableC0245d implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ Context f1687a;

        public RunnableC0245d(Context context) {
            this.f1687a = context;
        }

        public void run() {
            ZIDManager.m1785b(ZIDManager.this, this.f1687a);
        }
    }

    /* renamed from: a */
    public static /* synthetic */ String m1784a(ZIDManager zIDManager, Context context) {
        String str = null;
        if (!zIDManager.f1678a) {
            zIDManager.f1678a = true;
            JSONObject jSONObject = new JSONObject();
            try {
                String id = Spy.getID();
                jSONObject.put("zdata", id);
                String c = C0250c.m1795c(context);
                jSONObject.put("mac", c);
                String d = C0250c.m1797d(context);
                jSONObject.put("oaid", d);
                zIDManager.mo1026a(context, jSONObject);
                String a = C0246a.m1788a("https://aaid.umeng.com/api/postZdata", jSONObject.toString());
                if (!TextUtils.isEmpty(a)) {
                    JSONObject jSONObject2 = new JSONObject(a);
                    if (Boolean.valueOf(jSONObject2.optBoolean("suc")).booleanValue()) {
                        C0250c.m1800f(context, id);
                        C0250c.m1792a(context, c);
                        C0250c.m1794b(context, d);
                        str = jSONObject2.optString("aaid");
                        if (!TextUtils.isEmpty(str)) {
                            C0250c.m1799e(context, str);
                        }
                        String string = jSONObject2.getString("uabc");
                        if (!TextUtils.isEmpty(string)) {
                            C0250c.m1798d(context, string);
                        }
                        String string2 = jSONObject2.getString("resetToken");
                        if (!TextUtils.isEmpty(string2)) {
                            C0250c.m1796c(context, string2);
                        }
                    }
                }
            } catch (Throwable th) {
            }
            zIDManager.f1678a = false;
        }
        return str;
    }

    /* renamed from: b */
    public static /* synthetic */ String m1785b(ZIDManager zIDManager, Context context) {
        SharedPreferences a;
        SharedPreferences a2;
        SharedPreferences a3;
        SharedPreferences a4;
        String str = null;
        if (!zIDManager.f1679b) {
            zIDManager.f1679b = true;
            JSONObject jSONObject = new JSONObject();
            try {
                Object b = C0250c.m1793b(context);
                String id = Spy.getID();
                jSONObject.put("zdata", id);
                jSONObject.put("old_zdata", b);
                String str2 = "";
                Object string = (context == null || (a4 = C0246a.m1787a(context)) == null) ? str2 : a4.getString("oaid", str2);
                String d = C0250c.m1797d(context);
                jSONObject.put("old_oaid", string);
                jSONObject.put("oaid", d);
                Object string2 = (context == null || (a3 = C0246a.m1787a(context)) == null) ? str2 : a3.getString("mac", str2);
                String c = C0250c.m1795c(context);
                jSONObject.put("mac", c);
                jSONObject.put("old_mac", string2);
                zIDManager.mo1026a(context, jSONObject);
                jSONObject.put("aaid", C0250c.m1791a(context));
                jSONObject.put("uabc", (context == null || (a2 = C0246a.m1787a(context)) == null) ? str2 : a2.getString("uabc", str2));
                if (!(context == null || (a = C0246a.m1787a(context)) == null)) {
                    str2 = a.getString("resetToken", str2);
                }
                if (!TextUtils.isEmpty(str2)) {
                    jSONObject.put("resetToken", str2);
                }
                String a5 = C0246a.m1788a("https://aaid.umeng.com/api/updateZdata", jSONObject.toString());
                if (!TextUtils.isEmpty(a5)) {
                    JSONObject jSONObject2 = new JSONObject(a5);
                    if (Boolean.valueOf(jSONObject2.optBoolean("suc")).booleanValue()) {
                        C0250c.m1800f(context, id);
                        C0250c.m1792a(context, c);
                        C0250c.m1794b(context, d);
                        str = jSONObject2.optString("aaid");
                        if (!TextUtils.isEmpty(str)) {
                            C0250c.m1799e(context, str);
                        }
                        String string3 = jSONObject2.getString("uabc");
                        if (!TextUtils.isEmpty(string3)) {
                            C0250c.m1798d(context, string3);
                        }
                        String string4 = jSONObject2.getString("resetToken");
                        if (!TextUtils.isEmpty(string4)) {
                            C0250c.m1796c(context, string4);
                        }
                    }
                }
            } catch (Throwable th) {
            }
            zIDManager.f1679b = false;
        }
        return str;
    }

    public static synchronized ZIDManager getInstance() {
        ZIDManager zIDManager;
        synchronized (ZIDManager.class) {
            if (f1677c == null) {
                f1677c = new ZIDManager();
            }
            zIDManager = f1677c;
        }
        return zIDManager;
    }

    public static String getSDKVersion() {
        return "1.2.1";
    }

    public synchronized String getZID(Context context) {
        SharedPreferences a;
        if (context == null) {
            return "";
        }
        Context applicationContext = context.getApplicationContext();
        String a2 = C0250c.m1791a(applicationContext);
        if (TextUtils.isEmpty(a2)) {
            C0248b.m1790a(new RunnableC0244c(applicationContext));
            return "";
        }
        if (!((applicationContext == null || (a = C0246a.m1787a(applicationContext)) == null) ? "" : a.getString("zdata", null)).equals(Spy.getID())) {
            C0248b.m1790a(new RunnableC0245d(applicationContext));
        }
        return a2;
    }

    public synchronized void init(Context context, String str, IZIDCompletionCallback iZIDCompletionCallback) {
        SharedPreferences a;
        SharedPreferences.Editor edit;
        if (context == null) {
            if (iZIDCompletionCallback != null) {
                iZIDCompletionCallback.onFailure("1001", "传入参数Context为null");
            }
        } else if (TextUtils.isEmpty(str)) {
            if (iZIDCompletionCallback != null) {
                iZIDCompletionCallback.onFailure("1003", "传入参数appkey为空");
            }
        } else {
            Context applicationContext = context.getApplicationContext();
            if (!(applicationContext == null || str == null || TextUtils.isEmpty(str) || (a = C0246a.m1787a(applicationContext)) == null || (edit = a.edit()) == null)) {
                edit.putString("appkey", str).commit();
            }
            String a2 = C0250c.m1791a(applicationContext);
            if (a2 == null || TextUtils.isEmpty(a2)) {
                C0248b.m1790a(new RunnableC0242a(applicationContext, iZIDCompletionCallback));
            } else {
                C0248b.m1790a(new RunnableC0243b(applicationContext));
                if (iZIDCompletionCallback != null) {
                    iZIDCompletionCallback.onSuccess(a2);
                }
            }
            String str2 = "";
            SharedPreferences a3 = C0246a.m1787a(context);
            if (a3 != null) {
                str2 = a3.getString("uuid", "");
            }
            if (TextUtils.isEmpty(str2)) {
                String str3 = "";
                SharedPreferences a4 = C0246a.m1787a(context);
                try {
                    str3 = UUID.randomUUID().toString();
                } catch (Throwable th) {
                }
                if (a4 != null) {
                    a4.edit().putString("uuid", str3).commit();
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0074 A[SYNTHETIC, Splitter:B:22:0x0074] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0102 A[SYNTHETIC, Splitter:B:48:0x0102] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x014a  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0164  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final org.json.JSONObject mo1026a(android.content.Context r9, org.json.JSONObject r10) {
        /*
        // Method dump skipped, instructions count: 475
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.umzid.ZIDManager.mo1026a(android.content.Context, org.json.JSONObject):org.json.JSONObject");
    }
}
