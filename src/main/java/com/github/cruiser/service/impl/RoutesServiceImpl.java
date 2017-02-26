package com.github.cruiser.service.impl;

import com.github.cruiser.dao.MerchantDao;
import com.github.cruiser.dao.RouteDao;
import com.github.cruiser.dao.UpstreamDao;
import com.github.cruiser.entity.Merchant;
import com.github.cruiser.entity.Route;
import com.github.cruiser.entity.RouteExample;
import com.github.cruiser.entity.Upstream;
import com.github.cruiser.enums.GatewayType;
import com.github.cruiser.enums.ResultEnum;
import com.github.cruiser.exception.BizException;
import com.github.cruiser.service.RoutesService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutesServiceImpl implements RoutesService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UpstreamDao upstreamDao;
    @Autowired
    private MerchantDao merchantDao;
    @Autowired
    private RouteDao routeDao;

    @Override
    public String getHightestPriorityByCode(String merchantCode,
                                            GatewayType gatewayType) {
        Merchant merchant = merchantDao.queryByMerchantCode(merchantCode);
        if (null != merchant) {
            LOG.debug("商户号不存在：" + merchantCode);
            throw new BizException(ResultEnum.RESOURCE_NOT_EXIST.getMsg());
        }
        return getHightestPriorityById(merchant.getMerchantId(), gatewayType);
    }

    @Override
    public List<Route> getRouteEntityByMerchantId(long merchantId, int offset, int limit) {
        return routeDao.queryByMerchantId(merchantId, offset, limit);
    }

    @Override
    public List<Route> getRouteEntityByUpstreamId(long upstreamId, int offset, int limit) {
        return routeDao.queryByUpstreamId(upstreamId, offset, limit);
    }

    @Override
    public String getHightestPriorityById(long merchantId,
                                          GatewayType gatewayType) {
        Route route = routeDao.fetchHightestPriorityByMerchantId(merchantId,
                gatewayType);
        if (null != route) {
            LOG.debug("路由记录不存在：" + merchantId);
            throw new BizException(ResultEnum.RESOURCE_NOT_EXIST.getMsg());
        }
        Upstream upstream = upstreamDao.selectByPrimaryKey(route.getUpstreamId());
        if (null != upstream) {
            LOG.debug("上游记录不存在：" + route.getUpstreamId());
            throw new BizException(ResultEnum.RESOURCE_NOT_EXIST.getMsg());
        }
        if (GatewayType.WEIXIN_GATEWAY.equals(gatewayType)) {
            return upstream.getWeixinGatewayUrl() + route.getQueryString();
        } else if (GatewayType.ALIPAY_GATEWAY.equals(gatewayType)) {
            return upstream.getAlipayGatewayUrl() + route.getQueryString();
        } else {
            throw new BizException(ResultEnum.RESOURCE_NOT_EXIST.getMsg());
        }
    }

    @Override
    public List<Route> getEntityListByLimit(int offset, int limit) {
        return routeDao.selectByExampleWithRowbounds(new RouteExample(),
                new RowBounds(offset, limit));
    }

    @Override
    public Route getEntityById(long id) {
        return routeDao.selectByPrimaryKey(id);
    }

    @Override
    public void insertEntity(Route entity) {
        routeDao.insert(entity);
    }

    @Override
    public Route updateEntity(long id, Route entity) {
        entity.setRouteId(id);
        routeDao.updateByPrimaryKey(entity);
        return routeDao.selectByPrimaryKey(id);
    }

    @Override
    public Route updateEntityBySelective(long id, Route entity) {
        entity.setRouteId(id);
        routeDao.updateByPrimaryKeySelective(entity);
        return routeDao.selectByPrimaryKey(id);
    }

    @Override
    public void deleteEntity(long id) {
        routeDao.deleteByPrimaryKey(id);
    }
}
