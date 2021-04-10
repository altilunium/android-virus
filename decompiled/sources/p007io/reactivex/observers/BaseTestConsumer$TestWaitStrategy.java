package p007io.reactivex.observers;

/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
/* renamed from: io.reactivex.observers.BaseTestConsumer$TestWaitStrategy */
public abstract class BaseTestConsumer$TestWaitStrategy extends Enum implements Runnable {
    private static final /* synthetic */ BaseTestConsumer$TestWaitStrategy[] $VALUES;
    public static final BaseTestConsumer$TestWaitStrategy SLEEP_1000MS;
    public static final BaseTestConsumer$TestWaitStrategy SLEEP_100MS;
    public static final BaseTestConsumer$TestWaitStrategy SLEEP_10MS;
    public static final BaseTestConsumer$TestWaitStrategy SLEEP_1MS;
    public static final BaseTestConsumer$TestWaitStrategy SPIN;
    public static final BaseTestConsumer$TestWaitStrategy YIELD;

    public abstract void run();

    private BaseTestConsumer$TestWaitStrategy(String str, int i) {
    }

    public static BaseTestConsumer$TestWaitStrategy valueOf(String name) {
        return (BaseTestConsumer$TestWaitStrategy) Enum.valueOf(BaseTestConsumer$TestWaitStrategy.class, name);
    }

    public static BaseTestConsumer$TestWaitStrategy[] values() {
        return (BaseTestConsumer$TestWaitStrategy[]) $VALUES.clone();
    }

    static {
        C0284b bVar = new C0284b("SPIN", 0);
        SPIN = bVar;
        C0285c cVar = new C0285c("YIELD", 1);
        YIELD = cVar;
        C0286d dVar = new C0286d("SLEEP_1MS", 2);
        SLEEP_1MS = dVar;
        C0287e eVar = new C0287e("SLEEP_10MS", 3);
        SLEEP_10MS = eVar;
        C0288f fVar = new C0288f("SLEEP_100MS", 4);
        SLEEP_100MS = fVar;
        C0289g gVar = new C0289g("SLEEP_1000MS", 5);
        SLEEP_1000MS = gVar;
        $VALUES = new BaseTestConsumer$TestWaitStrategy[]{bVar, cVar, dVar, eVar, fVar, gVar};
    }

    static void sleep(int millis) {
        try {
            Thread.sleep((long) millis);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
