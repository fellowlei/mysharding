package com.mark.sharding.plugin.scanner;

/**
 * @Auther: lulei
 * @Date: 2020/1/9 14:35
 * @Description:
 */
@MyBean
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello() {
        System.out.println("HelloServiceImpl.hello");
        return "hello v1";
    }
}
