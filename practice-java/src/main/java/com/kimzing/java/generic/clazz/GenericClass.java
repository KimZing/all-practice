package com.kimzing.java.generic.clazz;

/**
 * 泛型类定义.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/3/27 12:27
 */
public class GenericClass<T> {

    private T param;

    public GenericClass(T param) {
        this.param = param;
    }

    public T getParam() {
        return param;
    }

    public static void main(String[] args) {
        GenericClass<String> hello = new GenericClass<>("Hello");
        GenericClass<Integer> num = new GenericClass<>(123);
    }

}
