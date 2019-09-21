package com.mark.sharding.aop;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Slf4j
@Component
public class MethodAspect {
    public static Counter counter = Counter.builder("request_count").tag("sys","mark").description("请求总数").register(Metrics.globalRegistry);

    @Around(value = "execution(* com.mark.sharding.action.*.*(..))")
    public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            //执行目标方法
            result = pjp.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            counter.increment();
        }

        return result;


    }
}
