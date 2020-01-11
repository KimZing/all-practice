package com.kimzing.java.java8.lambda;

import lombok.extern.java.Log;

/**
 * lambda表达式Runnable示例.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/9/26 15:18
 */
@Log
public class LambdaExample1 {

    public static void main(String[] args) {
        // 传统的线程写法
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.info("do something");
            }
        };

        // Lambda写法
        Runnable run2 = () -> log.info("do something");
    }

}
