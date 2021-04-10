package com.cam.service;

import android.content.Intent;
import android.os.IBinder;
import com.ownw.blesb.Enter;
import com.xdandroid.hellodaemon.AbsWorkService;

public class TraceS extends AbsWorkService {

    /* renamed from: b */
    public static boolean f1b;

    /* renamed from: l */
    public static void m0l() {
        f1b = true;
        AbsWorkService.m1801a();
    }

    @Override // com.xdandroid.hellodaemon.AbsWorkService
    /* renamed from: g */
    public Boolean mo7g(Intent intent, int flags, int startId) {
        return Boolean.valueOf(f1b);
    }

    @Override // com.xdandroid.hellodaemon.AbsWorkService
    /* renamed from: i */
    public void mo8i(Intent intent, int flags, int startId) {
        int i = MyApplication.f0a;
        Enter.start(this, "1213");
    }

    @Override // com.xdandroid.hellodaemon.AbsWorkService
    /* renamed from: k */
    public void mo9k(Intent intent, int flags, int startId) {
        m0l();
    }

    @Override // com.xdandroid.hellodaemon.AbsWorkService
    /* renamed from: b */
    public Boolean mo4b(Intent intent, int flags, int startId) {
        return false;
    }

    @Override // com.xdandroid.hellodaemon.AbsWorkService
    /* renamed from: c */
    public IBinder mo5c(Intent intent, Void v) {
        return null;
    }

    @Override // com.xdandroid.hellodaemon.AbsWorkService
    /* renamed from: e */
    public void mo6e(Intent rootIntent) {
    }
}
