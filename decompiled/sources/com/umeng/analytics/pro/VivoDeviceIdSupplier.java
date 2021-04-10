package com.umeng.analytics.pro;

import android.content.Context;
import com.own.bless.soy.C0017k3;
import com.umeng.commonsdk.debug.UMLog;

/* renamed from: com.umeng.analytics.pro.af */
public class VivoDeviceIdSupplier implements IDeviceIdSupplier {
    @Override // com.umeng.analytics.pro.IDeviceIdSupplier
    /* renamed from: a */
    public String mo282a(Context context) {
        if (context == null) {
        }
        try {
            if (C0017k3.m171a(context)) {
                return C0017k3.m172b(context);
            }
            UMLog.mutlInfo(2, "当前设备不支持获取OAID");
            return null;
        } catch (Exception e) {
            UMLog.mutlInfo(2, "未检测到您集成OAID SDK包");
            return null;
        }
    }
}
