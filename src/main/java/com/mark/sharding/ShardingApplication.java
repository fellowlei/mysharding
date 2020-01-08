package com.mark.sharding;

import com.mark.sharding.plugin.MyImportRegistrator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@MapperScan(value = {"com.mark.sharding.dao"})
@Import(MyImportRegistrator.class)
public class ShardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingApplication.class, args);
    }


}
