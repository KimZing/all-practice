package com.kimzing.java.concurrency.count;

import com.kimzing.java.concurrency.annotation.ThreadSafe;
import lombok.extern.java.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用代码进行并发测试-安全示例.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/9/23 22:05
 */
@ThreadSafe
@Log
public class CountExample2 {

    /**
     * 客户端请求数量
     */
    private static int clientTotal = 5000;

    /**
     * 并发执行的线程数
     */
    private static int threadTotal = 100;

    /**
     * CountDownlatch的计数
     */
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.info(e.getLocalizedMessage());
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executor.shutdown();
        log.info("count num is " + count);

    }

    private static void add() {
        count.getAndIncrement();
    }

}
