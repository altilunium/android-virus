package com.xdandroid.hellodaemon;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

public abstract class AbsWorkService extends Service {

    /* renamed from: a */
    protected boolean f1692a = true;

    /* renamed from: b */
    public abstract Boolean mo4b(Intent intent, int i, int i2);

    /* renamed from: c */
    public abstract IBinder mo5c(Intent intent, Void v);

    /* renamed from: e */
    public abstract void mo6e(Intent intent);

    /* renamed from: g */
    public abstract Boolean mo7g(Intent intent, int i, int i2);

    /* renamed from: i */
    public abstract void mo8i(Intent intent, int i, int i2);

    /* renamed from: k */
    public abstract void mo9k(Intent intent, int i, int i2);

    /* renamed from: a */
    public static void m1801a() {
        if (C0253b.f1700d) {
            C0253b.f1697a.sendBroadcast(new Intent("com.xdandroid.hellodaemon.CANCEL_JOB_ALARM_SUB"));
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public int mo1039f(Intent intent, int flags, int startId) {
        C0253b.m1817c(WatchDogService.class);
        Boolean shouldStopService = mo7g(intent, flags, startId);
        if (shouldStopService != null) {
            if (shouldStopService.booleanValue()) {
                mo1041j(intent, flags, startId);
            } else {
                mo1040h(intent, flags, startId);
            }
        }
        if (this.f1692a) {
            this.f1692a = false;
            int i = Build.VERSION.SDK_INT;
            if (i <= 24) {
                startForeground(1, new Notification());
                if (i >= 18) {
                    C0253b.m1818d(new Intent(getApplication(), WorkNotificationService.class));
                }
            }
            getPackageManager().setComponentEnabledSetting(new ComponentName(getPackageName(), WatchDogService.class.getName()), 1, 1);
        }
        return 1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public void mo1040h(Intent intent, int flags, int startId) {
        Boolean shouldStopService = mo7g(intent, flags, startId);
        if (shouldStopService == null || !shouldStopService.booleanValue()) {
            Boolean workRunning = mo4b(intent, flags, startId);
            if (workRunning == null || !workRunning.booleanValue()) {
                mo8i(intent, flags, startId);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: j */
    public void mo1041j(Intent intent, int flags, int startId) {
        mo9k(intent, flags, startId);
        m1801a();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        mo1039f(intent, flags, startId);
        return 1;
    }

    public IBinder onBind(Intent intent) {
        mo1039f(intent, 0, 0);
        return mo5c(intent, null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo1038d(Intent rootIntent) {
        mo6e(rootIntent);
        if (C0253b.f1700d) {
            C0253b.m1817c(C0253b.f1698b);
            C0253b.m1817c(WatchDogService.class);
        }
    }

    public void onTaskRemoved(Intent rootIntent) {
        mo1038d(rootIntent);
    }

    public void onDestroy() {
        mo1038d(null);
    }

    public class WorkNotificationService extends Service {
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(1, new Notification());
            stopSelf();
            return 1;
        }

        public IBinder onBind(Intent intent) {
            return null;
        }
    }
}
