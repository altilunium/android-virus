package com.ownw.blesb.arcs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.own.bless.soy.MyLog;
import com.ownw.blesb.StringFog;
import java.util.Map;

/* renamed from: com.ownw.blesb.arcs.h */
/* compiled from: BBBBrowserWebView */
public class C0054h extends WebView {

    /* renamed from: a */
    private boolean f153a;

    /* renamed from: b */
    private AbstractC0053g f154b;

    /* renamed from: c */
    private String f155c;

    public C0054h(Context context) {
        super(context);
        m297c();
    }

    public void setCallback(AbstractC0053g callback) {
        this.f154b = callback;
    }

    /* renamed from: c */
    private void m297c() {
        setScrollBarStyle(0);
        requestFocus();
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(true);
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setGeolocationDatabasePath(getContext().getFilesDir().getPath());
        int i = Build.VERSION.SDK_INT;
        if (i < 18) {
            settings.setPluginState(WebSettings.PluginState.ON);
        }
        setWebViewClient(new BBBBrowserWebView(this));
        setWebChromeClient(new C0050d(this));
        setOnKeyListener(new View$OnKeyListenerC0051e(this));
        if (i >= 21) {
            settings.setMixedContentMode(2);
        }
        setDownloadListener(new C0052f(this));
    }

    public boolean shouldOverrideUrlLoadingHandle(String url) {
        if (!isDeepLink(url)) {
            AbstractC0053g gVar = this.f154b;
            if (gVar != null) {
                ((AAABeanActivityAAA) gVar).mo149s(url);
            }
            String str = this.f155c;
            if (str == null || !str.equals(url)) {
                return false;
            }
            loadUrl(url);
            return true;
        }
        Intent intent = new Intent(StringFog.m313a("CwYOGRYQFloDBh4OFw1cFQkcAwQXVyQ9Lz8="), Uri.parse(url));
        if (!deviceCanHandleIntent(getContext(), intent)) {
            return false;
        }
        MyLog.m51e(StringFog.m313a("KQkGB1kKCwceDQdLHwwcFx4BBQUKQw==") + url);
        try {
            intent.addFlags(268435456);
            getContext().startActivity(intent);
            return true;
        } catch (Throwable throwable) {
            MyLog.m48b(StringFog.m313a("LxAeDgsXExhKAh8GCVkUFQMEDw9YWQ==") + url, throwable);
            return false;
        }
    }

    public boolean isDeepLink(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        String scheme = Uri.parse(url).getScheme();
        if (StringFog.m313a("AhweGw==").equalsIgnoreCase(scheme) || StringFog.m313a("AhweGwo=").equalsIgnoreCase(scheme)) {
            return false;
        }
        return true;
    }

    public boolean deviceCanHandleIntent(Context context, Intent intent) {
        try {
            return !context.getPackageManager().queryIntentActivities(intent, 0).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    @Override // android.webkit.WebView
    public final void loadUrl(String url, Map map) {
        super.loadUrl(url, map);
        m298d(url);
    }

    public void loadUrl(String url) {
        super.loadUrl(url);
        m298d(url);
    }

    public final void postUrl(String url, byte[] postData) {
        super.postUrl(url, postData);
        m298d(url);
    }

    public final void loadData(String data, String mimeType, String encoding) {
        super.loadData(data, mimeType, encoding);
        m298d(getUrl());
    }

    public final void loadDataWithBaseURL(String baseUrl, String data, String mimeType, String encoding, String historyUrl) {
        super.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
        m298d(getUrl());
    }

    public void reload() {
        super.reload();
        m298d(getUrl());
    }

    /* renamed from: d */
    private void m298d(String url) {
        if (TextUtils.isEmpty(url) || !url.startsWith(StringFog.m313a("AAkcCgoaAB0aHFA="))) {
            resetAllState();
        }
    }

    public boolean isTouchByUser() {
        return this.f153a;
    }

    /* access modifiers changed from: protected */
    public void resetAllState() {
        this.f153a = false;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == 0) {
            this.f153a = true;
        }
        return super.onTouchEvent(event);
    }
}
