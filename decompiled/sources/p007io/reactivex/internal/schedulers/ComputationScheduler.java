package p007io.reactivex.internal.schedulers;

import com.own.bless.soy.CompositeDisposable;
import com.own.bless.soy.Disposable;
import java.util.concurrent.TimeUnit;
import p007io.reactivex.AbstractC0261i;
import p007io.reactivex.internal.disposables.EmptyDisposable;
import p007io.reactivex.internal.disposables.ListCompositeDisposable;

/* renamed from: io.reactivex.internal.schedulers.b */
final class ComputationScheduler extends AbstractC0261i {

    /* renamed from: a */
    private final ListCompositeDisposable f1755a;

    /* renamed from: b */
    private final CompositeDisposable f1756b;

    /* renamed from: c */
    private final ListCompositeDisposable f1757c;

    /* renamed from: d */
    private final C0266d f1758d;

    /* renamed from: e */
    volatile boolean f1759e;

    ComputationScheduler(C0266d poolWorker) {
        this.f1758d = poolWorker;
        ListCompositeDisposable bVar = new ListCompositeDisposable();
        this.f1755a = bVar;
        CompositeDisposable d1Var = new CompositeDisposable();
        this.f1756b = d1Var;
        ListCompositeDisposable bVar2 = new ListCompositeDisposable();
        this.f1757c = bVar2;
        bVar2.mo39a(bVar);
        bVar2.mo39a(d1Var);
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        if (!this.f1759e) {
            this.f1759e = true;
            this.f1757c.dispose();
        }
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return this.f1759e;
    }

    @Override // p007io.reactivex.AbstractC0261i
    /* renamed from: b */
    public Disposable mo1092b(Runnable action, long delayTime, TimeUnit unit) {
        if (this.f1759e) {
            return EmptyDisposable.INSTANCE;
        }
        return this.f1758d.mo1215d(action, delayTime, unit, this.f1756b);
    }
}
