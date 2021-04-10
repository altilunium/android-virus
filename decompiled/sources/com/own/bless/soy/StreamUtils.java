package com.own.bless.soy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: com.own.bless.soy.l0 */
public class StreamUtils {
    /* renamed from: a */
    public static void m174a(InputStream is, OutputStream os) {
        if (is == null || os == null) {
            throw new IllegalArgumentException("Argument is null.");
        }
        byte[] bytes = new byte[8192];
        while (true) {
            int count = is.read(bytes, 0, 8192);
            if (count != -1) {
                os.write(bytes, 0, count);
            } else {
                return;
            }
        }
    }

    /* renamed from: b */
    public static String m175b(InputStream is) {
        if (is == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            m174a(is, baos);
            String byteArrayOutputStream = baos.toString();
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return byteArrayOutputStream;
        } catch (Exception e2) {
            e2.printStackTrace();
            try {
                baos.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            return null;
        } catch (Throwable th) {
            try {
                baos.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }
}
