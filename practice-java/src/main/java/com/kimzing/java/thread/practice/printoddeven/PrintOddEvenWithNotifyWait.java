package com.kimzing.java.thread.practice.printoddeven;

/**
 * 使用等待通知的机制进行交替打印.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/10/30 17:49
 */
public class PrintOddEvenWithNotifyWait {

    private static int count = 0;

    private static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                while (count < 100) {
                    if ((count & 1) == 0) {
                        lock.notify();
                        System.out.println(Thread.currentThread().getName() + " : " + count++);
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "偶数线程").start();

        new Thread(() -> {
            synchronized (lock) {
                while (count < 100) {
                    if ((count & 1) == 1) {
                        lock.notify();
                        System.out.println(Thread.currentThread().getName() + " : " + count++);
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "奇数线程").start();
    }

}
