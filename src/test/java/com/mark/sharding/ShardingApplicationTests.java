package com.mark.sharding;

import com.mark.sharding.dao.OrderDao;
import com.mark.sharding.domain.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingApplicationTests {

    @Autowired
    private OrderDao orderDao;


    @Test
    public void addOrder() {

        for(long i=1;i<2; i++){
            Order order = new Order();
            order.setName("name" + i);
            order.setOrderId(i);
            order.setUserId(i);
            Integer cont = orderDao.addOrder(order);
            System.out.println(cont);
        }
    }

}
