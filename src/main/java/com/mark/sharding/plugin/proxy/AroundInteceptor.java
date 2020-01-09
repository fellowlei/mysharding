package com.mark.sharding.plugin.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @Auther: lulei
 * @Date: 2020/1/9 14:31
 * @Description:
 */
public class AroundInteceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println(methodInvocation.getMethod().getName() + "调用之前");
        Object result = methodInvocation.proceed();
        System.out.println(methodInvocation.getMethod().getName() + "调用之后");
        return result;
    }

    public static void main(String[] args) {
//        ProxyFactory proxyFactory = new ProxyFactory();
//        proxyFactory.setTarget(new MyTarget());
//        proxyFactory.addAdvice(new AroundInteceptor());
//        MyTarget proxy = (MyTarget) proxyFactory.getProxy();
//        proxy.printName();

        ProxyFactory proxyFactory = new ProxyFactory();
//        proxyFactory.setInterfaces(new Class[]{HelloService.class});
        proxyFactory.setTarget(new HelloServiceImpl());
        proxyFactory.addAdvice(new AroundInteceptor());
        HelloService helloService = (HelloService) proxyFactory.getProxy();
        helloService.hello();
        System.out.println(helloService.getClass().getName());
    }

}
