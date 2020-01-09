package com.mark.sharding.plugin.scanner;

import java.lang.annotation.*;

/**
 * @Auther: lulei
 * @Date: 2020/1/9 17:17
 * @Description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyBean {
}
