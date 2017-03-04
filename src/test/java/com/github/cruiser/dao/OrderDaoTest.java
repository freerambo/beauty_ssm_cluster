package com.github.cruiser.dao;


import java.math.BigDecimal;
import java.util.Date;

import com.github.cruiser.entity.Order;
import com.github.cruiser.entity.OrderExample;
import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDaoTest extends AbstractDaoTest {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderDao dao;

    private Order entity;
    @Before
    public void initEntity(){
        entity = new Order();
        entity.setOrderNumber("SY000000000000000003");
        entity.setMerchantCode("W0144005331000001");
        entity.setMerchantName("测试很长测试很长测试很长测试很长测试很长测试很长测试很长测试很长测试很长测试很长测试很长测试很长测试");
        entity.setTxnAmt(new BigDecimal("123456.0987"));
        entity.setCurrencyCode("CNY");
        entity.setUpstreamOrderState("SYCODE00");
        entity.setSettleOrderState("01");
        entity.setSettleState("01");
        entity.setSettleAmt(new BigDecimal("43.45678"));
        entity.setSettleTime(null);
        entity.setGmtCreate(new Date());
        entity.setGmtModified(new Date());
        entity.setModifiedPerson("tester");

    }

    @Override
    public void testInsertEntity() {
        int cnt = dao.countByExample(new OrderExample());
        Assert.assertEquals(1, dao.insert(entity));
        Assert.assertEquals(cnt + 1, dao.countByExample(new OrderExample()));

    }

    @Override
    public void testUpdateEntity() {
        Assert.assertEquals(1, dao.insert(entity));
        //需要直接指定date对象，不然时间值会一直变
        Date date = new Date(1000000L);
        LOG.debug("**date: "+date);
        entity.setGmtModified(date);
        dao.updateByPrimaryKey(entity);
        Order updatedEntity = dao.selectByPrimaryKey(entity.getOrderId());
        Assert.assertEquals(date, updatedEntity.getGmtModified());
    }

    @Override
    public void testUpdateEntityBySelective() {

    }

    @Override
    public void testDeleteEntity() {
        int cnt = dao.countByExample(new OrderExample());
        Assert.assertEquals(1, dao.insert(entity));
        Assert.assertEquals(cnt + 1, dao.countByExample(new OrderExample()));
        Assert.assertNotNull(entity.getOrderId());
        Assert.assertEquals(1, dao.deleteByPrimaryKey(entity.getOrderId()));
        Assert.assertEquals(cnt, dao.countByExample(new OrderExample()));

    }
}
