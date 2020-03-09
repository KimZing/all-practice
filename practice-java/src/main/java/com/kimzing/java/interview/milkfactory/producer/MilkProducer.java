package com.kimzing.java.interview.milkfactory.producer;

/**
 * 牛奶生产线程.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/3/9 17:18
 */
public class MilkProducer implements Runnable{

    @Override
    public void run() {
        while(true) {
            System.out.println("生产了两份牛奶");
        }
    }

}
