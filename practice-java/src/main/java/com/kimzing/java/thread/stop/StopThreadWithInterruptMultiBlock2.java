package com.kimzing.java.thread.stop;

/**
 * 使用interrupt停止线程.
 *<p>
 *     验证：当异常被捕捉后，仍然会继续执行
 *</p>
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-06 15:00
 */
public class StopThreadWithInterruptMultiBlock2 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
                int a = 0;
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(a++);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("接收到中断信号");
                    }
                }
        });
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }

}
