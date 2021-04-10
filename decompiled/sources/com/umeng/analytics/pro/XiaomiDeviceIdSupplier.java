package com.umeng.analytics.pro;

import android.content.Context;
import com.own.bless.soy.IdentifierManager;
import com.umeng.commonsdk.debug.UMLog;

/* access modifiers changed from: package-private */
/* renamed from: com.umeng.analytics.pro.ag */
public class XiaomiDeviceIdSupplier implements IDeviceIdSupplier {
    XiaomiDeviceIdSupplier() {
    }

    @Override // com.umeng.analytics.pro.IDeviceIdSupplier
    /* renamed from: a */
    public String mo282a(Context context) {
        if (context == null) {
        }
        try {
            if (IdentifierManager.m85b()) {
                return IdentifierManager.m86c(context);
            }
            UMLog.mutlInfo(2, "当前设备不支持获取OAID");
            return null;
        } catch (Exception e) {
            UMLog.mutlInfo(2, "未检测到您集成OAID SDK包");
            return null;
        }
    }
}
