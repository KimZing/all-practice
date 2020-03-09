package com.kimzing.java.interview.milkfactory.consumer;

import com.kimzing.java.interview.milkfactory.container.WareHouse;

/**
 * 汽车运输消费线程.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/3/9 17:20
 */
public class CarConsumer implements Runnable{

    private WareHouse wareHouse;

    // 配送车容量
    private Integer capacity;

    public CarConsumer(WareHouse wareHouse, Integer capacity) {
        this.wareHouse = wareHouse;
        this.capacity = capacity;
    }

    @Override
    public void run() {
        // 记录总运送奶酪份数
        int sum = 0;
        try {
            while (true) {
                if (wareHouse.size() >= capacity) {
                    // 去除当前最大容量的奶酪
                    for (int i = 0; i < capacity; i++) {
                        wareHouse.take();
                    }
                    sum += 100;
                    System.out.println(String.format("%s:当前运送了%d份奶酪", Thread.currentThread().getName(), sum));
                }
            }
        } catch (InterruptedException e) {
            System.err.println("线程中断异常");
        }
    }

}
