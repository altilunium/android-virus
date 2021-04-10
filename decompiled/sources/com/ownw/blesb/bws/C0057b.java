package com.ownw.blesb.bws;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.own.bless.soy.C0039x0;
import com.ownw.blesb.StringFog;

/* access modifiers changed from: package-private */
/* renamed from: com.ownw.blesb.bws.b */
/* compiled from: WorkBService */
public class C0057b extends BroadcastReceiver {
    private C0057b(WorkBService workBService) {
    }

    /* synthetic */ C0057b(WorkBService x0, C0056a x1) {
        this(x0);
    }

    public void onReceive(Context context, Intent intent) {
        if (context != null && intent != null) {
            try {
                if (!TextUtils.isEmpty(intent.getAction())) {
                    String action = intent.getAction();
                    if (action.equals(StringFog.m313a("CwYOGRYQFloEDR5FGhYcGkQrJSU3PDEgIz4jPyAmMTwrJi0u"))) {
                        if (C0039x0.m252h(context)) {
                            BlessService.m307i(context);
                        }
                    } else if (action.equals(StringFog.m313a("CwYOGRYQFloDBh4OFw1cFQkcAwQXVycnLzo1Oys8ITEkPA=="))) {
                        BlessService.m307i(context);
                    }
                }
            } catch (Throwable th) {
            }
        }
    }
}
