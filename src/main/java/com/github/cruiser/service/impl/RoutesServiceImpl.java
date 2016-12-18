package com.github.cruiser.service.impl;

import com.github.cruiser.dao.RouteDao;
import com.github.cruiser.entity.Route;
import com.github.cruiser.service.RoutesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutesServiceImpl implements RoutesService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RouteDao routeDao;
	/*@Autowired
	private RedisCache cache;*/

	@Override
	public List<Route> getRoutesList(int offset, int limit) {
		List<Route> result = routeDao.queryAll(offset, limit);
		return result;
	}

	@Override
	public Route getHightestPriorityByCode(String merchantCode) {
		List<Route> result = routeDao.fetchOneByMerchantCode(merchantCode);
		return result.size()==1? result.get(0):null;
	}

	@Override
	public Route getHightestPriorityById(long merchantId) {
		List<Route> result = routeDao.fetchOneByMerchantId(merchantId);
		return result.size()==1? result.get(0):null;
	}
}
