package com.mark.sharding.plugin.v4;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Auther: lulei
 * @Date: 2020/1/9 16:00
 * @Description:
 */
public class CustomerBaseMapper implements BaseMapper {
    private List<String> dataList = new CopyOnWriteArrayList<>();
    @Override
    public void add(String value) {
        System.out.println("CustomerBaseMapper.add");
        dataList.add(value);
    }

    @Override
    public void remove(String key) {
        if(dataList.isEmpty()){
            throw new IllegalArgumentException("Can't remove because the list is Empty!");
        }
    }
}
