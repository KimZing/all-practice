package com.kimzing.java.interview.milkfactory.consumer;

import com.kimzing.java.interview.milkfactory.container.WareHouse;

/**
 * 汽车运输消费线程.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/3/9 17:20
 */
public class CarConsumerThread implements Runnable{

    private WareHouse wareHouse;

    private Integer capacity;

    public CarConsumerThread(WareHouse wareHouse, Integer capacity) {
        this.wareHouse = wareHouse;
        this.capacity = capacity;
    }

    @Override
    public void run() {
        try {
            wareHouse.take();
        } catch (InterruptedException e) {
            System.out.println("线程中断异常");
        }
    }

}
