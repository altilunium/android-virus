package com.umeng.analytics.pro;

import java.nio.ByteBuffer;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/* renamed from: com.umeng.analytics.pro.ar */
public final class TBaseHelper {

    /* renamed from: a */
    private static final Comparator f461a = new C0079a();

    private TBaseHelper() {
    }

    /* renamed from: a */
    public static int m547a(Object obj, Object obj2) {
        if (obj instanceof Comparable) {
            return m546a((Comparable) obj, (Comparable) obj2);
        }
        if (obj instanceof List) {
            return m550a((List) obj, (List) obj2);
        }
        if (obj instanceof Set) {
            return m552a((Set) obj, (Set) obj2);
        }
        if (obj instanceof Map) {
            return m551a((Map) obj, (Map) obj2);
        }
        if (obj instanceof byte[]) {
            return m555a((byte[]) obj, (byte[]) obj2);
        }
        throw new IllegalArgumentException("Cannot compare objects of type " + obj.getClass());
    }

    /* renamed from: a */
    public static int m554a(boolean z, boolean z2) {
        return Boolean.valueOf(z).compareTo(Boolean.valueOf(z2));
    }

    /* renamed from: a */
    public static int m542a(byte b, byte b2) {
        if (b < b2) {
            return -1;
        }
        if (b2 < b) {
            return 1;
        }
        return 0;
    }

    /* renamed from: a */
    public static int m553a(short s, short s2) {
        if (s < s2) {
            return -1;
        }
        if (s2 < s) {
            return 1;
        }
        return 0;
    }

    /* renamed from: a */
    public static int m544a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        if (i2 < i) {
            return 1;
        }
        return 0;
    }

    /* renamed from: a */
    public static int m545a(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        if (j2 < j) {
            return 1;
        }
        return 0;
    }

    /* renamed from: a */
    public static int m543a(double d, double d2) {
        if (d < d2) {
            return -1;
        }
        if (d2 < d) {
            return 1;
        }
        return 0;
    }

    /* renamed from: a */
    public static int m548a(String str, String str2) {
        return str.compareTo(str2);
    }

    /* renamed from: a */
    public static int m555a(byte[] bArr, byte[] bArr2) {
        int a = m544a(bArr.length, bArr2.length);
        if (a != 0) {
            return a;
        }
        for (int i = 0; i < bArr.length; i++) {
            int a2 = m542a(bArr[i], bArr2[i]);
            if (a2 != 0) {
                return a2;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static int m546a(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    /* renamed from: a */
    public static int m550a(List list, List list2) {
        int a = m544a(list.size(), list2.size());
        if (a != 0) {
            return a;
        }
        for (int i = 0; i < list.size(); i++) {
            int compare = f461a.compare(list.get(i), list2.get(i));
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static int m552a(Set set, Set set2) {
        int a = m544a(set.size(), set2.size());
        if (a != 0) {
            return a;
        }
        Comparator comparator = f461a;
        TreeSet treeSet = new TreeSet(comparator);
        treeSet.addAll(set);
        TreeSet treeSet2 = new TreeSet(comparator);
        treeSet2.addAll(set2);
        Iterator it = treeSet.iterator();
        Iterator it2 = treeSet2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            int compare = f461a.compare(it.next(), it2.next());
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static int m551a(Map map, Map map2) {
        int a = m544a(map.size(), map2.size());
        if (a != 0) {
            return a;
        }
        Comparator comparator = f461a;
        TreeMap treeMap = new TreeMap(comparator);
        treeMap.putAll(map);
        Iterator it = treeMap.entrySet().iterator();
        TreeMap treeMap2 = new TreeMap(comparator);
        treeMap2.putAll(map2);
        Iterator it2 = treeMap2.entrySet().iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Map.Entry entry2 = (Map.Entry) it2.next();
            Comparator comparator2 = f461a;
            int compare = comparator2.compare(entry.getKey(), entry2.getKey());
            if (compare != 0) {
                return compare;
            }
            int compare2 = comparator2.compare(entry.getValue(), entry2.getValue());
            if (compare2 != 0) {
                return compare2;
            }
        }
        return 0;
    }

    /* renamed from: com.umeng.analytics.pro.ar$a */
    /* compiled from: TBaseHelper */
    class C0079a implements Comparator {
        private C0079a() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            if (obj == null && obj2 == null) {
                return 0;
            }
            if (obj == null) {
                return -1;
            }
            if (obj2 == null) {
                return 1;
            }
            if (obj instanceof List) {
                return TBaseHelper.m550a((List) obj, (List) obj2);
            }
            if (obj instanceof Set) {
                return TBaseHelper.m552a((Set) obj, (Set) obj2);
            }
            if (obj instanceof Map) {
                return TBaseHelper.m551a((Map) obj, (Map) obj2);
            }
            if (obj instanceof byte[]) {
                return TBaseHelper.m555a((byte[]) obj, (byte[]) obj2);
            }
            return TBaseHelper.m546a((Comparable) obj, (Comparable) obj2);
        }
    }

    /* renamed from: a */
    public static void m557a(ByteBuffer byteBuffer, StringBuilder sb) {
        byte[] array = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset();
        int position = byteBuffer.position() + arrayOffset;
        int limit = arrayOffset + byteBuffer.limit();
        int i = limit - position > 128 ? position + 128 : limit;
        for (int i2 = position; i2 < i; i2++) {
            if (i2 > position) {
                sb.append(" ");
            }
            sb.append(m556a(array[i2]));
        }
        if (limit != i) {
            sb.append("...");
        }
    }

    /* renamed from: a */
    public static String m556a(byte b) {
        return Integer.toHexString((b | TType.f565a) & 511).toUpperCase().substring(1);
    }

    /* renamed from: a */
    public static byte[] m558a(ByteBuffer byteBuffer) {
        if (m560b(byteBuffer)) {
            return byteBuffer.array();
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        m549a(byteBuffer, bArr, 0);
        return bArr;
    }

    /* renamed from: b */
    public static boolean m560b(ByteBuffer byteBuffer) {
        return byteBuffer.hasArray() && byteBuffer.position() == 0 && byteBuffer.arrayOffset() == 0 && byteBuffer.remaining() == byteBuffer.capacity();
    }

    /* renamed from: a */
    public static int m549a(ByteBuffer byteBuffer, byte[] bArr, int i) {
        int remaining = byteBuffer.remaining();
        System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), bArr, i, remaining);
        return remaining;
    }

    /* renamed from: c */
    public static ByteBuffer m561c(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return null;
        }
        if (m560b(byteBuffer)) {
            return byteBuffer;
        }
        return ByteBuffer.wrap(m558a(byteBuffer));
    }

    /* renamed from: d */
    public static ByteBuffer m562d(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return null;
        }
        ByteBuffer wrap = ByteBuffer.wrap(new byte[byteBuffer.remaining()]);
        if (byteBuffer.hasArray()) {
            System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), wrap.array(), 0, byteBuffer.remaining());
        } else {
            byteBuffer.slice().get(wrap.array());
        }
        return wrap;
    }

    /* renamed from: a */
    public static byte[] m559a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }
}
