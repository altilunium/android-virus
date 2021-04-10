package com.own.bless.soy;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.Method;

/* renamed from: com.own.bless.soy.f3 */
public class IdentifierManager {

    /* renamed from: a */
    private static Object f34a;

    /* renamed from: b */
    private static Class f35b;

    /* renamed from: c */
    private static Method f36c;

    static {
        f36c = null;
        try {
            Class<?> cls = Class.forName("com.android.id.impl.IdProviderImpl");
            f35b = cls;
            f34a = cls.newInstance();
            f35b.getMethod("getUDID", Context.class);
            f36c = f35b.getMethod("getOAID", Context.class);
            f35b.getMethod("getVAID", Context.class);
            f35b.getMethod("getAAID", Context.class);
        } catch (Exception e) {
            Log.e("IdentifierManager", "reflect exception!", e);
        }
    }

    /* renamed from: b */
    public static boolean m85b() {
        return (f35b == null || f34a == null) ? false : true;
    }

    /* renamed from: c */
    public static String m86c(Context context) {
        return m84a(context, f36c);
    }

    /* renamed from: a */
    private static String m84a(Context context, Method method) {
        Object obj = f34a;
        if (obj == null || method == null) {
            return null;
        }
        try {
            Object invoke = method.invoke(obj, context);
            if (invoke != null) {
                return (String) invoke;
            }
            return null;
        } catch (Exception e) {
            Log.e("IdentifierManager", "invoke exception!", e);
            return null;
        }
    }
}
