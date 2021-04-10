package com.own.bless.soy;

/* renamed from: com.own.bless.soy.e2 */
/* compiled from: ObjectHelper */
public final class C0013e2 {
    /* renamed from: d */
    public static Object m76d(Object obj, String message) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(message);
    }

    /* renamed from: c */
    public static boolean m75c(Object o1, Object o2) {
        return o1 == o2 || (o1 != null && o1.equals(o2));
    }

    /* renamed from: a */
    public static int m73a(int v1, int v2) {
        if (v1 < v2) {
            return -1;
        }
        return v1 > v2 ? 1 : 0;
    }

    /* renamed from: b */
    public static int m74b(long v1, long v2) {
        if (v1 < v2) {
            return -1;
        }
        return v1 > v2 ? 1 : 0;
    }

    static {
        new ObjectHelper();
    }
}
