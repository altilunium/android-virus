package com.umeng.analytics.pro;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.umeng.commonsdk.debug.UMRTLog;
import java.util.ArrayList;

/* renamed from: com.umeng.analytics.pro.l */
public class BackgroundMonitor implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a */
    private static BackgroundMonitor f861a = new BackgroundMonitor();

    /* renamed from: b */
    private final int f862b = 3000;

    /* renamed from: c */
    private boolean f863c = false;

    /* renamed from: d */
    private boolean f864d = true;

    /* renamed from: e */
    private Handler f865e = new Handler(Looper.getMainLooper());

    /* renamed from: f */
    private ArrayList f866f = new ArrayList();

    /* renamed from: g */
    private RunnableC0111a f867g = new RunnableC0111a();

    /* renamed from: com.umeng.analytics.pro.l$a */
    /* compiled from: BackgroundMonitor */
    class RunnableC0111a implements Runnable {
        RunnableC0111a() {
        }

        public void run() {
            if (!BackgroundMonitor.this.f863c || !BackgroundMonitor.this.f864d) {
                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> still foreground.");
                return;
            }
            BackgroundMonitor.this.f863c = false;
            UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> went background.");
            for (int i = 0; i < BackgroundMonitor.this.f866f.size(); i++) {
                ((BackgroundWatcher) BackgroundMonitor.this.f866f.get(i)).mo259n();
            }
        }
    }

    /* renamed from: a */
    public static void m913a(Context context) {
        if (context instanceof Application) {
            ((Application) context).registerActivityLifecycleCallbacks(f861a);
        }
    }

    /* renamed from: a */
    public static BackgroundMonitor m912a() {
        return f861a;
    }

    private BackgroundMonitor() {
    }

    /* renamed from: a */
    public synchronized void mo546a(BackgroundWatcher mVar) {
        if (mVar != null) {
            this.f866f.add(mVar);
        }
    }

    /* renamed from: b */
    public synchronized void mo547b(BackgroundWatcher mVar) {
        if (mVar != null) {
            for (int i = 0; i < this.f866f.size(); i++) {
                if (this.f866f.get(i) == mVar) {
                    this.f866f.remove(i);
                }
            }
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
        this.f864d = false;
        this.f863c = true;
        RunnableC0111a aVar = this.f867g;
        if (aVar != null) {
            this.f865e.removeCallbacks(aVar);
        }
    }

    public void onActivityPaused(Activity activity) {
        this.f864d = true;
        RunnableC0111a aVar = this.f867g;
        if (aVar != null) {
            this.f865e.removeCallbacks(aVar);
            this.f865e.postDelayed(this.f867g, 3000);
        }
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }
}
