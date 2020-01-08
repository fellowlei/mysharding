package com.mark.sharding.plugin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: lulei
 * @Date: 2020/1/7 20:03
 * @Description:
 */
@RestController
public class ProductAction {

    @Autowired
    private ProductApi productApi;

    @GetMapping("/getProduct")
    public String getProduct(){
        return productApi.getProduct("mark");
    }
}
