package p007io.reactivex.exceptions;

import java.io.PrintStream;

/* access modifiers changed from: package-private */
/* renamed from: io.reactivex.exceptions.c */
/* compiled from: CompositeException */
public final class C0258c extends AbstractC0257b {

    /* renamed from: a */
    private final PrintStream f1702a;

    C0258c(PrintStream printStream) {
        this.f1702a = printStream;
    }

    /* access modifiers changed from: package-private */
    @Override // p007io.reactivex.exceptions.AbstractC0257b
    /* renamed from: a */
    public void mo1084a(Object o) {
        this.f1702a.println(o);
    }
}
