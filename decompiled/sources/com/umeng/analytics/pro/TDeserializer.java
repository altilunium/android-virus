package com.umeng.analytics.pro;

import com.umeng.analytics.pro.TCompactProtocol;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* renamed from: com.umeng.analytics.pro.at */
public class TDeserializer {

    /* renamed from: a */
    private final TProtocol f462a;

    /* renamed from: b */
    private final TMemoryInputTransport f463b;

    public TDeserializer() {
        this(new TCompactProtocol.C0086a());
    }

    public TDeserializer(TProtocolFactory brVar) {
        TMemoryInputTransport ccVar = new TMemoryInputTransport();
        this.f463b = ccVar;
        this.f462a = brVar.mo476a(ccVar);
    }

    /* renamed from: a */
    public void mo386a(TBase aqVar, byte[] bArr) {
        try {
            this.f463b.mo496a(bArr);
            aqVar.read(this.f462a);
        } finally {
            this.f463b.mo498e();
            this.f462a.mo477B();
        }
    }

    /* renamed from: a */
    public void mo385a(TBase aqVar, String str, String str2) {
        try {
            mo386a(aqVar, str.getBytes(str2));
            this.f462a.mo477B();
        } catch (UnsupportedEncodingException e) {
            throw new TException("JVM DOES NOT SUPPORT ENCODING: " + str2);
        } catch (Throwable th) {
            this.f462a.mo477B();
            throw th;
        }
    }

    /* renamed from: a */
    public void mo387a(TBase aqVar, byte[] bArr, TFieldIdEnum axVar, TFieldIdEnum... axVarArr) {
        try {
            if (m566j(bArr, axVar, axVarArr) != null) {
                aqVar.read(this.f462a);
            }
            this.f463b.mo498e();
            this.f462a.mo477B();
        } catch (Exception e) {
            throw new TException(e);
        } catch (Throwable th) {
            this.f463b.mo498e();
            this.f462a.mo477B();
            throw th;
        }
    }

    /* renamed from: a */
    public Boolean mo383a(byte[] bArr, TFieldIdEnum axVar, TFieldIdEnum... axVarArr) {
        return (Boolean) m565a((byte) 2, bArr, axVar, axVarArr);
    }

    /* renamed from: b */
    public Byte mo388b(byte[] bArr, TFieldIdEnum axVar, TFieldIdEnum... axVarArr) {
        return (Byte) m565a((byte) 3, bArr, axVar, axVarArr);
    }

    /* renamed from: c */
    public Double mo389c(byte[] bArr, TFieldIdEnum axVar, TFieldIdEnum... axVarArr) {
        return (Double) m565a((byte) 4, bArr, axVar, axVarArr);
    }

    /* renamed from: d */
    public Short mo390d(byte[] bArr, TFieldIdEnum axVar, TFieldIdEnum... axVarArr) {
        return (Short) m565a((byte) 6, bArr, axVar, axVarArr);
    }

    /* renamed from: e */
    public Integer mo391e(byte[] bArr, TFieldIdEnum axVar, TFieldIdEnum... axVarArr) {
        return (Integer) m565a((byte) 8, bArr, axVar, axVarArr);
    }

    /* renamed from: f */
    public Long mo392f(byte[] bArr, TFieldIdEnum axVar, TFieldIdEnum... axVarArr) {
        return (Long) m565a((byte) 10, bArr, axVar, axVarArr);
    }

    /* renamed from: g */
    public String mo393g(byte[] bArr, TFieldIdEnum axVar, TFieldIdEnum... axVarArr) {
        return (String) m565a((byte) 11, bArr, axVar, axVarArr);
    }

    /* renamed from: h */
    public ByteBuffer mo394h(byte[] bArr, TFieldIdEnum axVar, TFieldIdEnum... axVarArr) {
        return (ByteBuffer) m565a((byte) 100, bArr, axVar, axVarArr);
    }

