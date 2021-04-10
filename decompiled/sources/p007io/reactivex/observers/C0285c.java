package p007io.reactivex.observers;

/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
/* renamed from: io.reactivex.observers.c */
/* compiled from: BaseTestConsumer */
final class C0285c extends BaseTestConsumer$TestWaitStrategy {
    C0285c(String str, int i) {
        super(str, i);
    }

    @Override // p007io.reactivex.observers.BaseTestConsumer$TestWaitStrategy
    public void run() {
        Thread.yield();
    }
}
