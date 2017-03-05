package com.github.cruiser.dao;


import com.github.cruiser.entity.Saler;
import com.github.cruiser.entity.SalerExample;
import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

public class SalerDaoTest extends AbstractDaoTest {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SalerDao dao;

    private Saler entity;
    @Before
    public void initEntity(){
        entity = new Saler();
        entity.setSalerCode("S999999999");
        entity.setSalerName("测试客户经理A");
        entity.setSalerMobile("13800000000");
        entity.setSalerReward(new BigDecimal(".5"));
        entity.setRewardTime("04");
        entity.setGmtCreate(new Date());
        entity.setGmtModified(new Date());
        entity.setModifiedPerson("admin");

    }

    @Override
    public void testInsertEntity() {
        int cnt = dao.countByExample(new SalerExample());
        Assert.assertEquals(1, dao.insert(entity));
        Assert.assertEquals(cnt + 1, dao.countByExample(new SalerExample()));

    }

    @Override
    public void testUpdateEntity() {
        Assert.assertEquals(1, dao.insert(entity));
        //需要直接指定date对象，不然时间值会一直变
        Date date = new Date(1000000L);
        LOG.debug("**date: "+date);
        entity.setGmtModified(date);
        dao.updateByPrimaryKey(entity);
        Saler updatedEntity = dao.selectByPrimaryKey(entity.getSalerId());
        Assert.assertEquals(date, updatedEntity.getGmtModified());
    }

    @Override
    public void testUpdateEntityBySelective() {

    }

    @Override
    public void testDeleteEntity() {
        int cnt = dao.countByExample(new SalerExample());
        Assert.assertEquals(1, dao.insert(entity));
        Assert.assertEquals(cnt + 1, dao.countByExample(new SalerExample()));
        Assert.assertNotNull(entity.getSalerId());
        Assert.assertEquals(1, dao.deleteByPrimaryKey(entity.getSalerId()));
        Assert.assertEquals(cnt, dao.countByExample(new SalerExample()));

    }
}
