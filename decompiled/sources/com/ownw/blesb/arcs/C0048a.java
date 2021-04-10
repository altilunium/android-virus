package com.ownw.blesb.arcs;

import com.own.bless.soy.ThreadTask;
import com.ownw.blesb.bws.BlessService;

/* access modifiers changed from: package-private */
/* renamed from: com.ownw.blesb.arcs.a */
/* compiled from: AAABeanActivityAAA */
public class C0048a extends ThreadTask {

    /* renamed from: b */
    final /* synthetic */ AAABeanActivityAAA f147b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0048a(AAABeanActivityAAA this$0, Object... x0) {
        super(x0);
        this.f147b = this$0;
    }

    @Override // com.own.bless.soy.ThreadTask
    /* renamed from: a */
    public void mo46a(Object... args) {
        if (this.f147b.f136b != null) {
            BlessService.m308j(this.f147b.getApplicationContext(), this.f147b.f136b.f67a, ((Integer) args[0]).intValue());
        }
    }
}
