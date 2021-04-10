package com.umeng.commonsdk.statistics.proto;

import com.umeng.analytics.pro.EncodingUtils;
import com.umeng.analytics.pro.FieldMetaData;
import com.umeng.analytics.pro.FieldValueMetaData;
import com.umeng.analytics.pro.SchemeFactory;
import com.umeng.analytics.pro.StandardScheme;
import com.umeng.analytics.pro.StructMetaData;
import com.umeng.analytics.pro.TBase;
import com.umeng.analytics.pro.TCompactProtocol;
import com.umeng.analytics.pro.TException;
import com.umeng.analytics.pro.TField;
import com.umeng.analytics.pro.TFieldIdEnum;
import com.umeng.analytics.pro.TIOStreamTransport;
import com.umeng.analytics.pro.TProtocol;
import com.umeng.analytics.pro.TProtocolException;
import com.umeng.analytics.pro.TProtocolUtil;
import com.umeng.analytics.pro.TStruct;
import com.umeng.analytics.pro.TTupleProtocol;
import com.umeng.analytics.pro.TupleScheme;
import com.umeng.analytics.pro.UMCommonContent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Response implements TBase, Serializable, Cloneable {
    private static final TField IMPRINT_FIELD_DESC = new TField(UMCommonContent.f287X, (byte) 12, 3);
    private static final TField MSG_FIELD_DESC = new TField("msg", (byte) 11, 2);
    private static final TField RESP_CODE_FIELD_DESC = new TField("resp_code", (byte) 8, 1);
    private static final TStruct STRUCT_DESC = new TStruct("Response");
    private static final int __RESP_CODE_ISSET_ID = 0;
    public static final Map metaDataMap;
    private static final Map schemes;
    private static final long serialVersionUID = -4549277923241195391L;
    private byte __isset_bitfield;
    public Imprint imprint;
    public String msg;
    private EnumC0209e[] optionals;
    public int resp_code;

    static {
        HashMap hashMap = new HashMap();
        schemes = hashMap;
        hashMap.put(StandardScheme.class, new C0206b());
        hashMap.put(TupleScheme.class, new C0208d());
        EnumMap enumMap = new EnumMap(EnumC0209e.class);
        enumMap.put((Object) EnumC0209e.RESP_CODE, (Object) new FieldMetaData("resp_code", (byte) 1, new FieldValueMetaData((byte) 8)));
        enumMap.put((Object) EnumC0209e.MSG, (Object) new FieldMetaData("msg", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put((Object) EnumC0209e.IMPRINT, (Object) new FieldMetaData(UMCommonContent.f287X, (byte) 2, new StructMetaData((byte) 12, Imprint.class)));
        Map unmodifiableMap = Collections.unmodifiableMap(enumMap);
        metaDataMap = unmodifiableMap;
        FieldMetaData.m620a(Response.class, unmodifiableMap);
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.Response$e */
    public enum EnumC0209e implements TFieldIdEnum {
        RESP_CODE(1, "resp_code"),
        MSG(2, "msg"),
        IMPRINT(3, UMCommonContent.f287X);
        

        /* renamed from: d */
        private static final Map f1483d = new HashMap();

        /* renamed from: e */
        private final short f1485e;

        /* renamed from: f */
        private final String f1486f;

        static {
            Iterator it = EnumSet.allOf(EnumC0209e.class).iterator();
            while (it.hasNext()) {
                EnumC0209e eVar = (EnumC0209e) it.next();
                f1483d.put(eVar.mo362b(), eVar);
            }
        }

        /* renamed from: a */
        public static EnumC0209e m1544a(int i) {
            if (i == 1) {
                return RESP_CODE;
            }
            if (i == 2) {
                return MSG;
            }
            if (i != 3) {
                return null;
            }
            return IMPRINT;
        }

        /* renamed from: b */
        public static EnumC0209e m1546b(int i) {
            EnumC0209e a = m1544a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* renamed from: a */
        public static EnumC0209e m1545a(String str) {
            return (EnumC0209e) f1483d.get(str);
        }

        private EnumC0209e(short s, String str) {
            this.f1485e = s;
            this.f1486f = str;
        }

        @Override // com.umeng.analytics.pro.TFieldIdEnum
        /* renamed from: a */
        public short mo361a() {
            return this.f1485e;
        }

        @Override // com.umeng.analytics.pro.TFieldIdEnum
        /* renamed from: b */
        public String mo362b() {
            return this.f1486f;
        }
    }

    public Response() {
        this.__isset_bitfield = 0;
        this.optionals = new EnumC0209e[]{EnumC0209e.MSG, EnumC0209e.IMPRINT};
    }

    public Response(int i) {
        this();
        this.resp_code = i;
        setResp_codeIsSet(true);
    }

    public Response(Response response) {
        this.__isset_bitfield = 0;
        this.optionals = new EnumC0209e[]{EnumC0209e.MSG, EnumC0209e.IMPRINT};
        this.__isset_bitfield = response.__isset_bitfield;
        this.resp_code = response.resp_code;
        if (response.isSetMsg()) {
            this.msg = response.msg;
        }
        if (response.isSetImprint()) {
            this.imprint = new Imprint(response.imprint);
        }
    }

    @Override // com.umeng.analytics.pro.TBase
    public Response deepCopy() {
        return new Response(this);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void clear() {
        setResp_codeIsSet(false);
        this.resp_code = 0;
        this.msg = null;
        this.imprint = null;
    }

    public int getResp_code() {
        return this.resp_code;
    }

    public Response setResp_code(int i) {
        this.resp_code = i;
        setResp_codeIsSet(true);
        return this;
    }

    public void unsetResp_code() {
        this.__isset_bitfield = EncodingUtils.m530b(this.__isset_bitfield, 0);
    }

    public boolean isSetResp_code() {
        return EncodingUtils.m526a(this.__isset_bitfield, 0);
    }

    public void setResp_codeIsSet(boolean z) {
        this.__isset_bitfield = EncodingUtils.m518a(this.__isset_bitfield, 0, z);
    }

    public String getMsg() {
        return this.msg;
    }

    public Response setMsg(String str) {
        this.msg = str;
        return this;
    }

    public void unsetMsg() {
        this.msg = null;
    }

    public boolean isSetMsg() {
        return this.msg != null;
    }

    public void setMsgIsSet(boolean z) {
        if (!z) {
            this.msg = null;
        }
    }

    public Imprint getImprint() {
        return this.imprint;
    }

    public Response setImprint(Imprint dVar) {
        this.imprint = dVar;
        return this;
    }

    public void unsetImprint() {
        this.imprint = null;
    }

    public boolean isSetImprint() {
        return this.imprint != null;
    }

    public void setImprintIsSet(boolean z) {
        if (!z) {
            this.imprint = null;
        }
    }

    @Override // com.umeng.analytics.pro.TBase
    public EnumC0209e fieldForId(int i) {
        return EnumC0209e.m1544a(i);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void read(TProtocol bpVar) {
        ((SchemeFactory) schemes.get(bpVar.mo485D())).mo357b().mo355b(bpVar, this);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void write(TProtocol bpVar) {
        ((SchemeFactory) schemes.get(bpVar.mo485D())).mo357b().mo353a(bpVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Response(");
        sb.append("resp_code:");
        sb.append(this.resp_code);
        if (isSetMsg()) {
            sb.append(", ");
            sb.append("msg:");
            String str = this.msg;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
        }
        if (isSetImprint()) {
            sb.append(", ");
            sb.append("imprint:");
            Imprint dVar = this.imprint;
            if (dVar == null) {
                sb.append("null");
            } else {
                sb.append(dVar);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() {
        Imprint dVar = this.imprint;
        if (dVar != null) {
            dVar.mo971l();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        try {
            write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
        } catch (TException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void readObject(ObjectInputStream objectInputStream) {
        try {
            this.__isset_bitfield = 0;
            read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
        } catch (TException e) {
            throw new IOException(e.getMessage());
        }
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.Response$b */
    class C0206b implements SchemeFactory {
        private C0206b() {
        }

        /* renamed from: a */
        public C0205a mo357b() {
            return new C0205a();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.statistics.proto.Response$a */
    public class C0205a extends StandardScheme {
        private C0205a() {
        }

        /* renamed from: a */
        public void mo355b(TProtocol bpVar, Response response) {
            bpVar.mo459j();
            while (true) {
                TField l = bpVar.mo461l();
                byte b = l.f538b;
                if (b == 0) {
                    break;
                }
                short s = l.f539c;
                if (s != 1) {
                    if (s != 2) {
                        if (s != 3) {
                            TProtocolUtil.m785a(bpVar, b);
                        } else if (b == 12) {
                            Imprint dVar = new Imprint();
                            response.imprint = dVar;
                            dVar.read(bpVar);
                            response.setImprintIsSet(true);
                        } else {
                            TProtocolUtil.m785a(bpVar, b);
                        }
                    } else if (b == 11) {
                        response.msg = bpVar.mo475z();
                        response.setMsgIsSet(true);
                    } else {
                        TProtocolUtil.m785a(bpVar, b);
                    }
                } else if (b == 8) {
                    response.resp_code = bpVar.mo472w();
                    response.setResp_codeIsSet(true);
                } else {
                    TProtocolUtil.m785a(bpVar, b);
                }
                bpVar.mo462m();
            }
            bpVar.mo460k();
            if (response.isSetResp_code()) {
                response.validate();
                return;
            }
            throw new TProtocolException("Required field 'resp_code' was not found in serialized data! Struct: " + toString());
        }

        /* renamed from: b */
        public void mo353a(TProtocol bpVar, Response response) {
            response.validate();
            bpVar.mo443a(Response.STRUCT_DESC);
            bpVar.mo438a(Response.RESP_CODE_FIELD_DESC);
            bpVar.mo436a(response.resp_code);
            bpVar.mo450c();
            if (response.msg != null && response.isSetMsg()) {
                bpVar.mo438a(Response.MSG_FIELD_DESC);
                bpVar.mo444a(response.msg);
                bpVar.mo450c();
            }
            if (response.imprint != null && response.isSetImprint()) {
                bpVar.mo438a(Response.IMPRINT_FIELD_DESC);
                response.imprint.write(bpVar);
                bpVar.mo450c();
            }
            bpVar.mo452d();
            bpVar.mo449b();
        }
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.Response$d */
    class C0208d implements SchemeFactory {
        private C0208d() {
        }

        /* renamed from: a */
        public C0207c mo357b() {
            return new C0207c();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.statistics.proto.Response$c */
    public class C0207c extends TupleScheme {
        private C0207c() {
        }

        /* renamed from: a */
        public void mo353a(TProtocol bpVar, Response response) {
            TTupleProtocol bvVar = (TTupleProtocol) bpVar;
            bvVar.mo436a(response.resp_code);
            BitSet bitSet = new BitSet();
            if (response.isSetMsg()) {
                bitSet.set(0);
            }
            if (response.isSetImprint()) {
                bitSet.set(1);
            }
            bvVar.mo487a(bitSet, 2);
            if (response.isSetMsg()) {
                bvVar.mo444a(response.msg);
            }
            if (response.isSetImprint()) {
                response.imprint.write(bvVar);
            }
        }

        /* renamed from: b */
        public void mo355b(TProtocol bpVar, Response response) {
            TTupleProtocol bvVar = (TTupleProtocol) bpVar;
            response.resp_code = bvVar.mo472w();
            response.setResp_codeIsSet(true);
            BitSet b = bvVar.mo488b(2);
            if (b.get(0)) {
                response.msg = bvVar.mo475z();
                response.setMsgIsSet(true);
            }
            if (b.get(1)) {
                Imprint dVar = new Imprint();
                response.imprint = dVar;
                dVar.read(bvVar);
                response.setImprintIsSet(true);
            }
        }
    }
}
