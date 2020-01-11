package com.kimzing.java.thread.stop;

/**
 * 使用interrupt停止线程.
 * <p>
 *     睡眠时，会抛出异常并且清空当前的中断状态
 * </p>
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-06 15:00
 */
public class StopThreadWithInterruptBlock {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("接收到中断信号");
            }
        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

}
