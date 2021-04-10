package com.own.bless.soy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.umeng.analytics.pro.TApplicationException;
import com.umeng.commonsdk.statistics.common.ReportPolicy;

/* renamed from: com.own.bless.soy.d0 */
public class NetworkUtil {
    @SuppressLint({"MissingPermission"})
    /* renamed from: a */
    public static int m63a(Context context) {
        NetworkInfo.State state;
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connManager == null) {
            return -1;
        }
        if (!AppUtil.m229b(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return -2;
        }
        NetworkInfo activeNetInfo = connManager.getActiveNetworkInfo();
        if (activeNetInfo == null || !activeNetInfo.isAvailable()) {
            return -1;
        }
        NetworkInfo wifiInfo = connManager.getNetworkInfo(1);
        if (wifiInfo != null && (state = wifiInfo.getState()) != null && (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING)) {
            return 1;
        }
        switch (((TelephonyManager) context.getSystemService("phone")).getNetworkType()) {
            case 1:
            case 2:
            case 4:
            case TApplicationException.f455h /*{ENCODED_INT: 7}*/:
            case ReportPolicy.QUASI_REALTIME_POLICY /*{ENCODED_INT: 11}*/:
                return 2;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return 3;
            case 13:
                return 4;
            default:
                return 0;
        }
    }

    /* renamed from: b */
    public static boolean m64b(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivity == null) {
            return false;
        }
        try {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info == null || !info.isConnected() || info.getState() != NetworkInfo.State.CONNECTED) {
                return false;
            }
            return true;
        } catch (Exception | SecurityException e) {
            return false;
        }
    }
}
