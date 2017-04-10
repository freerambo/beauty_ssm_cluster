package com.github.cruiser.enums;

/**
 * 网关类型的枚举类
 * Created by think on 2017/2/19.
 */
public enum CurrencyCode {
    SHANG_LIAN_CNY_CODE("156"),
    YIN_MA_TONG_CNY_CODE("CNY"),
    DEFAULT_ERROR("999")
    ;

    private String value;

    CurrencyCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
