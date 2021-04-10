package com.umeng.commonsdk.statistics.common;

import android.text.TextUtils;
import android.util.Log;
import java.util.Formatter;
import java.util.Locale;

public class ULog {
    public static boolean DEBUG = false;
    private static final int LEVEL_DEBUG = 2;
    private static final int LEVEL_ERROR = 5;
    private static final int LEVEL_INFO = 3;
    private static final int LEVEL_VERBOSE = 1;
    private static final int LEVEL_WARN = 4;
    private static int LOG_MAXLENGTH = 2000;
    private static String TAG = "ULog";

    private ULog() {
    }

    /* renamed from: i */
    public static void m1398i(Locale locale, String str, Object... objArr) {
        try {
            m1394i(TAG, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            m1391e(th);
        }
    }

    /* renamed from: d */
    public static void m1386d(Locale locale, String str, Object... objArr) {
        try {
            m1382d(TAG, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            m1391e(th);
        }
    }

    /* renamed from: e */
    public static void m1392e(Locale locale, String str, Object... objArr) {
        try {
            m1388e(TAG, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            m1391e(th);
        }
    }

    /* renamed from: v */
    public static void m1404v(Locale locale, String str, Object... objArr) {
        try {
            m1400v(TAG, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            m1391e(th);
        }
    }

    /* renamed from: w */
    public static void m1410w(Locale locale, String str, Object... objArr) {
        try {
            m1406w(TAG, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            m1391e(th);
        }
    }

    /* renamed from: i */
    public static void m1396i(String str, Object... objArr) {
        String str2 = "";
        try {
            if (str.contains("%")) {
                m1394i(TAG, new Formatter().format(str, objArr).toString(), (Throwable) null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            m1394i(str, str2, (Throwable) null);
        } catch (Throwable th) {
            m1391e(th);
        }
    }

    /* renamed from: d */
    public static void m1384d(String str, Object... objArr) {
        String str2 = "";
        try {
            if (str.contains("%")) {
                m1382d(TAG, new Formatter().format(str, objArr).toString(), (Throwable) null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            m1382d(str, str2, (Throwable) null);
        } catch (Throwable th) {
            m1391e(th);
        }
    }

    /* renamed from: e */
    public static void m1390e(String str, Object... objArr) {
        String str2 = "";
        try {
            if (str.contains("%")) {
                m1388e(TAG, new Formatter().format(str, objArr).toString(), (Throwable) null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            m1388e(str, str2, (Throwable) null);
        } catch (Throwable th) {
            m1391e(th);
        }
    }

    /* renamed from: v */
    public static void m1402v(String str, Object... objArr) {
        String str2 = "";
        try {
            if (str.contains("%")) {
                m1400v(TAG, new Formatter().format(str, objArr).toString(), (Throwable) null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            m1400v(str, str2, (Throwable) null);
        } catch (Throwable th) {
            m1391e(th);
        }
    }

    /* renamed from: w */
    public static void m1408w(String str, Object... objArr) {
        String str2 = "";
        try {
            if (str.contains("%")) {
                m1406w(TAG, new Formatter().format(str, objArr).toString(), (Throwable) null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            m1406w(str, str2, (Throwable) null);
        } catch (Throwable th) {
            m1391e(th);
        }
    }

    /* renamed from: i */
    public static void m1397i(Throwable th) {
        m1394i(TAG, (String) null, th);
    }

    /* renamed from: v */
    public static void m1403v(Throwable th) {
        m1400v(TAG, (String) null, th);
    }

    /* renamed from: w */
    public static void m1409w(Throwable th) {
        m1406w(TAG, (String) null, th);
    }

    /* renamed from: d */
    public static void m1385d(Throwable th) {
        m1382d(TAG, (String) null, th);
    }

    /* renamed from: e */
    public static void m1391e(Throwable th) {
        m1388e(TAG, (String) null, th);
    }

    /* renamed from: i */
    public static void m1395i(String str, Throwable th) {
        m1394i(TAG, str, th);
    }

    /* renamed from: v */
    public static void m1401v(String str, Throwable th) {
        m1400v(TAG, str, th);
    }

    /* renamed from: w */
    public static void m1407w(String str, Throwable th) {
        m1406w(TAG, str, th);
    }

    /* renamed from: d */
    public static void m1383d(String str, Throwable th) {
        m1382d(TAG, str, th);
    }

    /* renamed from: e */
    public static void m1389e(String str, Throwable th) {
        m1388e(TAG, str, th);
    }

    /* renamed from: v */
    public static void m1399v(String str) {
        m1400v(TAG, str, (Throwable) null);
    }

    /* renamed from: d */
    public static void m1381d(String str) {
        m1382d(TAG, str, (Throwable) null);
    }

    /* renamed from: i */
    public static void m1393i(String str) {
        m1394i(TAG, str, (Throwable) null);
    }

    /* renamed from: w */
    public static void m1405w(String str) {
        m1406w(TAG, str, (Throwable) null);
    }

    /* renamed from: e */
    public static void m1387e(String str) {
        m1388e(TAG, str, (Throwable) null);
    }

    /* renamed from: v */
    public static void m1400v(String str, String str2, Throwable th) {
        if (DEBUG) {
            print(1, str, str2, th);
        }
    }

    /* renamed from: d */
    public static void m1382d(String str, String str2, Throwable th) {
        if (DEBUG) {
            print(2, str, str2, th);
        }
    }

    /* renamed from: i */
    public static void m1394i(String str, String str2, Throwable th) {
        if (DEBUG) {
            print(3, str, str2, th);
        }
    }

    /* renamed from: w */
    public static void m1406w(String str, String str2, Throwable th) {
        if (DEBUG) {
            print(4, str, str2, th);
        }
    }

    /* renamed from: e */
    public static void m1388e(String str, String str2, Throwable th) {
        if (DEBUG) {
            print(5, str, str2, th);
        }
    }

    private static void print(int i, String str, String str2, Throwable th) {
        if (!TextUtils.isEmpty(str2)) {
            int length = str2.length();
            int i2 = LOG_MAXLENGTH;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                if (i3 >= 100) {
                    break;
                } else if (length > i2) {
                    if (i == 1) {
                        Log.v(str, str2.substring(i4, i2));
                    } else if (i == 2) {
                        Log.d(str, str2.substring(i4, i2));
                    } else if (i == 3) {
                        Log.i(str, str2.substring(i4, i2));
                    } else if (i == 4) {
                        Log.w(str, str2.substring(i4, i2));
                    } else if (i == 5) {
                        Log.e(str, str2.substring(i4, i2));
                    }
                    i3++;
                    i4 = i2;
                    i2 = LOG_MAXLENGTH + i2;
                } else if (i == 1) {
                    Log.v(str, str2.substring(i4, length));
                } else if (i == 2) {
                    Log.d(str, str2.substring(i4, length));
                } else if (i == 3) {
                    Log.i(str, str2.substring(i4, length));
                } else if (i == 4) {
                    Log.w(str, str2.substring(i4, length));
                } else if (i == 5) {
                    Log.e(str, str2.substring(i4, length));
                }
            }
        }
        if (th != null) {
            String stackTrace = getStackTrace(th);
            if (TextUtils.isEmpty(stackTrace)) {
                return;
            }
            if (i == 1) {
                Log.v(str, stackTrace);
            } else if (i == 2) {
                Log.d(str, stackTrace);
            } else if (i == 3) {
                Log.i(str, stackTrace);
            } else if (i == 4) {
                Log.w(str, stackTrace);
            } else if (i == 5) {
                Log.e(str, stackTrace);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0030 A[SYNTHETIC, Splitter:B:18:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0037  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getStackTrace(java.lang.Throwable r3) {
        /*
            r0 = 0
            java.io.StringWriter r1 = new java.io.StringWriter     // Catch:{ all -> 0x002c }
            r1.<init>()     // Catch:{ all -> 0x002c }
            java.io.PrintWriter r2 = new java.io.PrintWriter     // Catch:{ all -> 0x0028 }
            r2.<init>(r1)     // Catch:{ all -> 0x0028 }
            r3.printStackTrace(r2)     // Catch:{ all -> 0x0026 }
            r2.flush()     // Catch:{ all -> 0x0026 }
            r1.flush()     // Catch:{ all -> 0x0026 }
            java.lang.String r3 = r1.toString()     // Catch:{ all -> 0x0026 }
            r1.close()     // Catch:{ all -> 0x0020 }
            goto L_0x0021
        L_0x0020:
            r0 = move-exception
        L_0x0021:
            r2.close()
            goto L_0x003c
        L_0x0026:
            r3 = move-exception
            goto L_0x002a
        L_0x0028:
            r3 = move-exception
            r2 = r0
        L_0x002a:
            r0 = r1
            goto L_0x002e
        L_0x002c:
            r3 = move-exception
            r2 = r0
        L_0x002e:
            if (r0 == 0) goto L_0x0035
            r0.close()     // Catch:{ all -> 0x0034 }
            goto L_0x0035
        L_0x0034:
            r3 = move-exception
        L_0x0035:
            if (r2 == 0) goto L_0x003a
            r2.close()
        L_0x003a:
            java.lang.String r3 = ""
        L_0x003c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.common.ULog.getStackTrace(java.lang.Throwable):java.lang.String");
    }
}
