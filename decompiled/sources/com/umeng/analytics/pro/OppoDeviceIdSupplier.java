package com.umeng.analytics.pro;

import android.content.Context;
import com.own.bless.soy.OpenIDSDK;
import com.umeng.commonsdk.debug.UMLog;

/* renamed from: com.umeng.analytics.pro.ad */
public class OppoDeviceIdSupplier implements IDeviceIdSupplier {

    /* renamed from: a */
    private boolean f254a = false;

    @Override // com.umeng.analytics.pro.IDeviceIdSupplier
    /* renamed from: a */
    public String mo282a(Context context) {
        if (context == null) {
        }
        try {
            if (!this.f254a) {
                OpenIDSDK.m77a(context);
                this.f254a = true;
            }
            if (OpenIDSDK.m78b()) {
                return OpenIDSDK.m79c(context);
            }
            UMLog.mutlInfo(2, "当前设备不支持获取OAID");
            return null;
        } catch (Exception e) {
            UMLog.mutlInfo(2, "未检测到您集成OAID SDK包");
            return null;
        }
    }
}
