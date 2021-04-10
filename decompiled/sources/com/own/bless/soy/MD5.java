package com.own.bless.soy;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.own.bless.soy.a0 */
public class MD5 {
    /* renamed from: a */
    public static String m29a(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();
            StringBuffer buf = new StringBuffer("");
            for (byte b2 : b) {
                int i = b2;
                if (b2 < 0) {
                    i = b2 + 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i == 1 ? 1 : 0));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
