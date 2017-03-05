package com.github.cruiser.dao;


import com.github.cruiser.entity.Route;
import com.github.cruiser.entity.RouteExample;
import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

public class RouteDaoTest extends AbstractDaoTest {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RouteDao dao;

    private Route entity;
    @Before
    public void initEntity(){
        entity = new Route();
        entity.setUpstreamId(1L);
        entity.setMerchantId(1L);
        entity.setPriority(51);
        entity.setGatewayType("W1");
        entity.setQueryString("009581053112159");
        entity.setGmtCreate(new Date());
        entity.setGmtModified(new Date());
        entity.setModifiedPerson("admin");

    }

    @Override
    public void testInsertEntity() {
        int cnt = dao.countByExample(new RouteExample());
        Assert.assertEquals(1, dao.insert(entity));
        Assert.assertEquals(cnt + 1, dao.countByExample(new RouteExample()));

    }

    @Override
    public void testUpdateEntity() {
        Assert.assertEquals(1, dao.insert(entity));
        //需要直接指定date对象，不然时间值会一直变
        Date date = new Date(1000000L);
        LOG.debug("**date: "+date);
        entity.setGmtModified(date);
        dao.updateByPrimaryKey(entity);
        Route updatedEntity = dao.selectByPrimaryKey(entity.getRouteId());
        Assert.assertEquals(date, updatedEntity.getGmtModified());
    }

    @Override
    public void testUpdateEntityBySelective() {

    }

    @Override
    public void testDeleteEntity() {
        int cnt = dao.countByExample(new RouteExample());
        Assert.assertEquals(1, dao.insert(entity));
        Assert.assertEquals(cnt + 1, dao.countByExample(new RouteExample()));
        Assert.assertNotNull(entity.getRouteId());
        Assert.assertEquals(1, dao.deleteByPrimaryKey(entity.getRouteId()));
        Assert.assertEquals(cnt, dao.countByExample(new RouteExample()));

    }
}
