package com.own.bless.soy;

import java.io.UnsupportedEncodingException;

/* renamed from: com.own.bless.soy.e */
public final class StringFogImpl {
    /* renamed from: a */
    public String mo45a(String data, String key) {
        try {
            byte[] a = C0012d.m60a(data, 2);
            m69b(a, key);
            return new String(a, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            byte[] a2 = C0012d.m60a(data, 2);
            m69b(a2, key);
            return new String(a2);
        }
    }

    /* renamed from: b */
    private static byte[] m69b(byte[] data, String key) {
        int len = data.length;
        int lenKey = key.length();
        int i = 0;
        int j = 0;
        while (i < len) {
            if (j >= lenKey) {
                j = 0;
            }
            data[i] = (byte) (data[i] ^ key.charAt(j));
            i++;
            j++;
        }
        return data;
    }
}
