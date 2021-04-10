package com.own.bless.soy;

import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;

/* renamed from: com.own.bless.soy.e0 */
public class NotificationUtil {
    /* renamed from: a */
    public static boolean m71a(Context mContext) {
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService("notification");
        int i = Build.VERSION.SDK_INT;
        if (i >= 24) {
            return notificationManager.areNotificationsEnabled();
        }
        if (i < 19) {
            return true;
        }
        AppOpsManager appOps = (AppOpsManager) mContext.getSystemService("appops");
        ApplicationInfo appInfo = mContext.getApplicationInfo();
        String pkg = mContext.getApplicationContext().getPackageName();
        int uid = appInfo.uid;
        try {
            Class<?> appOpsClass = Class.forName(AppOpsManager.class.getName());
            Class<?> cls = Integer.TYPE;
            if (((Integer) appOpsClass.getMethod("checkOpNoThrow", cls, cls, String.class).invoke(appOps, Integer.valueOf(((Integer) appOpsClass.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(uid), pkg)).intValue() == 0) {
                return true;
            }
            return false;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | RuntimeException | InvocationTargetException e) {
            return true;
        }
    }

    /* renamed from: b */
    public static Notification m72b(Context context, NoticeModel result, String channelId, PendingIntent pi, PendingIntent deletePI) {
        Notification.Builder builder;
        if (pi == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            builder = new Notification.Builder(context, channelId);
        } else {
            builder = new Notification.Builder(context);
        }
        builder.setContentTitle(result.f80h);
        builder.setContentText(result.f81i);
        builder.setAutoCancel(true);
        if (!TextUtils.isEmpty(result.f82j)) {
            builder.setLargeIcon(BitmapFactory.decodeFile(result.mo71a(context)));
        }
        builder.setSmallIcon(17301651);
        builder.setShowWhen(false);
        builder.setDefaults(3);
        if (deletePI != null) {
            builder.setDeleteIntent(deletePI);
        }
        builder.setContentIntent(pi);
        return builder.build();
    }
}
