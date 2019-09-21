package com.mark.sharding.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: lulei
 * @Date: 2019/9/20 11:00
 * @Description:
 */
@Data
public class Order implements Serializable {
    private Long id;
    private Long userId;
    private Long orderId;
    private Date createTime;
    private String name;

}
