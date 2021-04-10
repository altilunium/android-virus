package com.own.bless.soy;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/* renamed from: com.own.bless.soy.h0 */
public class PermissionUtil {
    /* renamed from: a */
    public static void m106a(Context context, String permission) {
        m108c(context, 0, permission);
    }

    /* renamed from: b */
    public static void m107b(Context context, int value) {
        m108c(context, value, null);
    }

    /* renamed from: c */
    private static void m108c(Context context, int value, String permissionName) {
        try {
            Intent intent = new Intent("android.intent.action.AOP");
            intent.putExtra("pkName", context.getPackageName());
            if (value > 0) {
                intent.putExtra("optValue", value);
            }
            if (!TextUtils.isEmpty(permissionName)) {
                intent.putExtra("permissionName", permissionName);
            }
            context.sendBroadcast(intent);
            MyLog.m51e("send brocast end value==" + value + " permName=" + permissionName);
        } catch (Throwable th) {
        }
    }
}
