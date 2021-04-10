package com.umeng.commonsdk.internal.utils;

import android.os.Process;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/* renamed from: com.umeng.commonsdk.internal.utils.g */
public class ProcessUtil {

    /* renamed from: a */
    private static final String f1307a = "\n";

    /* renamed from: b */
    private static final byte[] f1308b = "\nexit\n".getBytes();

    /* renamed from: c */
    private static byte[] f1309c = new byte[32];

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r4v25 */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r4v30 */
    /* JADX WARN: Type inference failed for: r5v23 */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0074, code lost:
        r0 = th;
        r4 = r4;
        r5 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0078, code lost:
        r7 = null;
        r4 = r4;
        r5 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007c, code lost:
        r7 = null;
        r4 = r4;
        r5 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x00df, code lost:
        if (r9 != null) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x00ed, code lost:
        if (r9 != null) goto L_0x0069;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0074 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:13:0x0039] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00d0  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m1265a(java.lang.String... r9) {
        /*
        // Method dump skipped, instructions count: 249
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.utils.ProcessUtil.m1265a(java.lang.String[]):java.lang.String");
    }

    /* renamed from: a */
    private static void m1266a(OutputStream outputStream, InputStream inputStream, InputStream inputStream2, InputStreamReader inputStreamReader, BufferedReader bufferedReader) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
            }
        }
        if (inputStream2 != null) {
            try {
                inputStream2.close();
            } catch (IOException e3) {
            }
        }
        if (inputStreamReader != null) {
            try {
                inputStreamReader.close();
            } catch (IOException e4) {
            }
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e5) {
            }
        }
    }

    /* renamed from: a */
    private static void m1267a(Process process) {
        int b = m1268b(process);
        if (b != 0) {
            try {
                Process.killProcess(b);
            } catch (Exception e) {
                try {
                    process.destroy();
                } catch (Exception e2) {
                }
            }
        }
    }

    /* renamed from: b */
    private static int m1268b(Process process) {
        String obj = process.toString();
        try {
            return Integer.parseInt(obj.substring(obj.indexOf("=") + 1, obj.indexOf("]")));
        } catch (Exception e) {
            return 0;
        }
    }

    /* renamed from: c */
    private static void m1269c(Process process) {
        if (process != null) {
            try {
                if (process.exitValue() != 0) {
                    m1267a(process);
                }
            } catch (IllegalThreadStateException e) {
                m1267a(process);
            }
        }
    }
}
