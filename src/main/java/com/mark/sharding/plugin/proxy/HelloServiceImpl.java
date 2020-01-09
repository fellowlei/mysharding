package com.mark.sharding.plugin.proxy;

/**
 * @Auther: lulei
 * @Date: 2020/1/9 14:35
 * @Description:
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello() {
        System.out.println("HelloServiceImpl.hello");
        return "hello";
    }
}
