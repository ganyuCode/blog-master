package com.ganyu.utils;


import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.stream.Collectors;


/**
 * DATA: 2022/8/28
 * Author: 晨夕
 */
public class BeanCopyUtils {
    //创建空参构造器
    private BeanCopyUtils(){

    }

    //源对象实例转换为目标对象实例
    public static <E> E copyBean(Object source,Class<E> clazz){
        //获取源对象实例
        E result = null;
        try {
            result = clazz.newInstance();
            //进行拷贝
            BeanUtils.copyProperties(source,result);
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //对一系列的源对象实例就那些转换为目标对象实例
    public static <V,E> List<E> copyBeanList(List<V> list,Class<E> clazz){
        //使用Lambda和Stream流
        //对集合list
        return list.stream()
                .map(o -> copyBean(o,clazz))
                .collect(Collectors.toList());
    }
}
