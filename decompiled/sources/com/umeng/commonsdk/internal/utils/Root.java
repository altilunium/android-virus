package com.umeng.commonsdk.internal.utils;

import android.os.Build;
import com.umeng.commonsdk.internal.utils.ExecShell;
import java.io.File;

/* renamed from: com.umeng.commonsdk.internal.utils.h */
public class Root {
    /* renamed from: a */
    public static boolean m1270a() {
        if (!m1271b() && !m1272c() && !m1273d() && !m1274e()) {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    private static boolean m1271b() {
        String str = Build.TAGS;
        if (str == null || !str.contains("test-keys")) {
            return false;
        }
        return true;
    }

    /* renamed from: c */
    private static boolean m1272c() {
        try {
            if (new File("/system/app/Superuser.apk").exists()) {
                return true;
            }
        } catch (Exception e) {
        }
        try {
            if (new File("/system/app/Kinguser.apk").exists()) {
                return true;
            }
            return false;
        } catch (Exception e2) {
            return false;
        }
    }

    /* renamed from: d */
    private static boolean m1273d() {
        if (new ExecShell().mo691a(ExecShell.EnumC0173a.check_su_binary) != null) {
            return true;
        }
        return false;
    }

    /* renamed from: e */
    private static boolean m1274e() {
        String[] strArr = {"/bin", "/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/", "/su/bin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"};
        for (int i = 0; i < 12; i++) {
            String str = strArr[i];
            if (new File(str + "su").exists()) {
                return true;
            }
        }
        return false;
    }
}
