package p007io.reactivex.observers;

/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
/* renamed from: io.reactivex.observers.d */
/* compiled from: BaseTestConsumer */
final class C0286d extends BaseTestConsumer$TestWaitStrategy {
    C0286d(String str, int i) {
        super(str, i);
    }

    @Override // p007io.reactivex.observers.BaseTestConsumer$TestWaitStrategy
    public void run() {
        BaseTestConsumer$TestWaitStrategy.sleep(1);
    }
}
