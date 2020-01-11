package com.kimzing.java.thread.state;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-10 10:16
 */
public class BlockedWaitingTimedWaiting implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new BlockedWaitingTimedWaiting();

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        // 获取到锁后，进入sleep方法，会变为TIMED_WAITING状态
        System.out.println(thread1.getState());
        // 无法获取Monitor锁，会变为BLOCKED状态
        System.out.println(thread2.getState());
        Thread.sleep(2200);
        System.out.println(thread1.getState());
    }

    @Override
    public void run() {
        sync();
    }

    public synchronized void sync() {
        try {
            Thread.sleep(2000);
            wait();
        } catch (InterruptedException e) {
            System.out.println("Interrupt");
        }
    }
}
