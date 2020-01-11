package com.kimzing.java.thread.sleep;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * sleep不会释放Monitor锁.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/11/11 17:50
 */
public class SleepNoReleaseLock implements Runnable {

    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        SleepNoReleaseLock sleepNoReleaseMonitor = new SleepNoReleaseLock();
        new Thread(sleepNoReleaseMonitor).start();
        new Thread(sleepNoReleaseMonitor).start();
    }

    @Override
    public void run() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "获得了锁");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
                lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + "释放了锁");
        lock.unlock();
    }
}
