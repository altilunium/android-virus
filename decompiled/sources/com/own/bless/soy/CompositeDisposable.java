package com.own.bless.soy;

import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;
import p007io.reactivex.exceptions.CompositeException;
import p007io.reactivex.exceptions.Exceptions;
import p007io.reactivex.internal.disposables.DisposableContainer;
import p007io.reactivex.internal.util.C0279c;
import p007io.reactivex.internal.util.OpenHashSet;

/* renamed from: com.own.bless.soy.d1 */
public final class CompositeDisposable implements Disposable, DisposableContainer {

    /* renamed from: a */
    OpenHashSet f31a;

    /* renamed from: b */
    volatile boolean f32b;

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        if (!this.f32b) {
            synchronized (this) {
                if (!this.f32b) {
                    this.f32b = true;
                    io.reactivex.internal.util.OpenHashSet<Disposable> set = this.f31a;
                    this.f31a = null;
                    mo42d(set);
                }
            }
        }
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return this.f32b;
    }

    @Override // p007io.reactivex.internal.disposables.DisposableContainer
    /* renamed from: a */
    public boolean mo39a(Disposable disposable) {
        C0013e2.m76d(disposable, "disposable is null");
        if (!this.f32b) {
            synchronized (this) {
                if (!this.f32b) {
                    io.reactivex.internal.util.OpenHashSet<Disposable> set = this.f31a;
                    if (set == null) {
                        set = new OpenHashSet();
                        this.f31a = set;
                    }
                    set.mo1313a(disposable);
                    return true;
                }
            }
        }
        disposable.dispose();
        return false;
    }

    @Override // p007io.reactivex.internal.disposables.DisposableContainer
    /* renamed from: b */
    public boolean mo40b(Disposable disposable) {
        if (!mo41c(disposable)) {
            return false;
        }
        disposable.dispose();
        return true;
    }

    @Override // p007io.reactivex.internal.disposables.DisposableContainer
    /* renamed from: c */
    public boolean mo41c(Disposable disposable) {
        C0013e2.m76d(disposable, "disposables is null");
        if (this.f32b) {
            return false;
        }
        synchronized (this) {
            if (this.f32b) {
                return false;
            }
            io.reactivex.internal.util.OpenHashSet<Disposable> set = this.f31a;
            if (set != null) {
                if (set.mo1316e(disposable)) {
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo42d(OpenHashSet hVar) {
        if (hVar != null) {
            List<Throwable> errors = null;
            Object[] array = hVar.mo1314b();
            for (Object o : array) {
                if (o instanceof Disposable) {
                    try {
                        ((Disposable) o).dispose();
                    } catch (Throwable ex) {
                        Exceptions.m1830a(ex);
                        if (errors == null) {
                            errors = new ArrayList<>();
                        }
                        errors.add(ex);
                    }
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
