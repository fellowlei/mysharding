package com.mark.sharding.plugin.v4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Auther: lulei
 * @Date: 2020/1/9 16:18
 * @Description:
 */
public class MapperJavaProxy implements InvocationHandler {
    private BaseMapper baseMapper;
    private Class<?> interfaceClass;
    public <T> MapperJavaProxy(BaseMapper baseMapper, Class<T> interfaceClass) {
        this.baseMapper = baseMapper;
        this.interfaceClass = interfaceClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(!interfaceClass.isInterface()){
            throw new IllegalArgumentException("mapperInterface is not interface.");
        }
        if(baseMapper == null){
            baseMapper = new CustomerBaseMapper();
        }
        return method.invoke(baseMapper,args);
    }
}
