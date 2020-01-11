package com.kimzing.java.thread.stop;

/**
 * 下游方法优先将异常向上抛出，由上游服务进行处理(恢复、记录、停止).
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-06 16:13
 */
public class ExceptionBestPractice2 implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ExceptionBestPractice2());
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();

    }

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("终止");
                break;
            }
            doWork();
        }

    }

    private void doWork() {
        try {
            System.out.println("working");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
