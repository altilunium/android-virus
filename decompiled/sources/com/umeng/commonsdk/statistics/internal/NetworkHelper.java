package com.umeng.commonsdk.statistics.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.umeng.analytics.CoreProtocol;
import com.umeng.analytics.pro.AutoViewPageTracker;
import com.umeng.analytics.pro.CoreProtocolImpl;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.UMInternalConfig;
import com.umeng.commonsdk.internal.UMInternalData;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.EnvelopeManager;
import com.umeng.commonsdk.statistics.SdkVersion;
import com.umeng.commonsdk.statistics.UMServerURL;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.utils.UMConstant;
import java.io.File;

/* renamed from: com.umeng.commonsdk.statistics.internal.c */
public class NetworkHelper {

    /* renamed from: e */
    private static boolean f1470e = false;

    /* renamed from: f */
    private static boolean f1471f = false;

    /* renamed from: g */
    private static boolean f1472g = false;

    /* renamed from: a */
    private String f1473a = "10.0.0.172";

    /* renamed from: b */
    private int f1474b = 80;

    /* renamed from: c */
    private Context f1475c;

    /* renamed from: d */
    private IRequestStat f1476d;

    public NetworkHelper(Context context) {
        this.f1475c = context;
    }

    /* renamed from: a */
    public void mo823a(IRequestStat bVar) {
        this.f1476d = bVar;
    }

    /* renamed from: a */
    private void m1524a() {
        String imprintProperty = UMEnvelopeBuild.imprintProperty(this.f1475c, "domain_p", "");
        String imprintProperty2 = UMEnvelopeBuild.imprintProperty(this.f1475c, "domain_s", "");
        if (!TextUtils.isEmpty(imprintProperty)) {
            UMServerURL.DEFAULT_URL = DataHelper.assembleURL(imprintProperty);
        }
        if (!TextUtils.isEmpty(imprintProperty2)) {
            UMServerURL.SECONDARY_URL = DataHelper.assembleURL(imprintProperty2);
        }
        AnalyticsConstants.APPLOG_URL_LIST = new String[]{UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL};
    }

    /* renamed from: b */
    private void m1525b() {
        String imprintProperty = UMEnvelopeBuild.imprintProperty(this.f1475c, "domain_p", "");
        String imprintProperty2 = UMEnvelopeBuild.imprintProperty(this.f1475c, "domain_s", "");
        if (!TextUtils.isEmpty(imprintProperty)) {
            UMServerURL.DEFAULT_URL = DataHelper.assembleURL(imprintProperty);
        }
        if (!TextUtils.isEmpty(imprintProperty2)) {
            UMServerURL.SECONDARY_URL = DataHelper.assembleURL(imprintProperty2);
        }
        String imprintProperty3 = UMEnvelopeBuild.imprintProperty(this.f1475c, "oversea_domain_p", "");
        String imprintProperty4 = UMEnvelopeBuild.imprintProperty(this.f1475c, "oversea_domain_s", "");
        if (!TextUtils.isEmpty(imprintProperty3)) {
            UMServerURL.OVERSEA_DEFAULT_URL = DataHelper.assembleURL(imprintProperty3);
        }
        if (!TextUtils.isEmpty(imprintProperty4)) {
            UMServerURL.OVERSEA_SECONDARY_URL = DataHelper.assembleURL(imprintProperty4);
        }
        AnalyticsConstants.APPLOG_URL_LIST = new String[]{UMServerURL.OVERSEA_DEFAULT_URL, UMServerURL.OVERSEA_SECONDARY_URL};
        if (TextUtils.isEmpty(EnvelopeManager.f1360b)) {
            return;
        }
        if (EnvelopeManager.f1360b.startsWith("460") || EnvelopeManager.f1360b.startsWith("461")) {
            AnalyticsConstants.APPLOG_URL_LIST = new String[]{UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL};
        }
    }

    /* renamed from: c */
    private void m1526c() {
        if (!f1472g) {
            ImprintHandler.getImprintService(this.f1475c).registImprintCallback(AnalyticsConstants.CFG_FIELD_KEY, new UMImprintChangeCallback() {
                /* class com.umeng.commonsdk.statistics.internal.NetworkHelper.C02011 */

                @Override // com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback
                public void onImprintValueChanged(String str, String str2) {
                    if (FieldManager.m1114b()) {
                        FieldManager.m1113a().mo664a(NetworkHelper.this.f1475c, str2);
                        UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> apply imprint change and exit: key=" + str + "; value= " + str2);
                        UMWorkDispatch.sendEvent(NetworkHelper.this.f1475c, UMInternalConfig.f1228w, UMInternalData.m1179a(NetworkHelper.this.f1475c).mo677a(), null);
                    }
                }
            });
            f1472g = true;
        }
    }

