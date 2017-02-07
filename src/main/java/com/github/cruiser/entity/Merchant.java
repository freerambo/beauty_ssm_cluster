package com.github.cruiser.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.cruiser.util.CustomDateSerializer;

import java.util.Date;

/**
 * 商户
 *
 * @author Qiming Gu
 */
public class Merchant {

    private long merchantId;

    private String merchantCode;

    private String merchantName;

    private String openId;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;

    private boolean enable;

    @Override
    public String toString() {
        return "Merchant [merchantId=" + merchantId
                + ", merchantCode=" + merchantCode + ", merchantName=" + merchantName
                + ", openId=" + openId + ", createTime=" + createTime
                + ", enable=" + enable
                + "]";
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
