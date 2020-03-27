package com.kimzing.java.generic.interf;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/3/27 13:16
 */
public class GenricInterfaceImpl2 implements GenericInterface<Integer, Float> {

    @Override
    public String contact(Integer s, Float i) {
        return String.format("integer:%s float:%f", s, i);
    }

    public static void main(String[] args) {
        GenricInterfaceImpl2 genricInterfaceImpl2 = new GenricInterfaceImpl2();
        System.out.println(genricInterfaceImpl2.contact(23, 1f));
    }
}