    /* renamed from: d */
    private void m1527d() {
        if (!f1471f) {
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 注册零号报文 imprint 应答处理回调。");
            ImprintHandler.getImprintService(this.f1475c).registPreProcessCallback(AnalyticsConstants.ZERO_RESPONSE_FLAG, new UMImprintPreProcessCallback() {
                /* class com.umeng.commonsdk.statistics.internal.NetworkHelper.C02022 */

                @Override // com.umeng.commonsdk.statistics.internal.UMImprintPreProcessCallback
                public boolean onPreProcessImprintKey(String str, String str2) {
                    UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> onImprintValueChanged: key=" + str + "; value= " + str2);
                    FieldManager.m1113a().mo663a(NetworkHelper.this.f1475c);
                    UMWorkDispatch.sendEvent(NetworkHelper.this.f1475c, UMInternalConfig.f1224s, UMInternalData.m1179a(NetworkHelper.this.f1475c).mo677a(), null);
                    ImprintHandler.getImprintService(NetworkHelper.this.f1475c).mo766a(AnalyticsConstants.ZERO_RESPONSE_FLAG);
                    return true;
                }
            });
            ImprintHandler.getImprintService(this.f1475c).registImprintCallback(AnalyticsConstants.CFG_FIELD_KEY, new UMImprintChangeCallback() {
                /* class com.umeng.commonsdk.statistics.internal.NetworkHelper.C02033 */

                @Override // com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback
                public void onImprintValueChanged(String str, String str2) {
                    UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> first onImprintValueChanged: key=" + str + "; value= " + str2);
                    FieldManager.m1113a().mo664a(NetworkHelper.this.f1475c, str2);
                    UMWorkDispatch.sendEvent(NetworkHelper.this.f1475c, UMInternalConfig.f1224s, UMInternalData.m1179a(NetworkHelper.this.f1475c).mo677a(), null);
                    if (FieldManager.allow(UMConstant.f1600E)) {
                        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> recv zcfg response: foregound count timer enabled.");
                        if (!UMWorkDispatch.eventHasExist()) {
                            UMWorkDispatch.sendEventEx(NetworkHelper.this.f1475c, CoreProtocolImpl.C0113a.f897C, CoreProtocol.getInstance(NetworkHelper.this.f1475c), null, 0);
                        }
                    }
                    if (FieldManager.allow(UMConstant.f1601F)) {
                        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> recv zcfg response: FirstResumeTrigger enabled.");
                        AutoViewPageTracker.m890a(NetworkHelper.this.f1475c).mo537b(NetworkHelper.this.f1475c);
                    }
                    ImprintHandler.getImprintService(NetworkHelper.this.f1475c).unregistImprintCallback(AnalyticsConstants.CFG_FIELD_KEY, this);
                }
            });
            f1471f = true;
        }
    }

    /* renamed from: a */
    public byte[] mo825a(byte[] bArr, boolean z, boolean z2, String str) {
        if (SdkVersion.SDK_TYPE == 0) {
            m1524a();
        } else {
            UMServerURL.DEFAULT_URL = UMServerURL.OVERSEA_DEFAULT_URL;
            UMServerURL.SECONDARY_URL = UMServerURL.OVERSEA_SECONDARY_URL;
            m1525b();
        }
        int i = 0;
        byte[] bArr2 = null;
        while (true) {
            String[] strArr = AnalyticsConstants.APPLOG_URL_LIST;
            if (i >= strArr.length) {
                break;
            }
            String str2 = strArr[i];
            if (z2) {
                m1527d();
            } else {
                m1526c();
            }
            bArr2 = mo824a(bArr, str2 + File.separator + str);
            if (bArr2 != null) {
                IRequestStat bVar = this.f1476d;
                if (bVar != null) {
                    bVar.onRequestSucceed(z);
                }
            } else {
                IRequestStat bVar2 = this.f1476d;
                if (bVar2 != null) {
                    bVar2.onRequestFailed();
                }
                i++;
            }
        }
        return bArr2;
    }

    /* renamed from: e */
    private boolean m1528e() {
        NetworkInfo activeNetworkInfo;
        String extraInfo;
        if (this.f1475c.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.f1475c.getPackageName()) != 0) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.f1475c.getSystemService("connectivity");
            if (!DeviceConfig.checkPermission(this.f1475c, "android.permission.ACCESS_NETWORK_STATE") || connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || activeNetworkInfo.getType() == 1 || (extraInfo = activeNetworkInfo.getExtraInfo()) == null || (!extraInfo.equals("cmwap") && !extraInfo.equals("3gwap") && !extraInfo.equals("uniwap"))) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.f1475c, th);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:64:0x0136 A[SYNTHETIC, Splitter:B:64:0x0136] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0142 A[SYNTHETIC, Splitter:B:69:0x0142] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x015a A[SYNTHETIC, Splitter:B:80:0x015a] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0166 A[SYNTHETIC, Splitter:B:85:0x0166] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x017e A[SYNTHETIC, Splitter:B:94:0x017e] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x018a A[SYNTHETIC, Splitter:B:99:0x018a] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] mo824a(byte[] r10, java.lang.String r11) {
        /*
        // Method dump skipped, instructions count: 435
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.internal.NetworkHelper.mo824a(byte[], java.lang.String):byte[]");
    }
}
