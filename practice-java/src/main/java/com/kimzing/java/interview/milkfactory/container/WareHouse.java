package com.kimzing.java.interview.milkfactory.container;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 冷库存储.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/3/9 17:21
 */
public class WareHouse {

    private AtomicInteger cheeses = new AtomicInteger(0);

    private Integer capacity;

    public WareHouse(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 放入奶酪
     */
    public void put() throws InterruptedException {
        if (cheeses.intValue() < capacity) {
            System.out.println(String.format("生产-当前奶酪:{}", cheeses.getAndIncrement()));
        } else {
            Thread.currentThread().wait();
        }
    }

    /**
     * 取走奶酪
     */
    public void take() throws InterruptedException {
        if (cheeses.intValue() >= 100) {
            cheeses.set(cheeses.intValue() - 100);
        } else {
            Thread.currentThread().wait();
        }
    }

}
