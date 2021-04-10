package com.umeng.commonsdk.config;

import java.util.HashMap;
import java.util.Map;

/* renamed from: com.umeng.commonsdk.config.b */
public class CollectController implements IFieldWatcher {

    /* renamed from: a */
    private static Map f1059a = new HashMap();

    /* renamed from: b */
    private static Object f1060b = new Object();

    private CollectController() {
    }

    /* renamed from: a */
    public void mo665a() {
        synchronized (f1060b) {
            f1059a.clear();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.config.b$a */
    /* compiled from: CollectController */
    public class C0146a {

        /* renamed from: a */
        private static final CollectController f1061a = new CollectController();

        private C0146a() {
        }
    }

    /* renamed from: b */
    public static CollectController m1125b() {
        return C0146a.f1061a;
    }

    /* renamed from: a */
    public static boolean m1124a(String str) {
        if (!FieldTable.m1130a(str)) {
            return false;
        }
        synchronized (f1060b) {
            if (!f1059a.containsKey(str)) {
                return true;
            }
            return ((Boolean) f1059a.get(str)).booleanValue();
        }
    }

    @Override // com.umeng.commonsdk.config.IFieldWatcher
    /* renamed from: a */
    public void mo666a(String str, Boolean bool) {
        if (FieldTable.m1130a(str)) {
            synchronized (f1060b) {
                Map map = f1059a;
                if (map != null) {
                    map.put(str, bool);
                }
            }
        }
    }
}
