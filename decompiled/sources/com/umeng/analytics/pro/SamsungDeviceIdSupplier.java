package com.umeng.analytics.pro;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.umeng.analytics.pro.IDeviceIdService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* renamed from: com.umeng.analytics.pro.ae */
public class SamsungDeviceIdSupplier implements IDeviceIdSupplier {

    /* renamed from: a */
    private static final String f255a = "DeviceIdService";

    /* renamed from: b */
    private static final String f256b = "com.samsung.android.deviceidservice";

    /* renamed from: c */
    private static final String f257c = "com.samsung.android.deviceidservice.DeviceIdService";

    /* renamed from: d */
    private String f258d = "";

    /* renamed from: e */
    private CountDownLatch f259e;

    /* renamed from: f */
    private final ServiceConnection f260f = new ServiceConnection() {
        /* class com.umeng.analytics.pro.SamsungDeviceIdSupplier.ServiceConnectionC00711 */

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                IDeviceIdService a = IDeviceIdService.AbstractBinderC0066a.m394a(iBinder);
                SamsungDeviceIdSupplier.this.f258d = a.mo275a();
                Log.d(SamsungDeviceIdSupplier.f255a, "onServiceConnected");
            } catch (RemoteException | NullPointerException e) {
                Log.e(SamsungDeviceIdSupplier.f255a, "onServiceConnected failed e=" + e.getMessage());
            }
            SamsungDeviceIdSupplier.this.f259e.countDown();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(SamsungDeviceIdSupplier.f255a, "onServiceDisconnected");
        }
    };

    /* JADX INFO: finally extract failed */
    @Override // com.umeng.analytics.pro.IDeviceIdSupplier
    /* renamed from: a */
    public String mo282a(Context context) {
        this.f259e = new CountDownLatch(1);
        try {
            m408b(context);
            if (!this.f259e.await(500, TimeUnit.MILLISECONDS)) {
                Log.e(f255a, "getOAID time-out");
            }
            String str = this.f258d;
            m409c(context);
            return str;
        } catch (InterruptedException e) {
            Log.e(f255a, "getOAID interrupted. e=" + e.getMessage());
            m409c(context);
            return null;
        } catch (Throwable th) {
            m409c(context);
            throw th;
        }
    }

    /* renamed from: b */
    private void m408b(Context context) {
        try {
            Intent intent = new Intent();
            intent.setClassName(f256b, f257c);
            if (!context.bindService(intent, this.f260f, 1)) {
                throw new UnsupportedOperationException("not supported service");
            }
        } catch (Error | Exception e) {
            Log.e(f255a, "bindService failed. e=" + e.getMessage());
            this.f259e.countDown();
        }
    }

    /* renamed from: c */
    private void m409c(Context context) {
        try {
            context.unbindService(this.f260f);
        } catch (Error | Exception e) {
            Log.e(f255a, "unbindService failed. e=" + e.getMessage());
        }
    }
}
