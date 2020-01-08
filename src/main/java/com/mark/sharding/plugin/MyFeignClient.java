package com.mark.sharding.plugin;

import java.lang.annotation.*;

/**
 * @Auther: lulei
 * @Date: 2020/1/7 19:49
 * @Description:
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyFeignClient {
}
