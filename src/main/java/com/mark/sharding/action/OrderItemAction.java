package com.mark.sharding.action;

import com.mark.sharding.dao.OrderDao;
import com.mark.sharding.dao.OrderItemDao;
import com.mark.sharding.domain.Order;
import com.mark.sharding.domain.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: lulei
 * @Date: 2019/9/20 10:47
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/api/orderItem")
public class OrderItemAction {

    @Autowired
    private OrderItemDao orderItemDao;

    @RequestMapping("/add")
    public Integer addOrder(){
        for(long i=0;i<10; i++){
            OrderItem orderItem = new OrderItem();
            orderItem.setId(i);
            orderItem.setName("name" + i);
            orderItem.setOrderId(i);
            orderItem.setUserId(i);
            Integer count = orderItemDao.addOrderItem(orderItem);
            System.out.println(count);
        }
        return 1;
    }

    @RequestMapping("/query")
    public Integer queryOrder(){
        for(long i=0;i<10; i++){
            List<OrderItem> orderItems = orderItemDao.queryOrderItem(i);
            log.info(i + "#" + orderItems.toString());
        }
        return 1;
    }
}
