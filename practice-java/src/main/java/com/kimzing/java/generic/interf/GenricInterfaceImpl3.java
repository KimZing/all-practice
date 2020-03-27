package com.kimzing.java.generic.interf;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/3/27 13:16
 */
public class GenricInterfaceImpl3<T,E,F> implements GenericInterface<T, E> {

    private F f;

    public GenricInterfaceImpl3(F f) {
        this.f = f;
    }

    @Override
    public String contact(T t, E e) {
        return String.format("t is %s, e is %s, f is %s", t, e, f);
    }

    public static void main(String[] args) {
        GenricInterfaceImpl3<String, String, Integer> impl3 =
                new GenricInterfaceImpl3<>(12);
        System.out.println(impl3.contact("Hello", "World"));
    }
}
