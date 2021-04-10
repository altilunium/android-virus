package com.ownw.blesb.arcs;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* access modifiers changed from: package-private */
/* renamed from: com.ownw.blesb.arcs.b */
public class BBBBrowserWebView extends WebViewClient {

    /* renamed from: a */
    final /* synthetic */ C0054h f148a;

    BBBBrowserWebView(C0054h this$0) {
        this.f148a = this$0;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (!this.f148a.shouldOverrideUrlLoadingHandle(url)) {
            return super.shouldOverrideUrlLoading(view, url);
        }
        return true;
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

    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        this.f148a.f155c = url;
        if (this.f148a.f154b != null) {
            ((AAABeanActivityAAA) this.f148a.f154b).mo144o(url);
        }
    }

    public void onPageFinished(WebView view, String url) {
        if (this.f148a.f154b != null) {
            ((AAABeanActivityAAA) this.f148a.f154b).mo143n(url);
        }
    }

    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        if (failingUrl != null && !failingUrl.equals(view.getUrl()) && !failingUrl.equals(view.getOriginalUrl())) {
            return;
        }
        if ((failingUrl != null || errorCode == -12) && errorCode != -1 && this.f148a.f154b != null) {
            ((AAABeanActivityAAA) this.f148a.f154b).mo147q(errorCode, description, failingUrl);
        }
    }

    @TargetApi(23)
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        try {
            onReceivedError(view, error.getErrorCode(), error.getDescription().toString(), request.getUrl().toString());
        } catch (Throwable th) {
        }
    }

    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();
    }
}
