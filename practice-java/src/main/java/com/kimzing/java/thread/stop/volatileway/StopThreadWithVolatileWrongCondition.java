package com.kimzing.java.thread.stop.volatileway;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 错误的方式，当有阻塞时，会无法正确响应中断请求.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-09 17:43
 */
public class StopThreadWithVolatileWrongCondition {

    public static void main(String[] args) throws InterruptedException {
        StopThreadWithVolatileWrongCondition demo = new StopThreadWithVolatileWrongCondition();
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        Producer producer = demo.new Producer(queue);
        Thread thread = new Thread(producer);
        thread.start();


        while (Math.random() < 0.9) {
            System.out.println("Take : " + queue.take());
            // 消费很慢，所以生产者会被阻塞
            Thread.sleep(100);
        }

        System.out.println("停止消费");
        producer.stopFlag = true;
    }

    class Producer implements Runnable{

        private BlockingQueue<Integer> blockingQueue;

        private volatile Boolean stopFlag = false;

        public Producer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                if (stopFlag) {
                    break;
                }
                try {
                    System.out.println("PUT :" + i);
                    // 满了会阻塞在这里
                    blockingQueue.put(i);
                } catch (InterruptedException e) {
                    System.out.println("PUT ERROR");
                    break;
                }
            }
        }
    }

}
