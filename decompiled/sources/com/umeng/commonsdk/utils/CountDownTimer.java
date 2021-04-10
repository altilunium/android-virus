package com.umeng.commonsdk.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

/* renamed from: com.umeng.commonsdk.utils.a */
public abstract class CountDownTimer {

    /* renamed from: e */
    private static final int f1587e = 1;

    /* renamed from: a */
    private final long f1588a;

    /* renamed from: b */
    private final long f1589b;

    /* renamed from: c */
    private long f1590c;

    /* renamed from: d */
    private boolean f1591d = false;

    /* renamed from: f */
    private HandlerThread f1592f;

    /* renamed from: g */
    private Handler f1593g;

    /* renamed from: h */
    private Handler.Callback f1594h = new Handler.Callback() {
        /* class com.umeng.commonsdk.utils.CountDownTimer.C02401 */

        public boolean handleMessage(Message message) {
            synchronized (CountDownTimer.this) {
                if (CountDownTimer.this.f1591d) {
                    return true;
                }
                long elapsedRealtime = CountDownTimer.this.f1590c - SystemClock.elapsedRealtime();
                if (elapsedRealtime <= 0) {
                    CountDownTimer.this.mo1014c();
                    if (CountDownTimer.this.f1592f != null) {
                        CountDownTimer.this.f1592f.quit();
                    }
                } else if (elapsedRealtime < CountDownTimer.this.f1589b) {
                    CountDownTimer.this.f1593g.sendMessageDelayed(CountDownTimer.this.f1593g.obtainMessage(1), elapsedRealtime);
                } else {
                    long elapsedRealtime2 = SystemClock.elapsedRealtime();
                    CountDownTimer.this.mo1012a(elapsedRealtime);
                    long elapsedRealtime3 = (elapsedRealtime2 + CountDownTimer.this.f1589b) - SystemClock.elapsedRealtime();
                    while (elapsedRealtime3 < 0) {
                        elapsedRealtime3 += CountDownTimer.this.f1589b;
                    }
                    CountDownTimer.this.f1593g.sendMessageDelayed(CountDownTimer.this.f1593g.obtainMessage(1), elapsedRealtime3);
                }
                return false;
            }
        }
    };

    /* renamed from: a */
    public abstract void mo1012a(long j);

    /* renamed from: c */
    public abstract void mo1014c();

    public CountDownTimer(long j, long j2) {
        this.f1588a = j;
        this.f1589b = j2;
        if (!m1771d()) {
            HandlerThread handlerThread = new HandlerThread("CountDownTimerThread");
            this.f1592f = handlerThread;
            handlerThread.start();
            this.f1593g = new Handler(this.f1592f.getLooper(), this.f1594h);
            return;
        }
        this.f1593g = new Handler(this.f1594h);
    }

    /* renamed from: a */
    public final synchronized void mo1011a() {
        this.f1591d = true;
        this.f1593g.removeMessages(1);
    }

    /* renamed from: b */
    public final synchronized CountDownTimer mo1013b() {
        this.f1591d = false;
        if (this.f1588a <= 0) {
            mo1014c();
            return this;
        }
        this.f1590c = SystemClock.elapsedRealtime() + this.f1588a;
        Handler handler = this.f1593g;
        handler.sendMessage(handler.obtainMessage(1));
        return this;
    }

    /* renamed from: d */
    private boolean m1771d() {
        return Looper.getMainLooper().getThread().equals(Thread.currentThread());
    }
}
