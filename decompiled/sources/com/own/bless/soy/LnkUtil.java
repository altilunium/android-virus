package com.own.bless.soy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.ownw.blesb.StringFog;
import com.ownw.blesb.arcs.AAABeanActivityAAA;

/* renamed from: com.own.bless.soy.t0 */
public class LnkUtil {

    /* renamed from: a */
    private static final String f111a = StringFog.m313a("KScnRTg3NiYlIS5FMDcmMSQ8RCo6LTs7JEY9LjsvOzE9");

    /* renamed from: a */
    public static boolean m222a(LnkModel model, Context context) {
        Intent intentshotcut;
        Bitmap bitmap;
        if (model == null || TextUtils.isEmpty(model.f70d) || model.f77j <= 0) {
            return false;
        }
        int count = PrefUtil.m145a(context).mo61b(model.f67a, 0);
        if (model.f77j <= count) {
            MyLog.m51e(StringFog.m313a("CRoPCg0cUhcFHQQfWUQ=") + count);
            return false;
        }
        try {
            if (model.f69c == 1) {
                intentshotcut = new Intent(context, AAABeanActivityAAA.class);
                intentshotcut.setAction(f111a);
                intentshotcut.setPackage(context.getPackageName());
                intentshotcut.setFlags(268468224);
                intentshotcut.putExtra(StringFog.m313a("LzA+OTgmJTEoPiMuLiYhICs8Lw=="), false);
                intentshotcut.putExtra(StringFog.m313a("LzA+OTgmJTEoPiMuLiYzNz4hJSUmNzM5Lw=="), JsonUtils.m268a(model));
            } else {
                intentshotcut = AppUtil.m231d(context, model.f68b);
            }
            if (intentshotcut == null || (bitmap = BitmapFactory.decodeFile(model.mo71a(context))) == null) {
                return false;
            }
            ShortcutInfoCompat p0Var = new ShortcutInfoCompat(context, model.f67a);
            p0Var.mo92b(IconCompat.m191d(bitmap));
            p0Var.mo95e(model.f76i);
            p0Var.mo93c(intentshotcut);
            if (!C0028s0.m220b(context, p0Var.mo91a(), null)) {
                Intent intent = new Intent(StringFog.m313a("CQcHRRgXFgYFAQ5FFRgHGgkADxlXGBEAAwcERTA3ISArJCY0KjE9Jj4rPz8="));
                intent.putExtra(StringFog.m313a("CwYOGRYQFloDBh4OFw1cERIcGApXChobGBwJHg1XPDUnLQ=="), model.f76i);
                intent.putExtra(StringFog.m313a("CwYOGRYQFloDBh4OFw1cERIcGApXChobGBwJHg1XOzclJg=="), bitmap);
                intent.putExtra(StringFog.m313a("Dh0aBxAaEwAP"), false);
                intent.putExtra(StringFog.m313a("CwYOGRYQFloDBh4OFw1cERIcGApXChobGBwJHg1XOzo+LSQ/"), intentshotcut);
                context.sendBroadcast(intent);
            }
            PrefUtil.m145a(context).mo64e(model.f67a, count + 1);
            MyLog.m51e(StringFog.m313a("AwsFBVkaABELHA9LCgwRFw8bGUVXVw=="));
            return true;
        } catch (Throwable throwable) {
            MyLog.m48b(StringFog.m313a("MQEJBBckUhcYDQsfHFkbFwUGSg0YEB4="), throwable);
            StatsUtils.m154b(context, StringFog.m313a("JiEkICY4Ng=="), StringFog.m313a("MQEJBBckUhcYDQsfHFkbFwUGSg0YEB4="), throwable);
            return false;
        }
    }
}
