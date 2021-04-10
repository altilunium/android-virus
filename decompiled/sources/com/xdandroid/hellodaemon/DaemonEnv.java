package com.xdandroid.hellodaemon;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

/* access modifiers changed from: package-private */
/* renamed from: com.xdandroid.hellodaemon.a */
public final class DaemonEnv implements ServiceConnection {

    /* renamed from: a */
    final /* synthetic */ Class f1695a;

    /* renamed from: b */
    final /* synthetic */ Intent f1696b;

    DaemonEnv(Class cls, Intent intent) {
        this.f1695a = cls;
        this.f1696b = intent;
    }

    public void onServiceConnected(ComponentName name, IBinder service) {
        C0253b.f1701e.put(this.f1695a, this);
    }

    public void onServiceDisconnected(ComponentName name) {
        C0253b.f1701e.remove(this.f1695a);
        C0253b.m1818d(this.f1696b);
        if (C0253b.f1700d) {
            C0253b.f1697a.bindService(this.f1696b, this, 1);
        }
    }

    public void onBindingDied(ComponentName name) {
        onServiceDisconnected(name);
    }
}
