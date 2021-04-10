package com.own.bless.soy;

import android.content.Context;
import android.content.SharedPreferences;

/* renamed from: com.own.bless.soy.j0 */
public class PrefUtil {

    /* renamed from: a */
    private static PrefUtil f60a;

    /* renamed from: b */
    private static SharedPreferences f61b;

    private PrefUtil(Context context) {
        f61b = context.getSharedPreferences("config", 0);
    }

    /* renamed from: a */
    public static PrefUtil m145a(Context context) {
        PrefUtil j0Var = f60a;
        if (j0Var != null) {
            return j0Var;
        }
        synchronized (PrefUtil.class) {
            PrefUtil j0Var2 = f60a;
            if (j0Var2 != null) {
                return j0Var2;
            }
            PrefUtil j0Var3 = new PrefUtil(context);
            f60a = j0Var3;
            return j0Var3;
        }
    }

    /* renamed from: g */
    public void mo66g(String key, String content) {
        f61b.edit().putString(key, content).commit();
    }

    /* renamed from: d */
    public String mo63d(String key, String def) {
        return f61b.getString(key, def);
    }

    /* renamed from: e */
    public void mo64e(String key, int value) {
        f61b.edit().putInt(key, value).commit();
    }

    /* renamed from: b */
    public int mo61b(String key, int def) {
        return f61b.getInt(key, def);
    }

    /* renamed from: f */
    public void mo65f(String key, long value) {
        f61b.edit().putLong(key, value).commit();
    }

    /* renamed from: c */
    public long mo62c(String key, long def) {
        return f61b.getLong(key, def);
    }
}
