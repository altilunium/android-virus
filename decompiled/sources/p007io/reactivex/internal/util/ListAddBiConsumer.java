package p007io.reactivex.internal.util;

import com.own.bless.soy.BiFunction;
import java.util.List;

/* renamed from: io.reactivex.internal.util.ListAddBiConsumer */
public enum ListAddBiConsumer implements BiFunction {
    INSTANCE;

    public static BiFunction instance() {
        return INSTANCE;
    }

    public List apply(List t1, Object t2) {
        t1.add(t2);
        return t1;
    }
}
