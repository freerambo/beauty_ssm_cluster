package com.github.cruiser.service.impl;

import com.github.cruiser.dao.MerchantDao;
import com.github.cruiser.dao.RouteDao;
import com.github.cruiser.dao.UpstreamDao;
import com.github.cruiser.entity.Merchant;
import com.github.cruiser.entity.Route;
import com.github.cruiser.entity.Upstream;
import com.github.cruiser.enums.GatewayType;
import com.github.cruiser.enums.ResultEnum;
import com.github.cruiser.exception.BizException;
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
    private UpstreamDao upstreamDao;
    @Autowired
    private MerchantDao merchantDao;
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
    public String getHightestPriorityByCode(String merchantCode,
                                           GatewayType gatewayType) {
        Merchant merchant = merchantDao.queryByMerchantCode(merchantCode);
        if (null!=merchant){
            LOG.debug("商户号不存在："+merchantCode);
            throw new BizException(ResultEnum.RESOURCE_NOT_EXIST.getMsg());
        }
        return getHightestPriorityById(merchant.getMerchantId(), gatewayType);
    }

    @Override
    public String getHightestPriorityById(long merchantId,
                                         GatewayType gatewayType) {
        Route route = routeDao.fetchHightestPriorityByMerchantId(merchantId,
                gatewayType);
        if(null!=route){
            LOG.debug("路由记录不存在："+merchantId);
            throw new BizException(ResultEnum.RESOURCE_NOT_EXIST.getMsg());
        }
        Upstream upstream = upstreamDao.selectByPrimaryKey(route.getUpstreamId());
        if(null!=upstream){
            LOG.debug("上游记录不存在："+route.getUpstreamId());
            throw new BizException(ResultEnum.RESOURCE_NOT_EXIST.getMsg());
        }
        if (GatewayType.WEIXIN_GATEWAY.equals(gatewayType)){
            return upstream.getWeixinGatewayUrl() + route.getQueryString();
        }else if (GatewayType.ALIPAY_GATEWAY.equals(gatewayType)){
            return upstream.getAlipayGatewayUrl() + route.getQueryString();
        }else{
            throw new BizException(ResultEnum.RESOURCE_NOT_EXIST.getMsg());
        }
    }
}
