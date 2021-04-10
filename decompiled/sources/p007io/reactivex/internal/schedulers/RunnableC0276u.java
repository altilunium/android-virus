package p007io.reactivex.internal.schedulers;

/* access modifiers changed from: package-private */
/* renamed from: io.reactivex.internal.schedulers.u */
/* compiled from: TrampolineScheduler */
public final class RunnableC0276u implements Runnable {

    /* renamed from: a */
    final C0275t f1816a;

    /* renamed from: b */
    final /* synthetic */ C0277v f1817b;

    RunnableC0276u(C0277v this$0, C0275t timedRunnable) {
        this.f1817b = this$0;
        this.f1816a = timedRunnable;
    }

    public void run() {
        this.f1816a.f1815d = true;
        this.f1817b.f1818a.remove(this.f1816a);
    }
}
