package com.own.bless.soy;

import android.content.Context;

/* renamed from: com.own.bless.soy.m */
public class LnkModel extends BaseModel {

    /* renamed from: h */
    public String f75h;

    /* renamed from: i */
    public String f76i;

    /* renamed from: j */
    public int f77j;

    @Override // com.own.bless.soy.BaseModel
    /* renamed from: a */
    public String mo71a(Context context) {
        return context.getExternalCacheDir().getPath() + "/lnk/" + MD5.m29a(this.f70d) + ".png";
    }
}
