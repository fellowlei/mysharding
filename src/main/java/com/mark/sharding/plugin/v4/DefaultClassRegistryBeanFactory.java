package com.mark.sharding.plugin.v4;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

/**
 * @Auther: lulei
 * @Date: 2020/1/9 16:25
 * @Description:
 */
public class DefaultClassRegistryBeanFactory implements ApplicationContextAware, BeanDefinitionRegistryPostProcessor, BeanNameAware {

    private String scanPackage;

    private String beanName;

    private String mapperManagerFactoryBean;

    private ApplicationContext applicationContext;

    public String getScanPackage() {
        return scanPackage;
    }

    public void setScanPackage(String scanPackage) {
        this.scanPackage = scanPackage;
    }

    public String getMapperManagerFactoryBean() {
        return mapperManagerFactoryBean;
    }

    public void setMapperManagerFactoryBean(String mapperManagerFactoryBean) {
        this.mapperManagerFactoryBean = mapperManagerFactoryBean;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        if (StringUtils.isEmpty(this.scanPackage)) {
            throw new IllegalArgumentException("scanPackage can't be null");
        }
        String basePackage2 = this.applicationContext.getEnvironment().resolvePlaceholders(this.scanPackage);
        String[] packages = StringUtils.tokenizeToStringArray(basePackage2, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
        DefaultClassPathScanner defaultClassPathScanner = new DefaultClassPathScanner(registry);
        defaultClassPathScanner.setMapperManagerFactoryBean(mapperManagerFactoryBean);
        defaultClassPathScanner.registerFilters();
        defaultClassPathScanner.doScan(packages);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
