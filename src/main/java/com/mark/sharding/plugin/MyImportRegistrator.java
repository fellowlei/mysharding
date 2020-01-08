package com.mark.sharding.plugin;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.util.Set;

/**
 * @Auther: lulei
 * @Date: 2020/1/7 19:58
 * @Description:
 * https://www.jianshu.com/p/1e212eac42ac
 */
public class MyImportRegistrator implements ImportBeanDefinitionRegistrar, EnvironmentAware {
    private Environment environment;
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        ClassPathScanningCandidateComponentProvider scanner = getScanner();
        scanner.addIncludeFilter(new AnnotationTypeFilter(MyFeignClient.class));

        Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(ClassUtils.getPackageName(annotationMetadata.getClassName()));

        for (BeanDefinition beanDefinition : candidateComponents) {
            if(beanDefinition.getBeanClassName().equals(ProductApi.class.getCanonicalName())){
                beanDefinition.setBeanClassName(ProductService.class.getCanonicalName());
                beanDefinitionRegistry.registerBeanDefinition(ClassUtils.getShortName(ProductApi.class),beanDefinition);
            }
        }
        // 注入beanFactoryPostProcessor
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(MyBeanFactoryPostProcessor.class);
        beanDefinitionRegistry.registerBeanDefinition("myBeanFactoryPostProcessor",beanDefinition);
        // 注入beanPostProcessor
        GenericBeanDefinition beanPostProcessor  = new GenericBeanDefinition();
        beanPostProcessor .setBeanClass(MyBeanPostProcessor.class);
        beanDefinitionRegistry.registerBeanDefinition("myBeanPostProcessor",beanPostProcessor );

    }

    private ClassPathScanningCandidateComponentProvider getScanner(){
        // 创建一个class path scanner
        return new ClassPathScanningCandidateComponentProvider(false, environment){
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                // 只要候选的class是个interface就让他过
                return beanDefinition.getMetadata().isInterface();
            }
        };
    }
}
