package com.kimzing.java.interview.milkfactory.producer;

import com.kimzing.java.interview.milkfactory.container.WareHouse;

/**
 * 奶酪生产线程.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/3/9 17:18
 */
public class CheeseProducerThread implements Runnable{

    private WareHouse wareHouse;

    private Integer maxProduceNum;

    public CheeseProducerThread(WareHouse wareHouse, Integer maxCheeseNum) {
        this.wareHouse = wareHouse;
        this.maxProduceNum = maxCheeseNum;
    }

    @Override
    public void run() {
        try {
            wareHouse.put();
        } catch (InterruptedException e) {
            System.out.println("线程中断异常");
        }
    }

}
