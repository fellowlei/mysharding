package com.mark.sharding.plugin.v2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther: lulei
 * @Date: 2020/1/8 11:37
 * @Description:
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("MyBeanPostProcessor.postProcessBeforeInitialization");
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("MyBeanPostProcessor.postProcessAfterInitialization");
        if(bean instanceof  UserApi){
            UserApi userApi = (UserApi) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{UserApi.class}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("before execute " + method.getName());
                    Object result = method.invoke(bean, args);
                    System.out.println("after execute " + method.getName());
                    return result + " from proxy";
                }
            });
            return userApi;
        }
        return null;
    }
}
