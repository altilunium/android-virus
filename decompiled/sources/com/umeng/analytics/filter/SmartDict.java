package com.umeng.analytics.filter;

import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.umeng.analytics.filter.d */
public class SmartDict {

    /* renamed from: b */
    private static final String f238b = "Ă";

    /* renamed from: a */
    private final String f239a = "MD5";

    /* renamed from: c */
    private MessageDigest f240c;

    /* renamed from: d */
    private Set f241d = new HashSet();

    /* renamed from: e */
    private boolean f242e;

    public SmartDict(boolean z, String str) {
        int i = 0;
        this.f242e = false;
        this.f242e = z;
        try {
            this.f240c = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (str == null) {
            return;
        }
        if (z) {
            try {
                byte[] decode = Base64.decode(str.getBytes(), 0);
                while (i < decode.length / 4) {
                    int i2 = i * 4;
                    this.f241d.add(Integer.valueOf(((decode[i2 + 0] & 255) << 24) + ((decode[i2 + 1] & 255) << 16) + ((decode[i2 + 2] & 255) << 8) + (decode[i2 + 3] & 255)));
                    i++;
                }
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            }
        } else {
            String[] split = str.split(f238b);
            int length = split.length;
            while (i < length) {
                this.f241d.add(split[i]);
                i++;
            }
        }
    }

    /* renamed from: c */
    private Integer m387c(String str) {
        try {
            this.f240c.update(str.getBytes());
            byte[] digest = this.f240c.digest();
            return Integer.valueOf(((digest[0] & 255) << 24) + ((digest[1] & 255) << 16) + ((digest[2] & 255) << 8) + (digest[3] & 255));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public boolean mo272a(String str) {
        if (this.f242e) {
            return this.f241d.contains(m387c(str));
        }
        return this.f241d.contains(str);
    }

    /* renamed from: b */
    public void mo273b(String str) {
        if (this.f242e) {
            this.f241d.add(m387c(str));
        } else {
            this.f241d.add(str);
        }
    }

    /* renamed from: a */
    public void mo271a() {
        StringBuilder sb = new StringBuilder();
        for (Object obj : this.f241d) {
            sb.append(obj);
            if (sb.length() > 0) {
                sb.append(",");
            }
        }
        System.out.println(sb.toString());
    }

    public String toString() {
        if (this.f242e) {
            byte[] bArr = new byte[(this.f241d.size() * 4)];
            int i = 0;
            for (Integer num : this.f241d) {
                int intValue = num.intValue();
                int i2 = i + 1;
                bArr[i] = (byte) ((-16777216 & intValue) >> 24);
                int i3 = i2 + 1;
                bArr[i2] = (byte) ((16711680 & intValue) >> 16);
                int i4 = i3 + 1;
                bArr[i3] = (byte) ((65280 & intValue) >> 8);
                i = i4 + 1;
                bArr[i4] = (byte) (intValue & 255);
            }
            return new String(Base64.encode(bArr, 0));
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : this.f241d) {
            if (sb.length() > 0) {
                sb.append(f238b);
            }
            sb.append(obj.toString());
        }
        return sb.toString();
    }
}
