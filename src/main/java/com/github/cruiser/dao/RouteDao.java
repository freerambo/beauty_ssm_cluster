package com.github.cruiser.dao;

import com.github.cruiser.entity.Route;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteDao {

    /**
     * 插入
     *
     * @return
     */
    int insert(Route route);

	/**
     *
     * @return
     */
    void update(Route route);

    /**
     * 通过id查询商户路由
     * @param merchantId
     * @return
     */
    List<Route> fetchOneByMerchantId(@Param("merchantId") long merchantId);

	/**
	 * 通过商户编号查询商户路由
	 * @param merchantCode
	 * @return
	 */
    List<Route> fetchOneByMerchantCode(@Param("merchantCode") String
            merchantCode);

    /**
     * 查询商户所有路由
     * @param merchantId
     * @return
     */
    List<Route> queryByMerchantId(@Param("merchantId") long merchantId);

    /**
     * 根据偏移量查询列表
     * @param offset
     * @param limit
     * @return
     */
    List<Route> queryAll(@Param("offset") int offset, @Param("limit") int
            limit);
}
