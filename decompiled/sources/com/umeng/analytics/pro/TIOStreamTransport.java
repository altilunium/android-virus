package com.umeng.analytics.pro;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: com.umeng.analytics.pro.cb */
public class TIOStreamTransport extends TTransport {

    /* renamed from: a */
    protected InputStream f665a = null;

    /* renamed from: b */
    protected OutputStream f666b = null;

    protected TIOStreamTransport() {
    }

    public TIOStreamTransport(InputStream inputStream) {
        this.f665a = inputStream;
    }

    public TIOStreamTransport(OutputStream outputStream) {
        this.f666b = outputStream;
    }

    public TIOStreamTransport(InputStream inputStream, OutputStream outputStream) {
        this.f665a = inputStream;
        this.f666b = outputStream;
    }

    @Override // com.umeng.analytics.pro.TTransport
    /* renamed from: a */
    public boolean mo490a() {
        return true;
    }

    @Override // com.umeng.analytics.pro.TTransport
    /* renamed from: b */
    public void mo491b() {
    }

    @Override // com.umeng.analytics.pro.TTransport
    /* renamed from: c */
    public void mo493c() {
        InputStream inputStream = this.f665a;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.f665a = null;
        }
        OutputStream outputStream = this.f666b;
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            this.f666b = null;
        }
    }

    @Override // com.umeng.analytics.pro.TTransport
    /* renamed from: a */
    public int mo489a(byte[] bArr, int i, int i2) {
        InputStream inputStream = this.f665a;
        if (inputStream != null) {
            try {
                int read = inputStream.read(bArr, i, i2);
                if (read >= 0) {
                    return read;
                }
                throw new TTransportException(4);
            } catch (IOException e) {
                throw new TTransportException(0, e);
            }
        } else {
            throw new TTransportException(1, "Cannot read from null inputStream");
        }
    }

    @Override // com.umeng.analytics.pro.TTransport
    /* renamed from: b */
    public void mo492b(byte[] bArr, int i, int i2) {
        OutputStream outputStream = this.f666b;
        if (outputStream != null) {
            try {
                outputStream.write(bArr, i, i2);
            } catch (IOException e) {
                throw new TTransportException(0, e);
            }
        } else {
            throw new TTransportException(1, "Cannot write to null outputStream");
        }
    }

    @Override // com.umeng.analytics.pro.TTransport
    /* renamed from: d */
    public void mo494d() {
        OutputStream outputStream = this.f666b;
        if (outputStream != null) {
            try {
                outputStream.flush();
            } catch (IOException e) {
                throw new TTransportException(0, e);
            }
        } else {
            throw new TTransportException(1, "Cannot flush null outputStream");
        }
    }
}
