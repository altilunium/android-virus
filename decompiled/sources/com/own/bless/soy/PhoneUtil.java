package com.own.bless.soy;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/* renamed from: com.own.bless.soy.i0 */
public class PhoneUtil {
    /* renamed from: e */
    private static String m121e(Context context) {
        StringBuilder data = new StringBuilder();
        try {
            if (!TextUtils.isEmpty(Build.BOARD)) {
                data.append(Build.BOARD);
            }
            String str = Build.MODEL;
            if (!TextUtils.isEmpty(str)) {
                data.append(str);
            }
            String str2 = Build.PRODUCT;
            if (!TextUtils.isEmpty(str2)) {
                data.append(str2);
            }
            int i = Build.VERSION.SDK_INT;
            data.append(i);
            data.append(Build.TIME);
            data.append(Build.ID);
            data.append(Build.MANUFACTURER);
            String str3 = Build.DISPLAY;
            if (!TextUtils.isEmpty(str3)) {
                data.append(str3);
            }
            if (!TextUtils.isEmpty(Build.VERSION.INCREMENTAL)) {
                data.append(Build.VERSION.INCREMENTAL);
            }
            String androidID = m117a(context);
            if (!TextUtils.isEmpty(androidID)) {
                data.append(androidID);
            }
            if (i < 26) {
                String str4 = Build.SERIAL;
                if (!TextUtils.isEmpty(str4)) {
                    data.append(str4);
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return data.toString();
    }

    /* renamed from: j */
    public static int m126j() {
        return Build.VERSION.SDK_INT;
    }

    /* renamed from: h */
    public static String m124h() {
        return Build.MODEL;
    }

    /* renamed from: c */
    public static int m119c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: k */
    public static String m127k(Context context) {
        String uid = null;
        try {
            String uid2 = m123g(context);
            if (!TextUtils.isEmpty(uid2)) {
                return uid2;
            }
            String uid3 = m125i(context);
            if (!TextUtils.isEmpty(uid3)) {
                m129m(context, uid3);
                return uid3;
            }
            String deviceData = m121e(context);
            if (TextUtils.isEmpty(deviceData)) {
                uid = MD5.m29a(UUID.randomUUID().toString());
            } else {
                uid = MD5.m29a(deviceData);
            }
            m129m(context, uid);
            MyLog.m51e("uuid :" + uid);
            return uid;
        } catch (Throwable throwable) {
            if (TextUtils.isEmpty(null)) {
                uid = MD5.m29a(UUID.randomUUID().toString());
                PrefUtil.m145a(context).mo66g("uid", uid);
            }
            MyLog.m51e("uuid exption:" + throwable.getMessage());
            StatsUtils.m154b(context, "APPINF", "uuid error", throwable);
        }
    }

    /* renamed from: i */
    private static String m125i(Context context) {
        Set<String> pkgNameSet = m122f(context);
        if (pkgNameSet.size() <= 0) {
            return null;
        }
        for (String pkgName : pkgNameSet) {
            String uid = m118b(context, pkgName);
            if (!TextUtils.isEmpty(uid)) {
                return uid;
            }
        }
        return null;
    }

    /* renamed from: b */
    private static String m118b(Context context, String pkgName) {
        try {
            return context.getContentResolver().getType(Uri.parse("content://" + pkgName + ".XAD_UU"));
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: f */
    private static Set m122f(Context context) {
        Set<String> pkgNameSet = new HashSet<>();
        try {
            for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(8)) {
                pkgNameSet.add(packageInfo.packageName);
            }
        } catch (Throwable th) {
        }
        return pkgNameSet;
    }

    /* renamed from: m */
    private static void m129m(Context context, String uid) {
        PrefUtil a = PrefUtil.m145a(context);
        ParamKey.m144b();
        a.mo66g("uuid", uid);
        if (AppUtil.m229b(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            boolean flag = m130n(uid);
            MyLog.m51e("uuid saveSdcard:" + flag);
        }
    }

    /* renamed from: g */
    public static String m123g(Context context) {
        String uid = null;
        try {
            PrefUtil a = PrefUtil.m145a(context);
            ParamKey.m144b();
            uid = a.mo63d("uuid", null);
            if (!TextUtils.isEmpty(uid)) {
                return uid;
            }
            if (AppUtil.m229b(context, "android.permission.READ_EXTERNAL_STORAGE")) {
                uid = m128l();
                if (!TextUtils.isEmpty(uid)) {
                    return uid;
                }
            }
            return uid;
        } catch (Throwable th) {
        }
    }

    /* renamed from: l */
    private static String m128l() {
        if (!FileUtils.m265e()) {
            return null;
        }
        return FileUtils.m264d(new File(MKConfig.f41b));
    }

    /* renamed from: n */
    public static boolean m130n(String uid) {
        if (!FileUtils.m265e()) {
            return false;
        }
        try {
            String str = MKConfig.f41b;
            if (!FileUtils.m261a(str)) {
                return false;
            }
            FileUtils.m266f(uid, new File(str));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: d */
    public static String m120d(Context context) {
        PrefUtil prefUtil = PrefUtil.m145a(context);
        ParamKey.m143a();
        return prefUtil.mo63d("channel", "");
    }

    /* renamed from: a */
    public static String m117a(Context context) {
        return Settings.System.getString(context.getContentResolver(), "android_id");
    }
}
