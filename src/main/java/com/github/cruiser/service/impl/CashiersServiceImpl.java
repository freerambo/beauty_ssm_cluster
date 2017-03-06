package com.github.cruiser.service.impl;

import com.github.cruiser.dao.CashierDao;
import com.github.cruiser.entity.Cashier;
import com.github.cruiser.entity.CashierExample;
import com.github.cruiser.enums.ResultEnum;
import com.github.cruiser.exception.BizException;
import com.github.cruiser.service.CashiersService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashiersServiceImpl implements CashiersService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CashierDao cashierDao;


    @Override
    public List<Cashier> getEntityListByLimit(long merchantId, int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        CashierExample example = new CashierExample();
        example.createCriteria().andMerchantIdEqualTo(merchantId);
        List<Cashier> list = cashierDao.selectByExampleWithRowbounds(example, rowBounds);
        return list;
    }

    @Override
    public Cashier getEntityById(long merchantId, long cashierId) {
        CashierExample example = new CashierExample();
        example.createCriteria()
                .andMerchantIdEqualTo(merchantId)
                .andCashierIdEqualTo(cashierId);
        return cashierDao.selectByExample(example).get(0);
    }

    @Override
    public void insertEntity(Cashier entity, long merchantId) {
        if (merchantId != entity.getMerchantId()) {
            throw new BizException(ResultEnum.PARAM_ERROR.getResultMsg());
        }
        cashierDao.insert(entity);

    }

    @Override
    public Cashier updateEntity(long merchantId, long cashierId, Cashier entity) {
        if (merchantId != entity.getMerchantId() || cashierId != entity.getCashierId()) {
            throw new BizException(ResultEnum.PARAM_ERROR.getResultMsg());
        }
        cashierDao.updateByPrimaryKey(entity);
        return cashierDao.selectByPrimaryKey(cashierId);
    }

    @Override
    public Cashier updateEntityBySelective(long merchantId, long cashierId, Cashier entity) {
        if (merchantId != entity.getMerchantId() || cashierId != entity.getCashierId()) {
            throw new BizException(ResultEnum.PARAM_ERROR.getResultMsg());
        }
        cashierDao.updateByPrimaryKeySelective(entity);
        return cashierDao.selectByPrimaryKey(cashierId);
    }

    @Override
    public void deleteEntity(long merchantId, long cashierId) {
        Cashier entity = getEntityById(merchantId, cashierId);
        if (null == entity) {
            throw new BizException(ResultEnum.PARAM_ERROR.getResultMsg());
        }
        cashierDao.deleteByPrimaryKey(cashierId);

    }

    @Override
    public Boolean signIn(long merchantId, String openId) {
        List<Cashier> list = getEntityListByLimit(merchantId, 0, 50);
        for (Cashier one: list){
            if (one.getOpenId().equals(openId)){
                one.setIsOncall(true);
                cashierDao.updateByPrimaryKey(one);
                return true;
            }
        }
        return false;
    }
}
