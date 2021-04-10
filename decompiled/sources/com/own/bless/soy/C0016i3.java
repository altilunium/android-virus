package com.own.bless.soy;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.umeng.analytics.pro.UContent;
import com.umeng.commonsdk.statistics.SdkVersion;

/* renamed from: com.own.bless.soy.i3 */
/* compiled from: IdentifierIdClient */
public class C0016i3 {

    /* renamed from: a */
    private static Context f48a;

    /* renamed from: b */
    private static boolean f49b = false;

    /* renamed from: c */
    private static IdentifierIdObserver f50c = null;

    /* renamed from: d */
    private static IdentifierIdObserver f51d = null;

    /* renamed from: e */
    private static IdentifierIdObserver f52e = null;

    /* renamed from: f */
    private static Object f53f = new Object();

    /* renamed from: g */
    private static HandlerThread f54g = null;

    /* renamed from: h */
    private static Handler f55h = null;

    /* renamed from: i */
    private static String f56i = null;

    /* renamed from: j */
    private static String f57j = null;

    /* renamed from: k */
    private static volatile C0016i3 f58k = null;

    /* renamed from: l */
    private static volatile DataBaseOperation f59l = null;

    private C0016i3() {
    }

    /* renamed from: b */
    public static C0016i3 m132b(Context context) {
        if (f58k == null) {
            synchronized (C0016i3.class) {
                f48a = context.getApplicationContext();
                f58k = new C0016i3();
            }
        }
        if (f59l == null) {
            synchronized (C0016i3.class) {
                f48a = context.getApplicationContext();
                m139l();
                f59l = new DataBaseOperation(f48a);
                m136i();
            }
        }
        return f58k;
    }

    /* renamed from: l */
    private static void m139l() {
        HandlerThread handlerThread = new HandlerThread("SqlWorkThread");
        f54g = handlerThread;
        handlerThread.start();
        f55h = new IdentifierIdClient(f54g.getLooper());
    }

    /* renamed from: e */
    public boolean mo59e() {
        return f49b;
    }

    /* renamed from: f */
    public String mo60f() {
        if (!mo59e()) {
            return null;
        }
        String str = f57j;
        if (str != null) {
            return str;
        }
        mo58c(0, null);
        if (f50c == null) {
            m133d(f48a, 0, null);
        }
        return f57j;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0029, code lost:
        if (r8 != 4) goto L_0x005d;
     */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo58c(int r8, java.lang.String r9) {
        /*
            r7 = this;
            java.lang.Object r0 = com.own.bless.soy.C0016i3.f53f
            monitor-enter(r0)
            r7.m134g(r8, r9)     // Catch:{ all -> 0x005f }
            long r1 = android.os.SystemClock.uptimeMillis()     // Catch:{ all -> 0x005f }
            r3 = 2000(0x7d0, double:9.88E-321)
            java.lang.Object r9 = com.own.bless.soy.C0016i3.f53f     // Catch:{ InterruptedException -> 0x0012 }
            r9.wait(r3)     // Catch:{ InterruptedException -> 0x0012 }
            goto L_0x0016
        L_0x0012:
            r9 = move-exception
            r9.printStackTrace()
        L_0x0016:
            long r5 = android.os.SystemClock.uptimeMillis()
            long r5 = r5 - r1
            int r9 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r9 >= 0) goto L_0x0056
            r9 = 0
            if (r8 == 0) goto L_0x004f
            r1 = 1
            if (r8 == r1) goto L_0x003f
            r1 = 2
            if (r8 == r1) goto L_0x002c
            r1 = 4
            if (r8 == r1) goto L_0x003b
            goto L_0x005d
        L_0x002c:
            java.lang.String r8 = com.own.bless.soy.C0016i3.f56i
            if (r8 == 0) goto L_0x0034
            com.own.bless.soy.C0016i3.f56i = r9
            goto L_0x003b
        L_0x0034:
            java.lang.String r8 = "VMS_IDLG_SDK_Client"
            java.lang.String r1 = "get aaid failed"
            android.util.Log.e(r8, r1)
        L_0x003b:
            com.own.bless.soy.C0016i3.f56i = r9
            goto L_0x005d
        L_0x003f:
            java.lang.String r8 = com.own.bless.soy.C0016i3.f56i
            if (r8 == 0) goto L_0x0047
            com.own.bless.soy.C0016i3.f56i = r9
            goto L_0x005d
        L_0x0047:
            java.lang.String r8 = "VMS_IDLG_SDK_Client"
            java.lang.String r9 = "get vaid failed"
            android.util.Log.e(r8, r9)
            goto L_0x005d
        L_0x004f:
            java.lang.String r8 = com.own.bless.soy.C0016i3.f56i
            com.own.bless.soy.C0016i3.f57j = r8
            com.own.bless.soy.C0016i3.f56i = r9
            goto L_0x005d
        L_0x0056:
            java.lang.String r8 = "VMS_IDLG_SDK_Client"
            java.lang.String r9 = "query timeout"
            android.util.Log.d(r8, r9)
        L_0x005d:
            monitor-exit(r0)
            return
        L_0x005f:
            r8 = move-exception
            monitor-exit(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.own.bless.soy.C0016i3.mo58c(int, java.lang.String):void");
    }

    /* renamed from: g */
    private void m134g(int i, String str) {
        Message obtainMessage = f55h.obtainMessage();
        obtainMessage.what = 11;
        Bundle bundle = new Bundle();
        bundle.putInt(UContent.f663y, i);
        if (i == 1 || i == 2) {
            bundle.putString("appid", str);
        }
        obtainMessage.setData(bundle);
        f55h.sendMessage(obtainMessage);
    }

    /* renamed from: i */
    public static void m136i() {
        f49b = SdkVersion.MINI_VERSION.equals(m131a("persist.sys.identifierid.supported", "0"));
    }

    /* renamed from: a */
    public static String m131a(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, "unknown");
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
        }
        return str2;
    }

    /* renamed from: d */
    private static void m133d(Context context, int i, String str) {
        if (i == 0) {
            f50c = new IdentifierIdObserver(f58k, 0, null);
            context.getContentResolver().registerContentObserver(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAID"), true, f50c);
        } else if (i == 1) {
            f51d = new IdentifierIdObserver(f58k, 1, str);
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.registerContentObserver(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/VAID_" + str), false, f51d);
        } else if (i == 2) {
            f52e = new IdentifierIdObserver(f58k, 2, str);
            ContentResolver contentResolver2 = context.getContentResolver();
            contentResolver2.registerContentObserver(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/AAID_" + str), false, f52e);
        }
    }
}
