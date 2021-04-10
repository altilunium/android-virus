package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.pro.TType;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.UMErrorCode;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.umeng.commonsdk.stateless.d */
public class UMSLUtils {

    /* renamed from: a */
    public static int f1351a;

    /* renamed from: b */
    private static final byte[] f1352b = {10, 1, 11, 5, 4, TType.f577m, 7, 9, 23, 3, 1, 6, 8, 12, TType.f575k, 91};

    /* renamed from: c */
    private static Object f1353c = new Object();

    /* renamed from: a */
    public static boolean m1319a(File file) {
        String[] list;
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            for (String str : file.list()) {
                if (!m1319a(new File(file, str))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    /* renamed from: a */
    public static int m1313a(Context context, String str, String str2, byte[] bArr) {
        Throwable th;
        int i = UMErrorCode.E_UM_BE_SAVE_FAILED;
        if (context != null) {
            FileOutputStream fileOutputStream = null;
            try {
                synchronized (f1353c) {
                    try {
                        StringBuilder sb = new StringBuilder();
                        sb.append(context.getFilesDir());
                        String str3 = File.separator;
                        sb.append(str3);
                        sb.append(str);
                        File file = new File(sb.toString());
                        if (!file.isDirectory()) {
                            file.mkdir();
                        }
                        FileOutputStream fileOutputStream2 = new FileOutputStream(new File(file.getPath() + str3 + str2));
                        try {
                            fileOutputStream2.write(bArr);
                            fileOutputStream2.close();
                            i = 0;
                        } catch (Throwable th2) {
                            th = th2;
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        throw th;
                    }
                }
            } catch (IOException e) {
                UMCrashManager.reportCrash(context, e);
                if (0 != 0) {
                    fileOutputStream.close();
                }
            } catch (Throwable th4) {
            }
        }
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b6, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00b8, code lost:
        r9 = e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e1 A[SYNTHETIC, Splitter:B:41:0x00e1] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m1325b(android.content.Context r8, java.lang.String r9, java.lang.String r10, byte[] r11) {
        /*
        // Method dump skipped, instructions count: 287
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.UMSLUtils.m1325b(android.content.Context, java.lang.String, java.lang.String, byte[]):boolean");
    }

    /* renamed from: a */
    public static byte[] m1320a(String str) {
        byte[] bArr;
        synchronized (f1353c) {
            FileChannel fileChannel = null;
            try {
                FileChannel channel = new RandomAccessFile(str, "r").getChannel();
                MappedByteBuffer load = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size()).load();
                bArr = new byte[((int) channel.size())];
                if (load.remaining() > 0) {
                    load.get(bArr, 0, load.remaining());
                }
                try {
                    channel.close();
                } catch (Throwable th) {
                }
            } catch (IOException e) {
                ULog.m1396i("walle", "[stateless] write envelope, e is " + e.getMessage());
                throw e;
            } catch (Throwable th2) {
            }
        }
        return bArr;
        throw th;
    }

    /* renamed from: a */
    public static File m1314a(Context context) {
        File[] listFiles;
        File[] listFiles2;
        File file = null;
        try {
            synchronized (f1353c) {
                ULog.m1396i("walle", "get last envelope begin, thread is " + Thread.currentThread());
                if (!(context == null || context.getApplicationContext() == null)) {
                    String str = context.getApplicationContext().getFilesDir() + File.separator + UMSLConfig.f1327e;
                    if (!TextUtils.isEmpty(str)) {
                        File file2 = new File(str);
                        if (file2.isDirectory() && (listFiles = file2.listFiles()) != null && listFiles.length > 0) {
                            for (File file3 : listFiles) {
                                if (file3 != null && file3.isDirectory() && (listFiles2 = file3.listFiles()) != null && listFiles2.length > 0) {
                                    Arrays.sort(listFiles2, new Comparator() {
                                        /* class com.umeng.commonsdk.stateless.UMSLUtils.C01811 */

                                        /* renamed from: a */
                                        public int compare(File file, File file2) {
                                            long lastModified = file.lastModified() - file2.lastModified();
                                            if (lastModified > 0) {
                                                return 1;
                                            }
                                            if (lastModified == 0) {
                                                return 0;
                                            }
                                            return -1;
                                        }
                                    });
                                    File file4 = listFiles2[0];
                                    if (file4 != null && (file == null || file.lastModified() > file4.lastModified())) {
                                        file = file4;
                                    }
                                }
                            }
                        }
                    }
                }
                ULog.m1396i("walle", "get last envelope end, thread is " + Thread.currentThread());
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
        }
        return file;
    }

    /* renamed from: b */
    public static File m1323b(Context context) {
        if (context == null) {
            return null;
        }
        try {
            synchronized (f1353c) {
                String str = context.getApplicationContext().getFilesDir() + File.separator + UMSLConfig.f1328f;
                if (TextUtils.isEmpty(str)) {
                    return null;
                }
                File file = new File(str);
                synchronized (f1353c) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        if (listFiles.length != 0) {
                            Arrays.sort(listFiles, new Comparator() {
                                /* class com.umeng.commonsdk.stateless.UMSLUtils.C01822 */

                                /* renamed from: a */
                                public int compare(File file, File file2) {
                                    long lastModified = file.lastModified() - file2.lastModified();
                                    if (lastModified > 0) {
                                        return 1;
                                    }
                                    if (lastModified == 0) {
                                        return 0;
                                    }
                                    return -1;
                                }
                            });
                            return listFiles[0];
                        }
                    }
                    return null;
                }
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    /* renamed from: a */
    public static String m1315a(Context context, boolean z) {
        if (context == null) {
            return null;
        }
        if (z) {
            try {
                return context.getApplicationContext().getFilesDir() + File.separator + UMSLConfig.f1327e;
            } catch (Throwable th) {
                return null;
            }
        } else {
            return context.getApplicationContext().getFilesDir() + File.separator + UMSLConfig.f1328f;
        }
    }

    /* renamed from: c */
    public static File[] m1329c(Context context) {
        if (context == null) {
            return null;
        }
        try {
            synchronized (f1353c) {
                String str = context.getApplicationContext().getFilesDir() + File.separator + UMSLConfig.f1328f;
                if (TextUtils.isEmpty(str)) {
                    return null;
                }
                File file = new File(str);
                synchronized (f1353c) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        if (listFiles.length != 0) {
                            Arrays.sort(listFiles, new Comparator() {
                                /* class com.umeng.commonsdk.stateless.UMSLUtils.C01833 */

                                /* renamed from: a */
                                public int compare(File file, File file2) {
                                    long lastModified = file.lastModified() - file2.lastModified();
                                    if (lastModified > 0) {
                                        return 1;
                                    }
                                    if (lastModified == 0) {
                                        return 0;
                                    }
                                    return -1;
                                }
                            });
                            return listFiles;
                        }
                    }
                    return null;
                }
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    /* renamed from: a */
    public static void m1316a(Context context, String str, int i) {
        if (str == null) {
            try {
                ULog.m1396i("AmapLBS", "[lbs-build] fileDir not exist, thread is " + Thread.currentThread());
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
        } else {
            File file = new File(str);
            if (!file.isDirectory()) {
                ULog.m1396i("AmapLBS", "[lbs-build] fileDir not exist, thread is " + Thread.currentThread());
                return;
            }
            synchronized (f1353c) {
                File[] listFiles = file.listFiles();
                ULog.m1396i("AmapLBS", "[lbs-build] delete file begin " + listFiles.length + ", thread is " + Thread.currentThread());
                if (listFiles.length >= i) {
                    ULog.m1396i("AmapLBS", "[lbs-build] file size >= max");
                    ArrayList arrayList = new ArrayList();
                    for (File file2 : listFiles) {
                        if (file2 != null) {
                            arrayList.add(file2);
                        }
                    }
                    if (arrayList.size() >= i) {
                        Collections.sort(arrayList, new Comparator() {
                            /* class com.umeng.commonsdk.stateless.UMSLUtils.C01844 */

                            /* renamed from: a */
                            public int compare(File file, File file2) {
                                if (file != null && file2 != null && file.lastModified() < file2.lastModified()) {
                                    return -1;
                                }
                                if (file == null || file2 == null || file.lastModified() != file2.lastModified()) {
                                    return 1;
                                }
                                return 0;
                            }
                        });
                        if (ULog.DEBUG) {
                            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                ULog.m1396i("AmapLBS", "[lbs-build] overrun native file is " + ((File) arrayList.get(i2)).getPath());
                            }
                        }
                        for (int i3 = 0; i3 <= arrayList.size() - i; i3++) {
                            if (arrayList.get(i3) != null) {
                                ULog.m1396i("AmapLBS", "[lbs-build] overrun remove file is " + ((File) arrayList.get(i3)).getPath());
                                try {
                                    ((File) arrayList.get(i3)).delete();
                                    arrayList.remove(i3);
                                } catch (Exception e) {
                                }
                            }
                        }
                    }
                } else {
                    ULog.m1396i("AmapLBS", "[lbs-build] file size < max");
                }
                ULog.m1396i("AmapLBS", "[lbs-build] delete file end " + listFiles.length + ", thread is " + Thread.currentThread());
            }
        }
    }

    /* renamed from: a */
    public static void m1317a(Context context, String str, final String str2, int i) {
        if (str != null) {
            try {
                File file = new File(str);
                if (file.isDirectory()) {
                    synchronized (f1353c) {
                        File[] listFiles = file.listFiles(new FilenameFilter() {
                            /* class com.umeng.commonsdk.stateless.UMSLUtils.C01855 */

                            public boolean accept(File file, String str) {
                                if (str.startsWith(str2)) {
                                    return true;
                                }
                                return false;
                            }
                        });
                        if (listFiles == null || listFiles.length < i) {
                            ULog.m1396i("AmapLBS", "[lbs-build] file size < max");
                        } else {
                            ULog.m1396i("AmapLBS", "[lbs-build] file size >= max");
                            ArrayList arrayList = new ArrayList();
                            for (File file2 : listFiles) {
                                if (file2 != null) {
                                    arrayList.add(file2);
                                }
                            }
                            if (arrayList.size() >= i) {
                                Collections.sort(arrayList, new Comparator() {
                                    /* class com.umeng.commonsdk.stateless.UMSLUtils.C01866 */

                                    /* renamed from: a */
                                    public int compare(File file, File file2) {
                                        if (file != null && file2 != null && file.lastModified() < file2.lastModified()) {
                                            return -1;
                                        }
                                        if (file == null || file2 == null || file.lastModified() != file2.lastModified()) {
                                            return 1;
                                        }
                                        return 0;
                                    }
                                });
                                if (ULog.DEBUG) {
                                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                        ULog.m1396i("AmapLBS", "[lbs-build] overrun native file is " + ((File) arrayList.get(i2)).getPath());
                                    }
                                }
                                for (int i3 = 0; i3 <= arrayList.size() - i; i3++) {
                                    if (arrayList.get(i3) != null) {
                                        ULog.m1396i("AmapLBS", "[lbs-build] overrun remove file is " + ((File) arrayList.get(i3)).getPath());
                                        try {
                                            ((File) arrayList.get(i3)).delete();
                                            arrayList.remove(i3);
                                        } catch (Exception e) {
                                        }
                                    }
                                }
                            }
                        }
                        ULog.m1396i("AmapLBS", "[lbs-build] delete file end " + listFiles.length + ", thread is " + Thread.currentThread());
                    }
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
        }
    }

    /* renamed from: a */
    public static boolean m1318a(long j, long j2) {
        if (j > j2) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0044  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] m1321a(byte[] r5) {
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
            com.umeng.commonsdk.stateless.UMSLUtils.f1351a = r2
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0041 }
            r3.<init>()     // Catch:{ all -> 0x0041 }
        L_0x001f:
            boolean r0 = r1.finished()     // Catch:{ all -> 0x003e }
            if (r0 != 0) goto L_0x0032
            int r0 = r1.deflate(r5)     // Catch:{ all -> 0x003e }
            int r4 = com.umeng.commonsdk.stateless.UMSLUtils.f1351a     // Catch:{ all -> 0x003e }
            int r4 = r4 + r0
            com.umeng.commonsdk.stateless.UMSLUtils.f1351a = r4     // Catch:{ all -> 0x003e }
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
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.UMSLUtils.m1321a(byte[]):byte[]");
    }

    /* renamed from: a */
    public static byte[] m1322a(byte[] bArr, byte[] bArr2) {
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
        instance.init(1, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(f1352b));
        return instance.doFinal(bArr);
    }

    /* renamed from: b */
    public static byte[] m1326b(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bArr);
            return instance.digest();
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: c */
    public static String m1328c(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append(String.format("%02X", Byte.valueOf(bArr[i])));
        }
        return stringBuffer.toString().toLowerCase(Locale.US);
    }

    /* renamed from: b */
    public static String m1324b(String str) {
        try {
            return Base64.encodeToString(str.getBytes(), 0);
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: c */
    public static String m1327c(String str) {
        try {
            return new String(Base64.decode(str, 0));
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: d */
    public static String m1330d(String str) {
        int lastIndexOf;
        int lastIndexOf2;
        if (!TextUtils.isEmpty(str) && str.indexOf("envelope") < 0 && (lastIndexOf = str.lastIndexOf("_")) >= 0 && (lastIndexOf2 = str.lastIndexOf(".")) >= 0) {
            return str.substring(lastIndexOf + 1, lastIndexOf2);
        }
        return "";
    }
}
