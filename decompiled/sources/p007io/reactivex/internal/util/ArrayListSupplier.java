package p007io.reactivex.internal.util;

import com.own.bless.soy.Function;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/* renamed from: io.reactivex.internal.util.ArrayListSupplier */
public enum ArrayListSupplier implements Callable, Function {
    INSTANCE;

    public static Callable asCallable() {
        return INSTANCE;
    }

    public static Function asFunction() {
        return INSTANCE;
    }

    @Override // java.util.concurrent.Callable
    public List call() {
        return new ArrayList();
    }

    @Override // com.own.bless.soy.Function
    public List apply(Object o) {
        return new ArrayList();
    }
}
