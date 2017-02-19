package com.github.cruiser.enums;

/**
 * 网关类型的枚举类
 * Created by think on 2017/2/19.
 */
public enum GatewayType {
    WEIXIN_GATEWAY("WX"), ALIPAY_GATEWAY("AL");

    private String value;

    GatewayType(String gatewayType) {
        this.value = gatewayType;
    }

    public String getValue() {
        return value;
    }
}
