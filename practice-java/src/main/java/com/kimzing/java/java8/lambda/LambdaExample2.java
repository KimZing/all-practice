package com.kimzing.java.java8.lambda;

import lombok.extern.java.Log;

/**
 * 线程接口演示示例.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/9/26 14:04
 */
@Log
public class LambdaExample2 {

    public static void main(String[] args) {

        //传统写法
        FunctionInterface<Integer, Integer, String> func = new FunctionInterface<Integer, Integer, String>() {
            @Override
            public String get(Integer t1, Integer t2) {
                int sum = t1 + t2;
                return String.format("sum is %d", sum);
            }
        };
        log.info(func.get(1, 2));

        //lambda写法
        FunctionInterface<Integer, Integer, String> func2 = (t1, t2) -> {
            int sum = t1 + t2;
            return String.format("sum is %d", sum);
        };
        log.info(func2.get(2, 3));
    }

    interface FunctionInterface<T1, T2, R> {
        R get(T1 t1, T2 t2);
    }

}
