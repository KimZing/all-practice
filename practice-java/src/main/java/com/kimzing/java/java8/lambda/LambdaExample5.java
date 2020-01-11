package com.kimzing.java.java8.lambda;

import lombok.extern.java.Log;

import java.util.function.Consumer;

/**
 * 普通方法引用示例.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/9/26 16:39
 */
@Log
public class LambdaExample5 {

    public static void main(String[] args) {
        LambdaExample5 lambdaExample = new LambdaExample5();
        Consumer<String> consumer = lambdaExample::hello;

        consumer.accept("World");
    }

    public  void hello(String word) {
        log.info("Hello " + word);
    }
}
