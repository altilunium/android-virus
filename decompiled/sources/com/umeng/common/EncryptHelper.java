package com.umeng.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.utils.UMUtils;

/* renamed from: com.umeng.common.a */
public class EncryptHelper {

    /* renamed from: a */
    private static String f1023a = null;

    /* renamed from: b */
    private static final String f1024b = "umeng+";

    /* renamed from: c */
    private static final String f1025c = "ek__id";

    /* renamed from: d */
    private static final String f1026d = "ek_key";

    /* renamed from: e */
    private static String f1027e = "";

    /* renamed from: f */
    private static final String f1028f = "umeng_subprocess_info";

    /* renamed from: g */
    private static String f1029g = "";

    /* renamed from: h */
    private static EncryptHelper f1030h;

    private EncryptHelper() {
    }

    /* renamed from: a */
    public static EncryptHelper m1084a() {
        if (f1030h == null) {
            synchronized (EncryptHelper.class) {
                if (f1030h == null) {
                    f1030h = new EncryptHelper();
                }
            }
        }
        return f1030h;
    }

    /* renamed from: c */
    private String m1085c(String str) {
        try {
            String substring = str.substring(1, 9);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < substring.length(); i++) {
                char charAt = substring.charAt(i);
                if (!Character.isDigit(charAt)) {
                    sb.append(charAt);
                } else if (Integer.parseInt(Character.toString(charAt)) == 0) {
                    sb.append(0);
                } else {
                    sb.append(10 - Integer.parseInt(Character.toString(charAt)));
                }
            }
            String sb2 = sb.toString();
            return sb2 + new StringBuilder(sb2).reverse().toString();
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: a */
    public void mo646a(Context context) {
        try {
            if (TextUtils.isEmpty(f1023a)) {
                String multiProcessSP = UMUtils.getMultiProcessSP(context, f1025c);
                if (!TextUtils.isEmpty(multiProcessSP)) {
                    f1027e = m1085c(multiProcessSP);
                    UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>>> primaryKey: " + f1027e);
                }
                SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f1028f, 0);
                if (sharedPreferences != null) {
                    f1029g = sharedPreferences.getString(f1025c, null);
                    UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 子进程备份秘钥：主进程key: " + f1029g);
                }
                f1023a = m1085c(UMUtils.genId());
                UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>>> 正式秘钥：key: " + f1023a);
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public String mo645a(String str) {
        try {
            if (TextUtils.isEmpty(f1023a)) {
                return str;
            }
            return Base64.encodeToString(DataHelper.encrypt(str.getBytes(), f1023a.getBytes()), 0);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: b */
    public String mo647b(String str) {
        String str2;
        String str3 = null;
        try {
            if (!TextUtils.isEmpty(f1023a)) {
                str = new String(DataHelper.decrypt(Base64.decode(str.getBytes(), 0), f1023a.getBytes()));
            }
            return str;
        } catch (Exception e) {
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 子进程事件数据解密失败!");
            if (TextUtils.isEmpty(f1027e)) {
                return null;
            }
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 子进程事件数据解密失败，换老秘钥重试");
            try {
                String str4 = new String(DataHelper.decrypt(Base64.decode(str.getBytes(), 0), f1027e.getBytes()));
                try {
                    UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 子进程事件数据解密失败，换老秘钥重试成功。");
                    return str4;
                } catch (Exception e2) {
                    str3 = str4;
                    UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 子进程事件数据解密失败，换老秘钥重试失败。换子进程备份key重试。");
                    try {
                        str2 = new String(DataHelper.decrypt(Base64.decode(str.getBytes(), 0), f1029g.getBytes()));
                        try {
                            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 子进程事件数据解密失败，子进程备份key重试成功。");
                            return str2;
                        } catch (Throwable th) {
                            str3 = str2;
                            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 子进程事件数据解密失败，子进程备份key重试失败。");
                            return str3;
                        }
                    } catch (Throwable th2) {
                        UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 子进程事件数据解密失败，子进程备份key重试失败。");
                        return str3;
                    }
                }
            } catch (Exception e3) {
                UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 子进程事件数据解密失败，换老秘钥重试失败。换子进程备份key重试。");
                str2 = new String(DataHelper.decrypt(Base64.decode(str.getBytes(), 0), f1029g.getBytes()));
                UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 子进程事件数据解密失败，子进程备份key重试成功。");
                return str2;
            }
        }
    }
}
