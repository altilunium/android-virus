package com.own.bless.soy;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ownw.blesb.StringFog;
import com.umeng.commonsdk.statistics.UMErrorCode;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* renamed from: com.own.bless.soy.x0 */
/* compiled from: NetManager */
public class C0039x0 {

    /* renamed from: d */
    private static C0039x0 f122d;

    /* renamed from: e */
    private static Handler f123e = new Handler(Looper.getMainLooper());

    /* renamed from: f */
    private static long f124f = 1800000;

    /* renamed from: a */
    private boolean f125a = false;

    /* renamed from: b */
    private Object f126b = new Object();

    /* renamed from: c */
    private Context f127c;

    private C0039x0(Context context) {
        this.f127c = context;
    }

    /* renamed from: e */
    public static C0039x0 m249e(Context context) {
        C0039x0 x0Var = f122d;
        if (x0Var != null) {
            return x0Var;
        }
        synchronized (C0039x0.class) {
            if (f122d == null) {
                f122d = new C0039x0(context);
            }
        }
        return f122d;
    }

    /* renamed from: k */
    public void mo122k(String aid, int action) {
        StringBuilder sb = new StringBuilder();
        sb.append(StringFog.m313a("GA0aBAsNIQALHA9WRBgbEFc="));
        sb.append(aid);
        sb.append(StringFog.m313a("SgkJHxAWHEk="));
        sb.append(action);
        sb.append(StringFog.m313a("Vw=="));
        sb.append(StringFog.m313a(action == 1 ? "jPDUjN3D" : "jerTjv7C"));
        MyLog.m51e(sb.toString());
        Map<String, Object> params = ParamUtil.m90c(this.f127c, aid, action);
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.mo82e(params);
        requestInfo.mo83f(MKConfig.f45f);
        C0033u.m224b().mo110a(requestInfo);
    }

    /* renamed from: f */
    private int m250f(int min, int max) {
        double d = (double) min;
        double random = Math.random();
        double d2 = (double) ((max - min) + 1);
        Double.isNaN(d2);
        Double.isNaN(d);
        return (int) (d + (random * d2));
    }

    /* renamed from: m */
    public void mo123m() {
        int random = m250f(1, UMErrorCode.E_UM_BE_NOT_MAINPROCESS);
        MyLog.m51e(StringFog.m313a("Dg0GCgBZ") + random + StringFog.m313a("ShsPCBYXFgdGSBgOCAwXBx5ICQQXHxsTSkNBQFJSWQ=="));
        ThreadUtil.m178b(new NetManager(this, new Object[0]), (long) random, TimeUnit.SECONDS);
    }

    /* renamed from: i */
    public void mo121i() {
        MyLog.m51e(StringFog.m313a("JA0eJhgXExMPGkoHFhgWNwUGDAIeWRsHOB0EBRAXFUk=") + this.f125a);
        if (!this.f125a) {
            synchronized (this.f126b) {
                if (!this.f125a) {
                    this.f125a = true;
                    m254l();
                }
            }
        }
    }

