package com.kimzing.java.thread.stop.wrong;

/**
 * 使用Stop强制结束线程.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-09 16:41
 */
public class StopWrongWay {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(i + " + 100");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    System.out.println("Sleep Error");
                }
                System.out.println(i + " - 100");
            }
        });
        thread.start();
        Thread.sleep(200);
        thread.stop();
    }

}
