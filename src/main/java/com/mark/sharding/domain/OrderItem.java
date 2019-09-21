package com.mark.sharding.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: lulei
 * @Date: 2019/9/21 14:38
 * @Description:
 */
@Data
public class OrderItem {
    private Long id;
    private Long orderId;
    private Long userId;
    private String name;
    private Date createTime;
}
