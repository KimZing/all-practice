package com.kimzing.java.thread.state;

/**
 * 三种线程状态New,Runnable,Terminated.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-10 10:05
 */
public class NewRunnableTerminated {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println(i);
            }
        });
        // 创建并未运行 NEW
        System.out.println(thread.getState());
        thread.start();
        // 刚刚启动的状态-> RUNNABLE
        System.out.println(thread.getState());
        Thread.sleep(10);
        // 线程运行中的状态->RUNNABLE
        System.out.println(thread.getState());
        Thread.sleep(1000);
        // 运行结束-> TERMINATEDs
        System.out.println(thread.getState());
    }

}
