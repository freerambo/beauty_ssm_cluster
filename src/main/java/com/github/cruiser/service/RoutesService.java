package com.github.cruiser.service;

import com.github.cruiser.entity.Route;

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
	Route getHightestPriorityById(long merchantId);

	/**
	 * 获取优先级最高的路由记录
	 *
	 * @param merchantCode
	 */
	Route getHightestPriorityByCode(String merchantCode);

}
