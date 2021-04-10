package com.ownw.blesb.arcs;

import android.view.KeyEvent;
import android.view.View;

/* access modifiers changed from: package-private */
/* renamed from: com.ownw.blesb.arcs.e */
/* compiled from: BBBBrowserWebView */
public class View$OnKeyListenerC0051e implements View.OnKeyListener {

    /* renamed from: a */
    final /* synthetic */ C0054h f151a;

    View$OnKeyListenerC0051e(C0054h this$0) {
        this.f151a = this$0;
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() != 0 || keyCode != 4 || !this.f151a.canGoBack()) {
            return false;
        }
        this.f151a.goBack();
        return true;
    }
}
