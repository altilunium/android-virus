package com.umeng.commonsdk;

import android.content.Context;
import com.umeng.commonsdk.statistics.SdkVersion;
import com.umeng.commonsdk.utils.UMUtils;
import java.lang.reflect.Method;

/* renamed from: com.umeng.commonsdk.a */
public class UMInnerManager {

    /* renamed from: a */
    private static Class f1052a;

    /* renamed from: b */
    private static Method f1053b;

    static {
        f1052a = null;
        f1053b = null;
        try {
            Class<?> cls = Class.forName("com.umeng.commonsdk.UMInnerImpl");
            f1052a = cls;
            Method declaredMethod = cls.getDeclaredMethod("initAndSendInternal", Context.class);
            if (declaredMethod != null) {
                f1053b = declaredMethod;
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public static void m1111a(Context context) {
        Method method;
        if (context != null && UMUtils.isMainProgress(context)) {
            if (SdkVersion.SDK_TYPE != 1) {
                Class cls = f1052a;
                if (cls != null && (method = f1053b) != null) {
                    try {
                        method.invoke(cls, context);
                    } catch (Throwable th) {
                    }
                }
            } else {
                UMConfigureInternation.sendInternal(context);
            }
        }
    }
}
