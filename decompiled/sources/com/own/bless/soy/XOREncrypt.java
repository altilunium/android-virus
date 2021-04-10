package com.own.bless.soy;

import java.util.UUID;

/* renamed from: com.own.bless.soy.n0 */
public class XOREncrypt {
    /* renamed from: a */
    public static String m181a(String str, String key) {
        if (str == null || "".equals(str) || key == null || "".equals(key)) {
            return str;
        }
        try {
            char[] data = str.toCharArray();
            char[] keyData = key.toCharArray();
            int keyIndex = 0;
            for (int x = 0; x < data.length; x++) {
                data[x] = (char) (data[x] ^ keyData[keyIndex]);
                keyIndex++;
                if (keyIndex == keyData.length) {
                    keyIndex = 0;
                }
            }
            return new String(data);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    /* renamed from: b */
    public static String m182b() {
        return UUID.randomUUID().toString();
    }
}
