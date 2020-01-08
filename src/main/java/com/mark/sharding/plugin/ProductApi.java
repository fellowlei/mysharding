package com.mark.sharding.plugin;

/**
 * @Auther: lulei
 * @Date: 2020/1/7 19:55
 * @Description:
 */
@MyFeignClient
public interface ProductApi {
    String getProduct(String name);
}
