package com.own.bless.soy;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.own.bless.soy.h */
/* compiled from: ErrManager */
public class C0015h {

    /* renamed from: a */
    private static Context f39a;

    /* renamed from: j */
    public static C0015h m99j(Context context) {
        if (f39a == null) {
            f39a = context;
        }
        return C0014g.m87a();
    }

    /* renamed from: f */
    private Context m95f() {
        return f39a;
    }

    /* renamed from: b */
    public void mo50b(String tag, String msg) {
        mo51c(tag, msg, null);
    }

    /* renamed from: c */
    public void mo51c(String tag, String msg, Throwable throwable) {
        ThreadUtil.m177a(new ErrManager(this, tag, msg, throwable));
    }

    /* renamed from: k */
    public String mo53k() {
        synchronized ("66a640060466490b8f35fa5e87123456") {
            try {
                File folder = m98i();
                if (folder.exists()) {
                    if (folder.isDirectory()) {
                        File[] files = folder.listFiles();
                        if (files != null) {
                            if (files.length > 0) {
                                JSONArray jsonArray = new JSONArray();
                                Set<File> errorFileSet = new HashSet<>();
                                Set<String> objectSet = new HashSet<>();
                                int length = files.length;
                                int i = 0;
                                while (true) {
                                    if (i >= length) {
                                        break;
                                    }
                                    File value = files[i];
                                    String errJson = FileUtils.m264d(value);
                                    errorFileSet.add(value);
                                    if (!TextUtils.isEmpty(errJson)) {
                                        JSONObject object = new JSONObject(errJson);
                                        String jsonStr = object.toString();
                                        if (!objectSet.contains(jsonStr)) {
                                            objectSet.add(jsonStr);
                                            jsonArray.put(object);
                                            if (jsonArray.length() >= 50) {
                                                break;
                                            }
                                        } else {
                                            continue;
                                        }
                                    }
                                    i++;
                                }
                                if (errorFileSet.size() > 0) {
                                    for (File file : errorFileSet) {
                                        FileUtils.m262b(file);
                                    }
                                }
                                return jsonArray.toString();
                            }
                        }
                        return null;
                    }
                }
                return null;
            } catch (Throwable throwable) {
                MyLog.m48b("[error] pack error info fail", throwable);
                return "";
            }
        }
    }

    /* renamed from: l */
    public void mo54l(String content) {
        synchronized ("66a640060466490b8f35fa5e87123456") {
            try {
                FileUtils.m266f(!TextUtils.isEmpty(content) ? content : "", m96g());
            } catch (Throwable throwable) {
                MyLog.m48b("[error] save cache error info fail!", throwable);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: m */
    public void m100m(String content) {
        File file = m97h();
        MyLog.m51e("[error] save error: " + content + ", path : " + file.getAbsolutePath());
        FileUtils.m261a(file.getAbsolutePath());
        FileUtils.m266f(content, file);
    }

    /* renamed from: d */
    public String mo52d() {
        File cacheFile = m96g();
        if (!cacheFile.exists() || !cacheFile.isFile()) {
            return null;
        }
        return FileUtils.m264d(cacheFile);
    }

    /* renamed from: h */
    private File m97h() {
        File i = m98i();
        return new File(i, "/" + System.currentTimeMillis());
    }

    /* renamed from: i */
    private File m98i() {
        return new File(m94e(), "errLog");
    }

    /* renamed from: g */
    private File m96g() {
        return new File(m94e(), "errLogCache.dat");
    }

    /* renamed from: e */
    private File m94e() {
        return new File(m95f().getFilesDir(), "cacheyy");
    }
}
