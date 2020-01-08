package com.mark.sharding.plugin.v2;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;

/**
 * @Auther: lulei
 * @Date: 2020/1/8 11:15
 * @Description:
 */
public class MyClassPathDefinitionScanner extends ClassPathBeanDefinitionScanner {
    private Class type;
    public MyClassPathDefinitionScanner(BeanDefinitionRegistry registry, Class<? extends Annotation> type) {
        super(registry);
        this.type = type;
    }

    protected void registerTypeFilter() {
        addIncludeFilter(new AnnotationTypeFilter(type));
    }

    public static void main(String[] args) {
        String BASE_package="com.vipkid.digitallibrary.v2";
        GenericApplicationContext context = new GenericApplicationContext();
        // BeanDefinitionPostProcesser
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(MyBeanDefinitionPostProcesser.class);
        context.registerBeanDefinition("myBeanDefinitionPostProcesser",beanDefinition);
        // BeanFactoryPostProcesser
        GenericBeanDefinition beanDefinition1 = new GenericBeanDefinition();
        beanDefinition1.setBeanClass(MyBeanFactoryPostProcesser.class);
        context.registerBeanDefinition("myBeanFactoryPostProcesser",beanDefinition1);
        // BeanPostProcessor
        GenericBeanDefinition beanDefinition2 = new GenericBeanDefinition();
        beanDefinition2.setBeanClass(MyBeanPostProcessor.class);
        context.registerBeanDefinition("myBeanPostProcessor",beanDefinition2);


        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder.addPropertyValue("name","mark");
        builder.addPropertyValue("age",18);
        context.registerBeanDefinition("user",builder.getBeanDefinition());

        MyClassPathDefinitionScanner scanner = new MyClassPathDefinitionScanner(context, MyBean.class);
        scanner.registerTypeFilter();
        int beanCount = scanner.scan(BASE_package);
        context.refresh();

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(beanCount);
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        User user = (User) context.getBean("user");
        System.out.println(user);
        UserApi userApi = (UserApi) context.getBean("userApi");
        System.out.println(userApi.hello("mark"));
    }


}
