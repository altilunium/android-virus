package p007io.reactivex.internal.util;

/* renamed from: io.reactivex.internal.util.h */
public final class OpenHashSet {

    /* renamed from: a */
    int f1827a;

    /* renamed from: b */
    int f1828b;

    /* renamed from: c */
    int f1829c;

    /* renamed from: d */
    Object[] f1830d;

    public OpenHashSet() {
        this(16, 0.75f);
    }

    public OpenHashSet(int i, float f) {
        int a = Pow2.m1943a(i);
        this.f1827a = a - 1;
        this.f1829c = (int) (((float) a) * f);
        this.f1830d = new Object[a];
    }

    /* renamed from: a */
    public boolean mo1313a(Object obj) {
        Object obj2;
        Object[] objArr = this.f1830d;
        int m = this.f1827a;
        int pos = m1937c(obj.hashCode()) & m;
        Object obj3 = objArr[pos];
        if (obj3 != null) {
            if (obj3.equals(obj)) {
                return false;
            }
            do {
                pos = (pos + 1) & m;
                obj2 = objArr[pos];
                if (obj2 == null) {
                }
            } while (!obj2.equals(obj));
            return false;
        }
        objArr[pos] = obj;
        int i = this.f1828b + 1;
        this.f1828b = i;
        if (i >= this.f1829c) {
            mo1315d();
        }
        return true;
    }

    /* renamed from: e */
    public boolean mo1316e(Object obj) {
        Object obj2;
        Object[] objArr = this.f1830d;
        int m = this.f1827a;
        int pos = m1937c(obj.hashCode()) & m;
        Object obj3 = objArr[pos];
        if (obj3 == null) {
            return false;
        }
        if (obj3.equals(obj)) {
            mo1317f(pos, objArr, m);
            return true;
        }
        do {
            pos = (pos + 1) & m;
            obj2 = objArr[pos];
            if (obj2 == null) {
                return false;
            }
        } while (!obj2.equals(obj));
        mo1317f(pos, objArr, m);
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public boolean mo1317f(int pos, Object[] objArr, int m) {
        Object obj;
        this.f1828b--;
        while (true) {
            pos = (pos + 1) & m;
            while (true) {
                obj = objArr[pos];
                if (obj == null) {
                    objArr[pos] = null;
                    return true;
                }
                int slot = m1937c(obj.hashCode()) & m;
                if (pos <= pos) {
                    if (pos >= slot || slot > pos) {
                        break;
                    }
                    pos = (pos + 1) & m;
                } else {
                    if (pos >= slot && slot > pos) {
                        break;
                    }
                    pos = (pos + 1) & m;
                }
            }
            objArr[pos] = obj;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo1315d() {
        Object[] objArr = this.f1830d;
        int length = objArr.length;
        int i = length << 1;
        int i2 = i - 1;
        Object[] objArr2 = new Object[i];
        int i3 = this.f1828b;
        while (true) {
            int i4 = i3 - 1;
            if (i3 != 0) {
                do {
                    length--;
                } while (objArr[length] == null);
                int c = m1937c(objArr[length].hashCode()) & i2;
                if (objArr2[c] != null) {
                    do {
                        c = (c + 1) & i2;
                    } while (objArr2[c] != null);
                }
                objArr2[c] = objArr[length];
                i3 = i4;
            } else {
                this.f1827a = i2;
                this.f1829c = (int) (((float) i) * 0.75f);
                this.f1830d = objArr2;
                return;
            }
        }
    }

    /* renamed from: c */
    static int m1937c(int x) {
        int h = -1640531527 * x;
        return (h >>> 16) ^ h;
    }

    /* renamed from: b */
    public Object[] mo1314b() {
        return this.f1830d;
    }
}
