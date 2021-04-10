package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.EnvelopeManager;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

/* renamed from: com.umeng.commonsdk.stateless.c */
public class UMSLNetWorkSenderHelper {

    /* renamed from: a */
    private String f1348a = "10.0.0.172";

    /* renamed from: b */
    private int f1349b = 80;

    /* renamed from: c */
    private Context f1350c;

    public UMSLNetWorkSenderHelper(Context context) {
        this.f1350c = context;
    }

    /* renamed from: a */
    private void m1309a() {
        String imprintProperty = UMEnvelopeBuild.imprintProperty(this.f1350c, "sl_domain_p", "");
        if (!TextUtils.isEmpty(imprintProperty)) {
            UMSLConfig.f1331i = DataHelper.assembleStatelessURL(imprintProperty);
        }
    }

    /* renamed from: b */
    private void m1310b() {
        String imprintProperty = UMEnvelopeBuild.imprintProperty(this.f1350c, "sl_domain_p", "");
        String imprintProperty2 = UMEnvelopeBuild.imprintProperty(this.f1350c, "oversea_sl_domain_p", "");
        if (!TextUtils.isEmpty(imprintProperty)) {
            UMSLConfig.f1330h = DataHelper.assembleStatelessURL(imprintProperty);
        }
        if (!TextUtils.isEmpty(imprintProperty2)) {
            UMSLConfig.f1333k = DataHelper.assembleStatelessURL(imprintProperty2);
        }
        UMSLConfig.f1331i = UMSLConfig.f1333k;
        if (TextUtils.isEmpty(EnvelopeManager.f1360b)) {
            return;
        }
        if (EnvelopeManager.f1360b.startsWith("460") || EnvelopeManager.f1360b.startsWith("461")) {
            UMSLConfig.f1331i = UMSLConfig.f1330h;
        }
    }

    /* renamed from: c */
    private boolean m1311c() {
        NetworkInfo activeNetworkInfo;
        String extraInfo;
        Context context = this.f1350c;
        if (context == null || context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.f1350c.getPackageName()) != 0) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.f1350c.getSystemService("connectivity");
            if (!DeviceConfig.checkPermission(this.f1350c, "android.permission.ACCESS_NETWORK_STATE") || connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || activeNetworkInfo.getType() == 1 || (extraInfo = activeNetworkInfo.getExtraInfo()) == null || (!extraInfo.equals("cmwap") && !extraInfo.equals("3gwap") && !extraInfo.equals("uniwap"))) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.f1350c, th);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0129 A[SYNTHETIC, Splitter:B:35:0x0129] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0140 A[SYNTHETIC, Splitter:B:44:0x0140] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0147  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo705a(byte[] r11, java.lang.String r12, java.lang.String r13, java.lang.String r14) {
        /*
        // Method dump skipped, instructions count: 367
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.UMSLNetWorkSenderHelper.mo705a(byte[], java.lang.String, java.lang.String, java.lang.String):boolean");
    }
}
