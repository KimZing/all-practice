package com.kimzing.java.thread.sleep;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * sleep响应中断.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/11/11 17:58
 */
public class SleepInterrupt implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        SleepInterrupt sleepInterrupt = new SleepInterrupt();
        Thread thread = new Thread(sleepInterrupt);
        thread.start();
        Thread.sleep(4000);
        thread.interrupt();
    }

    @Override
    public void run() {
        Stream.iterate(0, i -> i + 1).limit(3).forEach(i -> {
            System.out.println("执行:" + i);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("中断执行");
            }
        });
    }

}
