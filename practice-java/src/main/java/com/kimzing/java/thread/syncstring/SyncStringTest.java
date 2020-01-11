package com.kimzing.java.thread.syncstring;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/10/29 11:24
 */
public class SyncStringTest {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                //noSync("123");
                withSync("123");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                //noSync("123");
                withSync("123");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void noSync(String id) throws InterruptedException {
        System.out.println("start");
        Thread.sleep(1000);
        System.out.println("end");
    }

    public static void withSync(String id) throws InterruptedException {
        synchronized (id.intern()) {
            System.out.println("start");
            Thread.sleep(1000);
            System.out.println("end");
        }
    }

}
