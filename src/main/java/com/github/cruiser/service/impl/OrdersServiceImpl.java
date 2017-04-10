package com.github.cruiser.service.impl;

import com.github.cruiser.dao.OrderDao;
import com.github.cruiser.entity.Order;
import com.github.cruiser.entity.Order;
import com.github.cruiser.entity.OrderExample;
import com.github.cruiser.service.OrdersService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderDao orderDao;


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
}
