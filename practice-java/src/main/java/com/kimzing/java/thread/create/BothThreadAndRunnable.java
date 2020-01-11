package com.kimzing.java.thread.create;

/**
 * 使用两种实现，确认真正调用方.
 * <p>
 *     Thread进行覆写，所以会执行Thread的run方法
 * </p>
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-05 20:02
 */
public class BothThreadAndRunnable {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable Running");
            }
        }) {
            @Override
            public void run() {
                System.out.println("Thread Running");
            }
        }.start();
    }

}
