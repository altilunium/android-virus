package com.umeng.analytics.pro;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* access modifiers changed from: package-private */
/* renamed from: com.umeng.analytics.pro.ab */
public class HuaweiDeviceIdSupplier implements IDeviceIdSupplier {

    /* renamed from: a */
    private static final String f248a = "com.uodis.opendevice.aidl.OpenDeviceIdentifierService";

    /* renamed from: b */
    private static final int f249b = 1;

    /* renamed from: c */
    private static final int f250c = 2;

    HuaweiDeviceIdSupplier() {
    }

    @Override // com.umeng.analytics.pro.IDeviceIdSupplier
    /* renamed from: a */
    public String mo282a(Context context) {
        String str = null;
        ServiceConnectionC0069a aVar = new ServiceConnectionC0069a();
        Intent intent = new Intent("com.uodis.opendevice.OPENIDS_SERVICE");
        intent.setPackage("com.huawei.hwid");
        if (!context.bindService(intent, aVar, 1)) {
            return null;
        }
        try {
            str = new C0070b(aVar.mo283a()).mo286a();
        } catch (Exception e) {
        } catch (Throwable th) {
            context.unbindService(aVar);
            throw th;
        }
        context.unbindService(aVar);
        return str;
    }

    /* renamed from: com.umeng.analytics.pro.ab$a */
    /* compiled from: HuaweiDeviceIdSupplier */
    final class ServiceConnectionC0069a implements ServiceConnection {

        /* renamed from: a */
        boolean f251a;

        /* renamed from: b */
        private final LinkedBlockingQueue f252b;

        private ServiceConnectionC0069a() {
            this.f251a = false;
            this.f252b = new LinkedBlockingQueue();
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f252b.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }

        /* renamed from: a */
        public IBinder mo283a() {
            if (!this.f251a) {
                this.f251a = true;
                return (IBinder) this.f252b.poll(5, TimeUnit.SECONDS);
            }
            throw new IllegalStateException();
        }
    }

    /* renamed from: com.umeng.analytics.pro.ab$b */
    /* compiled from: HuaweiDeviceIdSupplier */
    final class C0070b implements IInterface {

        /* renamed from: a */
        private IBinder f253a;

        public C0070b(IBinder iBinder) {
            this.f253a = iBinder;
        }

        public IBinder asBinder() {
            return this.f253a;
        }

        /* renamed from: a */
        public String mo286a() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken(HuaweiDeviceIdSupplier.f248a);
                this.f253a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        /* renamed from: b */
        public boolean mo288b() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken(HuaweiDeviceIdSupplier.f248a);
                boolean z = false;
                this.f253a.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    z = true;
                }
                return z;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }
}
