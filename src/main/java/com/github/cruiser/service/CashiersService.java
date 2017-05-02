package com.github.cruiser.service;

import com.github.cruiser.entity.Cashier;

import java.util.List;

/**
 * Created by think on 2017/3/5.
 */
public interface CashiersService {
    List<Cashier> getEntityListByLimit(long merchantId, int offset, int limit);

    Cashier getEntityById(long merchantId, long cashierId);

    Cashier getActiveEntityById(long merchantId);

    void insertEntity(Cashier entity, long merchantId);

    Cashier updateEntity(long merchantId, long cashierId, Cashier entity);

    Cashier updateEntityBySelective(long merchantId, long cashierId, Cashier entity);

    void deleteEntity(long merchantId, long cashierId);

    Boolean signIn(long merchantId, String openId);
}
