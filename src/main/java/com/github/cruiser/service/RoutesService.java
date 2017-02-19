package com.github.cruiser.service;

import com.github.cruiser.entity.Route;
import com.github.cruiser.enums.GatewayType;

import java.util.List;

public interface RoutesService {

    /**
     * 根据偏移量查询路由列表
     *
     * @param offset
     * @param limit
     * @return
     */
    List<Route> getRoutesList(int offset, int limit);

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

}
