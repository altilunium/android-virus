package com.own.bless.soy;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/* renamed from: com.own.bless.soy.p0 */
public class ShortcutInfoCompat {

    /* renamed from: a */
    private final C0022q0 f98a;

    public ShortcutInfoCompat(Context context, String id) {
        C0022q0 q0Var = new C0022q0();
        this.f98a = q0Var;
        q0Var.f104a = context;
        q0Var.f105b = id;
    }

    /* renamed from: e */
    public ShortcutInfoCompat mo95e(CharSequence shortLabel) {
        this.f98a.f107d = shortLabel;
        return this;
    }

    /* renamed from: c */
    public ShortcutInfoCompat mo93c(Intent intent) {
        mo94d(new Intent[]{intent});
        return this;
    }

    /* renamed from: d */
    public ShortcutInfoCompat mo94d(Intent[] intents) {
        this.f98a.f106c = intents;
        return this;
    }

    /* renamed from: b */
    public ShortcutInfoCompat mo92b(IconCompat icon) {
        this.f98a.f108e = icon;
        return this;
    }

    /* renamed from: a */
    public C0022q0 mo91a() {
        if (!TextUtils.isEmpty(this.f98a.f107d)) {
            C0022q0 q0Var = this.f98a;
            Intent[] intentArr = q0Var.f106c;
            if (intentArr != null && intentArr.length != 0) {
                return q0Var;
            }
            throw new IllegalArgumentException("Shortcut must have an intent");
        }
        throw new IllegalArgumentException("Shortcut must have a non-empty label");
    }
}
