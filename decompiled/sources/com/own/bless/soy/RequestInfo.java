package com.own.bless.soy;

import java.util.Map;

/* renamed from: com.own.bless.soy.o */
public class RequestInfo {

    /* renamed from: a */
    private String f85a;

    /* renamed from: b */
    private Map f86b;

    /* renamed from: c */
    private HttpNetUtil f87c;

    /* renamed from: c */
    public String mo80c() {
        return this.f85a;
    }

    /* renamed from: f */
    public void mo83f(String url) {
        this.f85a = url;
    }

    /* renamed from: b */
    public Map mo79b() {
        return this.f86b;
    }

    /* renamed from: e */
    public void mo82e(Map map) {
        this.f86b = map;
    }

    /* renamed from: a */
    public HttpNetUtil mo78a() {
        return this.f87c;
    }

    /* renamed from: d */
    public void mo81d(HttpNetUtil callBack) {
        this.f87c = callBack;
    }
}
