package com.mark.sharding.plugin;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Auther: lulei
 * @Date: 2020/1/7 19:56
 * @Description:
 */
@Data
public class ProductService implements ProductApi, InitializingBean {

    private String hello = "hello ";

    @Override
    public String getProduct(String name) {
        System.out.println("ProductService.getProduct " + name);
        return hello + name;
    }

    public ProductService() {
        System.out.println("ProductService.ProductService init");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("ProductService.afterPropertiesSet");
    }
}
