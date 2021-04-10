package com.own.bless.soy;

import android.content.Context;
import java.io.File;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map;

/* renamed from: com.own.bless.soy.s */
/* compiled from: HttpNetUtil */
public class C0027s {
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x009b, code lost:
        if (0 == 0) goto L_0x009e;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m213a(java.lang.String r9, java.util.Map r10, com.own.bless.soy.HttpNetUtil r11) {
        /*
        // Method dump skipped, instructions count: 180
        */
        throw new UnsupportedOperationException("Method not decompiled: com.own.bless.soy.C0027s.m213a(java.lang.String, java.util.Map, com.own.bless.soy.r):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00aa, code lost:
        if (0 == 0) goto L_0x00ad;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m214b(java.lang.String r10, java.util.Map r11, com.own.bless.soy.HttpNetUtil r12) {
        /*
        // Method dump skipped, instructions count: 195
        */
        throw new UnsupportedOperationException("Method not decompiled: com.own.bless.soy.C0027s.m214b(java.lang.String, java.util.Map, com.own.bless.soy.r):void");
    }

    /* renamed from: c */
    public static int m215c(Context context, String url, String path, int maxRedirection) {
        int status = 4;
        if (context != null && !NetworkUtil.m64b(context)) {
            return 7;
        }
        try {
            File folder = new File(path.substring(0, path.lastIndexOf(File.separator)));
            if (!folder.exists()) {
                folder.mkdirs();
            }
        } catch (Throwable th) {
        }
        if (url.toLowerCase(Locale.getDefault()).startsWith("https")) {
            status = m217e(context, url, path, maxRedirection);
        }
        if (status == 1 || status == 3) {
            return status;
        }
        return m216d(context, url, path, maxRedirection);
    }

    /* JADX WARNING: Removed duplicated region for block: B:64:0x014b A[SYNTHETIC, Splitter:B:64:0x014b] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x015a A[SYNTHETIC, Splitter:B:70:0x015a] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x017a  */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int m216d(android.content.Context r19, java.lang.String r20, java.lang.String r21, int r22) {
        /*
        // Method dump skipped, instructions count: 394
        */
        throw new UnsupportedOperationException("Method not decompiled: com.own.bless.soy.C0027s.m216d(android.content.Context, java.lang.String, java.lang.String, int):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:103:0x01d0  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01d8  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x01e1  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01b2 A[SYNTHETIC, Splitter:B:91:0x01b2] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01c1 A[SYNTHETIC, Splitter:B:97:0x01c1] */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int m217e(android.content.Context r23, java.lang.String r24, java.lang.String r25, int r26) {
        /*
        // Method dump skipped, instructions count: 495
        */
        throw new UnsupportedOperationException("Method not decompiled: com.own.bless.soy.C0027s.m217e(android.content.Context, java.lang.String, java.lang.String, int):int");
    }

    /* renamed from: f */
    private static String m218f(Map map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : map.entrySet()) {
            try {
                sb.append(i.getKey());
                sb.append("=");
                sb.append(URLEncoder.encode(i.getValue() + "", "UTF-8"));
                sb.append("&");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
