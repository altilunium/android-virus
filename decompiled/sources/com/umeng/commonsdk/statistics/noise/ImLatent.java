package com.umeng.commonsdk.statistics.noise;

import android.content.Context;
import com.umeng.analytics.pro.UMCommonContent;
import com.umeng.commonsdk.statistics.BusinessWrapperConfig;
import com.umeng.commonsdk.statistics.common.StoreHelper;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.internal.OnImprintChangedListener;
import com.umeng.commonsdk.statistics.internal.StatTracer;

public class ImLatent implements OnImprintChangedListener {
    private static ImLatent instanse = null;
    private final int LATENT_MAX = 1800000;
    private final int LATENT_WINDOW = 10;
    private final long _360HOURS_IN_MS = 1296000000;
    private final long _36HOURS_IN_MS = 129600000;
    private final int _DEFAULT_HOURS = 360;
    private final int _DEFAULT_MAX_LATENT = 1800;
    private final int _DEFAULT_MIN_HOURS = 36;
    private final int _DEFAULT_MIN_LATENT = 1;
    private final long _ONE_HOURS_IN_MS = 3600000;
    private Context context;
    private long latentHour = 1296000000;
    private int latentWindow = 10;
    private long mDelay = 0;
    private long mElapsed = 0;
    private boolean mLatentActivite = false;
    private Object mLatentLock = new Object();
    private StatTracer statTracer;
    private StoreHelper storeHelper;

    public static synchronized ImLatent getService(Context context2, StatTracer statTracer2) {
        ImLatent imLatent;
        synchronized (ImLatent.class) {
            if (instanse == null) {
                ImLatent imLatent2 = new ImLatent(context2, statTracer2);
                instanse = imLatent2;
                imLatent2.onImprintChanged(ImprintHandler.getImprintService(context2).mo771c());
            }
            imLatent = instanse;
        }
        return imLatent;
    }

    private ImLatent(Context context2, StatTracer statTracer2) {
        this.context = context2;
        this.storeHelper = StoreHelper.m1426a(context2);
        this.statTracer = statTracer2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001d, code lost:
        r4 = java.lang.System.currentTimeMillis() - r8.statTracer.getLastReqTime();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        if (r4 <= r8.latentHour) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        r1 = com.umeng.commonsdk.statistics.idtracking.Envelope.getSignature(r8.context);
        r2 = r8.mLatentLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0037, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r8.mDelay = (long) com.umeng.commonsdk.statistics.common.DataHelper.random(r8.latentWindow, r1);
        r8.mElapsed = r4;
        r8.mLatentActivite = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0045, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0046, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004f, code lost:
        if (r4 <= 129600000) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0051, code lost:
        r2 = r8.mLatentLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0053, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r8.mDelay = 0;
        r8.mElapsed = r4;
        r8.mLatentActivite = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005c, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005d, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0061, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean shouldStartLatency() {
        /*
        // Method dump skipped, instructions count: 101
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.noise.ImLatent.shouldStartLatency():boolean");
    }

    public boolean isLatentActivite() {
        boolean z;
        synchronized (this.mLatentLock) {
            z = this.mLatentActivite;
        }
        return z;
    }

    public void latentDeactivite() {
        synchronized (this.mLatentLock) {
            this.mLatentActivite = false;
        }
    }

    public long getDelayTime() {
        long j;
        synchronized (this.mLatentLock) {
            j = this.mDelay;
        }
        return j;
    }

    public long getElapsedTime() {
        return this.mElapsed;
    }

    @Override // com.umeng.commonsdk.statistics.internal.OnImprintChangedListener
    public void onImprintChanged(ImprintHandler.C0195a aVar) {
        int i = 360;
        int intValue = Integer.valueOf(aVar.mo776a("latent_hours", String.valueOf(360))).intValue();
        if (intValue > 36) {
            i = intValue;
        }
        this.latentHour = ((long) i) * 3600000;
        int intValue2 = Integer.valueOf(aVar.mo776a(UMCommonContent.f306aP, "0")).intValue();
        if (intValue2 < 1 || intValue2 > 1800) {
            intValue2 = 0;
        }
        if (intValue2 == 0) {
            int i2 = BusinessWrapperConfig.f1358c;
            if (i2 <= 0 || i2 > 1800000) {
                this.latentWindow = 10;
            } else {
                this.latentWindow = i2;
            }
        } else {
            this.latentWindow = intValue2;
        }
    }
}
