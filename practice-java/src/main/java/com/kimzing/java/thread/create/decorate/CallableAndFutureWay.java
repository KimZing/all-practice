package com.kimzing.java.thread.create.decorate;

import java.util.concurrent.*;

/**
 * 使用Future.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-06 10:26
 */
public class CallableAndFutureWay {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " : Callable");
                return "I am Callable";
            }
        });
        System.out.println(Thread.currentThread().getName() + ":" + future.get());
    }

}
