package com.kimzing.java.generic.interf;

/**
 * 泛型接口.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/3/27 13:12
 */
public interface GenericInterface <T, E>{

    /**
     * 将两种类型拼接成字符串返回
     */
    String contact(T t, E e);

}
