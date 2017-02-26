package com.github.cruiser.service.impl;

import com.github.cruiser.dao.UpstreamDao;
import com.github.cruiser.entity.Route;
import com.github.cruiser.entity.Upstream;
import com.github.cruiser.entity.UpstreamExample;
import com.github.cruiser.service.RoutesService;
import com.github.cruiser.service.UpstreamsService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpstreamsServiceImpl implements UpstreamsService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UpstreamDao upstreamDao;
    @Autowired
    private RoutesService routesService;


    @Override
    public List<Upstream> getEntityListByLimit(int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        UpstreamExample example = new UpstreamExample();
        List<Upstream> list = upstreamDao.selectByExampleWithRowbounds(example, rowBounds);
        return list;
    }

    @Override
    public Upstream getEntityById(long id) {
        return upstreamDao.selectByPrimaryKey(id);
    }

    @Override
    public void insertEntity(Upstream entity) {
        upstreamDao.insert(entity);
    }

    @Override
    public Upstream updateEntity(long id, Upstream entity) {
        entity.setUpstreamId(id);
        upstreamDao.updateByPrimaryKey(entity);
        return upstreamDao.selectByPrimaryKey(id);
    }

    @Override
    public Upstream updateEntityBySelective(long id, Upstream entity) {
        entity.setUpstreamId(id);
        upstreamDao.updateByPrimaryKeySelective(entity);
        return upstreamDao.selectByPrimaryKey(id);
    }

    @Override
    public void deleteEntity(long id) {
        upstreamDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<Route> getRouteEntityByUpstreamId(long id, int limit, int offset) {
        return routesService.getRouteEntityByUpstreamId(id, offset, limit);
    }

    @Override
    public Route getRouteEntityByRouteId(long upstreamId, long routeId) {
        Route route = routesService.getEntityById(routeId);
        if (upstreamId != route.getUpstreamId()) {
            return null;
        }
        return route;
    }

    @Override
    public List<Upstream> queryUpstreamByName(String upstreamName,
                                              int limit,
                                              int offset) {
        UpstreamExample example = new UpstreamExample();
        example.createCriteria().andUpstreamNameLike(upstreamName);
        RowBounds rowBounds = new RowBounds(offset, limit);
        return upstreamDao.selectByExampleWithRowbounds(example, rowBounds);
    }
}
