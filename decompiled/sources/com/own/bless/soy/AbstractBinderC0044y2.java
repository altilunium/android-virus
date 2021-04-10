package com.own.bless.soy;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* renamed from: com.own.bless.soy.y2 */
/* compiled from: IOpenID */
public abstract class AbstractBinderC0044y2 extends Binder implements IOpenID {
    /* renamed from: a */
    public static IOpenID m267a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.heytap.openid.IOpenID");
        if (queryLocalInterface == null || !(queryLocalInterface instanceof IOpenID)) {
            return new C0041x2(iBinder);
        }
        return (IOpenID) queryLocalInterface;
    }
}
