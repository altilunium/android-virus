package p007io.reactivex.internal.operators.maybe;

import com.own.bless.soy.Function;
import com.own.bless.soy.Publisher;
import p007io.reactivex.MaybeSource;

/* renamed from: io.reactivex.internal.operators.maybe.MaybeToPublisher */
public enum MaybeToPublisher implements Function {
    INSTANCE;

    public static Function instance() {
        return INSTANCE;
    }

    public Publisher apply(MaybeSource dVar) {
        return new MaybeToFlowable(dVar);
    }
}
