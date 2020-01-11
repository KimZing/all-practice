package com.kimzing.java.thread.method;

/**
 * 使用notifyAll进行唤醒，包含对比与notify的区别.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-16 14:00
 */
public class WaitAndNotifyAll implements Runnable{

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        WaitAndNotifyAll runnable = new WaitAndNotifyAll();
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        // 同时运行两个线程，两个线程都会进行waiting状态
        t1.start();
        t2.start();

        Thread.sleep(200);
        // 打印线程状态
        System.out.println(t1.getState());
        System.out.println(t2.getState());

        new Thread(() -> {
            // 因为notify,notifyAll是唤醒某个对象的等待线程，所以必需放在synchronized中
           synchronized (lock) {
               // MARK 因为notify只会唤醒等待线程的其中一个，所以只会唤醒其中一个线程，另外一个会一直阻塞
               // MARK notify()或者notifyAll()方法并不是真正释放锁，必须等到synchronized方法或者语法块执行完才真正释放锁
               //lock.notify();
               // 会唤醒所有等待线程，都会正常执行完毕
               lock.notifyAll();
           }
        }).start();

    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " is running");
            System.out.println(Thread.currentThread().getName() + " is waiting");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupet");
            }
            System.out.println(Thread.currentThread().getName() + " finished");
        }
    }


}
