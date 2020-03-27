package com.kimzing.java.generic.interf;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/3/27 13:16
 */
public class GenricInterfaceImpl1 implements GenericInterface<String, Integer> {

    @Override
    public String contact(String s, Integer i) {
        return String.format("string:%s integer:%d", s, i);
    }

    public static void main(String[] args) {
        GenricInterfaceImpl1 genricInterfaceImpl1 = new GenricInterfaceImpl1();
        System.out.println(genricInterfaceImpl1.contact("a", 1));
    }
}
