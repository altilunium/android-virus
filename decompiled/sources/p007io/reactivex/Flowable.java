package p007io.reactivex;

import com.own.bless.soy.Publisher;

/* renamed from: io.reactivex.b */
public abstract class Flowable implements Publisher {
    static {
        Math.max(1, Integer.getInteger("rx2.buffer-size", 128).intValue());
    }
}
