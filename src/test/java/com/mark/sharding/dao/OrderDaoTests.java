package com.mark.sharding.dao;

import com.mark.sharding.domain.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDaoTests {

    @Autowired
    private OrderDao orderDao;


    @Test
    public void addOrder() {

        for(long i=0;i<10; i++){
            Order order = new Order();
            order.setName("name" + i);
            order.setOrderId(i);
            order.setUserId(i);
            Integer cont = orderDao.addOrder(order);
            System.out.println(cont);
        }
    }

    @Test
    public void queryOrder() {

        for(long i=0;i<10; i++){
            List<Order> orders = orderDao.queryOrder(i);
            System.out.println(orders);
        }
    }

}
