package com.mark.sharding.plugin.v4;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Auther: lulei
 * @Date: 2020/1/9 16:20
 * @Description:
 */
public class MapperManagerFactoryBean implements FactoryBean<BaseMapper>, InitializingBean, ApplicationListener<ApplicationEvent> {
    @Override
    public BaseMapper getObject() throws Exception {
        return new CustomerBaseMapper();
    }

    @Override
    public Class<?> getObjectType() {
        return BaseMapper.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println(applicationEvent.toString());
    }
}
