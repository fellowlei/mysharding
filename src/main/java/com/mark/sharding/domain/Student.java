package com.mark.sharding.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: lulei
 * @Date: 2019/9/21 15:03
 * @Description:
 */
@Data
public class Student {
    private Long id;
    private Long studentId;
    private Long bookId;
    private Date createTime;
}
