package com.mark.sharding.dao;

import com.mark.sharding.domain.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: lulei
 * @Date: 2019/9/20 11:02
 * @Description:
 */
public interface OrderDao {
    Integer addOrder(Order order);
    List<Order> queryOrder(@Param("orderId") Long orderId);
}
