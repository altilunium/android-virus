package p007io.reactivex.exceptions;

import java.io.PrintWriter;

/* renamed from: io.reactivex.exceptions.d */
/* compiled from: CompositeException */
final class C0259d extends AbstractC0257b {

    /* renamed from: a */
    private final PrintWriter f1703a;

    C0259d(PrintWriter printWriter) {
        this.f1703a = printWriter;
    }

    /* access modifiers changed from: package-private */
    @Override // p007io.reactivex.exceptions.AbstractC0257b
    /* renamed from: a */
    public void mo1084a(Object o) {
        this.f1703a.println(o);
    }
}
