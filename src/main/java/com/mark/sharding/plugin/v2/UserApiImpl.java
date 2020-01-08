package com.mark.sharding.plugin.v2;

/**
 * @Auther: lulei
 * @Date: 2020/1/8 16:29
 * @Description:
 */
public class UserApiImpl implements UserApi {
    @Override
    public String hello(String name) {
        System.out.println("UserApiImpl.hello");
        return "hello " + name;
    }
}
