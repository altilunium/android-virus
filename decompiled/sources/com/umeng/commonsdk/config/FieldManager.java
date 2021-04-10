package com.umeng.commonsdk.config;

import android.content.Context;
import android.util.Pair;
import com.umeng.commonsdk.config.FieldTable;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import java.util.ArrayList;

public class FieldManager {

    /* renamed from: a */
    private static final String f1054a = "cfgfd";

    /* renamed from: b */
    private static CollectController f1055b = CollectController.m1125b();

    /* renamed from: c */
    private static boolean f1056c = false;

    /* renamed from: d */
    private static Object f1057d = new Object();

    private FieldManager() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.config.FieldManager$a */
    public class C0144a {

        /* renamed from: a */
        private static final FieldManager f1058a = new FieldManager();

        private C0144a() {
        }
    }

    /* renamed from: a */
    public static FieldManager m1113a() {
        return C0144a.f1058a;
    }

    public static boolean allow(String str) {
        synchronized (f1057d) {
            if (!f1056c) {
                return false;
            }
            return CollectController.m1124a(str);
        }
    }

    /* renamed from: b */
    public static boolean m1114b() {
        boolean z;
        synchronized (f1057d) {
            z = f1056c;
        }
        return z;
    }

    /* renamed from: a */
    public void mo663a(Context context) {
        String str;
        String str2 = "1001@3758096383,2147483647,262143,2047";
        String[] strArr = {FieldTable.EnumC0147a.class.getName(), FieldTable.EnumC0148b.class.getName(), FieldTable.EnumC0149c.class.getName(), FieldTable.EnumC0150d.class.getName()};
        String imprintProperty = UMEnvelopeBuild.imprintProperty(context, "cfgfd", str2);
        synchronized (f1057d) {
            Pair a = m1112a(imprintProperty);
            if (((Long) a.first).longValue() > 1000 && (str = (String) a.second) != null && str.length() > 0) {
                str2 = str;
            }
            String[] split = str2.split(",");
            int length = split.length;
            if (length > 0) {
                ArrayList arrayList = new ArrayList();
                SensitiveFieldHandler gVar = new SensitiveFieldHandler();
                for (int i = 0; i < length; i++) {
                    arrayList.add(gVar);
                    ((IConfigHandler) arrayList.get(i)).mo667a(split[i], f1055b, FieldTable.m1131b(strArr[i]));
                }
            }
            f1056c = true;
        }
    }

    /* renamed from: a */
    public void mo664a(Context context, String str) {
        String str2;
        String str3 = "1001@3758096383,2147483647,262143,2047";
        String[] strArr = {FieldTable.EnumC0147a.class.getName(), FieldTable.EnumC0148b.class.getName(), FieldTable.EnumC0149c.class.getName(), FieldTable.EnumC0150d.class.getName()};
        synchronized (f1057d) {
            f1055b.mo665a();
            if (str != null) {
                Pair a = m1112a(str);
                if (((Long) a.first).longValue() > 1000 && (str2 = (String) a.second) != null && str2.length() > 0) {
                    str3 = str2;
                }
            }
            String[] split = str3.split(",");
            int length = split.length;
            if (length > 0) {
                ArrayList arrayList = new ArrayList();
                SensitiveFieldHandler gVar = new SensitiveFieldHandler();
                for (int i = 0; i < length; i++) {
                    arrayList.add(gVar);
                    ((IConfigHandler) arrayList.get(i)).mo667a(split[i], f1055b, FieldTable.m1131b(strArr[i]));
                }
            }
            f1056c = true;
        }
    }

    /* renamed from: a */
    private static Pair m1112a(String str) {
        Pair pair = new Pair(-1L, null);
        if (str == null || str.length() < 2) {
            return pair;
        }
        String[] split = str.split("@");
        if (split.length < 2) {
            return pair;
        }
        try {
            long parseLong = Long.parseLong(split[0]);
            return new Pair(Long.valueOf(parseLong), split[1]);
        } catch (Throwable th) {
            return pair;
        }
    }
}
