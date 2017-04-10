package com.github.cruiser.enums;

/**
 * 网关类型的枚举类
 * Created by think on 2017/2/19.
 */
public enum SettleOrderState {
    UNCONFIRM("00"), CONFIRM("01");

    private String value;

    SettleOrderState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
