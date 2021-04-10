package com.own.bless.soy;

/* renamed from: com.own.bless.soy.c */
/* compiled from: Base64 */
class C0009c extends AbstractC0005b {

    /* renamed from: f */
    private static final int[] f15f = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    /* renamed from: g */
    private static final int[] f16g = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    /* renamed from: c */
    private int f17c;

    /* renamed from: d */
    private int f18d;

    /* renamed from: e */
    private final int[] f19e;

    private C0009c(int flags, byte[] output) {
        this.f8a = output;
        this.f19e = (flags & 8) == 0 ? f15f : f16g;
        this.f17c = 0;
        this.f18d = 0;
    }

    /* renamed from: a */
    public boolean mo35a(byte[] input, int offset, int len, boolean finish) {
        if (this.f17c == 6) {
            return false;
        }
        int p = offset;
        int len2 = len + offset;
        int state = this.f17c;
        int value = this.f18d;
        int op = 0;
        byte[] output = this.f8a;
        int[] alphabet = this.f19e;
        while (p < len2) {
            if (state == 0) {
                while (p + 4 <= len2) {
                    int i = (alphabet[input[p] & 255] << 18) | (alphabet[input[p + 1] & 255] << 12) | (alphabet[input[p + 2] & 255] << 6) | alphabet[input[p + 3] & 255];
                    value = i;
                    if (i < 0) {
                        break;
                    }
                    output[op + 2] = (byte) value;
                    output[op + 1] = (byte) (value >> 8);
                    output[op] = (byte) (value >> 16);
                    op += 3;
                    p += 4;
                }
                if (p >= len2) {
                    break;
                }
            }
            int p2 = p + 1;
            int d = alphabet[input[p] & 255];
            if (state != 0) {
                if (state != 1) {
                    if (state != 2) {
                        if (state != 3) {
                            if (state != 4) {
                                if (state == 5 && d != -1) {
                                    this.f17c = 6;
                                    return false;
                                }
                            } else if (d == -2) {
                                state++;
                            } else if (d != -1) {
                                this.f17c = 6;
                                return false;
                            }
                        } else if (d >= 0) {
                            value = (value << 6) | d;
                            output[op + 2] = (byte) value;
                            output[op + 1] = (byte) (value >> 8);
                            output[op] = (byte) (value >> 16);
                            op += 3;
                            state = 0;
                        } else if (d == -2) {
                            output[op + 1] = (byte) (value >> 2);
                            output[op] = (byte) (value >> 10);
                            op += 2;
                            state = 5;
                        } else if (d != -1) {
                            this.f17c = 6;
                            return false;
                        }
                    } else if (d >= 0) {
                        value = (value << 6) | d;
                        state++;
                    } else if (d == -2) {
                        output[op] = (byte) (value >> 4);
                        state = 4;
                        op++;
                    } else if (d != -1) {
                        this.f17c = 6;
                        return false;
                    }
                } else if (d >= 0) {
                    value = (value << 6) | d;
                    state++;
                } else if (d != -1) {
                    this.f17c = 6;
                    return false;
                }
            } else if (d >= 0) {
                value = d;
                state++;
            } else if (d != -1) {
                this.f17c = 6;
                return false;
            }
            p = p2;
        }
        if (!finish) {
            this.f17c = state;
            this.f18d = value;
            this.f9b = op;
            return true;
        } else if (state != 1) {
            if (state == 2) {
                output[op] = (byte) (value >> 4);
                op++;
            } else if (state == 3) {
                int op2 = op + 1;
                output[op] = (byte) (value >> 10);
                op = op2 + 1;
                output[op2] = (byte) (value >> 2);
            } else if (state == 4) {
                this.f17c = 6;
                return false;
            }
            this.f17c = state;
            this.f9b = op;
            return true;
        } else {
            this.f17c = 6;
            return false;
        }
    }
}
