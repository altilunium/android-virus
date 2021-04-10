package p007io.reactivex.internal.disposables;

import com.own.bless.soy.C0013e2;
import com.own.bless.soy.Disposable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import p007io.reactivex.exceptions.CompositeException;
import p007io.reactivex.exceptions.Exceptions;
import p007io.reactivex.internal.util.C0279c;

/* renamed from: io.reactivex.internal.disposables.b */
public final class ListCompositeDisposable implements Disposable, DisposableContainer {

    /* renamed from: a */
    List f1714a;

    /* renamed from: b */
    volatile boolean f1715b;

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        if (!this.f1715b) {
            synchronized (this) {
                if (!this.f1715b) {
                    this.f1715b = true;
                    List<io.reactivex.disposables.Disposable> set = this.f1714a;
                    this.f1714a = null;
                    mo1103d(set);
                }
            }
        }
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return this.f1715b;
    }

    @Override // p007io.reactivex.internal.disposables.DisposableContainer
    /* renamed from: a */
    public boolean mo39a(Disposable d) {
        C0013e2.m76d(d, "d is null");
        if (!this.f1715b) {
            synchronized (this) {
                if (!this.f1715b) {
                    List<io.reactivex.disposables.Disposable> set = this.f1714a;
                    if (set == null) {
                        set = new LinkedList<>();
                        this.f1714a = set;
                    }
                    set.add(d);
                    return true;
                }
            }
        }
        d.dispose();
        return false;
    }

    @Override // p007io.reactivex.internal.disposables.DisposableContainer
    /* renamed from: b */
    public boolean mo40b(Disposable d) {
        if (!mo41c(d)) {
            return false;
        }
        d.dispose();
        return true;
    }

    @Override // p007io.reactivex.internal.disposables.DisposableContainer
    /* renamed from: c */
    public boolean mo41c(Disposable d) {
        C0013e2.m76d(d, "Disposable item is null");
        if (this.f1715b) {
            return false;
        }
        synchronized (this) {
            if (this.f1715b) {
                return false;
            }
            List<io.reactivex.disposables.Disposable> set = this.f1714a;
            if (set != null) {
                if (set.remove(d)) {
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo1103d(List list) {
        if (list != null) {
            List<Throwable> errors = null;
            Iterator it = list.iterator();
            while (it.hasNext()) {
                try {
                    ((Disposable) it.next()).dispose();
                } catch (Throwable ex) {
                    Exceptions.m1830a(ex);
                    if (errors == null) {
                        errors = new ArrayList<>();
                    }
                    errors.add(ex);
                }
            }
            if (errors == null) {
                return;
            }
            if (errors.size() == 1) {
                throw C0279c.m1933c(errors.get(0));
            }
            throw new CompositeException(errors);
        }
    }
}
