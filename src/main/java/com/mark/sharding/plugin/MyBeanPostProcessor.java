package com.mark.sharding.plugin;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther: lulei
 * @Date: 2020/1/7 20:12
 * @Description:
 */
public class MyBeanPostProcessor implements BeanPostProcessor, BeanClassLoaderAware {
    private ClassLoader classLoader;
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {
        if(bean instanceof ProductApi){
            System.out.println("invoke before Initialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {
        if(bean instanceof ProductApi){
            System.out.println("invoke after initialization");
            ProductApi proxy = (ProductApi) Proxy.newProxyInstance(classLoader, new Class[]{ProductApi.class}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("before invoke");
                    Object result = method.invoke(bean, args);
                    if (method.getName().equals("getProduct")) {
                        result = result.toString() + " from proxy";
                    }
                    System.out.println("after invoke");
                    return result;
                }
            });
            return proxy;
        }
        return bean;
    }
}
