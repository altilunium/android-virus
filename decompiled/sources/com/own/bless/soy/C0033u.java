package com.own.bless.soy;

import android.os.Handler;
import android.os.HandlerThread;

/* renamed from: com.own.bless.soy.u */
/* compiled from: RequestManager */
public class C0033u {

    /* renamed from: c */
    private static C0033u f113c;

    /* renamed from: a */
    private HandlerThread f114a;

    /* renamed from: b */
    private Handler f115b = new Handler(this.f114a.getLooper(), new RequestManager(this));

    private C0033u() {
        HandlerThread handlerThread = new HandlerThread("request");
        this.f114a = handlerThread;
        handlerThread.start();
    }

    /* renamed from: b */
    public static C0033u m224b() {
        C0033u uVar = f113c;
        if (uVar != null) {
            return uVar;
        }
        synchronized (C0033u.class) {
            C0033u uVar2 = f113c;
            if (uVar2 != null) {
                return uVar2;
            }
            C0033u uVar3 = new C0033u();
            f113c = uVar3;
            return uVar3;
        }
    }

    /* renamed from: a */
    public void mo110a(RequestInfo requestInfo) {
        this.f115b.sendMessage(this.f115b.obtainMessage(0, requestInfo));
    }
}
