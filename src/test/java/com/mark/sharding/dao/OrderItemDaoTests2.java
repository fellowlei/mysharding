package com.mark.sharding.dao;

import com.mark.sharding.dao.OrderDao;
import com.mark.sharding.dao.OrderItemDao;
import com.mark.sharding.domain.Order;
import com.mark.sharding.domain.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderItemDaoTests2 {

    @Autowired
    private OrderItemDao orderItemDao;


    @Test
    public void addOrderItem() {

        for(long i=0;i<10; i++){
            OrderItem orderItem= new OrderItem();
            orderItem.setName("name" + i);
            orderItem.setOrderId(i);
            orderItem.setUserId(i);
            Integer cont = orderItemDao.addOrderItem(orderItem);
            System.out.println(cont);
        }
    }

    @Test
    public void queryOrderItem() {

        for(long i=0;i<10; i++){
            List<OrderItem> orderItems = orderItemDao.queryOrderItem(i);
            System.out.println(orderItems);
        }
    }

}
