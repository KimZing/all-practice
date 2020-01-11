package com.kimzing.java.thread.sleep;

import java.util.concurrent.TimeUnit;

/**
 * sleep不会释放Monitor锁.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/11/11 17:50
 */
public class SleepNoReleaseMonitor implements Runnable{

    private Object lock = new Object();

    public static void main(String[] args) {
        SleepNoReleaseMonitor sleepNoReleaseMonitor = new SleepNoReleaseMonitor();
        new Thread(sleepNoReleaseMonitor).start();
        new Thread(sleepNoReleaseMonitor).start();
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + "获得了锁");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "释放了锁");
        }
    }
}
