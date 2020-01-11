package com.kimzing.java.thread.create.decorate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-05 20:11
 */
public class ThreadPoolWay {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
            System.out.println("Executors Running!");
        });
    }

}
