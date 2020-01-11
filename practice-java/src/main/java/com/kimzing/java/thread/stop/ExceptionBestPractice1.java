package com.kimzing.java.thread.stop;

/**
 * 下游方法优先将异常向上抛出，由上游服务进行处理(恢复、记录、停止).
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-06 16:13
 */
public class ExceptionBestPractice1 implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ExceptionBestPractice1());
        thread.start();
        Thread.sleep(3000);
        thread.interrupt();


    }

    @Override
    public void run() {
        int a = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(a++);
            try {
                doWork();
            } catch (InterruptedException e) {
                // 1. 通过记录日志进行处理
                //System.out.println("记录日志");

                // 通过中断程序进行处理
                System.out.println("中断线程");
                break;
            }
        }

    }

    private void doWork() throws InterruptedException {
        Thread.sleep(2000);
    }
}
