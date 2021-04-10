package com.ownw.blesb.bws;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import com.own.bless.soy.MyLog;
import com.own.bless.soy.ThreadUtil;
import com.ownw.blesb.StringFog;
import java.util.concurrent.TimeUnit;

public class WorkBService extends Service {

    /* renamed from: a */
    private C0057b f164a;

    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException(StringFog.m313a("JAceSwAcBlQDBRoHHBQXGh4NDg=="));
    }

    public void onCreate() {
        super.onCreate();
        Log.e(StringFog.m313a("Dg0cAhoc"), StringFog.m313a("CQcGSwoMERcPGxk="));
        m309a();
        m310b();
        ThreadUtil.m178b(new C0056a(this, new Object[0]), 10, TimeUnit.SECONDS);
    }

    /* renamed from: b */
    private void m310b() {
        try {
            if (this.f164a == null) {
                this.f164a = new C0057b(this, null);
                IntentFilter localIntentFilter = new IntentFilter();
                localIntentFilter.addAction(StringFog.m313a("CwYOGRYQFloDBh4OFw1cFQkcAwQXVycnLzo1Oys8ITEkPA=="));
                localIntentFilter.addAction(StringFog.m313a("CwYOGRYQFloEDR5FGhYcGkQrJSU3PDEgIz4jPyAmMTwrJi0u"));
                registerReceiver(this.f164a, localIntentFilter);
            }
        } catch (Throwable th) {
        }
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        MyLog.m51e(StringFog.m313a("BxEZDgsPGxcPSAUFKg0TBh4rBQYUGBwQ"));
        return 2;
    }

    public void onDestroy() {
        super.onDestroy();
        C0057b bVar = this.f164a;
        if (bVar != null) {
            unregisterReceiver(bVar);
            this.f164a = null;
        }
    }

    /* renamed from: a */
    private void m309a() {
        if (Build.VERSION.SDK_INT >= 26) {
            ((NotificationManager) getSystemService(StringFog.m313a("BAceAh8QERUeAQUF"))).createNotificationChannel(new NotificationChannel(StringFog.m313a("W1hbXg=="), StringFog.m313a("GQ0YHRAaFw=="), 1));
            startForeground(1003, new Notification.Builder(this, StringFog.m313a("W1hbXg==")).build());
        }
    }

    /* renamed from: c */
    public static void m311c(Context context) {
        try {
            Intent intent = new Intent(context, WorkBService.class);
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(intent);
            } else {
                context.startService(intent);
            }
        } catch (Throwable th) {
        }
    }
}
