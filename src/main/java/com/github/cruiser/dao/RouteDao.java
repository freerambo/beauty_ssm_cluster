package com.github.cruiser.dao;

import com.github.cruiser.entity.Route;
import com.github.cruiser.enums.GatewayType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteDao extends RouteMapper {

    /**
     * 通过id查询商户路由
     *
     * @param merchantId
     * @return
     */
    List<Route> fetchOneByMerchantId(@Param("merchantId") long merchantId);

    /**
     * 通过商户id（非商户编号）得到该网关类型中最高优先级的路由记录
     *
     * @param merchantId
     * @param gatewayType
     * @return
     */
    Route fetchHightestPriorityByMerchantId(@Param("merchantId") long merchantId, GatewayType gatewayType);

    /**
     * 通过商户编号查询商户路由
     *
     * @param merchantCode
     * @return
     */
    List<Route> fetchOneByMerchantCode(@Param("merchantCode") String
                                               merchantCode);

    /**
     * 查询商户所有路由
     *
     * @param merchantId
     * @return
     */
    List<Route> queryByMerchantId(@Param("merchantId") long merchantId,
                                  @Param("offset") int offset,
                                  @Param("limit") int limit);

    /**
     * 根据偏移量查询列表
     *
     * @param offset
     * @param limit
     * @return
     */
    List<Route> queryAll(@Param("offset") int offset, @Param("limit") int
            limit);

    List<Route> queryByUpstreamId(@Param("upstreamId") long upstreamId,
                                  @Param("offset") int offset,
                                  @Param("limit") int limit);
}
