package com.gao.consumer.service;

import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.val;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ReflectionUtils;

import java.util.concurrent.ExecutionException;

public class OutsideAccessorUtils {
    private static final Cache<Pair<String, Class<?>>, Object> cache = CacheBuilder.newBuilder().weakValues().build();
    private static  int i=0;

    @SuppressWarnings("unchecked")

    public static <T> T buildAccessor(String serverName, Class<T> accessInterface) {

        val pair = Pair.<String, Class<?>>of(serverName, accessInterface);

        try {
            return (T) cache.get(pair, () -> construct(serverName, accessInterface));
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T construct(String name, Class<T> accessInterface) throws Exception{

        val clazz = Class.forName("org.springframework.cloud.openfeign.FeignClientFactoryBean");

        val instance = ClassUtils.newInstance(clazz);

        setField(clazz, "type", instance, accessInterface);

        setField(clazz, "name", instance, "ql");
        setField(clazz, "url", instance, name);
        //setField(clazz, "contextId", instance, ""+i);
        //其要求path值非null

        setField(clazz, "path", instance, "");


        //设置上下文


        ((ApplicationContextAware) instance).setApplicationContext(ApplicationContextUtils.getApplicationContext());

        val obj =((FactoryBean) instance).getObject();

        return (T) obj;

    }

    private static void setField(Class clazz, String fieldName, Object obj, Object value) {

        val field = ReflectionUtils.findField(clazz, fieldName);

        assert field !=null;

        ReflectionUtils.makeAccessible(field);

        ReflectionUtils.setField(field, obj, value);

    }

}
