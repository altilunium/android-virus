package com.own.bless.soy;

import android.content.Context;

/* renamed from: com.own.bless.soy.e3 */
public class OpenIDSDK {
    /* renamed from: a */
    public static void m77a(Context context) {
        HeytapIDSDK.f133b = C0008b3.f14a.mo37b(context.getApplicationContext());
        HeytapIDSDK.f132a = true;
    }

    /* renamed from: b */
    public static boolean m78b() {
        if (HeytapIDSDK.f132a) {
            return HeytapIDSDK.f133b;
        }
        throw new RuntimeException("SDK Need Init First!");
    }

    /* renamed from: c */
    public static String m79c(Context context) {
        if (HeytapIDSDK.f132a) {
            return C0008b3.f14a.mo36a(context.getApplicationContext(), "OUID");
        }
        throw new RuntimeException("SDK Need Init First!");
    }
}
