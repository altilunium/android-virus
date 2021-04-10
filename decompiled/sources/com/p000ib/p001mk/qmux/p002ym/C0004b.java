package com.p000ib.p001mk.qmux.p002ym;

import android.content.Context;
import com.p000ib.p001mk.qmux.p002ym.p003a.C0003b;
import com.umeng.analytics.pro.TType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/* renamed from: com.ib.mk.qmux.ym.b */
public class C0004b {
    /* renamed from: a */
    public static Object m26a(Context context, String str) {
        Object a = m27a(C0004b.class.getClass(), C0003b.m25a(new byte[]{20, TType.f576l, 21, 59, 31, 2, 10, 28, 33, 4, 19, 28, 28, 4}));
        Class<?>[] clsArr = {String.class, String.class, String.class, Class.forName(C0003b.m25a(new byte[]{25, 10, 23, 25, 93, TType.f577m, 24, 1, 10, 69, 49, 20, 24, 5, 22, 57, 10, 11, 12, 8, 20}))};
        return Class.forName(C0003b.m25a(new byte[]{23, 10, TType.f575k, TType.f576l, 26, 8, 87, 28, 20, 24, 6, 29, 20, 88, 33, TType.f578n, 29, 41, 4, 12, 21, TType.f578n, 46, 5, 18, TType.f577m, 4, 10})).getConstructor(clsArr).newInstance(str, context.getFilesDir().getPath(), null, a);
    }

    /* renamed from: a */
    private static Object m27a(Class cls, String str) {
        try {
            return cls.getMethod(str, new Class[0]).invoke(cls, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static void m28a(Object obj, String str, String str2, Context context) {
        Class cls = (Class) obj.getClass().getMethod(C0003b.m25a(new byte[]{31, 4, 0, 28, 48, TType.f577m, 24, 28, 30}), String.class).invoke(obj, str);
        Constructor constructor = cls.getConstructor(new Class[0]);
        Method method = cls.getMethod(str2, Context.class);
        method.setAccessible(true);
        method.invoke(constructor.newInstance(new Object[0]), context);
    }
}
