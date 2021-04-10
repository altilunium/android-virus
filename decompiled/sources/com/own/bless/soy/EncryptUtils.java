package com.own.bless.soy;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Base64;
import java.io.UnsupportedEncodingException;

/* renamed from: com.own.bless.soy.x */
public class EncryptUtils {
    @SuppressLint({"NewApi"})
    /* renamed from: b */
    public static String m244b(String content, String key) {
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        try {
            return Base64.encodeToString(XOREncrypt.m181a(content, key).getBytes("utf-8"), 2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public static String m243a(String content, String key) {
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        try {
            return XOREncrypt.m181a(new String(Base64.decode(content, 2), "utf-8"), key);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