    /* renamed from: i */
    public Short mo395i(byte[] bArr, TFieldIdEnum axVar, TFieldIdEnum... axVarArr) {
        Short sh;
        try {
            if (m566j(bArr, axVar, axVarArr) != null) {
                this.f462a.mo459j();
                sh = Short.valueOf(this.f462a.mo461l().f539c);
            } else {
                sh = null;
            }
            this.f463b.mo498e();
            this.f462a.mo477B();
            return sh;
        } catch (Exception e) {
            throw new TException(e);
        } catch (Throwable th) {
            this.f463b.mo498e();
            this.f462a.mo477B();
            throw th;
        }
    }

    /* renamed from: a */
    private Object m565a(byte b, byte[] bArr, TFieldIdEnum axVar, TFieldIdEnum... axVarArr) {
        Object obj;
        try {
            TField j = m566j(bArr, axVar, axVarArr);
            if (j != null) {
                if (b != 2) {
                    if (b != 3) {
                        if (b != 4) {
                            if (b != 6) {
                                if (b != 8) {
                                    if (b != 100) {
                                        if (b != 10) {
                                            if (b == 11) {
                                                if (j.f538b == 11) {
                                                    obj = this.f462a.mo475z();
                                                    this.f463b.mo498e();
                                                    this.f462a.mo477B();
                                                    return obj;
                                                }
                                            }
                                        } else if (j.f538b == 10) {
                                            obj = Long.valueOf(this.f462a.mo473x());
                                            this.f463b.mo498e();
                                            this.f462a.mo477B();
                                            return obj;
                                        }
                                    } else if (j.f538b == 11) {
                                        obj = this.f462a.mo432A();
                                        this.f463b.mo498e();
                                        this.f462a.mo477B();
                                        return obj;
                                    }
                                } else if (j.f538b == 8) {
                                    obj = Integer.valueOf(this.f462a.mo472w());
                                    this.f463b.mo498e();
                                    this.f462a.mo477B();
                                    return obj;
                                }
                            } else if (j.f538b == 6) {
                                obj = Short.valueOf(this.f462a.mo471v());
                                this.f463b.mo498e();
                                this.f462a.mo477B();
                                return obj;
                            }
                        } else if (j.f538b == 4) {
                            obj = Double.valueOf(this.f462a.mo474y());
                            this.f463b.mo498e();
                            this.f462a.mo477B();
                            return obj;
                        }
                    } else if (j.f538b == 3) {
                        obj = Byte.valueOf(this.f462a.mo470u());
                        this.f463b.mo498e();
                        this.f462a.mo477B();
                        return obj;
                    }
                } else if (j.f538b == 2) {
                    obj = Boolean.valueOf(this.f462a.mo469t());
                    this.f463b.mo498e();
                    this.f462a.mo477B();
                    return obj;
                }
            }
            obj = null;
            this.f463b.mo498e();
            this.f462a.mo477B();
            return obj;
        } catch (Exception e) {
            throw new TException(e);
        } catch (Throwable th) {
            this.f463b.mo498e();
            this.f462a.mo477B();
            throw th;
        }
    }

    /* renamed from: j */
    private TField m566j(byte[] bArr, TFieldIdEnum axVar, TFieldIdEnum... axVarArr) {
        this.f463b.mo496a(bArr);
        int length = axVarArr.length + 1;
        TFieldIdEnum[] axVarArr2 = new TFieldIdEnum[length];
        int i = 0;
        axVarArr2[0] = axVar;
        int i2 = 0;
        while (i2 < axVarArr.length) {
            int i3 = i2 + 1;
            axVarArr2[i3] = axVarArr[i2];
            i2 = i3;
        }
        this.f462a.mo459j();
        TField bkVar = null;
        while (i < length) {
            bkVar = this.f462a.mo461l();
            if (bkVar.f538b == 0 || bkVar.f539c > axVarArr2[i].mo361a()) {
                return null;
            }
            if (bkVar.f539c != axVarArr2[i].mo361a()) {
                TProtocolUtil.m785a(this.f462a, bkVar.f538b);
                this.f462a.mo462m();
            } else {
                i++;
                if (i < length) {
                    this.f462a.mo459j();
                }
            }
        }
        return bkVar;
    }

    /* renamed from: a */
    public void mo384a(TBase aqVar, String str) {
        mo386a(aqVar, str.getBytes());
    }
}
