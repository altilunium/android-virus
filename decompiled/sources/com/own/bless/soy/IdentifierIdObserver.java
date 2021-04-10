package com.own.bless.soy;

import android.database.ContentObserver;
import android.util.Log;

/* renamed from: com.own.bless.soy.j3 */
public class IdentifierIdObserver extends ContentObserver {

    /* renamed from: a */
    private String f63a;

    /* renamed from: b */
    private int f64b;

    /* renamed from: c */
    private C0016i3 f65c;

    public IdentifierIdObserver(C0016i3 i3Var, int i, String str) {
        super(null);
        this.f65c = i3Var;
        this.f64b = i;
        this.f63a = str;
    }

    public void onChange(boolean z) {
        C0016i3 i3Var = this.f65c;
        if (i3Var != null) {
            i3Var.mo58c(this.f64b, this.f63a);
        } else {
            Log.e("VMS_IDLG_SDK_Observer", "mIdentifierIdClient is null");
        }
    }
}
