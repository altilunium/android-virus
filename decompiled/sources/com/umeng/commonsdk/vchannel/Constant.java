package com.umeng.commonsdk.vchannel;

/* renamed from: com.umeng.commonsdk.vchannel.a */
public class Constant {

    /* renamed from: a */
    public static String f1661a = "https://pslog.umeng.com";

    /* renamed from: b */
    public static String f1662b = "https://pslog.umeng.com/";

    /* renamed from: c */
    public static String f1663c = "explog";

    /* renamed from: d */
    public static final String f1664d = "analytics";

    /* renamed from: e */
    public static final String f1665e = "ekv";

    /* renamed from: f */
    public static final String f1666f = "id";

    /* renamed from: g */
    public static final String f1667g = "ts";

    /* renamed from: h */
    public static final String f1668h = "ds";

    /* renamed from: i */
    public static final String f1669i = "pn";

    /* renamed from: j */
    public static String f1670j;

    static {
        f1670j = "";
        String str = "SUB" + System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(String.format("%0" + (32 - str.length()) + "d", 0));
        f1670j = sb.toString();
    }
}
