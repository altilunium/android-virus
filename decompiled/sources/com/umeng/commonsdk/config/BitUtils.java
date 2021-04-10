package com.umeng.commonsdk.config;

/* renamed from: com.umeng.commonsdk.config.a */
public class BitUtils {
    /* renamed from: a */
    public static boolean m1118a(int i, int i2) {
        if (i2 < 0 || i2 > 31 || (i & (1 << i2)) == 0) {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    public static int m1120b(int i, int i2) {
        return i | (1 << i2);
    }

    /* renamed from: c */
    public static int m1122c(int i, int i2) {
        return i & ((1 << i2) ^ -1);
    }

    /* renamed from: a */
    public static boolean m1119a(long j, int i) {
        if (i < 0 || i > 63 || (j & (1 << i)) == 0) {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    public static long m1121b(long j, int i) {
        if (i < 0 || i > 63) {
            return j;
        }
        return j | (1 << i);
    }

    /* renamed from: c */
    public static long m1123c(long j, int i) {
        if (i < 0 || i > 63) {
            return j;
        }
        return j & ((1 << i) ^ -1);
    }
}
