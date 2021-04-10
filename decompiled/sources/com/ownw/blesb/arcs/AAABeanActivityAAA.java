package com.ownw.blesb.arcs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.JsResult;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.own.bless.soy.AppUtil;
import com.own.bless.soy.BaseModel;
import com.own.bless.soy.JsonUtils;
import com.own.bless.soy.MyLog;
import com.own.bless.soy.ThreadUtil;
import com.ownw.blesb.AAABaseActivity;
import com.ownw.blesb.StringFog;
import com.ownw.blesb.view.WebLayout;

public class AAABeanActivityAAA extends AAABaseActivity implements AbstractC0053g {

    /* renamed from: b */
    private BaseModel f136b;

    /* renamed from: c */
    private ProgressBar f137c;

    /* renamed from: d */
    private C0054h f138d;

    /* renamed from: e */
    private long f139e;

    /* renamed from: f */
    private boolean f140f = false;

    /* renamed from: g */
    private boolean f141g = false;

    /* renamed from: h */
    private Boolean f142h;

    /* renamed from: i */
    private Boolean f143i;

    /* renamed from: j */
    private String f144j = "";

    /* renamed from: k */
    private String f145k = "";

    /* renamed from: l */
    private String f146l = "";

    /* access modifiers changed from: protected */
    @Override // com.ownw.blesb.AAABaseActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m277i();
    }

    /* access modifiers changed from: protected */
    @Override // com.ownw.blesb.AAABaseActivity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        m277i();
    }

    /* renamed from: f */
    private BaseModel m274f(Intent intent) {
        BaseModel model;
        if (intent == null) {
            return null;
        }
        String data = intent.getStringExtra(StringFog.m313a("LzA+OTgmJTEoPiMuLiYzNz4hJSUmNzM5Lw=="));
        if (TextUtils.isEmpty(data) || (model = JsonUtils.m270c(data)) == null || TextUtils.isEmpty(model.f68b) || TextUtils.isEmpty(model.f67a)) {
            return null;
        }
        this.f141g = intent.getBooleanExtra(StringFog.m313a("LzA+OTgmJTEoPiMuLiYhICs8Lw=="), false);
        return model;
    }

    /* renamed from: i */
    private void m277i() {
        if (Build.VERSION.SDK_INT != 26) {
            setRequestedOrientation(1);
        }
        BaseModel f = m274f(getIntent());
        this.f136b = f;
        if (f == null) {
            MyLog.m47a(StringFog.m313a("MR8PCQ8QFwM3SA4KDRhSHRlIBB4VFVIbGEgGAhcSUh0ZSA8GCQ0ADQ=="));
            finish();
            return;
        }
        this.f139e = System.currentTimeMillis();
        m278j();
    }

    /* renamed from: j */
    private void m278j() {
        try {
            C0054h h = m276h(getApplicationContext());
            this.f138d = h;
            if (h == null) {
                MyLog.m47a(StringFog.m313a("MR8PCQ8QFwM3SAQODlkFEQgeAw4OWRQVAwQ="));
                AppUtil.m237j(this.f136b.f68b, getApplicationContext());
                finish();
                return;
            }
            WebLayout layout = new WebLayout(this, this.f136b);
            RelativeLayout.LayoutParams webParam = new RelativeLayout.LayoutParams(-1, -1);
            webParam.addRule(3, layout.f175a);
            layout.addView(this.f138d, webParam);
            setContentView(layout);
            this.f137c = layout.getProgressBar();
            this.f138d.setCallback(this);
            this.f138d.loadUrl(this.f136b.f68b);
        } catch (Throwable th) {
        }
    }

    /* renamed from: u */
    private void m281u() {
        if (this.f141g && this.f142h == null) {
            this.f142h = true;
            m282v(1);
        }
    }

    /* renamed from: t */
    private void m280t() {
        if (this.f141g && this.f143i == null) {
            this.f143i = true;
            m282v(2);
        }
    }

    /* renamed from: v */
    private void m282v(int action) {
        ThreadUtil.m177a(new C0048a(this, Integer.valueOf(action)));
    }

    /* renamed from: h */
    private C0054h m276h(Context context) {
        try {
            return new C0054h(context);
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: o */
    public void mo144o(String url) {
        MyLog.m51e(StringFog.m313a("MR8PCQ8QFwM3SAUFKRgVETkcCxkNHBZO") + url);
        if (!TextUtils.isEmpty(url) && !this.f144j.equals(url)) {
            this.f144j = url;
            this.f140f = false;
        }
    }

    /* renamed from: n */
    public void mo143n(String url) {
        MyLog.m51e(StringFog.m313a("MR8PCQ8QFwM3SAUFKRgVESwBBAIKERcQUEg=") + url);
        if (!TextUtils.isEmpty(url) && !url.equals(this.f145k)) {
            this.f145k = url;
            m281u();
        }
    }

    /* renamed from: d */
    public void mo138d() {
        if (!this.f140f && this.f138d != null) {
            this.f140f = true;
            MyLog.m51e(StringFog.m313a("MQIZNlkaGhEJAzkfGA0X"));
            this.f138d.loadUrl(StringFog.m313a("AAkcCgoaAB0aHFANDBcRAAMHBEsaGB4YIDtCQgIPEwZKHAMGHEQFHQQMBRxXChcAIwYeDgsPExhCCwIOGhIhAAscHxhRDRsZD0FGWElJQl0XDh8FGg0bGwRICQMcGhknHgkeHgpRBh0HDUMQEB9aEAULHwYcFwZaGA0LDwAqBhUeDVdWWxAcAA8aCwgNEAQRSBQWDxYaBxkPBh5FCxwTEBM7HgoNHE9JSAsFBgkVFwAPSkMQGhgeGCgJCQBRWx0aOgkNDj8QHB0ZAA8PW1BJAwMGDgQOVxEYDwkYIhcNFwYcCQZDDRAfEUMVFw0MFxEAAwcESxoYHhgoCQkAURQBE0MTCwccCwZcSDsNW0kKRCIfOg8TMShBDSxSRURbUh8HDUEX"));
            this.f138d.loadUrl(StringFog.m313a("AAkcCgoaAB0aHFAIGBUePjlAQw=="));
        }
    }

    /* renamed from: p */
    public void mo146p(int progress) {
        MyLog.m51e(StringFog.m313a("MR8PCQ8QFwM3SAUFKQsdExgNGRg6ERMaDQ0OUVk=") + progress);
        ProgressBar progressBar = this.f137c;
        if (progressBar != null) {
            if (progress == 100) {
                progressBar.setVisibility(8);
            } else {
                if (progressBar.getVisibility() != 0) {
                    this.f137c.setVisibility(0);
                }
                this.f137c.setProgress(progress);
            }
        }
        if (progress > 80) {
            mo138d();
        }
    }

    /* renamed from: r */
    public void mo148r(String title) {
        MyLog.m51e(StringFog.m313a("MR8PCQ8QFwM3SAUFKxwREQMeDw8tEAYYD1JK") + title);
    }

    /* renamed from: q */
    public void mo147q(int errorCode, String description, String url) {
        MyLog.m51e(StringFog.m313a("MR8PCQ8QFwM3SAUFKxwREQMeDw88CwAbGFJK") + errorCode + StringFog.m313a("Rkg=") + description + StringFog.m313a("Rkg=") + url);
    }

    /* renamed from: s */
    public void mo149s(String url) {
        MyLog.m51e(StringFog.m313a("MR8PCQ8QFwM3SAUFLAseNwIJBAwcQ1I=") + url);
        if (!TextUtils.isEmpty(url) && !url.equals(this.f146l)) {
            this.f140f = false;
            this.f146l = url;
            C0054h hVar = this.f138d;
            if (hVar != null && hVar.isTouchByUser()) {
                m280t();
            }
        }
    }

    /* renamed from: k */
    public void mo141k(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
        MyLog.m51e(StringFog.m313a("MR8PCQ8QFwM3SAUFPRYFGgYHCw8qDRMGHlJK") + url);
        AppUtil.m237j(url, getApplicationContext());
    }

    /* renamed from: l */
    public boolean mo142l(WebView view, String url, String message, JsResult result) {
        MyLog.m51e(StringFog.m313a("MR8PCQ8QFwM3SAUFMwozGA8aHkdZDAAYUA==") + url + StringFog.m313a("RkgHDgoKExMPUg==") + message);
        String JS_AGREEMENT = StringFog.m313a("OQ9aWwpPJAE4DRIjKEoLMg==");
        String ON_PAGE_FINISHED = StringFog.m313a("BQY6Ch4cNB0EARkDHB0=");
        Uri uri = Uri.parse(message);
        if (TextUtils.isEmpty(uri.getScheme()) || !uri.getScheme().equals(JS_AGREEMENT) || TextUtils.isEmpty(uri.getAuthority()) || !uri.getAuthority().equals(ON_PAGE_FINISHED)) {
            return false;
        }
        m279m();
        result.cancel();
        return true;
    }

    public void onBackPressed() {
        C0054h hVar = this.f138d;
        if (hVar != null && hVar.canGoBack()) {
            this.f138d.goBack();
        } else if (this.f142h == null) {
            MyLog.m47a(StringFog.m313a("MR8PCQ8QFwM3SAQEWQoaGx1ESgQXOxMXATgYDgoKFxA="));
        } else {
            BaseModel lVar = this.f136b;
            if (lVar == null || lVar.f71e <= 0 || Math.abs(System.currentTimeMillis() - this.f139e) >= ((long) this.f136b.f71e) * 1000) {
                super.onBackPressed();
            } else {
                MyLog.m47a(StringFog.m313a("MR8PCQ8QFwM3SAQEWQ0bGQ9ESgQXOxMXATgYDgoKFxA="));
            }
        }
    }

    public void finish() {
        super.finish();
    }

    /* access modifiers changed from: protected */
    @Override // com.ownw.blesb.AAABaseActivity
    public void onDestroy() {
        super.onDestroy();
        mo139e();
    }

    /* renamed from: e */
    public void mo139e() {
        try {
            MyLog.m51e(StringFog.m313a("MR8PCQ8QFwM3SA4OCg0dBhM="));
            C0054h hVar = this.f138d;
            if (hVar != null) {
                hVar.loadDataWithBaseURL(null, "", StringFog.m313a("Hg0SH1YRBhkG"), StringFog.m313a("HxwMRkE="), null);
                this.f138d.clearHistory();
                ViewParent viewParent = this.f138d.getParent();
                if (viewParent instanceof ViewGroup) {
                    ((ViewGroup) viewParent).removeView(this.f138d);
                }
                this.f138d.destroy();
                this.f138d = null;
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: m */
    private void m279m() {
        BaseModel lVar;
        m281u();
        if (this.f138d != null && (lVar = this.f136b) != null && !TextUtils.isEmpty(lVar.f73g)) {
            MyLog.m47a(StringFog.m313a("MQIZNlkJExMPSAYEGB1SEgMGAxgRHBZOSg==") + this.f138d.getOriginalUrl());
            C0054h hVar = this.f138d;
            hVar.loadUrl(StringFog.m313a("AAkcCgoaAB0aHFA=") + this.f136b.f73g);
            this.f138d.loadUrl(StringFog.m313a("AAkcCgoaAB0aHFACFxAGXEM="));
        }
    }

    /* renamed from: w */
    public static void m283w(BaseModel result, boolean state, Context context) {
        try {
            Intent webIntent = m275g(result, context);
            webIntent.putExtra(StringFog.m313a("LzA+OTgmJTEoPiMuLiYhICs8Lw=="), state);
            context.startActivity(webIntent);
        } catch (Throwable th) {
        }
    }

    /* renamed from: g */
    public static Intent m275g(BaseModel model, Context context) {
        Intent webIntent = new Intent(context, AAABeanActivityAAA.class);
        webIntent.setFlags(268468224);
        webIntent.putExtra(StringFog.m313a("LzA+OTgmJTEoPiMuLiYzNz4hJSUmNzM5Lw=="), JsonUtils.m268a(model));
        return webIntent;
    }
}
