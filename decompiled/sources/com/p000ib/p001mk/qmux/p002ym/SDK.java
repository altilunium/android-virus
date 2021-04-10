package com.p000ib.p001mk.qmux.p002ym;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.p000ib.p001mk.qmux.p002ym.p003a.C0003b;
import com.umeng.analytics.pro.TType;
import java.io.File;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* renamed from: com.ib.mk.qmux.ym.SDK */
public class SDK {

    /* renamed from: a */
    private static Object f3a = null;

    /* renamed from: a */
    private static String m7a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            byte[] digest = MessageDigest.getInstance(C0003b.m25a(new byte[]{62, 47, 84})).digest(str.getBytes(C0003b.m25a(new byte[]{38, 63, 39, 85, 75})));
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    stringBuffer.append(String.valueOf(0));
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            return stringBuffer.toString().toUpperCase(Locale.getDefault());
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: a */
    private static void m8a(String str, byte[] bArr) {
        try {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bArr);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    private static File m9b(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /* renamed from: c */
    private static void m10c(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        } catch (Throwable th) {
        }
    }

    public static void init(Context context) {
        String a = C0003b.m25a(new byte[]{5, 18, 79, 0, 17, 5, 87, 26, 31, TType.f575k, 92, 25, 87, 55, 28, 24, 21, 4, 29, 4, 10});
        String a2 = C0003b.m25a(new byte[]{26, 5, 8, 12});
        try {
            Object obj = f3a;
            if (obj != null) {
                C0004b.m28a(obj, a, a2, context);
                return;
            }
        } catch (Throwable th) {
        }
        try {
            String a3 = m7a(a);
            ArrayList arrayList = new ArrayList();
            C0001a aVar = new C0001a();
            arrayList.addAll(aVar.mo11a());
            arrayList.addAll(aVar.mo12b());
            arrayList.addAll(aVar.mo13c());
            arrayList.addAll(aVar.mo14d());
            arrayList.addAll(aVar.mo15e());
            arrayList.addAll(aVar.mo16f());
            arrayList.addAll(aVar.mo17g());
            arrayList.addAll(aVar.mo18h());
            arrayList.addAll(aVar.mo19i());
            arrayList.addAll(aVar.mo20j());
            arrayList.addAll(aVar.mo21k());
            arrayList.addAll(aVar.mo22l());
            StringBuffer stringBuffer = new StringBuffer();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                stringBuffer.append((String) it.next());
            }
            m9b(context.getFilesDir().getPath());
            StringBuilder sb = new StringBuilder();
            sb.append(context.getFilesDir().getPath());
            String str = File.separator;
            sb.append(str);
            sb.append(a3);
            sb.append(C0003b.m25a(new byte[]{93, 1, 0, 10}));
            String sb2 = sb.toString();
            String str2 = context.getFilesDir().getPath() + str + a3 + C0003b.m25a(new byte[]{93, TType.f577m, 4, 0});
            m8a(sb2, Base64.decode(stringBuffer.toString(), 0));
            Object a4 = C0004b.m26a(context, sb2);
            f3a = a4;
            C0004b.m28a(a4, a, a2, context);
            m10c(sb2);
            m10c(str2);
        } catch (Throwable th2) {
            Log.e(C0003b.m25a(new byte[]{22, 25, 19, 23, 1}), C0003b.m25a(new byte[]{22, 25, 19, 23, 1, 67, 67, 79}) + Log.getStackTraceString(th2));
        }
    }
}
