package com.kimzing.java.thread.start;

/**
 * 使用run和start方式启动的对比.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-06 10:38
 */
public class RunAndStartWay {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
        Thread thread = new Thread(runnable);

        thread.run();
        thread.start();
    }

}
