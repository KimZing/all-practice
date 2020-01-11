package com.kimzing.java.thread.create.ext;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-06 10:52
 */
public class OneThreadMultiTask {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable1 = () -> System.out.println(Thread.currentThread().getName() + ": Task1");
        Runnable runnable2 = () -> System.out.println(Thread.currentThread().getName() + ": Task2");

        MultiTaskThread thread = new MultiTaskThread();
        thread.setRunnable(runnable1);
        thread.start();
        Thread.sleep(3000);
        thread.setRunnable(runnable2);

    }

}

class MultiTaskThread extends Thread {

    public Runnable runnable;

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        if (runnable != null) {
            while (true) {
                runnable.run();
            }
        }
    }
}
