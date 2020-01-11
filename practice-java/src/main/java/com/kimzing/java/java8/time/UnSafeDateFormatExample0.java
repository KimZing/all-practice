package com.kimzing.java.java8.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 不安全的格式化示例.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/9/25 19:41
 */
public class UnSafeDateFormatExample0 {


    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(5000);
        final Semaphore semaphore = new Semaphore(50);

        for (int i = 0; i < 5000; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    format();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    private static void format() {
        try {
            format.parse("1999-12-01 12:12");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
