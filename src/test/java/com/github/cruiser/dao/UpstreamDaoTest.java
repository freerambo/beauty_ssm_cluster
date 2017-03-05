package com.github.cruiser.dao;


import com.github.cruiser.entity.Upstream;
import com.github.cruiser.entity.UpstreamExample;
import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

public class UpstreamDaoTest extends AbstractDaoTest {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UpstreamDao dao;

    private Upstream entity;
    @Before
    public void initEntity(){
        entity = new Upstream();
        entity.setUpstreamName("测试上游2");
        entity.setWeixinGatewayUrl("http://gateway.30pay.net:7018/EpayOnline/fp/");
        entity.setWeixinCost(new BigDecimal("0"));
        entity.setAlipayGatewayUrl("http://gateway.30pay.net:7018/EpayOnline/fp/");
        entity.setAlipayCost(new BigDecimal("0"));
        entity.setIsEnable(true);
        entity.setGmtCreate(new Date());
        entity.setGmtModified(new Date());
        entity.setModifiedPerson("admin");

    }

    @Override
    public void testInsertEntity() {
        int cnt = dao.countByExample(new UpstreamExample());
        Assert.assertEquals(1, dao.insert(entity));
        Assert.assertEquals(cnt + 1, dao.countByExample(new UpstreamExample()));

    }

    @Override
    public void testUpdateEntity() {
        Assert.assertEquals(1, dao.insert(entity));
        //需要直接指定date对象，不然时间值会一直变
        Date date = new Date(1000000L);
        LOG.debug("**date: "+date);
        entity.setGmtModified(date);
        dao.updateByPrimaryKey(entity);
        Upstream updatedEntity = dao.selectByPrimaryKey(entity.getUpstreamId());
        Assert.assertEquals(date, updatedEntity.getGmtModified());
    }

    @Override
    public void testUpdateEntityBySelective() {

    }

    @Override
    public void testDeleteEntity() {
        int cnt = dao.countByExample(new UpstreamExample());
        Assert.assertEquals(1, dao.insert(entity));
        Assert.assertEquals(cnt + 1, dao.countByExample(new UpstreamExample()));
        Assert.assertNotNull(entity.getUpstreamId());
        Assert.assertEquals(1, dao.deleteByPrimaryKey(entity.getUpstreamId()));
        Assert.assertEquals(cnt, dao.countByExample(new UpstreamExample()));

    }
}
