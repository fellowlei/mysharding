package com.mark.sharding.plugin.scanner;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

/**
 * @Auther: lulei
 * @Date: 2020/1/9 17:15
 * @Description:
 */
public class MyClassScanner extends ClassPathBeanDefinitionScanner {
    public MyClassScanner(BeanDefinitionRegistry registry) {
        super(registry,false);
        addIncludeFilter(new AnnotationTypeFilter(MyBean.class));
//        Set<BeanDefinitionHolder> beanDefinitionHolders = doScan("com.vipkid.digitallibrary.plugin.scanner");
//        System.out.println(beanDefinitionHolders.size());
//        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
//            System.out.println(beanDefinitionHolder.getBeanName());
//        }
    }

    public Set<BeanDefinitionHolder> doScan(String packages){
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(packages);
        return beanDefinitionHolders;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.vipkid.digitallibrary.plugin.scanner");
        MyClassScanner scanner = new MyClassScanner(applicationContext);
        int count = scanner.scan("com.vipkid.digitallibrary.plugin.scanner");
        System.out.println(count);

        HelloServiceImpl helloService =  applicationContext.getBean(HelloServiceImpl.class);
        helloService.hello();
    }


}
