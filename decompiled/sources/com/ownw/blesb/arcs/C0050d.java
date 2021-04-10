package com.ownw.blesb.arcs;

import android.os.Message;
import android.webkit.GeolocationPermissions;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* access modifiers changed from: package-private */
/* renamed from: com.ownw.blesb.arcs.d */
/* compiled from: BBBBrowserWebView */
public class C0050d extends WebChromeClient {

    /* renamed from: a */
    final /* synthetic */ C0054h f150a;

    C0050d(C0054h this$0) {
        this.f150a = this$0;
    }

    public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
        WebView webView = new WebView(this.f150a.getContext());
        webView.setWebViewClient(new C0049c(this));
        ((WebView.WebViewTransport) resultMsg.obj).setWebView(webView);
        resultMsg.sendToTarget();
        return true;
    }

    public void onProgressChanged(WebView view, int newProgress) {
        if (this.f150a.f154b != null) {
            ((AAABeanActivityAAA) this.f150a.f154b).mo146p(newProgress);
        }
    }

    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        if (this.f150a.f154b == null || !((AAABeanActivityAAA) this.f150a.f154b).mo142l(view, url, message, result)) {
            return super.onJsAlert(view, url, message, result);
        }
        return true;
    }

    public void onReceivedTitle(WebView view, String title) {
        if (this.f150a.f154b != null) {
            ((AAABeanActivityAAA) this.f150a.f154b).mo148r(title);
        }
    }

    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
        super.onGeolocationPermissionsShowPrompt(origin, callback);
        callback.invoke(origin, true, true);
    }
}
