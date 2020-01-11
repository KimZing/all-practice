package com.kimzing.java.thread.stop.volatileway;

/**
 * 看似正确的volatile停止线程, 没有阻塞时，是ok的.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-09 17:35
 */
public class StopThreadWithVolatileFlagRightCondition {

    private static volatile Boolean stopFlag = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                if (stopFlag) {
                    break;
                }
                System.out.println(i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("Sleep Wrong");
                }
            }
        });

        thread.start();
        Thread.sleep(1000);
        stopFlag = true;
    }

}
