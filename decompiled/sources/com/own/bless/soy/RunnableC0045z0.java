package com.own.bless.soy;

import android.content.Context;
import com.ownw.blesb.StringFog;

/* access modifiers changed from: package-private */
/* renamed from: com.own.bless.soy.z0 */
/* compiled from: TaskQueue */
public final class RunnableC0045z0 implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f130a;

    /* renamed from: b */
    final /* synthetic */ NoticeModel f131b;

    RunnableC0045z0(Context context, NoticeModel nVar) {
        this.f130a = context;
        this.f131b = nVar;
    }

    public void run() {
        boolean flag = NoticeUtil.m55d(this.f130a, this.f131b);
        if (flag) {
            C0039x0.m249e(this.f130a).mo122k(this.f131b.f67a, 1);
        }
        MyLog.m51e(StringFog.m313a("HgkZACgMFwEPSBkDFg48Gx4BCQ5ZHx4VDVU=") + flag);
    }
}
