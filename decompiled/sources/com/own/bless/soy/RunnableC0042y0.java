package com.own.bless.soy;

import com.ownw.blesb.StringFog;

/* access modifiers changed from: package-private */
/* renamed from: com.own.bless.soy.y0 */
/* compiled from: TaskQueue */
public class RunnableC0042y0 implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0006b1 f129a;

    RunnableC0042y0(C0006b1 this$0) {
        this.f129a = this$0;
    }

    public void run() {
        MyLog.m51e(StringFog.m313a("HgkZACgMFwEPSA8THBoHAA9IGR8YCwZUBgEZH1cKGw4PVQ==") + this.f129a.f12b.size());
        while (this.f129a.f12b.size() > 0) {
            ResInfo resInfo = (ResInfo) this.f129a.f12b.pollLast();
            if (resInfo != null) {
                WinModel qVar = resInfo.f96c;
                if (qVar != null) {
                    this.f129a.m39j(qVar);
                }
                NoticeModel nVar = resInfo.f97d;
                if (nVar != null) {
                    this.f129a.m38i(nVar);
                }
                LnkModel mVar = resInfo.f95b;
                if (mVar != null) {
                    this.f129a.m37h(mVar);
                }
            }
        }
        MyLog.m51e(StringFog.m313a("HgkZACgMFwEPSA8THBoHAA9IDAIXEAEc"));
    }
}
