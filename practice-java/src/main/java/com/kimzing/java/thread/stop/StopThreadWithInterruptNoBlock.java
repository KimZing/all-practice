package com.kimzing.java.thread.stop;

/**
 * 使用interrupt停止线程.
 * <p>
 *     没有阻塞，直接使用interrupt即可
 * </p>
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-06 15:00
 */
public class StopThreadWithInterruptNoBlock {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int num = 0;
            // 加入线程中断判断
            while (!Thread.interrupted() && num < Integer.MAX_VALUE) {
                if (num % 10000 == 0) {
                    System.out.println(num);
                }
                num++;
            }
        });
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }

}
