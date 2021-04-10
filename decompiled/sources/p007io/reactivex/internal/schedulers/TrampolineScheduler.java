package p007io.reactivex.internal.schedulers;

import com.own.bless.soy.RxJavaPlugins;
import java.util.concurrent.TimeUnit;

/* renamed from: io.reactivex.internal.schedulers.s */
final class TrampolineScheduler implements Runnable {

    /* renamed from: a */
    private final Runnable f1809a;

    /* renamed from: b */
    private final C0277v f1810b;

    /* renamed from: c */
    private final long f1811c;

    TrampolineScheduler(Runnable run, C0277v worker, long execTime) {
        this.f1809a = run;
        this.f1810b = worker;
        this.f1811c = execTime;
    }

    public void run() {
        if (!this.f1810b.f1821d) {
            long t = this.f1810b.mo1091a(TimeUnit.MILLISECONDS);
            long j = this.f1811c;
            if (j > t) {
                try {
                    Thread.sleep(j - t);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    RxJavaPlugins.m167l(e);
                    return;
                }
            }
            if (!this.f1810b.f1821d) {
                this.f1809a.run();
            }
        }
    }
}
