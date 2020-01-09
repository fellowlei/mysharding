package com.mark.sharding.plugin.v3;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

import java.util.Map;

/**
 * @Auther: lulei
 * @Date: 2020/1/9 15:07
 * @Description:
 */
public class RoutingBeanProxyFactory {
    private final static String DEFAULT_BEAN_NAME = "helloServiceImplA";

    public static Object createProxy(String name, Class<?> type, Map<String, Object> beansOfType) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(type);
        Object target = beansOfType.get(name);
        proxyFactory.addAdvice(new RoutingMethodInterceptor(target));
        return proxyFactory.getProxy();
    }

    static class RoutingMethodInterceptor implements MethodInterceptor {
        private Object targetObject;
        public RoutingMethodInterceptor(Object targetObject) {
            this.targetObject = targetObject;
        }

        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {
            return methodInvocation.getMethod().invoke(targetObject,methodInvocation.getArguments());
        }
    }
}
