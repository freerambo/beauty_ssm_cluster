package com.github.cruiser.service.impl;

import com.github.cruiser.dao.EventMsgDao;
import com.github.cruiser.entity.*;
import com.github.cruiser.service.EventMsgsService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventMsgsServiceImpl implements EventMsgsService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EventMsgDao eventMsgDao;


    @Override
    public List<EventMsg> getEntityListByLimit(int offset, int limit) {
        RowBounds rowBounds = new RowBounds(0, 10);
        EventMsgExample example = new EventMsgExample();
        List<EventMsg> list = eventMsgDao.selectByExampleWithRowbounds(example, rowBounds);
        return list;
    }

    @Override
    public EventMsg getEntityById(long id) {
        return eventMsgDao.selectByPrimaryKey(id);
    }

    @Override
    public void insertEntity(EventMsg entity) {
        eventMsgDao.insert(entity);
    }

    @Override
    public EventMsg updateEntity(long id, EventMsg entity) {
        return null;
    }

    @Override
    public EventMsg updateEntityBySelective(long id, EventMsg entity) {
        return null;
    }

    @Override
    public void deleteEntity(long id) {
        return;
    }

    @Override
    public List<EventMsg> getEntityListByOpenid(String userOpenid, int limit, int offset) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        EventMsgExample example = new EventMsgExample();
        example.createCriteria().andFromUserNameEqualTo(userOpenid);
        return eventMsgDao.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public List<EventMsg> getEntityListByOrderNumber(String orderNumber, int limit, int offset) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        EventMsgExample example = new EventMsgExample();
        example.createCriteria().andFromUserNameEqualTo(orderNumber);
        return eventMsgDao.selectByExampleWithRowbounds(example, rowBounds);
    }
}
