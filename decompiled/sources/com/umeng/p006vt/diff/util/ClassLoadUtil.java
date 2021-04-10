package com.umeng.p006vt.diff.util;

import android.text.TextUtils;

/* renamed from: com.umeng.vt.diff.util.ClassLoadUtil */
public class ClassLoadUtil {
    public static Class findClass(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return Class.forName(str);
        } catch (Throwable th) {
            return null;
        }
    }
}
