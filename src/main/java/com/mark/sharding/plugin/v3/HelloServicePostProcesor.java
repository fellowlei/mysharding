package com.mark.sharding.plugin.v3;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @Auther: lulei
 * @Date: 2020/1/9 14:59
 * @Description:
 */
@Component
public class HelloServicePostProcesor implements BeanPostProcessor {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClazz = bean.getClass();
        Field[] declaredFields = targetClazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if(field.isAnnotationPresent(RoutingInjected.class)){
                if(!field.getType().isInterface()){
                    throw new BeanCreationException("RoutingInjected field must be declared as an interface:" + field.getName()
                            + " @Class " + targetClazz.getName());
                }
                try {
                    handleRoutingInjected(field,bean,field.getType());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }

    private void handleRoutingInjected(Field field, Object bean, Class<?> type) throws IllegalAccessException {
        Map<String, Object> beansOfType = this.applicationContext.getBeansOfType((Class<Object>) type);
        field.setAccessible(true);
        if(beansOfType.size() == 1){
            field.set(bean,beansOfType.values().iterator().next());
        }else if(beansOfType.size() == 2){
            String value = field.getAnnotation(RoutingInjected.class).value();
            Object proxy = RoutingBeanProxyFactory.createProxy(value, type, beansOfType);
            field.set(bean, proxy);
        }else{
            throw new IllegalArgumentException("Find more than 2 beans for type: " + type);
        }
    }
}
