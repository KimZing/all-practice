package com.kimzing.java.java8.lambda.closure;

import lombok.extern.java.Log;

/**
 * 闭包演示.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/9/26 13:38
 */
@Log
public class ClosureDemo {

    public static void main(String[] args) {
        log.info("closure value: " +  closureTest().get());
        log.info("lambda value: " + lambdaTest().get());
    }

    /**
     * 使用闭包.
     *
     * @return lambda.closure.Supplier<java.lang.Integer>
     */
    private static Supplier<Integer> closureTest() {
        final int i = 1;

        return new Supplier<Integer>() {
            @Override
            public Integer get() {
                return i;
            }
        };
    }

    /**
     * 使用lambda表达式.
     *
     * @return lambda.closure.Supplier<java.lang.Integer>
     */
    private static Supplier<Integer> lambdaTest() {
        int i = 1;
        return () -> i;
    }
}

/**
 *
 * @param <T>
 */
interface Supplier<T> {
    T get();
}
