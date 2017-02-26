package com.github.cruiser.service.impl;

import com.github.cruiser.dao.SalerDao;
import com.github.cruiser.entity.Saler;
import com.github.cruiser.entity.SalerExample;
import com.github.cruiser.enums.ResultEnum;
import com.github.cruiser.exception.BizException;
import com.github.cruiser.service.SalersService;
import com.github.cruiser.service.SalersService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class SalersServiceImpl implements SalersService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SalerDao salerDao;


    @Override
    public List<Saler> getEntityListByLimit(int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        SalerExample example = new SalerExample();
        List<Saler> list = salerDao.selectByExampleWithRowbounds(example, rowBounds);
        return list;
    }

    @Override
    public Saler getEntityById(long id) {
        return salerDao.selectByPrimaryKey(id);
    }

    @Override
    public void insertEntity(Saler entity) {
        salerDao.insert(entity);
    }

    @Override
    public Saler updateEntity(long id, Saler entity) {
        entity.setSalerId(id);
        salerDao.updateByPrimaryKey(entity);
        return salerDao.selectByPrimaryKey(id);
    }

    @Override
    public Saler updateEntityBySelective(long id, Saler entity) {
        entity.setSalerId(id);
        salerDao.updateByPrimaryKeySelective(entity);
        return salerDao.selectByPrimaryKey(id);
    }

    @Override
    public void deleteEntity(long id) {
        salerDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<Saler> querySalerByName(String salerName,
                                 int limit,
                                 int offset){
        SalerExample example = new SalerExample();
        example.createCriteria().andSalerNameLike(salerName);
        RowBounds rowBounds = new RowBounds(offset, limit);
        return salerDao.selectByExampleWithRowbounds(example, rowBounds);
    }
}
