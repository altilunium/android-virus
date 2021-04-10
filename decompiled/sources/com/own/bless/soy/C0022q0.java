package com.own.bless.soy;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.os.PersistableBundle;
import android.text.TextUtils;

/* renamed from: com.own.bless.soy.q0 */
/* compiled from: ShortcutInfoCompat */
public class C0022q0 {

    /* renamed from: a */
    Context f104a;

    /* renamed from: b */
    String f105b;

    /* renamed from: c */
    Intent[] f106c;

    /* renamed from: d */
    CharSequence f107d;

    /* renamed from: e */
    IconCompat f108e;

    C0022q0() {
    }

    @TargetApi(25)
    /* renamed from: c */
    public ShortcutInfo mo97c() {
        ShortcutInfo.Builder builder = new ShortcutInfo.Builder(this.f104a, this.f105b).setShortLabel(this.f107d).setIntents(this.f106c);
        IconCompat o0Var = this.f108e;
        if (o0Var != null) {
            builder.setIcon(o0Var.mo88j());
        }
        if (!TextUtils.isEmpty(null)) {
            builder.setLongLabel(null);
        }
        if (!TextUtils.isEmpty(null)) {
            builder.setDisabledMessage(null);
        }
        builder.setExtras(m208b());
        return builder.build();
    }

    @TargetApi(22)
    /* renamed from: b */
    private PersistableBundle m208b() {
        PersistableBundle bundle = new PersistableBundle();
        bundle.putBoolean("extraLongLived", false);
        return bundle;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public Intent mo96a(Intent outIntent) {
        Intent[] intentArr = this.f106c;
        outIntent.putExtra("android.intent.extra.shortcut.INTENT", intentArr[intentArr.length - 1]).putExtra("android.intent.extra.shortcut.NAME", this.f107d.toString());
        IconCompat o0Var = this.f108e;
        if (o0Var != null) {
            o0Var.mo84a(outIntent, null, this.f104a);
        }
        return outIntent;
    }
}
