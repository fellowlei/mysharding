package com.mark.sharding.plugin.v3;

import org.springframework.stereotype.Service;

/**
 * @Auther: lulei
 * @Date: 2020/1/9 14:56
 * @Description:
 */
@Service
public class HelloServiceImplB implements HelloService {
    @Override
    public String hello() {
        System.out.println("HelloServiceImplB.hello");
        return "hello b";
    }
}
