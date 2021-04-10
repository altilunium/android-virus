package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.UMInternalConfig;
import com.umeng.commonsdk.internal.UMInternalData;
import com.umeng.commonsdk.statistics.common.ULog;

/* renamed from: com.umeng.commonsdk.internal.utils.b */
public class BaseStationUtils {

    /* renamed from: b */
    private static final String f1264b = "BaseStationUtils";

    /* renamed from: c */
    private static boolean f1265c = false;

    /* renamed from: d */
    private static Context f1266d = null;

    /* renamed from: a */
    PhoneStateListener f1267a;

    /* renamed from: e */
    private TelephonyManager f1268e;

    private BaseStationUtils(Context context) {
        this.f1267a = new PhoneStateListener() {
            /* class com.umeng.commonsdk.internal.utils.BaseStationUtils.C01681 */

            public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                String str;
                super.onSignalStrengthsChanged(signalStrength);
                ULog.m1390e(BaseStationUtils.f1264b, "base station onSignalStrengthsChanged");
                try {
                    BaseStationUtils.this.f1268e = (TelephonyManager) BaseStationUtils.f1266d.getSystemService("phone");
                    String[] split = signalStrength.toString().split(" ");
                    String str2 = null;
                    if (BaseStationUtils.this.f1268e != null && BaseStationUtils.this.f1268e.getNetworkType() == 13) {
                        str = "" + Integer.parseInt(split[9]);
                    } else if (BaseStationUtils.this.f1268e == null || !(BaseStationUtils.this.f1268e.getNetworkType() == 8 || BaseStationUtils.this.f1268e.getNetworkType() == 10 || BaseStationUtils.this.f1268e.getNetworkType() == 9 || BaseStationUtils.this.f1268e.getNetworkType() == 3)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append((signalStrength.getGsmSignalStrength() * 2) - 113);
                        sb.append("");
                        str = sb.toString();
                    } else {
                        String e = BaseStationUtils.this.m1244e();
                        if (!TextUtils.isEmpty(e) && e.equals("中国移动")) {
                            str2 = "0";
                        } else if (!TextUtils.isEmpty(e) && e.equals("中国联通")) {
                            str2 = signalStrength.getCdmaDbm() + "";
                        } else if (!TextUtils.isEmpty(e) && e.equals("中国电信")) {
                            str2 = signalStrength.getEvdoDbm() + "";
                        }
                        str = str2;
                    }
                    ULog.m1390e(BaseStationUtils.f1264b, "stationStrength is " + str);
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            UMWorkDispatch.sendEvent(BaseStationUtils.f1266d, UMInternalConfig.f1214i, UMInternalData.m1179a(BaseStationUtils.f1266d).mo677a(), str);
                        } catch (Throwable th) {
                        }
                    }
                    BaseStationUtils.this.mo685c();
                } catch (Exception e2) {
                }
            }
        };
        if (context != null) {
            try {
                this.f1268e = (TelephonyManager) context.getSystemService("phone");
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: com.umeng.commonsdk.internal.utils.b$a */
    /* compiled from: BaseStationUtils */
    class C0169a {

        /* renamed from: a */
        private static final BaseStationUtils f1270a = new BaseStationUtils(BaseStationUtils.f1266d);

        private C0169a() {
        }
    }

    /* renamed from: a */
    public static BaseStationUtils m1241a(Context context) {
        if (f1266d == null && context != null) {
            f1266d = context.getApplicationContext();
        }
        return C0169a.f1270a;
    }

    /* renamed from: a */
    public synchronized boolean mo683a() {
        return f1265c;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private String m1244e() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) f1266d.getSystemService("phone");
            if (telephonyManager == null) {
                return null;
            }
            String simOperator = telephonyManager.getSimOperator();
            if (TextUtils.isEmpty(simOperator)) {
                return null;
            }
            if (!simOperator.equals("46000")) {
                if (!simOperator.equals("46002")) {
                    if (simOperator.equals("46001")) {
                        return "中国联通";
                    }
                    if (simOperator.equals("46003")) {
                        return "中国电信";
                    }
                    return null;
                }
            }
            return "中国移动";
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: b */
    public synchronized void mo684b() {
        ULog.m1390e(f1264b, "base station registerListener");
        try {
            TelephonyManager telephonyManager = this.f1268e;
            if (telephonyManager != null) {
                telephonyManager.listen(this.f1267a, 256);
            }
            f1265c = true;
        } catch (Throwable th) {
        }
    }

    /* renamed from: c */
    public synchronized void mo685c() {
        ULog.m1390e(f1264b, "base station unRegisterListener");
        try {
            TelephonyManager telephonyManager = this.f1268e;
            if (telephonyManager != null) {
                telephonyManager.listen(this.f1267a, 0);
            }
            f1265c = false;
        } catch (Throwable th) {
        }
    }
}
