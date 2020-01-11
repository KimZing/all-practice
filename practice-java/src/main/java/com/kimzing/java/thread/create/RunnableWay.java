package com.kimzing.java.thread.create;

/**
 * 通过Runnable接口的方式.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-05 16:56
 */
public class RunnableWay implements Runnable {

    public static void main(String[] args) {
        new Thread(new RunnableWay())
                .start();
    }

    @Override
    public void run() {
        System.out.println("Runnable启动方式");
    }

}
