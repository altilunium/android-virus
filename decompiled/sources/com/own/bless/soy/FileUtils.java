package com.own.bless.soy;

import android.os.Environment;
import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* renamed from: com.own.bless.soy.y */
public class FileUtils {
    /* renamed from: e */
    public static boolean m265e() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    /* renamed from: d */
    public static String m264d(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            String b = StreamUtils.m175b(fis);
            try {
                fis.close();
            } catch (IOException e) {
            }
            return b;
        } catch (Exception e2) {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e3) {
                }
            }
            return null;
        } catch (Throwable th) {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e4) {
                }
            }
            throw th;
        }
    }

    /* renamed from: f */
    public static void m266f(String content, File file) {
        if (content == null || file == null) {
            throw new IllegalArgumentException("argument is null.");
        }
        FileOutputStream fos = new FileOutputStream(file);
        ByteArrayInputStream bais = new ByteArrayInputStream(content.getBytes());
        StreamUtils.m174a(bais, fos);
        try {
            fos.close();
            bais.close();
        } catch (Exception e) {
        }
    }

    /* renamed from: b */
    public static void m262b(File file) {
        if (file != null && file.isFile() && !file.delete()) {
            file.deleteOnExit();
        }
    }

    /* renamed from: c */
    public static boolean m263c(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        return new File(filePath).exists();
    }

    /* renamed from: a */
    public static boolean m261a(String filePath) {
        if (m263c(filePath)) {
            return true;
        }
        try {
            File file = new File(filePath);
            boolean parentExist = file.getParentFile().exists();
            if (!parentExist) {
                parentExist = file.getParentFile().mkdirs();
            }
            if (!parentExist) {
                return false;
            }
            return file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
