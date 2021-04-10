package com.own.bless.soy;

import android.os.IBinder;
import android.os.Parcel;

/* renamed from: com.own.bless.soy.x2 */
/* compiled from: IOpenID */
public class C0041x2 implements IOpenID {

    /* renamed from: a */
    public IBinder f128a;

    public C0041x2(IBinder iBinder) {
        this.f128a = iBinder;
    }

    /* renamed from: a */
    public String mo126a(String str, String str2, String str3) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.heytap.openid.IOpenID");
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeString(str3);
            this.f128a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public IBinder asBinder() {
        return this.f128a;
    }
}
