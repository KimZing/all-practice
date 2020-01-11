package com.kimzing.java.thread.create;

/**
 * 继承Thread类方式.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-05 17:06
 */
public class ThreadWay extends Thread {

    public static void main(String[] args) {
        new ThreadWay()
                .start();
    }

    @Override
    public void run() {
        System.out.println("Thread方式");
    }
}
