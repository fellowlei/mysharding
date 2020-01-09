package com.mark.sharding.plugin.v4;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.lang.reflect.Proxy;

/**
 * @Auther: lulei
 * @Date: 2020/1/9 16:14
 * @Description:
 */
public class BaseMapperFactoryBean<T> implements FactoryBean<T>, InitializingBean, ApplicationContextAware, ApplicationListener<ApplicationEvent> {

    private Class<T> mapperInterface;
    private ApplicationContext applicationContext;
    private BaseMapper mapperManagerFactoryBean;

    @Override
    public T getObject() throws Exception {
        return (T) Proxy.newProxyInstance(applicationContext.getClassLoader(),new Class[]{mapperInterface},new MapperJavaProxy(mapperManagerFactoryBean, mapperInterface));
    }

    @Override
    public Class<?> getObjectType() {
        return this.mapperInterface;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (null == mapperInterface)
            throw new IllegalArgumentException("Mapper Interface Can't Be Null!!");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

    }

    public void setMapperManagerFactoryBean(BaseMapper mapperManagerFactoryBean) {
        this.mapperManagerFactoryBean = mapperManagerFactoryBean;
    }
}
