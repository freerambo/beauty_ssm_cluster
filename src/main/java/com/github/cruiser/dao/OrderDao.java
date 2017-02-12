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

	
    /**
     * 根据偏移量查询订单列表
     * @param offset
     * @param limit
     * @return
     */
    List<Order> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
