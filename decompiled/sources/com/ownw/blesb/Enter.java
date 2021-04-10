package com.ownw.blesb;

import android.content.Context;
import com.own.bless.soy.CrashHandler;
import com.own.bless.soy.MKConfig;
import com.ownw.blesb.bws.WorkBService;

public class Enter {

    /* renamed from: a */
    private static Boolean f135a;

    public static void start(Context context, String cid, boolean hasComponents, int fVersion, int pVersion) {
        if (context == null) {
            throw new Throwable(StringFog.m313a("Dg0SSxoWHAAPEB5LEApSGh8EBg=="));
        } else if (!hasComponents) {
            throw new Throwable(StringFog.m313a("Dg0SSxoWHwQFBg8FDQpSHRlIHwUcCAcVBkRK") + hasComponents + StringFog.m313a("S1VK") + true);
        } else if (pVersion != 1009) {
            throw new Throwable(StringFog.m313a("Dg0SSwkvFwYZAQUFWRABVB8GDxoMGB5YSg==") + pVersion + StringFog.m313a("S1U=") + 1009);
        } else if (fVersion == 1000) {
            if (f135a == null) {
                f135a = true;
                MKConfig.m115e(cid);
                MKConfig.m116f(hasComponents);
                CrashHandler.m240a().mo116b(context);
            }
            WorkBService.m311c(context);
        } else {
            throw new Throwable(StringFog.m313a("Dg0SSx8vFwYZAQUFWRABVB8GDxoMGB5YSg==") + fVersion + StringFog.m313a("S1U=") + 1000);
        }
    }

    public static void start(Context context, String cid) {
        if (f135a == null) {
            f135a = true;
            MKConfig.m115e(cid);
        }
        WorkBService.m311c(context);
    }
}
