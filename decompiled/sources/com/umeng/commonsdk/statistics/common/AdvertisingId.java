package com.umeng.commonsdk.statistics.common;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/* renamed from: com.umeng.commonsdk.statistics.common.a */
public class AdvertisingId {

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.statistics.common.a$a */
    /* compiled from: AdvertisingId */
    public final class C0189a {

        /* renamed from: a */
        private final String f1390a;

        /* renamed from: b */
        private final boolean f1391b;

        C0189a(String str, boolean z) {
            this.f1390a = str;
            this.f1391b = z;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        /* renamed from: b */
        private String m1415b() {
            return this.f1390a;
        }

        /* renamed from: a */
        public boolean mo730a() {
            return this.f1391b;
        }
    }

    /* renamed from: a */
    public static String m1411a(Context context) {
        try {
            C0189a c = m1413c(context);
            if (c != null && !c.mo730a()) {
                return c.m1415b();
            }
        } catch (Exception e) {
        }
        return null;
    }

    /* renamed from: b */
    public static String m1412b(Context context) {
        try {
            C0189a c = m1413c(context);
            if (c == null) {
                return null;
            }
            return c.m1415b();
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: c */
    private static C0189a m1413c(Context context) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return null;
        }
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            ServiceConnectionC0190b bVar = new ServiceConnectionC0190b();
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            if (context.bindService(intent, bVar, 1)) {
                try {
                    C0191c cVar = new C0191c(bVar.mo731a());
                    boolean a = cVar.mo735a(true);
                    String str = "";
                    if (!a) {
                        str = cVar.mo734a();
                    }
                    C0189a aVar = new C0189a(str, a);
                    context.unbindService(bVar);
                    return aVar;
                } catch (Exception e) {
                    throw e;
                } catch (Throwable th) {
                    context.unbindService(bVar);
                    throw th;
                }
            } else {
                throw new IOException("Google Play connection failed");
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.statistics.common.a$b */
    /* compiled from: AdvertisingId */
    public final class ServiceConnectionC0190b implements ServiceConnection {

        /* renamed from: a */
        boolean f1392a;

        /* renamed from: b */
        private final LinkedBlockingQueue f1393b;

        private ServiceConnectionC0190b() {
            this.f1392a = false;
            this.f1393b = new LinkedBlockingQueue(1);
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f1393b.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }

        /* renamed from: a */
        public IBinder mo731a() {
            if (!this.f1392a) {
                this.f1392a = true;
                return (IBinder) this.f1393b.take();
            }
            throw new IllegalStateException();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.statistics.common.a$c */
    /* compiled from: AdvertisingId */
    public final class C0191c implements IInterface {

        /* renamed from: a */
        private IBinder f1394a;

        public C0191c(IBinder iBinder) {
            this.f1394a = iBinder;
        }

        public IBinder asBinder() {
            return this.f1394a;
        }

        /* renamed from: a */
        public String mo734a() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f1394a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        /* renamed from: a */
        public boolean mo735a(boolean z) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                boolean z2 = true;
                obtain.writeInt(z ? 1 : 0);
                this.f1394a.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z2 = false;
                }
                return z2;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }
}
