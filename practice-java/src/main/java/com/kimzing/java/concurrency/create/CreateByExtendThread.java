package com.kimzing.java.concurrency.create;

/**
 * 通过继承Thread类创建线程.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/4/20 18:25
 */
public class CreateByExtendThread {

    public static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("I am Thread!");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
    }

}
