package com.github.cruiser.service;

import com.github.cruiser.entity.Order;
import com.github.cruiser.entity.Saler;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface SalersService extends CommonResourceService<Saler> {

    List<Saler> querySalerByName(String salerName,
                                 int limit,
                                 int offset);

}
