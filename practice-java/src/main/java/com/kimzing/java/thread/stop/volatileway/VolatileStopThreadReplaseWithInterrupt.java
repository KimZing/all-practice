package com.kimzing.java.thread.stop.volatileway;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 错误的方式，当有阻塞时，会无法正确响应中断请求.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-09 17:43
 */
public class VolatileStopThreadReplaseWithInterrupt {

    public static void main(String[] args) throws InterruptedException {

        // 容纳10个元素的阻塞队列
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(queue);
        Thread thread = new Thread(producer);
        // 启动生产者
        thread.start();

        // 直接用主线程当消费者
        while (Math.random() < 0.9D) {
            System.out.println("消费:" + queue.take());
            // 消费很慢，导致生产者会被阻塞
            Thread.sleep(100);
        }

        System.out.println("消费线程停止消费，并通知生产者停止生产");
        thread.interrupt();
    }

}

class Producer implements Runnable{

    private BlockingQueue<Integer> blockingQueue;

    public Producer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                System.out.println("生产:" + i);
                // 阻塞方法
                blockingQueue.put(i);
            } catch (InterruptedException e) {
                System.out.println("生产线程接收到中断信号");
                break;
            } finally {
                System.out.println("生产线程已经停止生产元素");
            }
        }
    }
}
