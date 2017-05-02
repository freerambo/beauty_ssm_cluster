package com.github.cruiser.service.impl;

import com.github.cruiser.dao.OrderDao;
import com.github.cruiser.entity.Order;
import com.github.cruiser.entity.OrderExample;
import com.github.cruiser.enums.CommonValue;
import com.github.cruiser.enums.CurrencyCode;
import com.github.cruiser.enums.SettleOrderState;
import com.github.cruiser.enums.SettleState;
import com.github.cruiser.service.MerchantsService;
import com.github.cruiser.service.OrdersService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrdersServiceImpl implements OrdersService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private MerchantsService merchantsService;


    @Override
    public List<Order> getEntityListByLimit(int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        OrderExample example = new OrderExample();
        List<Order> list = orderDao.selectByExampleWithRowbounds(example, rowBounds);
        return list;
    }

    @Override
    public Order getEntityById(long id) {
        return orderDao.selectByPrimaryKey(id);
    }

    @Override
    public void insertEntity(Order entity) {
        orderDao.insert(entity);
    }

    @Override
    public Order updateEntity(long id, Order entity) {
        return null;
    }

    @Override
    public Order updateEntityBySelective(long id, Order entity) {
        return null;
    }

    @Override
    public void deleteEntity(long id) {
        return;
    }

    @Override
    public void insertEntityByParams(Map<String, String> paramsMap) {
        Order order = new Order();
        order.setOrderNumber(paramsMap.get("orderId"));
        order.setMerchantCode(paramsMap.get("merchantCode"));
        order.setMerchantName(paramsMap.get("merName"));
        order.setTxnAmt(new BigDecimal(paramsMap.get("txnAmt"))
                .divide(new BigDecimal("100")));
        order.setCurrencyCode(
                paramsMap.get("currencyCode").equals(
                        CurrencyCode.SHANG_LIAN_CNY_CODE.getValue()) ?
                        CurrencyCode.YIN_MA_TONG_CNY_CODE.getValue() :
                        CurrencyCode.DEFAULT_ERROR.getValue());
        order.setUpstreamOrderState(paramsMap.get("orderStau"));
        order.setSettleOrderState(SettleOrderState.UNCONFIRM.getValue());
        order.setSettleState(SettleState.UN_CLEARED.getValue());
        order.setRemark(paramsMap.get("txnType") + "|"
                + paramsMap.get("merId") + "|"
                + paramsMap.get("orderQid") + "|"
                + paramsMap.get("orderStau"));
        order.setGmtCreate(new Date());
        order.setGmtModified(new Date());
        order.setModifiedPerson(CommonValue.SYSTEM_USER.getValue());
        this.insertEntity(order);
    }

    @Override
    public Order updateEntityByParams(long id, String settleOrderState,
                                      String settleState, BigDecimal settleAmt,
                                      Date settleTime) {
        Order entity = new Order();
        entity.setOrderId(id);
        entity.setSettleOrderState(settleOrderState);
        entity.setSettleState(settleState);
        entity.setSettleAmt(settleAmt);
        entity.setSettleTime(settleTime);
        orderDao.updateByPrimaryKeySelective(entity);
        return orderDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Order> getEntityListByOrderNumber(String orderNumber) {
        OrderExample example = new OrderExample();
        example.createCriteria().andOrderNumberEqualTo(orderNumber);
        return orderDao.selectByExample(example);
    }

    @Override
    public List<Order> getEntityListByOrder(int offset, int limit, String orderBy) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        OrderExample example = new OrderExample();
        example.setOrderByClause(orderBy);
        List<Order> list = orderDao.selectByExampleWithRowbounds(example, rowBounds);
        return list;
    }

    public List<Order> getEntityListByMerchantCode(int offset, int limit, String settleOrderState, String merchantCode) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        OrderExample example = new OrderExample();
        example.createCriteria().andSettleStateEqualTo(settleOrderState).andMerchantCodeEqualTo(merchantCode);
        List<Order> list = orderDao.selectByExampleWithRowbounds(example, rowBounds);
        return list;
    }
}
