package com.mark.sharding.plugin.v3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Auther: lulei
 * @Date: 2020/1/9 15:14
 * @Description:
 */
@Component
public class HelloServiceTest {
    @RoutingInjected("helloServiceImplB")
    private HelloService helloService;

    public void hello(){
        String hello = helloService.hello();
        System.out.println(hello);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.vipkid.digitallibrary.plugin.v3");
        HelloServiceTest helloServiceTest = applicationContext.getBean(HelloServiceTest.class);
        helloServiceTest.hello();
    }
}
