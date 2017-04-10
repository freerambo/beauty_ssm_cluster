package com.github.cruiser.enums;

/**
 * 网关类型的枚举类
 * Created by think on 2017/2/19.
 */
public enum CommonValue {
    SYSTEM_USER("SYSTEM");

    private String value;

    CommonValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
