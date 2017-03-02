package com.github.cruiser.dao;

import com.github.cruiser.entity.Merchant;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MerchantDao extends MerchantMapper {

    /**
     * 按照商户编号进行查询
     * @param merchantCode
     * @return
     */
    Merchant queryByMerchantCode(
            @Param("merchantCode") String merchantCode);

    /**
     * 按照商户名称进行模糊查询
     * 提供偏移量查询功能
     * 创建时间倒序
     *
     * @param offset
     * @param limit
     * @param merchantName
     * @return
     */
    List<Merchant> queryByMerchantName(
            @Param("offset") int offset,
            @Param ("limit") int limit,
            @Param("merchantName") String merchantName);

    /**
     * 启用对象
     * @param merchantId
     */
    void enableById(@Param("merchantId") long merchantId);

    /**
     * 禁用对象
     * @param merchantId
     */
    void disableById(@Param("merchantId") long merchantId);

}
