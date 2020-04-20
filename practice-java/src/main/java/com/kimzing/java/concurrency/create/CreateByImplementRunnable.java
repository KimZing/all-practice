package com.kimzing.java.concurrency.create;

/**
 * 通过实现Runnable接口创建线程.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/4/20 18:29
 */
public class CreateByImplementRunnable {

    public static void main(String[] args) {
        new Thread(() -> System.out.println("Hello Thread!")).start();
    }

}
