package com.own.bless.soy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;

/* access modifiers changed from: package-private */
/* renamed from: com.own.bless.soy.r0 */
public final class ShortcutManagerCompat extends BroadcastReceiver {

    /* renamed from: a */
    final /* synthetic */ IntentSender f109a;

    ShortcutManagerCompat(IntentSender intentSender) {
        this.f109a = intentSender;
    }

    public void onReceive(Context context, Intent intent) {
        try {
            this.f109a.sendIntent(context, 0, null, null, null);
        } catch (IntentSender.SendIntentException e) {
        }
    }
}
