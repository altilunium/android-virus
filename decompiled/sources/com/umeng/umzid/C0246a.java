package com.umeng.umzid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* renamed from: com.umeng.umzid.a */
public class C0246a {

    /* renamed from: com.umeng.umzid.a$a */
    public final class C0247a implements HostnameVerifier {
        public boolean verify(String str, SSLSession sSLSession) {
            if (!TextUtils.isEmpty(str)) {
                return "aaid.umeng.com".equalsIgnoreCase(str) || "pre-aaid.umeng.com".equalsIgnoreCase(str);
            }
            return false;
        }
    }

    /* renamed from: a */
    public static SharedPreferences m1787a(Context context) {
        if (context != null) {
            return context.getSharedPreferences("umzid_general_config", 0);
        }
        return null;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.lang.String m1788a(java.lang.String r4, java.lang.String r5) {
        /*
        // Method dump skipped, instructions count: 134
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.umzid.C0246a.m1788a(java.lang.String, java.lang.String):java.lang.String");
    }
}
