package com.umeng.commonsdk.statistics.internal;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.umeng.analytics.pro.UMCommonContent;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.utils.UMUtils;

/* renamed from: com.umeng.commonsdk.statistics.internal.a */
public class HeaderHelper {

    /* renamed from: a */
    private static Context f1466a = null;

    /* renamed from: b */
    private String f1467b;

    /* renamed from: c */
    private String f1468c;

    private HeaderHelper() {
        this.f1467b = null;
        this.f1468c = null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.statistics.internal.a$a */
    /* compiled from: HeaderHelper */
    public class C0200a {

        /* renamed from: a */
        private static final HeaderHelper f1469a = new HeaderHelper();

        private C0200a() {
        }
    }

    /* renamed from: a */
    public static HeaderHelper m1513a(Context context) {
        if (f1466a == null && context != null) {
            f1466a = context.getApplicationContext();
        }
        return C0200a.f1469a;
    }

    /* renamed from: a */
    public boolean mo818a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.startsWith(UMCommonContent.f336at);
        }
        return false;
    }

    /* renamed from: b */
    public boolean mo820b(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.startsWith(UMCommonContent.f296aF);
        }
        return false;
    }

    /* renamed from: c */
    public boolean mo821c(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.startsWith(UMCommonContent.f292aB);
        }
        return false;
    }

    /* renamed from: d */
    public void mo822d(String str) {
        String substring = str.substring(0, str.indexOf(95));
        m1515f(substring);
        m1514e(substring);
    }

    /* renamed from: e */
    private void m1514e(String str) {
        try {
            String replaceAll = str.replaceAll("&=", " ").replaceAll("&&", " ").replaceAll("==", "/");
            this.f1467b = replaceAll + "/" + "Android" + "/" + Build.DISPLAY + "/" + Build.MODEL + "/" + Build.VERSION.RELEASE + " " + HelperUtils.getUmengMD5(UMUtils.getAppkey(f1466a));
        } catch (Throwable th) {
            UMCrashManager.reportCrash(f1466a, th);
        }
    }

    /* renamed from: f */
    private void m1515f(String str) {
        try {
            String str2 = str.split("&&")[0];
            if (!TextUtils.isEmpty(str2)) {
                String[] split = str2.split("&=");
                StringBuilder sb = new StringBuilder();
                sb.append(UMCommonContent.f304aN);
                for (String str3 : split) {
                    if (!TextUtils.isEmpty(str3)) {
                        String substring = str3.substring(0, 2);
                        if (substring.endsWith("=")) {
                            substring = substring.replace("=", "");
                        }
                        sb.append(substring);
                    }
                }
                this.f1468c = sb.toString();
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(f1466a, th);
        }
    }

    /* renamed from: a */
    public String mo817a() {
        return this.f1468c;
    }

    /* renamed from: b */
    public String mo819b() {
        return this.f1467b;
    }
}
