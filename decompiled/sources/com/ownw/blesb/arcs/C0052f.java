package com.ownw.blesb.arcs;

import android.webkit.DownloadListener;

/* access modifiers changed from: package-private */
/* renamed from: com.ownw.blesb.arcs.f */
/* compiled from: BBBBrowserWebView */
public class C0052f implements DownloadListener {

    /* renamed from: a */
    final /* synthetic */ C0054h f152a;

    C0052f(C0054h this$0) {
        this.f152a = this$0;
    }

    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
        if (this.f152a.f154b != null) {
            ((AAABeanActivityAAA) this.f152a.f154b).mo141k(url, userAgent, contentDisposition, mimetype, contentLength);
        }
    }
}
