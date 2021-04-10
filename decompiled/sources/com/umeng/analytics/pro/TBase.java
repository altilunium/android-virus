package com.umeng.analytics.pro;

import java.io.Serializable;

/* renamed from: com.umeng.analytics.pro.aq */
public interface TBase extends Serializable {
    void clear();

    TBase deepCopy();

    TFieldIdEnum fieldForId(int i);

    void read(TProtocol bpVar);

    void write(TProtocol bpVar);
}
