package com.ownw.blesb.arcs;

import android.annotation.TargetApi;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* renamed from: com.ownw.blesb.arcs.c */
/* compiled from: BBBBrowserWebView */
class C0049c extends WebViewClient {

    /* renamed from: a */
    final /* synthetic */ C0050d f149a;

    C0049c(C0050d this$1) {
        this.f149a = this$1;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (this.f149a.f150a.shouldOverrideUrlLoadingHandle(url)) {
            return true;
        }
        this.f149a.f150a.loadUrl(url);
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override // android.webkit.WebViewClient
    @TargetApi(21)
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        try {
            return shouldOverrideUrlLoading(view, request.getUrl().toString());
        } catch (Throwable th) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }
}
