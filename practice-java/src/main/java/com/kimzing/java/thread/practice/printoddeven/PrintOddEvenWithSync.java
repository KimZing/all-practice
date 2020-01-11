package com.kimzing.java.thread.practice.printoddeven;

/**
 * 使用sync关键字打印奇偶数.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/10/30 17:34
 */
public class PrintOddEvenWithSync {

    private static final Object lock = new Object();
    private static int count = 0;

    /**
     * MARK 使用synchronized进行加锁，只有符合对应条件才会执行，但问题是可能会有多次多余的调用
     * @param args
     */
    public static void main(String[] args) {
        new Thread(() -> {
            // MARK while放在上面，在内部进行锁竞争
            while (count < 100) {
                synchronized (lock) {
                    if ((count & 1) == 0) {
                        System.out.println(Thread.currentThread().getName() + " : " + count++);
                    }
                }
            }
        }, "偶数线程").start();

        new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    if ((count & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + " : " + count++);
                    }
                }
            }
        }, "奇数线程").start();
    }

}
