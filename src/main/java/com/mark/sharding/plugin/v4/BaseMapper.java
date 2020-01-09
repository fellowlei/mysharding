package com.mark.sharding.plugin.v4;

/**
 * @Auther: lulei
 * @Date: 2020/1/9 16:00
 * @Description:
 */
public interface BaseMapper {
    /**
     * @param value
     */
    public void add(String value);

    /**
     * @param key
     */
    public void remove(String key);
}
