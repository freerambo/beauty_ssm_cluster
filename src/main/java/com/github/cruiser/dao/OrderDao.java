package com.github.cruiser.dao;

import java.util.List;

import com.github.cruiser.entity.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderDao extends OrderMapper{

    /**
     * 根据用户手机号查询订单
     *
     * @param userPhone
     * @return
     */
    List<Order> queryByUserPhone(@Param("userPhone") long userPhone);

}
