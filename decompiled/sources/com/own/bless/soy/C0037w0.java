package com.own.bless.soy;

import com.ownw.blesb.StringFog;

/* access modifiers changed from: package-private */
/* renamed from: com.own.bless.soy.w0 */
/* compiled from: NetManager */
public class C0037w0 implements HttpNetUtil {

    /* renamed from: a */
    final /* synthetic */ String f120a;

    /* renamed from: b */
    final /* synthetic */ C0039x0 f121b;

    C0037w0(C0039x0 this$0, String str) {
        this.f121b = this$0;
        this.f120a = str;
    }

    @Override // com.own.bless.soy.HttpNetUtil
    /* renamed from: a */
    public void mo101a(Throwable paramThrowable) {
        MyLog.m51e(StringFog.m313a("MQ0YGRYLL1QYDRseHAoGVFdVBQU/GBsYHxoP"));
        C0015h.m99j(this.f121b.f127c).mo54l(this.f120a);
    }

    @Override // com.own.bless.soy.HttpNetUtil
    public void onSuccess(String response) {
        MyLog.m51e(StringFog.m313a("MQ0YGRYLL1QYDRseHAoGVFdVBQUqDBEXDxsZ"));
        C0015h.m99j(this.f121b.f127c).mo54l("");
        PrefUtil.m145a(this.f121b.f127c).mo65f(StringFog.m313a("GA0bLgsLPhUZHD4CFBw="), System.currentTimeMillis());
    }
}
