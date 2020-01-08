package com.mark.sharding.plugin.v2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @Auther: lulei
 * @Date: 2020/1/7 14:05
 * @Description:
 */
public class MyBeanDefinitionPostProcesser implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("MyBeanDefinitionPostProcesser.postProcessBeanDefinitionRegistry");
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(UserApiImpl.class);
        registry.registerBeanDefinition("userApi",builder.getBeanDefinition());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanDefinitionPostProcesser.postProcessBeanFactory");
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry)beanFactory;
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(UserApiImpl.class);
        registry.registerBeanDefinition("userApi2",builder.getBeanDefinition());
    }
}
