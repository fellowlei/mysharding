package com.mark.sharding.plugin.v4;

import com.mark.sharding.plugin.v4.dao.UserMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: lulei
 * @Date: 2020/1/9 16:31
 * @Description:
 */
@Configuration
public class ClassRegistryBeanScannerConfig {

    @Bean(name = "mapperManagerFactoryBean")
    public MapperManagerFactoryBean configMapperManagerFactoryBean(){
        return new MapperManagerFactoryBean();
    }

    @Bean
    public DefaultClassRegistryBeanFactory configDefaultClassRegistryBeanFactory(){
        DefaultClassRegistryBeanFactory defaultClassRegistryBeanFactory = new DefaultClassRegistryBeanFactory();
        defaultClassRegistryBeanFactory.setScanPackage("com.vipkid.digitallibrary.plugin.v4.dao");
        defaultClassRegistryBeanFactory.setMapperManagerFactoryBean("mapperManagerFactoryBean");
        return defaultClassRegistryBeanFactory;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.vipkid.digitallibrary.plugin.v4");
        UserMapper userMapper = applicationContext.getBean(UserMapper.class);
        userMapper.add("test");
    }
}
