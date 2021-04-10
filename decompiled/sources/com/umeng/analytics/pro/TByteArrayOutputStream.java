package com.umeng.analytics.pro;

import java.io.ByteArrayOutputStream;

/* renamed from: com.umeng.analytics.pro.as */
public class TByteArrayOutputStream extends ByteArrayOutputStream {
    public TByteArrayOutputStream(int i) {
        super(i);
    }

    public TByteArrayOutputStream() {
    }

    /* renamed from: a */
    public byte[] mo381a() {
        return ((ByteArrayOutputStream) this).buf;
    }

    /* renamed from: b */
    public int mo382b() {
        return ((ByteArrayOutputStream) this).count;
    }
}
