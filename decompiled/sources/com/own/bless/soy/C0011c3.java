package com.own.bless.soy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.own.bless.soy.c3 */
/* compiled from: OpenIDHelper */
public class C0011c3 {

    /* renamed from: a */
    public IOpenID f26a = null;

    /* renamed from: b */
    public String f27b = null;

    /* renamed from: c */
    public String f28c = null;

    /* renamed from: d */
    public final Object f29d = new Object();

    /* renamed from: e */
    public ServiceConnection f30e = new OpenIDHelper(this);

    /* renamed from: b */
    public boolean mo37b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.heytap.openid", 0);
            if (Build.VERSION.SDK_INT >= 28) {
                if (packageInfo == null || packageInfo.getLongVersionCode() < 1) {
                    return false;
                }
                return true;
            } else if (packageInfo == null || packageInfo.versionCode < 1) {
                return false;
            } else {
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: c */
    public final String mo38c(Context context, String str) {
        Signature[] signatureArr;
        if (TextUtils.isEmpty(this.f27b)) {
            this.f27b = context.getPackageName();
        }
        if (TextUtils.isEmpty(this.f28c)) {
            String str2 = null;
            try {
                signatureArr = context.getPackageManager().getPackageInfo(this.f27b, 64).signatures;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                signatureArr = null;
            }
            if (signatureArr != null && signatureArr.length > 0) {
                byte[] byteArray = signatureArr[0].toByteArray();
                try {
                    MessageDigest instance = MessageDigest.getInstance("SHA1");
                    if (instance != null) {
                        byte[] digest = instance.digest(byteArray);
                        StringBuilder sb = new StringBuilder();
                        for (byte b : digest) {
                            sb.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
                        }
                        str2 = sb.toString();
                    }
                } catch (NoSuchAlgorithmException e2) {
                    e2.printStackTrace();
                }
            }
            this.f28c = str2;
        }
        String a = ((C0041x2) this.f26a).mo126a(this.f27b, this.f28c, str);
        return TextUtils.isEmpty(a) ? "" : a;
    }

    /* renamed from: a */
    public synchronized String mo36a(Context context, String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot run on MainThread");
        } else if (this.f26a == null) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.heytap.openid", "com.heytap.openid.IdentifyService"));
            intent.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
            if (context.bindService(intent, this.f30e, 1)) {
                synchronized (this.f29d) {
                    try {
                        this.f29d.wait(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (this.f26a == null) {
                return "";
            }
            try {
                return mo38c(context, str);
            } catch (RemoteException e2) {
                e2.printStackTrace();
                return "";
            }
        } else {
            try {
                return mo38c(context, str);
            } catch (RemoteException e3) {
                e3.printStackTrace();
                return "";
            }
        }
    }
}
