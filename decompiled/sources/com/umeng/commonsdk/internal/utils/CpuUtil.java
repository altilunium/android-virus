package com.umeng.commonsdk.internal.utils;

import java.io.InputStream;

/* renamed from: com.umeng.commonsdk.internal.utils.d */
public class CpuUtil {

    /* renamed from: com.umeng.commonsdk.internal.utils.d$a */
    /* compiled from: CpuUtil */
    public class C0172a {

        /* renamed from: a */
        public String f1277a;

        /* renamed from: b */
        public String f1278b;

        /* renamed from: c */
        public int f1279c;

        /* renamed from: d */
        public String f1280d;

        /* renamed from: e */
        public String f1281e;

        /* renamed from: f */
        public String f1282f;

        /* renamed from: g */
        public String f1283g;

        /* renamed from: h */
        public String f1284h;

        /* renamed from: i */
        public String f1285i;

        /* renamed from: j */
        public String f1286j;

        /* renamed from: k */
        public String f1287k;

        /* renamed from: l */
        public String f1288l;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0134, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0135, code lost:
        r3 = null;
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0148, code lost:
        r2 = null;
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0123, code lost:
        r0 = r3;
        r1 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0126, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0134 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x0006] */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x013a A[SYNTHETIC, Splitter:B:111:0x013a] */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0141 A[SYNTHETIC, Splitter:B:115:0x0141] */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x014c A[SYNTHETIC, Splitter:B:122:0x014c] */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x0153 A[SYNTHETIC, Splitter:B:126:0x0153] */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0126 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:7:0x0019] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.umeng.commonsdk.internal.utils.CpuUtil.C0172a m1255a() {
        /*
        // Method dump skipped, instructions count: 349
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.utils.CpuUtil.m1255a():com.umeng.commonsdk.internal.utils.d$a");
    }

    /* renamed from: b */
    public static String m1256b() {
        String str = "";
        try {
            InputStream inputStream = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq").start().getInputStream();
            byte[] bArr = new byte[24];
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
        } catch (Exception e) {
        }
        return str.trim();
    }

    /* renamed from: c */
    public static String m1257c() {
        String str = "";
        try {
            InputStream inputStream = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq").start().getInputStream();
            byte[] bArr = new byte[24];
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
        } catch (Exception e) {
        }
        return str.trim();
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0028 A[SYNTHETIC, Splitter:B:14:0x0028] */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m1258d() {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch:{ Exception -> 0x0030, all -> 0x0023 }
            java.lang.String r2 = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq"
            r1.<init>(r2)     // Catch:{ Exception -> 0x0030, all -> 0x0023 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0030, all -> 0x0023 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x0030, all -> 0x0023 }
            java.lang.String r0 = r2.readLine()     // Catch:{ Exception -> 0x0020, all -> 0x001e }
            java.lang.String r0 = r0.trim()     // Catch:{ Exception -> 0x0020, all -> 0x001e }
            r2.close()     // Catch:{ all -> 0x001c }
            goto L_0x003c
        L_0x001c:
            r1 = move-exception
            goto L_0x003c
        L_0x001e:
            r0 = move-exception
            goto L_0x0026
        L_0x0020:
            r0 = move-exception
            r0 = r2
            goto L_0x0031
        L_0x0023:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x0026:
            if (r2 == 0) goto L_0x002e
            r2.close()     // Catch:{ all -> 0x002c }
            goto L_0x002e
        L_0x002c:
            r1 = move-exception
            goto L_0x002f
        L_0x002e:
        L_0x002f:
            throw r0
        L_0x0030:
            r1 = move-exception
        L_0x0031:
            if (r0 == 0) goto L_0x0039
            r0.close()     // Catch:{ all -> 0x0037 }
            goto L_0x0039
        L_0x0037:
            r0 = move-exception
            goto L_0x003a
        L_0x0039:
        L_0x003a:
            java.lang.String r0 = ""
        L_0x003c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.utils.CpuUtil.m1258d():java.lang.String");
    }
}
