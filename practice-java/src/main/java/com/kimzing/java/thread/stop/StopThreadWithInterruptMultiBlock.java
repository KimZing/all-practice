package com.kimzing.java.thread.stop;

/**
 * 使用interrupt停止线程.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-06 15:00
 */
public class StopThreadWithInterruptMultiBlock {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                int a = 0;
                while (true) {
                    System.out.println(a++);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("接收到中断信号");
            }
        });
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }

}
