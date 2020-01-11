package com.kimzing.java.thread.method.producerconsumer;

import java.util.LinkedList;

/**
 * 生产者与消费者模式，使用notify与wait机制.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-16 18:18
 */
public class ProducerConsumerModel {

    public static void main(String[] args) {
        Storage storage = new Storage();
        new Thread(new Producer(storage)).start();
        new Thread(new Consumer(storage)).start();
    }


    static class Producer implements Runnable {

        private Storage storage;

        public Producer(Storage storage) {
            this.storage = storage;
        }

        @Override

        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    storage.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable{

        private Storage storage;

        public Consumer(Storage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    storage.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Storage {

        private int maxSize;

        private LinkedList<Integer> data;

        public Storage() {
            maxSize = 10;
            data = new LinkedList<>();
        }

        public synchronized void put(int i) throws InterruptedException {
            if (data.size() >= maxSize) {
                wait();
            }
            System.out.println("Producer:" + i);
            data.add(i);
            notify();
        }

        public synchronized void take() throws InterruptedException {
            if (data.size() == 0) {
                wait();
            }
            System.out.println("Consumer:" + data.poll());

            notify();
        }

    }

}
