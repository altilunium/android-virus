package com.xdandroid.hellodaemon;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import com.own.bless.soy.Disposable;
import java.util.concurrent.TimeUnit;
import p007io.reactivex.Observable;

public class WatchDogService extends Service {

    /* renamed from: a */
    protected static Disposable f1693a;

    /* renamed from: b */
    protected static PendingIntent f1694b;

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final int mo1053c() {
        if (!C0253b.f1700d) {
            return 1;
        }
        Disposable e1Var = f1693a;
        if (e1Var != null && !e1Var.isDisposed()) {
            return 1;
        }
        int i = Build.VERSION.SDK_INT;
        if (i <= 24) {
            startForeground(2, new Notification());
            if (i >= 18) {
                C0253b.m1818d(new Intent(C0253b.f1697a, WatchDogNotificationService.class));
            }
        }
        if (i >= 21) {
            JobInfo.Builder builder = new JobInfo.Builder(2, new ComponentName(C0253b.f1697a, JobSchedulerService.class));
            builder.setPeriodic((long) C0253b.m1815a());
            if (i >= 24) {
                builder.setPeriodic(JobInfo.getMinPeriodMillis(), JobInfo.getMinFlexMillis());
            }
            builder.setPersisted(true);
            ((JobScheduler) getSystemService("jobscheduler")).schedule(builder.build());
        } else {
            f1694b = PendingIntent.getService(C0253b.f1697a, 2, new Intent(C0253b.f1697a, C0253b.f1698b), 134217728);
            ((AlarmManager) getSystemService("alarm")).setRepeating(0, ((long) C0253b.m1815a()) + System.currentTimeMillis(), (long) C0253b.m1815a(), f1694b);
        }
        f1693a = Observable.m1822b((long) C0253b.m1815a(), TimeUnit.MILLISECONDS).mo1071c(new C0254c(this), new C0255d(this));
        getPackageManager().setComponentEnabledSetting(new ComponentName(getPackageName(), C0253b.f1698b.getName()), 1, 1);
        return 1;
    }

    public final int onStartCommand(Intent intent, int flags, int startId) {
        return mo1053c();
    }

    public final IBinder onBind(Intent intent) {
        mo1053c();
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo1052b() {
        if (C0253b.f1700d) {
            C0253b.m1817c(C0253b.f1698b);
            C0253b.m1817c(WatchDogService.class);
        }
    }

    public void onTaskRemoved(Intent rootIntent) {
        mo1052b();
    }

    public void onDestroy() {
        mo1052b();
    }

    /* renamed from: a */
    public static void m1812a() {
        if (C0253b.f1700d) {
            if (Build.VERSION.SDK_INT >= 21) {
                ((JobScheduler) C0253b.f1697a.getSystemService("jobscheduler")).cancel(2);
            } else {
                AlarmManager am = (AlarmManager) C0253b.f1697a.getSystemService("alarm");
                PendingIntent pendingIntent = f1694b;
                if (pendingIntent != null) {
                    am.cancel(pendingIntent);
                }
            }
            Disposable e1Var = f1693a;
            if (e1Var != null) {
                e1Var.dispose();
            }
        }
    }

    public class WatchDogNotificationService extends Service {
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(2, new Notification());
            stopSelf();
            return 1;
        }

        public IBinder onBind(Intent intent) {
            return null;
        }
    }
}
