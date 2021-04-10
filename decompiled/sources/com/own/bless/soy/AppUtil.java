package com.own.bless.soy;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import java.util.List;

/* renamed from: com.own.bless.soy.v */
public class AppUtil {
    /* renamed from: f */
    public static long m233f(Context context) {
        long installTime = PrefUtil.m145a(context).mo62c("installTime", 0);
        if (installTime > 0) {
            return installTime;
        }
        try {
            installTime = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (RuntimeException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        if (installTime <= 0) {
            installTime = System.currentTimeMillis();
        }
        PrefUtil.m145a(context).mo65f("installTime", installTime);
        return installTime;
    }

    /* renamed from: j */
    public static boolean m237j(String url, Context context) {
        try {
            Intent intent = m231d(context, url);
            if (intent == null) {
                return false;
            }
            context.startActivity(intent);
            return true;
        } catch (Throwable throwable) {
            StatsUtils.m154b(context, "TOOL", "[tool] open browser fail: " + url, throwable);
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m229b(Context mContexts, String permission) {
        return mContexts.checkCallingOrSelfPermission(permission) == 0;
    }

    /* renamed from: d */
    public static Intent m231d(Context context, String url) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setFlags(268435456);
        intent.setData(Uri.parse(url));
        try {
            List<ResolveInfo> activities = context.getPackageManager().queryIntentActivities(intent, 65536);
            if (activities == null) {
                return null;
            }
            if (activities.size() == 0) {
                return null;
            }
            ActivityInfo activityInfo = activities.get(0).activityInfo;
            intent.setClassName(activityInfo.packageName, activityInfo.name);
            return intent;
        } catch (Exception e) {
        }
    }

    /* renamed from: e */
    public static int m232e(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setFlags(268435456);
        intent.setData(Uri.parse("http://a.b"));
        try {
            List<ResolveInfo> activities = context.getPackageManager().queryIntentActivities(intent, 65536);
            if (activities != null) {
                if (activities.size() != 0) {
                    return activities.size();
                }
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    /* renamed from: i */
    public static boolean m236i(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (capabilities == null || !capabilities.hasTransport(4)) {
                    return false;
                }
                return true;
            }
            Log.d("VPN-RAJ", "in Old Android Version");
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(17);
            if (networkInfo == null || !networkInfo.isConnected()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m228a(Context context) {
        if (Build.VERSION.SDK_INT < 21 || ((AppOpsManager) context.getSystemService("appops")).checkOpNoThrow("android:get_usage_stats", Binder.getCallingUid(), context.getPackageName()) == 0) {
            return true;
        }
        return false;
    }

    /* renamed from: h */
    public static int m235h(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /* renamed from: g */
    public static int m234g(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /* renamed from: c */
    public static int m230c(Context context, float dpValue) {
        return (int) ((dpValue * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
