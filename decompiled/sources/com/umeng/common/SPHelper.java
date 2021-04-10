package com.umeng.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;

/* renamed from: com.umeng.common.b */
public final class SPHelper {

    /* renamed from: a */
    private static SPHelper f1031a = null;

    /* renamed from: b */
    private static Context f1032b = null;

    /* renamed from: c */
    private static String f1033c = null;

    /* renamed from: d */
    private static final String f1034d = "mobclick_agent_user_";

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.common.b$a */
    /* compiled from: SPHelper */
    public final class C0134a {

        /* renamed from: a */
        private static final SPHelper f1035a = new SPHelper();

        private C0134a() {
        }
    }

    /* renamed from: a */
    public static synchronized SPHelper m1089a(Context context) {
        SPHelper bVar;
        synchronized (SPHelper.class) {
            if (f1032b == null && context != null) {
                f1032b = context.getApplicationContext();
            }
            if (f1032b != null) {
                f1033c = context.getPackageName();
            }
            bVar = C0134a.f1035a;
        }
        return bVar;
    }

    /* renamed from: a */
    public void mo650a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            SharedPreferences.Editor edit = m1090e().edit();
            edit.putString("au_p", str);
            edit.putString("au_u", str2);
            edit.commit();
        }
    }

    /* renamed from: a */
    public String[] mo651a() {
        SharedPreferences e = m1090e();
        if (e != null) {
            String string = e.getString("au_p", null);
            String string2 = e.getString("au_u", null);
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                return new String[]{string, string2};
            }
        }
        return null;
    }

    /* renamed from: b */
    public void mo652b() {
        SharedPreferences e = m1090e();
        if (e != null) {
            e.edit().remove("au_p").remove("au_u").commit();
        }
    }

    /* renamed from: c */
    public String mo653c() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f1032b);
        if (sharedPreferences != null) {
            return sharedPreferences.getString("st", null);
        }
        return null;
    }

    /* renamed from: a */
    public void mo649a(String str) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f1032b);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString("st", str).commit();
        }
    }

    /* renamed from: a */
    public void mo648a(int i) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f1032b);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt("vt", i).commit();
        }
    }

    /* renamed from: d */
    public int mo654d() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f1032b);
        if (sharedPreferences != null) {
            return sharedPreferences.getInt("vt", 0);
        }
        return 0;
    }

    /* renamed from: e */
    private SharedPreferences m1090e() {
        Context context = f1032b;
        if (context == null) {
            return null;
        }
        return context.getSharedPreferences(f1034d + f1033c, 0);
    }
}
