package com.mark.sharding.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class SlowMethodAspect {
    public static Long SlowMethodCostTime = 3000L;

    @Around(value = "execution(* com.mark.sharding.action.*.*(..)) ||" +
            "execution(* com.mark.sharding.dao.*.*(..))")
    public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
        if(SlowMethodCostTime <1){ // 超时时间小于1
            return pjp.proceed();
        }else{
            String methodName = pjp.getSignature().getName();
            String className = "defaultName";
            Signature sig = pjp.getSignature();
            Class<?> clazz = pjp.getTarget().getClass();
            if (sig instanceof MethodSignature) {
                MethodSignature msig = (MethodSignature) sig;
                className = msig.getMethod().getDeclaringClass().getSimpleName();
            }
            long startTime = System.currentTimeMillis();
            Object result = null;
            try {
                //执行目标方法
                result = pjp.proceed();
            } finally {
                long endTime = System.currentTimeMillis();
                if((endTime - startTime) >= SlowMethodCostTime){
                    String slowMsg = String.format("slow method=%s,cost time=%s,great than %s", className + "." + methodName, endTime - startTime, SlowMethodCostTime);
                    log.info(slowMsg);
                    SlowProfiler.addSlowMethodTrace(slowMsg);
                    if(clazz.getName().startsWith("com.mark.sharding.action")){
                        String slowMethodTrace = SlowProfiler.getSlowMethodTrace();
                        log.info(slowMethodTrace);
                        SlowProfiler.clearTrace();
                    }
                }
            }

            return result;
        }


    }

}
