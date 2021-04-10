package com.umeng.analytics.pro;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.umeng.analytics.pro.bc */
public class FieldMetaData implements Serializable {

    /* renamed from: d */
    private static Map f476d = new HashMap();

    /* renamed from: a */
    public final String f477a;

    /* renamed from: b */
    public final byte f478b;

    /* renamed from: c */
    public final FieldValueMetaData f479c;

    public FieldMetaData(String str, byte b, FieldValueMetaData bdVar) {
        this.f477a = str;
        this.f478b = b;
        this.f479c = bdVar;
    }

    /* renamed from: a */
    public static void m620a(Class cls, Map map) {
        f476d.put(cls, map);
    }

    /* renamed from: a */
    public static Map m619a(Class cls) {
        if (!f476d.containsKey(cls)) {
            try {
                cls.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("InstantiationException for TBase class: " + cls.getName() + ", message: " + e.getMessage());
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("IllegalAccessException for TBase class: " + cls.getName() + ", message: " + e2.getMessage());
            }
        }
        return (Map) f476d.get(cls);
    }
}
