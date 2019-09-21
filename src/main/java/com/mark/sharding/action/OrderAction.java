package com.mark.sharding.action;

import com.mark.sharding.dao.OrderDao;
import com.mark.sharding.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * @Auther: lulei
 * @Date: 2019/9/20 10:47
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/api/debug")
public class OrderAction {


    @Autowired
    private OrderDao orderDao;

    @RequestMapping("/add")
    public Integer addOrder(){
        for(long i=0;i<10; i++){
            Order order = new Order();
            order.setId(i);
            order.setName("name" + i);
            order.setOrderId(i);
            order.setUserId(i);
            orderDao.addOrder(order);
        }
        return 1;
    }

    @RequestMapping("/query")
    public Integer queryOrder(){
        for(long i=0;i<10; i++){
            List<Order> orders = orderDao.queryOrder(i);
            log.info(i + "#" + orders.toString());
        }
        return 1;
    }
}
