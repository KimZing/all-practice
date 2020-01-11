package com.kimzing.java.thread.create.decorate;

/**
 * 匿名内部类.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-06 10:05
 */
public class AnonymousAndLambdaWay {

    public static void main(String[] args) {
        // Lambda
        new Thread(() -> System.out.println(Thread.currentThread().getName() + ": I am Lambda!")).start();
        // Anonymous Thread
        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": I am Thread!");
            }
        }.start();
        // Anonymous Runnable
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": I am Runnable!");
            }
        }).start();

    }

}
