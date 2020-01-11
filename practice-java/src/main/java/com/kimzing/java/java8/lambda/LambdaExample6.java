package com.kimzing.java.java8.lambda;

import lombok.extern.java.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * 构造函数引用.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/9/26 16:44
 */
@Log
public class LambdaExample6 {

    public static void main(String[] args) throws Exception {
        // 构造一个指定大小的ArrayList
        Function<Integer, ArrayList<String>> function = ArrayList::new;
        ArrayList<String> list = function.apply(10);

        //利用反射查看容量，for practice
        Class clazz = list.getClass();
        Field field = clazz.getDeclaredField("elementData");
        field.setAccessible(true);
        Object[] objects = (Object[]) field.get(list);
        log.info("list capacity is " +  objects.length);
    }
}
