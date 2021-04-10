package p007io.reactivex.internal.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/* renamed from: io.reactivex.internal.util.HashMapSupplier */
public enum HashMapSupplier implements Callable {
    INSTANCE;

    public static Callable asCallable() {
        return INSTANCE;
    }

    @Override // java.util.concurrent.Callable
    public Map call() {
        return new HashMap();
    }
}
