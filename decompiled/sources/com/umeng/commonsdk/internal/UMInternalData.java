package com.umeng.commonsdk.internal;

import android.content.Context;

/* renamed from: com.umeng.commonsdk.internal.b */
public class UMInternalData {

    /* renamed from: b */
    private static UMInternalData f1232b;

    /* renamed from: a */
    private Context f1233a;

    /* renamed from: c */
    private UMInternalDataProtocol f1234c;

    private UMInternalData(Context context) {
        this.f1233a = context;
        this.f1234c = new UMInternalDataProtocol(context);
    }

    /* renamed from: a */
    public static synchronized UMInternalData m1179a(Context context) {
        UMInternalData bVar;
        synchronized (UMInternalData.class) {
            if (f1232b == null) {
                f1232b = new UMInternalData(context.getApplicationContext());
            }
            bVar = f1232b;
        }
        return bVar;
    }

    /* renamed from: a */
    public UMInternalDataProtocol mo677a() {
        return this.f1234c;
    }
}
