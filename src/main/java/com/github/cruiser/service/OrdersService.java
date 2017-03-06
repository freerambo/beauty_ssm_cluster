package com.github.cruiser.service;

import com.github.cruiser.entity.EventMsg;
import com.github.cruiser.entity.Order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OrdersService extends CommonResourceService<Order> {

    Order updateEntityByParams(long id,
                               String settleOrderState,
                               String settleState,
                               BigDecimal settleAmt,
                               Date settleTime);

    List<Order> getEntityListByOrder(int offset,
                                     int limit,
                                     String orderBy);
}
