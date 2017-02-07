package com.github.cruiser.dao;

import com.github.cruiser.entity.EventMsg;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface EventMsgDao {

    /**
     * 按照通知的微信openid进行查询，
     * 提供偏移量查询功能
     * 创建时间倒序
     *
     * @param offset
     * @param limit
     * @param fromUserName
     * @return
     */
    List<EventMsg> queryByFromUserName(@Param("offset") int offset, @Param("limit") int limit,
                            @Param("fromUserName") String fromUserName);

	 /**
     * 按照订单号进行查询，
     * 提供偏移量查询功能
     * 创建时间倒序
     *
     * @param orderId
     * @return
     */
    List<EventMsg> queryByOrderId( @Param("orderId") long orderId);

    /**
     * 插入对象
     *
     * @return
     */
    int insert(EventMsg eventMsg);

}
