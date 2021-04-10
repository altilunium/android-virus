package com.ownw.blesb.bws;

import com.own.bless.soy.MyLog;
import com.own.bless.soy.ThreadTask;
import com.ownw.blesb.StringFog;

/* access modifiers changed from: package-private */
/* renamed from: com.ownw.blesb.bws.a */
/* compiled from: WorkBService */
public class C0056a extends ThreadTask {

    /* renamed from: b */
    final /* synthetic */ WorkBService f165b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0056a(WorkBService this$0, Object... x0) {
        super(x0);
        this.f165b = this$0;
    }

    @Override // com.own.bless.soy.ThreadTask
    /* renamed from: a */
    public void mo46a(Object... args) {
        MyLog.m51e(StringFog.m313a("GQ0ED1kdFxgLEUpaSVkBEQkHBA8KWRwRHg=="));
        BlessService.m306h(this.f165b.getApplicationContext());
    }
}
