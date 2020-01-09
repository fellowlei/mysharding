package com.mark.sharding.plugin.v3;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Auther: lulei
 * @Date: 2020/1/9 14:57
 * @Description:
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RoutingInjected {
    String value() default "helloServiceImplA";
}
