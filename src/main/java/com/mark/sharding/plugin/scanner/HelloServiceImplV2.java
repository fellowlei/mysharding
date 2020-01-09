package com.mark.sharding.plugin.scanner;

/**
 * @Auther: lulei
 * @Date: 2020/1/9 17:27
 * @Description:
 */
@MyBean
public class HelloServiceImplV2 implements HelloService {
    @Override
    public String hello() {
        System.out.println("HelloServiceImplV2.hello");
        return "hello v2";
    }
}
