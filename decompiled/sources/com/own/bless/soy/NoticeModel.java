package com.own.bless.soy;

import android.content.Context;

/* renamed from: com.own.bless.soy.n */
public class NoticeModel extends BaseModel {

    /* renamed from: h */
    public String f80h;

    /* renamed from: i */
    public String f81i;

    /* renamed from: j */
    public String f82j;

    /* renamed from: k */
    public String f83k;

    /* renamed from: l */
    public int f84l;

    @Override // com.own.bless.soy.BaseModel
    /* renamed from: a */
    public String mo71a(Context context) {
        return context.getExternalCacheDir().getPath() + "/notice/" + MD5.m29a(this.f70d);
    }
}
