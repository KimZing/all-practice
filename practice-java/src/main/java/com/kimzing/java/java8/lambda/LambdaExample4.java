package com.kimzing.java.java8.lambda;

import lombok.extern.java.Log;
import java.util.function.Consumer;


/**
 * 演示静态方法引用.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/9/26 16:26
 */
@Log
public class LambdaExample4 {

    public static void main(String[] args) {
        // 自己实现lambda
        Consumer<String> consumer = (str) -> log.info("Hello " + str);
        consumer.accept("World");

        //调用已有的方法进行引用
        Consumer<String> consumer2 = LambdaExample4::hello;
        consumer2.accept("World");

    }

    public static void hello(String word) {
        log.info("Hello " + word);
    }

}
