package com.umeng.commonsdk.statistics.common;

import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.util.zip.Inflater;

/* renamed from: com.umeng.commonsdk.statistics.common.b */
public class DeflaterHelper {

    /* renamed from: a */
    public static int f1395a;

    /* renamed from: a */
    public static byte[] m1421a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return m1422a(str.getBytes(str2));
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0044  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] m1422a(byte[] r5) {
        /*
            r0 = 0
            if (r5 == 0) goto L_0x0048
            int r1 = r5.length
            if (r1 > 0) goto L_0x0007
            goto L_0x0048
        L_0x0007:
            java.util.zip.Deflater r1 = new java.util.zip.Deflater
            r1.<init>()
            r1.setInput(r5)
            r1.finish()
            r5 = 8192(0x2000, float:1.14794E-41)
            byte[] r5 = new byte[r5]
            r2 = 0
            com.umeng.commonsdk.statistics.common.DeflaterHelper.f1395a = r2
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0041 }
            r3.<init>()     // Catch:{ all -> 0x0041 }
        L_0x001f:
            boolean r0 = r1.finished()     // Catch:{ all -> 0x003e }
            if (r0 != 0) goto L_0x0032
            int r0 = r1.deflate(r5)     // Catch:{ all -> 0x003e }
            int r4 = com.umeng.commonsdk.statistics.common.DeflaterHelper.f1395a     // Catch:{ all -> 0x003e }
            int r4 = r4 + r0
            com.umeng.commonsdk.statistics.common.DeflaterHelper.f1395a = r4     // Catch:{ all -> 0x003e }
            r3.write(r5, r2, r0)     // Catch:{ all -> 0x003e }
            goto L_0x001f
        L_0x0032:
            r1.end()     // Catch:{ all -> 0x003e }
            r3.close()
            byte[] r5 = r3.toByteArray()
            return r5
        L_0x003e:
            r5 = move-exception
            r0 = r3
            goto L_0x0042
        L_0x0041:
            r5 = move-exception
        L_0x0042:
            if (r0 == 0) goto L_0x0047
            r0.close()
        L_0x0047:
            throw r5
        L_0x0048:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.common.DeflaterHelper.m1422a(byte[]):byte[]");
    }

    /* renamed from: a */
    public static String m1420a(byte[] bArr, String str) {
        byte[] b = m1423b(bArr);
        if (b != null) {
            return new String(b, str);
        }
        return null;
    }

    /* renamed from: b */
    public static byte[] m1423b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        Inflater inflater = new Inflater();
        int i = 0;
        inflater.setInput(bArr, 0, bArr.length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr2 = new byte[1024];
        while (!inflater.needsInput()) {
            int inflate = inflater.inflate(bArr2);
            byteArrayOutputStream.write(bArr2, i, inflate);
            i += inflate;
        }
        inflater.end();
        return byteArrayOutputStream.toByteArray();
    }
}