    /* renamed from: l */
    private void m254l() {
        if (!m252h(this.f127c)) {
            this.f125a = false;
        } else if (OtherUtil.m82b(this.f127c)) {
            MyLog.m51e(StringFog.m313a("EgEPVkQVHRUOKwUFHxAVVAMbJgQaEiEXGA0PBVkcCh0eUw=="));
            this.f125a = false;
        } else {
            long reqCycle = PrefUtil.m145a(this.f127c).mo62c(StringFog.m313a("GA0bKAAaHhE="), 2400000);
            if (reqCycle < 1800000) {
                reqCycle = 1800000;
            } else if (reqCycle > 10800000) {
                reqCycle = 10800000;
            }
            if (!OtherUtil.m81a(this.f127c, StringFog.m313a("GA0bJxgKBiADBQ8="), reqCycle)) {
                MyLog.m51e(StringFog.m313a("Bg0ZGFkLFwUpEQkHHFlPSQ==") + reqCycle);
                this.f125a = false;
                return;
            }
            if (Math.abs(System.currentTimeMillis() - AppUtil.m233f(this.f127c)) / 1000 < 5) {
                this.f125a = false;
                MyLog.m51e(StringFog.m313a("Bg0ZGFkQHAceCQYHWQ0bGQ9IX0sKHBEbBAwZ"));
                return;
            }
            RequestInfo requestInfo = new RequestInfo();
            requestInfo.mo82e(ParamUtil.m88a(this.f127c));
            requestInfo.mo83f(MKConfig.f44e);
            requestInfo.mo81d(new C0035v0(this));
            C0033u.m224b().mo110a(requestInfo);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: j */
    public ResInfo m253j(String json) {
        MyLog.m51e(StringFog.m313a("EgEPVkQTARsEUg==") + json);
        try {
            JSONObject result = new JSONObject(json);
            String deContent = EncryptUtils.m243a(result.optString(StringFog.m313a("CA=="), null), result.optString(StringFog.m313a("Cw=="), null));
            if (TextUtils.isEmpty(deContent)) {
                return null;
            }
            MyLog.m51e(StringFog.m313a("EgEPVkQdFxcFBh4OFw1I") + deContent);
            JSONObject rootObject = new JSONObject(deContent);
            ResInfo resInfo = new ResInfo();
            resInfo.f94a = rootObject.optInt(StringFog.m313a("CREJBxw="), 0);
            if (!rootObject.has(StringFog.m313a("DxoYJRY="))) {
                String aid = rootObject.optString(StringFog.m313a("CwEO"), null);
                String link = rootObject.optString(StringFog.m313a("BgEEACwLHg=="), null);
                int i = 0;
                int position = rootObject.optInt(StringFog.m313a("GgcZAg0QHRo="), 0);
                String title = rootObject.optString(StringFog.m313a("HgEeBxw="), null);
                if (TextUtils.isEmpty(aid)) {
                    return null;
                }
                if (position == 5 || position == 6) {
                    WinModel winModel = new WinModel();
                    winModel.f67a = aid;
                    winModel.f68b = link;
                    winModel.f103k = 1;
                    if (position != 5) {
                        i = 1;
                    }
                    winModel.f69c = i;
                    resInfo.f96c = winModel;
                } else if (position == 3 || position == 4 || position == 7) {
                    if (TextUtils.isEmpty(title) || TextUtils.isEmpty(link)) {
                        return null;
                    }
                    NoticeModel noticeModel = new NoticeModel();
                    noticeModel.f67a = aid;
                    noticeModel.f69c = position == 7 ? 1 : 0;
                    noticeModel.f68b = link;
                    noticeModel.f80h = title;
                    noticeModel.f81i = rootObject.optString(StringFog.m313a("CQcEHxwXBg=="), null);
                    resInfo.f97d = noticeModel;
                }
            } else if (rootObject.optInt(StringFog.m313a("DxoYJRY="), 0) != 0) {
                return null;
            } else {
                if (rootObject.has(StringFog.m313a("BgYB"))) {
                    JSONObject lnkObject = rootObject.getJSONObject(StringFog.m313a("BgYB"));
                    LnkModel lnkModel = new LnkModel();
                    lnkModel.f77j = lnkObject.optInt(StringFog.m313a("CQcfBQ0="), 0);
                    lnkModel.f76i = lnkObject.optString(StringFog.m313a("BAkHDg=="), null);
                    lnkModel.f75h = lnkObject.optString(StringFog.m313a("AwsFBQ=="), null);
                    m251g(lnkModel, lnkObject);
                    resInfo.f95b = lnkModel;
                }
                if (rootObject.has(StringFog.m313a("BAceAhoc"))) {
                    JSONObject noticeObject = rootObject.getJSONObject(StringFog.m313a("BAceAhoc"));
                    NoticeModel noticeModel2 = new NoticeModel();
                    noticeModel2.f80h = noticeObject.optString(StringFog.m313a("HgEeBxw="), null);
                    noticeModel2.f81i = noticeObject.optString(StringFog.m313a("Dg0ZCA=="), null);
                    noticeModel2.f82j = noticeObject.optString(StringFog.m313a("AwsFBQ=="), null);
                    noticeModel2.f83k = noticeObject.optString(StringFog.m313a("CAENIhoWHA=="), null);
                    noticeModel2.f84l = noticeObject.optInt(StringFog.m313a("GQYJBA=="), 0);
                    noticeModel2.f71e = noticeObject.optInt(StringFog.m313a("CQQFGBw6CxcGDQ=="), 0);
                    noticeModel2.f72f = noticeObject.optInt(StringFog.m313a("CQQDCBI6CxcGDQ=="), 0);
                    noticeModel2.f73g = noticeObject.optString(StringFog.m313a("ABspBBcNFxoe"), "");
                    m251g(noticeModel2, noticeObject);
                    resInfo.f97d = noticeModel2;
                }
                if (rootObject.has(StringFog.m313a("HQEE"))) {
                    JSONObject winObject = rootObject.getJSONObject(StringFog.m313a("HQEE"));
                    WinModel winModel2 = new WinModel();
                    winModel2.f103k = winObject.optInt(StringFog.m313a("AxsOAgscEQA="), 0);
                    winModel2.f101i = winObject.optString(StringFog.m313a("AwsFBQ=="), null);
                    winModel2.f100h = winObject.optInt(StringFog.m313a("AxssHhUV"), 0);
                    winModel2.f71e = winObject.optInt(StringFog.m313a("CQQFGBw6CxcGDQ=="), 0);
                    winModel2.f72f = winObject.optInt(StringFog.m313a("CQQDCBI6CxcGDQ=="), 0);
                    winModel2.f73g = winObject.optString(StringFog.m313a("ABspBBcNFxoe"), "");
                    m251g(winModel2, winObject);
                    resInfo.f96c = winModel2;
                }
            }
            return resInfo;
        } catch (Throwable throwable) {
            MyLog.m48b(StringFog.m313a("MQsFBR8QFSlKGAsZChxSFwUGDAIeWTgHBQZKDRgQHg=="), throwable);
            StatsUtils.m154b(this.f127c, StringFog.m313a("KSckLTA+"), StringFog.m313a("MQsFBR8QFSlKGAsZChxSFwUGDAIeWTgHBQZKDRgQHg=="), throwable);
            return null;
        }
    }

    /* renamed from: n */
    public synchronized void mo124n() {
        try {
            if (!m252h(this.f127c)) {
                MyLog.m51e(StringFog.m313a("MQ0YGRYLL1QkDR4cFgsZVB8GCx0YEB4VCAQPR1kaExoJDQZLCxwDAQ8bHg=="));
                return;
            } else if (!OtherUtil.m81a(this.f127c, StringFog.m313a("GA0bLgsLPhUZHD4CFBw="), f124f)) {
                MyLog.m51e(StringFog.m313a("MQ0YGRYLL1QGDRkYWQsXBSkRCQccWU9J") + f124f);
                return;
            } else {
                String errInfo = C0015h.m99j(this.f127c).mo52d();
                if (TextUtils.isEmpty(errInfo)) {
                    errInfo = C0015h.m99j(this.f127c).mo53k();
                    if (TextUtils.isEmpty(errInfo)) {
                        MyLog.m51e(StringFog.m313a("MQ0YGRYLL1QfGEoOCwtSHQQOBUsQClIRBxgeElVZERUECw8HWQsXBR8NGR8="));
                        return;
                    }
                }
                PrefUtil.m145a(this.f127c).mo65f(StringFog.m313a("GA0bLgsLPhUZHD4CFBw="), System.currentTimeMillis());
                RequestInfo requestInfo = new RequestInfo();
                requestInfo.mo82e(ParamUtil.m89b(this.f127c, errInfo));
                requestInfo.mo83f(MKConfig.f46g);
                requestInfo.mo81d(new C0037w0(this, errInfo));
                C0033u.m224b().mo110a(requestInfo);
                return;
            }
        } catch (Throwable throwable) {
            MyLog.m48b(StringFog.m313a("DxoYBAtVUgEaSA8ZC1kbGgwHSg0YEB4="), throwable);
            PrefUtil.m145a(this.f127c).mo65f(StringFog.m313a("GA0bLgsLPhUZHD4CFBw="), System.currentTimeMillis() - Math.abs(f124f - 900000));
        }
    }

    /* renamed from: g */
    private void m251g(BaseModel model, JSONObject object) {
        model.f69c = object.optInt(StringFog.m313a("BRgPBS0AAhE="), 0);
        model.f68b = object.optString(StringFog.m313a("BgEEAA=="), "");
        model.f67a = object.optString(StringFog.m313a("CwEO"), "");
    }

    /* renamed from: h */
    public static boolean m252h(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(StringFog.m313a("CQcEBRwaBh0cAR4S"));
        if (cm == null) {
            return false;
        }
        try {
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info == null) {
                return false;
            }
            return info.isAvailable();
        } catch (Throwable th) {
            return false;
        }
    }

    /* renamed from: d */
    static Handler m248d() {
        return f123e;
    }
}
