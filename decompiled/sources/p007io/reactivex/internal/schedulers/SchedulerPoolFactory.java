package p007io.reactivex.internal.schedulers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/* access modifiers changed from: package-private */
/* renamed from: io.reactivex.internal.schedulers.n */
public final class SchedulerPoolFactory implements Runnable {
    SchedulerPoolFactory() {
    }

    public void run() {
        Iterator it = new ArrayList(C0273p.f1802d.keySet()).iterator();
        while (it.hasNext()) {
            ScheduledThreadPoolExecutor e = (ScheduledThreadPoolExecutor) it.next();
            if (e.isShutdown()) {
                C0273p.f1802d.remove(e);
            } else {
                e.purge();
            }
        }
    }
}
