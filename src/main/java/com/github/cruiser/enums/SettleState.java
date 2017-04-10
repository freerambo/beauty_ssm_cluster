package com.github.cruiser.enums;

/**
 * 网关类型的枚举类
 * Created by think on 2017/2/19.
 */
public enum SettleState {
    CLEARED("01"), UN_CLEARED("00");

    private String value;

    SettleState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
