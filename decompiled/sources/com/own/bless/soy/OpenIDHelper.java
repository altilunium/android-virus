package com.own.bless.soy;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* renamed from: com.own.bless.soy.a3 */
public class OpenIDHelper implements ServiceConnection {

    /* renamed from: a */
    public final /* synthetic */ C0011c3 f7a;

    public OpenIDHelper(C0011c3 c3Var) {
        this.f7a = c3Var;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f7a.f26a = AbstractBinderC0044y2.m267a(iBinder);
        synchronized (this.f7a.f29d) {
            this.f7a.f29d.notify();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.f7a.f26a = null;
    }
}
