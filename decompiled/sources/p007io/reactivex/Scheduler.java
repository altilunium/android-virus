package p007io.reactivex;

import com.own.bless.soy.Disposable;
import p007io.reactivex.exceptions.Exceptions;
import p007io.reactivex.internal.util.C0279c;

/* renamed from: io.reactivex.g */
final class Scheduler implements Disposable, Runnable {

    /* renamed from: a */
    final Runnable f1704a;

    /* renamed from: b */
    final AbstractC0261i f1705b;

    /* renamed from: c */
    volatile boolean f1706c;

    Scheduler(Runnable run, AbstractC0261i worker) {
        this.f1704a = run;
        this.f1705b = worker;
    }

    public void run() {
        if (!this.f1706c) {
            try {
                this.f1704a.run();
            } catch (Throwable ex) {
                Exceptions.m1830a(ex);
                this.f1705b.dispose();
                throw C0279c.m1933c(ex);
            }
        }
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        this.f1706c = true;
        this.f1705b.dispose();
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return this.f1706c;
    }
}
