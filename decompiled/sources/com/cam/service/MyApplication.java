package com.cam.service;

import android.app.Application;
import com.ownw.blesb.Enter;
import com.p000ib.p001mk.qmux.p002ym.SDK;
import com.umeng.commonsdk.UMConfigure;
import com.xdandroid.hellodaemon.C0253b;

public class MyApplication extends Application {

    /* renamed from: a */
    public static final /* synthetic */ int f0a = 0;

    public void onCreate() {
        super.onCreate();
        Enter.start(this, "1213");
        C0253b.m1816b(this, TraceS.class, 360000);
        TraceS.f1b = false;
        C0253b.m1817c(TraceS.class);
        SDK.init(this);
        UMConfigure.init(this, "6061b424b8c8d45c13b5fb14", "305", 1, null);
    }
}
