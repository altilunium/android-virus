package com.ownw.blesb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.own.bless.soy.MyLog;

/* access modifiers changed from: package-private */
/* renamed from: com.ownw.blesb.b */
/* compiled from: AAABaseActivity */
public class C0055b extends BroadcastReceiver {

    /* renamed from: a */
    final /* synthetic */ AAABaseActivity f156a;

    private C0055b(AAABaseActivity aAABaseActivity) {
        this.f156a = aAABaseActivity;
    }

    public void onReceive(Context context, Intent intent) {
        String reason;
        try {
            if (StringFog.m313a("CwYOGRYQFloDBh4OFw1cFQkcAwQXVzE4JTsvNCogISAvJTUvMDg+Oy07").equals(intent.getAction()) && (reason = intent.getStringExtra(StringFog.m313a("GA0LGBYX"))) != null) {
                if (reason.equals(StringFog.m313a("AgcHDhIcCw==")) || reason.equals(StringFog.m313a("GA0JDhcNEwQaGw=="))) {
                    MyLog.m47a(StringFog.m313a("MQkJHxAPGwATNUoDFhQXVAsLHgIWFw=="));
                    this.f156a.finish();
                }
            }
        } catch (Throwable th) {
            MyLog.m47a(StringFog.m313a("MQkJHxAPGwATNUoJCxYTEAkJGR9ZCxcXDwEcDgs="));
        }
    }
}
