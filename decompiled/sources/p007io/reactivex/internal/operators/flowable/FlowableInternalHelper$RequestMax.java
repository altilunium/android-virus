package p007io.reactivex.internal.operators.flowable;

import com.own.bless.soy.Consumer;
import com.own.bless.soy.Subscription;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableInternalHelper$RequestMax */
public enum FlowableInternalHelper$RequestMax implements Consumer {
    INSTANCE;

    public void accept(Subscription t) {
        t.request(Long.MAX_VALUE);
    }
}
