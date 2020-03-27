package com.kimzing.java.generic.method;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/3/27 16:30
 */
public class GenericMethod {

    public <T> String convertToString(T t) {
        return t.toString();
    }

    public static <T> String addPrefix(T t) {
        return "pre:" + t.toString();
    }

    public static void main(String[] args) {
        GenericMethod genericMethod = new GenericMethod();
        System.out.println(genericMethod.convertToString(12));
        System.out.println(GenericMethod.addPrefix(new int[]{1, 2}));
    }

}
