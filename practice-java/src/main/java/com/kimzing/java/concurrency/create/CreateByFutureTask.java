package com.kimzing.java.concurrency.create;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用FutureTask中的Callable来实现.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/4/20 18:31
 */
public class CreateByFutureTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(() -> "Hello Thread!");
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }

}
