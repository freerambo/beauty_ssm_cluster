package com.github.cruiser.service.impl;

import com.github.cruiser.dao.MerchantDao;
import com.github.cruiser.entity.Route;
import com.github.cruiser.entity.Merchant;
import com.github.cruiser.entity.MerchantExample;
import com.github.cruiser.service.RoutesService;
import com.github.cruiser.service.MerchantsService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantsServiceImpl implements MerchantsService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MerchantDao merchantDao;
    @Autowired
    private RoutesService routesService;


    @Override
    public List<Merchant> getEntityListByLimit(int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        MerchantExample example = new MerchantExample();
        List<Merchant> list = merchantDao.selectByExampleWithRowbounds(example, rowBounds);
        return list;
    }

    @Override
    public Merchant getEntityById(long id) {
        return merchantDao.selectByPrimaryKey(id);
    }

    @Override
    public void insertEntity(Merchant entity) {
        merchantDao.insert(entity);
    }

    @Override
    public Merchant updateEntity(long id, Merchant entity) {
        entity.setMerchantId(id);
        merchantDao.updateByPrimaryKey(entity);
        return merchantDao.selectByPrimaryKey(id);
    }

    @Override
    public Merchant updateEntityBySelective(long id, Merchant entity) {
        entity.setMerchantId(id);
        merchantDao.updateByPrimaryKeySelective(entity);
        return merchantDao.selectByPrimaryKey(id);
    }

    @Override
    public void deleteEntity(long id) {
        merchantDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<Route> getRouteEntityByMerchantId(long id, int limit, int offset) {
        return routesService.getRouteEntityByMerchantId(id, offset, limit);
    }

    @Override
    public Route getRouteEntityByRouteId(long merchantId, long routeId) {
        Route route = routesService.getEntityById(routeId);
        if (merchantId != route.getMerchantId()) {
            return null;
        }
        return route;
    }

    @Override
    public List<Merchant> queryBySelective(String merchantCode,
                                           String merchantType,
                                           String bossCertificateNumber,
                                           String settlementAccount,
                                           String salerCode, int limit, int offset) {
        MerchantExample example = new MerchantExample();
        MerchantExample.Criteria criteria = example.createCriteria();

        if (null != merchantCode && !merchantCode.equals("")) {
            criteria.andMerchantCodeEqualTo(merchantCode);
        }
        if (null != merchantType && !merchantType.equals("")) {
            criteria.andMerchantTypeEqualTo(merchantType);
        }
        if (null != bossCertificateNumber && !bossCertificateNumber.equals
                ("")) {
            criteria.andBossCertificateNumberEqualTo(bossCertificateNumber);
        }
        if (null != settlementAccount && !settlementAccount.equals("")) {
            criteria.andSettlementAccountEqualTo(settlementAccount);
        }
        if (null != salerCode && !salerCode.equals("")) {
            criteria.andSalerCodeEqualTo(salerCode);
        }
        return merchantDao.selectByExampleWithRowbounds(example, new RowBounds(offset, limit));
    }

}
