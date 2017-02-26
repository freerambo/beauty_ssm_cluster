package com.github.cruiser.service;

import com.github.cruiser.entity.Route;
import com.github.cruiser.enums.GatewayType;

import java.util.List;

public interface RoutesService extends CommonResourceService<Route> {

    /**
     * 获取优先级最高的路由记录
     *
     * @param merchantId
     */
    String getHightestPriorityById(long merchantId,
                                   GatewayType gatewayType);

    /**
     * 获取优先级最高的路由记录
     *
     * @param merchantCode
     */
    String getHightestPriorityByCode(String merchantCode,
                                     GatewayType gatewayType);

    List<Route> getRouteEntityByMerchantId(long merchantId,
                                           int offset,
                                           int limit);

    List<Route> getRouteEntityByUpstreamId(long upstreamId,
                                           int offset,
                                           int limit);
}
