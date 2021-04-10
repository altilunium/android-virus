package com.umeng.analytics.pro;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: com.umeng.analytics.pro.a */
public interface IDeviceIdService extends IInterface {
    /* renamed from: a */
    String mo275a();

    /* renamed from: a */
    String mo276a(String str);

    /* renamed from: b */
    String mo277b(String str);

    /* renamed from: com.umeng.analytics.pro.a$a */
    /* compiled from: IDeviceIdService */
    public abstract class AbstractBinderC0066a extends Binder implements IDeviceIdService {

        /* renamed from: a */
        static final int f243a = 1;

        /* renamed from: b */
        static final int f244b = 2;

        /* renamed from: c */
        static final int f245c = 3;

        /* renamed from: d */
        private static final String f246d = "com.samsung.android.deviceidservice.IDeviceIdService";

        public AbstractBinderC0066a() {
            attachInterface(this, f246d);
        }

        /* renamed from: a */
        public static IDeviceIdService m394a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(f246d);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IDeviceIdService)) {
                return new C0067a(iBinder);
            }
            return (IDeviceIdService) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface(f246d);
                String a = mo275a();
                parcel2.writeNoException();
                parcel2.writeString(a);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(f246d);
                String a2 = mo276a(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(a2);
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(f246d);
                String b = mo277b(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(b);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(f246d);
                return true;
            }
        }

        /* renamed from: com.umeng.analytics.pro.a$a$a */
        /* compiled from: IDeviceIdService */
        class C0067a implements IDeviceIdService {

            /* renamed from: a */
            private IBinder f247a;

            C0067a(IBinder iBinder) {
                this.f247a = iBinder;
            }

            public IBinder asBinder() {
                return this.f247a;
            }

            /* renamed from: b */
            public String mo281b() {
                return AbstractBinderC0066a.f246d;
            }

            @Override // com.umeng.analytics.pro.IDeviceIdService
            /* renamed from: a */
            public String mo275a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC0066a.f246d);
                    this.f247a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.umeng.analytics.pro.IDeviceIdService
            /* renamed from: a */
            public String mo276a(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC0066a.f246d);
                    obtain.writeString(str);
                    this.f247a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.umeng.analytics.pro.IDeviceIdService
            /* renamed from: b */
            public String mo277b(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC0066a.f246d);
                    obtain.writeString(str);
                    this.f247a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
