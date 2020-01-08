package com.mark.sharding.plugin.v2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

/**
 * @Auther: lulei
 * @Date: 2020/1/7 14:11
 * @Description:
 */
public class MyBeanFactoryPostProcesser implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcesser.postProcessBeanFactory");
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry)beanFactory;

    }

}
