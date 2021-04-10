package com.umeng.analytics.pro;

import android.content.Context;
import android.os.Looper;

/* renamed from: com.umeng.analytics.pro.z */
public class OpenDeviceId {

    /* renamed from: a */
    private static IDeviceIdSupplier f978a = null;

    /* renamed from: b */
    private static boolean f979b = false;

    /* renamed from: a */
    public static synchronized String m1067a(Context context) {
        synchronized (OpenDeviceId.class) {
            if (context == null) {
                throw new RuntimeException("Context is null");
            } else if (Looper.myLooper() != Looper.getMainLooper()) {
                m1068b(context);
                IDeviceIdSupplier yVar = f978a;
                if (yVar != null) {
                    try {
                        return yVar.mo282a(context);
                    } catch (Exception e) {
                    }
                }
                return null;
            } else {
                throw new IllegalStateException("Cannot be called from the main thread");
            }
        }
    }

    /* renamed from: b */
    private static void m1068b(Context context) {
        if (f978a == null && !f979b) {
            synchronized (OpenDeviceId.class) {
                if (f978a == null && !f979b) {
                    f978a = DeviceIdSupplier.m399a(context);
                    f979b = true;
                }
            }
        }
    }
}
