package p007io.reactivex.internal.util;

import com.own.bless.soy.C0013e2;
import java.io.Serializable;

/* access modifiers changed from: package-private */
/* renamed from: io.reactivex.internal.util.f */
/* compiled from: NotificationLite */
public final class C0281f implements Serializable {

    /* renamed from: a */
    final Throwable f1825a;

    C0281f(Throwable e) {
        this.f1825a = e;
    }

    public String toString() {
        return "NotificationLite.Error[" + this.f1825a + "]";
    }

    public int hashCode() {
        return this.f1825a.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof C0281f) {
            return C0013e2.m75c(this.f1825a, ((C0281f) obj).f1825a);
        }
        return false;
    }
}
