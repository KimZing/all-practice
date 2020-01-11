package com.kimzing.java.thread.method;

/**
 * wait进行阻塞，释放锁. notify唤醒指定对象的等待线程
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-16 11:40
 */
public class WaitAndNotifyDemo {

    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Thread1());
        Thread thread2 = new Thread(new Thread2());
        thread1.start();
        Thread.sleep(200);
        thread2.start();
    }

    static class Thread1 implements Runnable{

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "开始执行了");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "继续执行");
            }
        }
    }

    static class Thread2 implements Runnable{

        @Override
        public void run() {
            synchronized (lock) {
                // 当调用notify方法后，锁并不会立即释放，而是会等待当前任务执行完毕
                lock.notify();
                System.out.println(Thread.currentThread().getName() + "调用notify");
            }
        }
    }

}
