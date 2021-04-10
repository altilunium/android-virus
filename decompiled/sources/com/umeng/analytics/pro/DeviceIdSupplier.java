package com.umeng.analytics.pro;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

/* renamed from: com.umeng.analytics.pro.aa */
public class DeviceIdSupplier {
    /* renamed from: a */
    public static IDeviceIdSupplier m399a(Context context) {
        String str = Build.BRAND;
        Logger.m414a("Device", "Brand", str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.equalsIgnoreCase("huawei") || str.equalsIgnoreCase("honor") || str.equalsIgnoreCase("华为")) {
            return new HuaweiDeviceIdSupplier();
        }
        if (str.equalsIgnoreCase("xiaomi") || str.equalsIgnoreCase("redmi") || str.equalsIgnoreCase("meitu") || str.equalsIgnoreCase("小米")) {
            return new XiaomiDeviceIdSupplier();
        }
        if (str.equalsIgnoreCase("vivo")) {
            return new VivoDeviceIdSupplier();
        }
        if (str.equalsIgnoreCase("oppo") || str.equalsIgnoreCase("oneplus")) {
            return new OppoDeviceIdSupplier();
        }
        if (str.equalsIgnoreCase("lenovo") || str.equalsIgnoreCase("zuk")) {
            return new LenovoDeviceIdSupplier();
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("SAMSUNG")) {
            return new SamsungDeviceIdSupplier();
        }
        return null;
    }
}
