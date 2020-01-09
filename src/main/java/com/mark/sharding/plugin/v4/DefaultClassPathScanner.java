package com.mark.sharding.plugin.v4;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

/**
 * @Auther: lulei
 * @Date: 2020/1/9 16:02
 * @Description:
 */
public class DefaultClassPathScanner extends ClassPathBeanDefinitionScanner {
    private String mapperManagerFactoryBean;
    private final String DEFAULT_MAPPER_SUFFIX = "Mapper";
    public DefaultClassPathScanner(BeanDefinitionRegistry registry) {
        super(registry,false);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        if(beanDefinitionHolders.isEmpty()){
            logger.warn("系统没有在 '" + Arrays.toString(basePackages) + "' 包中找到任何Mapper，请检查配置");
        }else{
            processBeanDefinition(beanDefinitionHolders);
        }
        return beanDefinitionHolders;
    }

    protected void registerFilters(){
        addIncludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                String className = metadataReader.getClassMetadata().getClassName();
                return className.endsWith(DEFAULT_MAPPER_SUFFIX);
            }
        });

        addExcludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                String className = metadataReader.getClassMetadata().getClassName();
                return className.endsWith("package-info");
            }
        });
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isInterface();
    }

    private void processBeanDefinition(Set<BeanDefinitionHolder> beanDefinitionHolders) {
        for (BeanDefinitionHolder holder : beanDefinitionHolders) {
            GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
            String beanClassName = definition.getBeanClassName();
            definition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
            definition.getPropertyValues().add("mapperManagerFactoryBean",new RuntimeBeanReference(this.mapperManagerFactoryBean));
            definition.setBeanClass(BaseMapperFactoryBean.class);
            definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
            System.out.println("已开启自动按照类型注入 '" + holder.getBeanName() + "'.");
        }
    }

    public void setMapperManagerFactoryBean(String mapperManagerFactoryBean) {
        this.mapperManagerFactoryBean = mapperManagerFactoryBean;
    }

}
