package com.own.bless.soy;

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;
import android.view.WindowManager;

/* renamed from: com.own.bless.soy.f0 */
public class OtherUtil {
    /* renamed from: a */
    public static boolean m81a(Context context, String key, long value) {
        return Math.abs(System.currentTimeMillis() - PrefUtil.m145a(context).mo62c(key, 0)) > value;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: b */
    public static boolean m82b(Context context) {
        PhoneUtil.m126j();
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getState() == 1;
        }
        return ((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }
}
