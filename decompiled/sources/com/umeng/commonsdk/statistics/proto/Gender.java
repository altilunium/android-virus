package com.umeng.commonsdk.statistics.proto;

import com.umeng.analytics.pro.TEnum;

public enum Gender implements TEnum {
    MALE(0),
    FEMALE(1),
    UNKNOWN(2);
    
    private final int value;

    private Gender(int i) {
        this.value = i;
    }

    @Override // com.umeng.analytics.pro.TEnum
    public int getValue() {
        return this.value;
    }

    public static Gender findByValue(int i) {
        if (i == 0) {
            return MALE;
        }
        if (i == 1) {
            return FEMALE;
        }
        if (i != 2) {
            return null;
        }
        return UNKNOWN;
    }
}
