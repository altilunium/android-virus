package com.umeng.commonsdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UMConfigureImpl {

    /* renamed from: a */
    private static String f1040a = "delayed_transmission_flag_new";

    /* renamed from: b */
    private static CopyOnWriteArrayList f1041b = new CopyOnWriteArrayList();

    /* renamed from: c */
    private static int f1042c = 0;

    /* renamed from: d */
    private static boolean f1043d = false;

    /* renamed from: e */
    private static final int f1044e = 1000;

    /* renamed from: f */
    private static ScheduledExecutorService f1045f;

    /* renamed from: g */
    private static Context f1046g;

    /* renamed from: h */
    private static int f1047h = 0;

    /* renamed from: i */
    private static Runnable f1048i = new Runnable() {
        /* class com.umeng.commonsdk.UMConfigureImpl.RunnableC01391 */

        public void run() {
            try {
                if (UMConfigureImpl.f1042c == 0 || UMConfigureImpl.f1047h >= 10) {
                    if (!UMConfigureImpl.f1043d) {
                        boolean unused = UMConfigureImpl.f1043d = true;
                        UMConfigureImpl.m1104b(UMConfigureImpl.f1046g);
                    }
                    if (UMConfigureImpl.f1045f != null) {
                        UMConfigureImpl.f1045f.shutdown();
                        ScheduledExecutorService unused2 = UMConfigureImpl.f1045f = null;
                    }
                }
                UMConfigureImpl.m1110f();
            } catch (Exception e) {
            }
        }
    };

    /* renamed from: f */
    static /* synthetic */ int m1110f() {
        int i = f1047h;
        f1047h = i + 1;
        return i;
    }

    public static void init(Context context) {
        if (context != null) {
            f1046g = context;
            try {
                if (f1042c >= 1) {
                    if (!m1108d(context)) {
                        UMEnvelopeBuild.setTransmissionSendFlag(false);
                        m1105c(context);
                        if (f1045f == null) {
                            ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);
                            f1045f = newScheduledThreadPool;
                            newScheduledThreadPool.scheduleAtFixedRate(f1048i, 0, 100, TimeUnit.MILLISECONDS);
                        }
                    } else {
                        UMEnvelopeBuild.setTransmissionSendFlag(true);
                    }
                    return;
                }
                UMEnvelopeBuild.setTransmissionSendFlag(true);
            } catch (Exception e) {
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void m1104b(android.content.Context r2) {
        /*
            java.lang.Class<com.umeng.commonsdk.UMConfigureImpl> r2 = com.umeng.commonsdk.UMConfigureImpl.class
            monitor-enter(r2)
            r0 = 1
            com.umeng.commonsdk.framework.UMEnvelopeBuild.setTransmissionSendFlag(r0)     // Catch:{ Exception -> 0x002b, all -> 0x0028 }
            java.util.concurrent.CopyOnWriteArrayList r0 = com.umeng.commonsdk.UMConfigureImpl.f1041b     // Catch:{ Exception -> 0x002b, all -> 0x0028 }
            if (r0 == 0) goto L_0x0027
            int r0 = r0.size()     // Catch:{ Exception -> 0x002b, all -> 0x0028 }
            if (r0 <= 0) goto L_0x0027
            java.util.concurrent.CopyOnWriteArrayList r0 = com.umeng.commonsdk.UMConfigureImpl.f1041b     // Catch:{ Exception -> 0x002b, all -> 0x0028 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Exception -> 0x002b, all -> 0x0028 }
        L_0x0017:
            boolean r1 = r0.hasNext()     // Catch:{ Exception -> 0x002b, all -> 0x0028 }
            if (r1 == 0) goto L_0x0027
            java.lang.Object r1 = r0.next()     // Catch:{ Exception -> 0x002b, all -> 0x0028 }
            com.umeng.commonsdk.utils.onMessageSendListener r1 = (com.umeng.commonsdk.utils.onMessageSendListener) r1     // Catch:{ Exception -> 0x002b, all -> 0x0028 }
            r1.onMessageSend()     // Catch:{ Exception -> 0x002b, all -> 0x0028 }
            goto L_0x0017
        L_0x0027:
            goto L_0x002c
        L_0x0028:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        L_0x002b:
            r0 = move-exception
        L_0x002c:
            monitor-exit(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.UMConfigureImpl.m1104b(android.content.Context):void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void registerMessageSendListener(com.umeng.commonsdk.utils.onMessageSendListener r2) {
        /*
            java.lang.Class<com.umeng.commonsdk.UMConfigureImpl> r0 = com.umeng.commonsdk.UMConfigureImpl.class
            monitor-enter(r0)
            java.util.concurrent.CopyOnWriteArrayList r1 = com.umeng.commonsdk.UMConfigureImpl.f1041b     // Catch:{ Exception -> 0x0034, all -> 0x0031 }
            if (r1 == 0) goto L_0x000a
            r1.add(r2)     // Catch:{ Exception -> 0x0034, all -> 0x0031 }
        L_0x000a:
            boolean r2 = com.umeng.commonsdk.framework.UMEnvelopeBuild.getTransmissionSendFlag()     // Catch:{ Exception -> 0x0034, all -> 0x0031 }
            if (r2 == 0) goto L_0x0030
            java.util.concurrent.CopyOnWriteArrayList r2 = com.umeng.commonsdk.UMConfigureImpl.f1041b     // Catch:{ Exception -> 0x0034, all -> 0x0031 }
            if (r2 == 0) goto L_0x0030
            int r2 = r2.size()     // Catch:{ Exception -> 0x0034, all -> 0x0031 }
            if (r2 <= 0) goto L_0x0030
            java.util.concurrent.CopyOnWriteArrayList r2 = com.umeng.commonsdk.UMConfigureImpl.f1041b     // Catch:{ Exception -> 0x0034, all -> 0x0031 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ Exception -> 0x0034, all -> 0x0031 }
        L_0x0020:
            boolean r1 = r2.hasNext()     // Catch:{ Exception -> 0x0034, all -> 0x0031 }
            if (r1 == 0) goto L_0x0030
            java.lang.Object r1 = r2.next()     // Catch:{ Exception -> 0x0034, all -> 0x0031 }
            com.umeng.commonsdk.utils.onMessageSendListener r1 = (com.umeng.commonsdk.utils.onMessageSendListener) r1     // Catch:{ Exception -> 0x0034, all -> 0x0031 }
            r1.onMessageSend()     // Catch:{ Exception -> 0x0034, all -> 0x0031 }
            goto L_0x0020
        L_0x0030:
            goto L_0x0035
        L_0x0031:
            r2 = move-exception
            monitor-exit(r0)
            throw r2
        L_0x0034:
            r2 = move-exception
        L_0x0035:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.UMConfigureImpl.registerMessageSendListener(com.umeng.commonsdk.utils.onMessageSendListener):void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void removeMessageSendListener(com.umeng.commonsdk.utils.onMessageSendListener r2) {
        /*
            java.lang.Class<com.umeng.commonsdk.UMConfigureImpl> r0 = com.umeng.commonsdk.UMConfigureImpl.class
            monitor-enter(r0)
            java.util.concurrent.CopyOnWriteArrayList r1 = com.umeng.commonsdk.UMConfigureImpl.f1041b     // Catch:{ Exception -> 0x000e, all -> 0x000b }
            if (r1 == 0) goto L_0x000a
            r1.remove(r2)     // Catch:{ Exception -> 0x000e, all -> 0x000b }
        L_0x000a:
            goto L_0x000f
        L_0x000b:
            r2 = move-exception
            monitor-exit(r0)
            throw r2
        L_0x000e:
            r2 = move-exception
        L_0x000f:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.UMConfigureImpl.removeMessageSendListener(com.umeng.commonsdk.utils.onMessageSendListener):void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void registerInterruptFlag() {
        /*
            java.lang.Class<com.umeng.commonsdk.UMConfigureImpl> r0 = com.umeng.commonsdk.UMConfigureImpl.class
            monitor-enter(r0)
            int r1 = com.umeng.commonsdk.UMConfigureImpl.f1042c     // Catch:{ Exception -> 0x000d, all -> 0x000a }
            int r1 = r1 + 1
            com.umeng.commonsdk.UMConfigureImpl.f1042c = r1     // Catch:{ Exception -> 0x000d, all -> 0x000a }
            goto L_0x000e
        L_0x000a:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        L_0x000d:
            r1 = move-exception
        L_0x000e:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.UMConfigureImpl.registerInterruptFlag():void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void removeInterruptFlag() {
        /*
            java.lang.Class<com.umeng.commonsdk.UMConfigureImpl> r0 = com.umeng.commonsdk.UMConfigureImpl.class
            monitor-enter(r0)
            int r1 = com.umeng.commonsdk.UMConfigureImpl.f1042c     // Catch:{ Exception -> 0x000d, all -> 0x000a }
            int r1 = r1 + -1
            com.umeng.commonsdk.UMConfigureImpl.f1042c = r1     // Catch:{ Exception -> 0x000d, all -> 0x000a }
            goto L_0x000e
        L_0x000a:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        L_0x000d:
            r1 = move-exception
        L_0x000e:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.UMConfigureImpl.removeInterruptFlag():void");
    }

    /* renamed from: c */
    private static void m1105c(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(f1040a, 0);
            if (sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean(f1040a, true);
                edit.commit();
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: d */
    private static boolean m1108d(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(f1040a, 0);
            if (sharedPreferences != null) {
                return sharedPreferences.getBoolean(f1040a, false);
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }
}
