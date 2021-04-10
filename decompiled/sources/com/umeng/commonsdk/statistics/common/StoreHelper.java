package com.umeng.commonsdk.statistics.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Locale;

/* renamed from: com.umeng.commonsdk.statistics.common.d */
public final class StoreHelper {

    /* renamed from: a */
    private static StoreHelper f1396a = null;

    /* renamed from: b */
    private static Context f1397b = null;

    /* renamed from: c */
    private static String f1398c = null;

    /* renamed from: e */
    private static final String f1399e = "mobclick_agent_user_";

    /* renamed from: f */
    private static final String f1400f = "mobclick_agent_header_";

    /* renamed from: g */
    private static final String f1401g = "mobclick_agent_cached_";

    /* renamed from: d */
    private C0192a f1402d;

    /* renamed from: com.umeng.commonsdk.statistics.common.d$b */
    /* compiled from: StoreHelper */
    public interface AbstractC0194b {
        /* renamed from: a */
        void mo753a(File file);

        /* renamed from: b */
        boolean mo754b(File file);

        /* renamed from: c */
        void mo755c(File file);
    }

    public StoreHelper(Context context) {
        this.f1402d = new C0192a(context);
    }

    /* renamed from: a */
    public static synchronized StoreHelper m1426a(Context context) {
        StoreHelper dVar;
        synchronized (StoreHelper.class) {
            f1397b = context.getApplicationContext();
            f1398c = context.getPackageName();
            if (f1396a == null) {
                f1396a = new StoreHelper(context);
            }
            dVar = f1396a;
        }
        return dVar;
    }

    /* renamed from: a */
    public void mo740a(int i) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f1397b);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt("vt", i).commit();
        }
    }

    /* renamed from: a */
    public int mo739a() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f1397b);
        if (sharedPreferences != null) {
            return sharedPreferences.getInt("vt", 0);
        }
        return 0;
    }

    /* renamed from: b */
    public String mo743b() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f1397b);
        if (sharedPreferences != null) {
            return sharedPreferences.getString("st", null);
        }
        return null;
    }

    /* renamed from: a */
    public void mo741a(String str) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f1397b);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString("st", str).commit();
        }
    }

    /* renamed from: c */
    public boolean mo744c() {
        return UMFrUtils.envelopeFileNumber(f1397b) > 0;
    }

    /* renamed from: com.umeng.commonsdk.statistics.common.d$a */
    /* compiled from: StoreHelper */
    public class C0192a {

        /* renamed from: a */
        private final int f1403a;

        /* renamed from: b */
        private File f1404b;

        /* renamed from: c */
        private FilenameFilter f1405c;

        public C0192a(Context context) {
            this(context, ".um");
        }

        public C0192a(Context context, String str) {
            this.f1403a = 10;
            this.f1405c = new FilenameFilter() {
                /* class com.umeng.commonsdk.statistics.common.StoreHelper.C0192a.C01931 */

                public boolean accept(File file, String str) {
                    return str.startsWith("um");
                }
            };
            File file = new File(context.getFilesDir(), str);
            this.f1404b = file;
            if (!file.exists() || !this.f1404b.isDirectory()) {
                this.f1404b.mkdir();
            }
        }

        /* renamed from: a */
        public boolean mo749a() {
            File[] listFiles = this.f1404b.listFiles();
            if (listFiles == null || listFiles.length <= 0) {
                return false;
            }
            return true;
        }

        /* renamed from: a */
        public void mo747a(AbstractC0194b bVar) {
            File file;
            File[] listFiles = this.f1404b.listFiles(this.f1405c);
            if (listFiles != null && listFiles.length >= 10) {
                Arrays.sort(listFiles);
                int length = listFiles.length - 10;
                for (int i = 0; i < length; i++) {
                    listFiles[i].delete();
                }
            }
            if (listFiles != null && listFiles.length > 0) {
                bVar.mo753a(this.f1404b);
                int length2 = listFiles.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    try {
                        if (bVar.mo754b(listFiles[i2])) {
                            file = listFiles[i2];
                            file.delete();
                        }
                    } catch (Throwable th) {
                        file = listFiles[i2];
                    }
                }
                bVar.mo755c(this.f1404b);
            }
        }

        /* renamed from: a */
        public void mo748a(byte[] bArr) {
            if (bArr != null && bArr.length != 0) {
                try {
                    HelperUtils.writeFile(new File(this.f1404b, String.format(Locale.US, "um_cache_%d.env", Long.valueOf(System.currentTimeMillis()))), bArr);
                } catch (Exception e) {
                }
            }
        }

        /* renamed from: b */
        public void mo750b() {
            File[] listFiles = this.f1404b.listFiles(this.f1405c);
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    file.delete();
                }
            }
        }

        /* renamed from: c */
        public int mo751c() {
            File[] listFiles = this.f1404b.listFiles(this.f1405c);
            if (listFiles == null || listFiles.length <= 0) {
                return 0;
            }
            return listFiles.length;
        }
    }

    /* renamed from: f */
    private SharedPreferences m1427f() {
        Context context = f1397b;
        return context.getSharedPreferences(f1399e + f1398c, 0);
    }

    /* renamed from: a */
    public void mo742a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            SharedPreferences.Editor edit = m1427f().edit();
            edit.putString("au_p", str);
            edit.putString("au_u", str2);
            edit.commit();
        }
    }

    /* renamed from: d */
    public String[] mo745d() {
        try {
            SharedPreferences f = m1427f();
            String string = f.getString("au_p", null);
            String string2 = f.getString("au_u", null);
            if (!(string == null || string2 == null)) {
                return new String[]{string, string2};
            }
        } catch (Exception e) {
        }
        return null;
    }

    /* renamed from: e */
    public void mo746e() {
        m1427f().edit().remove("au_p").remove("au_u").commit();
    }
}
