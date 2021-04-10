package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import com.umeng.commonsdk.config.FieldManager;

public class ApplicationLayerUtilAgent {
    public static void wifiChange(Context context) {
        if (FieldManager.m1114b()) {
            ApplicationLayerUtil.m1223c(context);
        }
    }
}
