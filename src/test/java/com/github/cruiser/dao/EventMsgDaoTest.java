package com.github.cruiser.dao;


import com.github.cruiser.entity.EventMsg;
import com.github.cruiser.entity.EventMsgExample;
import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class EventMsgDaoTest extends AbstractDaoTest {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EventMsgDao dao;

    private EventMsg entity;
    @Before
    public void initEntity(){
        entity = new EventMsg();
        entity.setToUserName("o3g--vnH0WoX9KF0EyTeOa1REKhM");
        entity.setFromUserName("o3g--vnH0WoX9KF0EyTeOa1REKhM");
        entity.setMsgType("event");
        entity.setEvent("TEMPLATESENDJOBFINISH");
        entity.setMsgId(200163840);
        entity.setCallbackStatus("block");
        entity.setPushStatus("01");
        entity.setOrderNumber("SY000000000000000003");
        entity.setGmtCreate(new Date());
        entity.setGmtModified(new Date());

    }

    @Override
    public void testInsertEntity() {
        int cnt = dao.countByExample(new EventMsgExample());
        Assert.assertEquals(1, dao.insert(entity));
        Assert.assertEquals(cnt + 1, dao.countByExample(new EventMsgExample()));

    }

    @Override
    public void testUpdateEntity() {
        Assert.assertEquals(1, dao.insert(entity));
        //需要直接指定date对象，不然时间值会一直变
        Date date = new Date(1000000L);
        LOG.debug("**date: "+date);
        entity.setGmtModified(date);
        dao.updateByPrimaryKey(entity);
        EventMsg updatedEntity = dao.selectByPrimaryKey(entity.getEventMsgId());
        Assert.assertEquals(date, updatedEntity.getGmtModified());
    }

    @Override
    public void testUpdateEntityBySelective() {

    }

    @Override
    public void testDeleteEntity() {
        int cnt = dao.countByExample(new EventMsgExample());
        Assert.assertEquals(1, dao.insert(entity));
        Assert.assertEquals(cnt + 1, dao.countByExample(new EventMsgExample()));
        Assert.assertNotNull(entity.getEventMsgId());
        Assert.assertEquals(1, dao.deleteByPrimaryKey(entity.getEventMsgId()));
        Assert.assertEquals(cnt, dao.countByExample(new EventMsgExample()));

    }
}
