package com.own.bless.soy;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.ownw.blesb.StringFog;
import com.ownw.blesb.bws.BlessService;
import java.util.Random;

/* renamed from: com.own.bless.soy.c1 */
public class NoticeUtil {

    /* renamed from: a */
    public static final String f21a = StringFog.m313a("DxAeGRgmFhUeCQ==");

    /* renamed from: d */
    public static boolean m55d(Context mContext, NoticeModel result) {
        Notification notification;
        if (!NotificationUtil.m71a(mContext)) {
            MyLog.m51e(StringFog.m313a("GQAFHDcWBh0MAQkKDRAdGkoNBAobFRc6BRwDCBxETxILBBkO"));
            StatsUtils.m153a(mContext, StringFog.m313a("JCc+Ijo8LTUu"), StringFog.m313a("MQYFHxAaFylKGwIEDlkcGx4BCQ5ZHxMdBkRKDhcYEBgPJgUfEBoXVAMbSg0YFQER"));
            return false;
        }
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(StringFog.m313a("BAceAh8QERUeAQUF"));
        try {
            int notificationId = new Random().nextInt(999) + 1000;
            PendingIntent pi = m54c(mContext, result);
            PendingIntent deletePi = m53b(mContext, result);
            if (Build.VERSION.SDK_INT < 26) {
                notification = NotificationUtil.m72b(mContext, result, null, pi, deletePi);
            } else {
                String channelId = StringFog.m313a("CQY=") + notificationId;
                Notification notification2 = NotificationUtil.m72b(mContext, result, channelId, pi, deletePi);
                notificationManager.createNotificationChannel(m52a(channelId));
                notification = notification2;
            }
            if (notification == null) {
                MyLog.m51e(StringFog.m313a("GQAFHDcWBh0MAQkKDRAdGkoKHwIVHRcGV1UEHhUV"));
                StatsUtils.m153a(mContext, StringFog.m313a("JCc+Ijo8LTUu"), StringFog.m313a("MQYFHxAaFylKGwIEDlkcGx4BCQ5ZHxMdBkRKBRYNGxIDCwsfEBYcVAgdAwcdWRsHSgYfBxU="));
                return false;
            }
            notificationManager.notify(notificationId, notification);
            return true;
        } catch (Throwable throwable) {
            MyLog.m48b(StringFog.m313a("MQYFHxAaFylKGwIEDlkcGx4BCQ5ZHxMdBg=="), throwable);
            StatsUtils.m154b(mContext, StringFog.m313a("JCc+Ijo8LTUu"), StringFog.m313a("MQYFHxAaFylKGwIEDlkcGx4BCQ5ZHxMdBg=="), throwable);
            return false;
        }
    }

    @TargetApi(26)
    /* renamed from: a */
    private static NotificationChannel m52a(String channelId) {
        NotificationChannel mChannel = new NotificationChannel(channelId, channelId, 3);
        mChannel.canBypassDnd();
        mChannel.enableVibration(true);
        mChannel.setVibrationPattern(new long[]{100, 200, 300});
        mChannel.setShowBadge(false);
        return mChannel;
    }

    /* renamed from: c */
    private static PendingIntent m54c(Context context, NoticeModel result) {
        Intent intent = BlessService.m299a(context);
        String data = JsonUtils.m268a(result);
        MyLog.m51e(StringFog.m313a("V1VXSx4cBiQPBg4CFx47Gh4NBB9DWQ==") + data);
        intent.putExtra(f21a, data);
        return PendingIntent.getService(context, 1, intent, 134217728);
    }

    /* renamed from: b */
    private static PendingIntent m53b(Context context, NoticeModel result) {
        Intent intent = AppUtil.m231d(context, result.f68b);
        if (intent != null) {
            return PendingIntent.getActivity(context, 1, intent, 134217728);
        }
        return null;
    }
}
