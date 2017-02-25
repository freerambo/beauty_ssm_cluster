package com.github.cruiser.service;

import com.github.cruiser.entity.AbstractEntity;

import java.util.List;

public interface CommonResourceService<T extends AbstractEntity> {

    /**
     * 根据偏移量查询实体列表
     *
     * @param offset
     * @param limit
     * @return
     */
    List<T> getEntityListByLimit(int offset, int limit);

    /**
     * 获取实体记录
     *
     * @param id
     */
    T getEntityById(long id);

    /**
     * 插入实体记录
     *
     * @param entity
     */
    void insertEntity(T entity);

    /**
     * 更新整条实体记录
     *
     * @param id
     */
    T updateEntity(long id, T entity);

    /**
     * 更新部分实体记录
     *
     * @param id
     */
    T updateEntityBySelective(long id, T entity);

    /**
     * 删除实体记录
     *
     * @param id
     */
    void deleteEntity(long id);

}
