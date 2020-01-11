package com.kimzing.java.java8.lambda;

import lombok.extern.java.Log;

/**
 * 演示接口方法实现.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/9/26 17:16
 */
public interface DefaultMethodExample7 {

    default String hello() {
        return "Hello World!";
    }

    default String sameMethod() {
        return "From DefaultMethodExample7";
    }

    static String say() {
        return "Hello Friends!";
    }
}

/**
 * @author KimZing - kimzing@163.com
 * @since 2018/9/26 17:16
 */
@Log
class TestDemo implements DefaultMethodExample7, SameInterface {

    public static void main(String[] args) {
        TestDemo testDemo = new TestDemo();
        // 可以直接访问接口的默认方法
        log.info(testDemo.hello());
        // 接口的静态方法只能通过接口访问
        log.info(DefaultMethodExample7.say());

        // 当实现的接口有相同的方法时，需要子类覆盖
        log.info(testDemo.sameMethod());

    }

    @Override
    public String sameMethod() {
        // 在方法中可以调用相应的接口的同名默认方法
        // 格式： 接口.super.方法名
        DefaultMethodExample7.super.sameMethod();
        SameInterface.super.sameMethod();
        return "My Owner Method";
    }
}

/**
 * @author KimZing - kimzing@163.com
 * @since 2018/9/26 17:16
 */
interface SameInterface {

    default String sameMethod() {
        return "From DefaultMethodExample7";
    }

}