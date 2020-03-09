package com.kimzing.java.interview.milkfactory.producer;

import com.kimzing.java.interview.milkfactory.container.WareHouse;

/**
 * 奶酪生产线程.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/3/9 17:18
 */
public class CheeseProducer implements Runnable{

    private WareHouse wareHouse;

    // 工厂一天生产的最大奶酪数量
    private Integer maxCheeseNum;

    public CheeseProducer(WareHouse wareHouse, Integer maxCheeseNum) {
        this.wareHouse = wareHouse;
        this.maxCheeseNum = maxCheeseNum;
    }

    @Override
    public void run() {
        try {
            // 记录当前生产的奶酪数量
            int i = 0;
            while (true) {
                if (i < maxCheeseNum) {
                    i++;
                    wareHouse.put(i);
                    System.out.println(String.format("%s:当前生产了%d份奶酪", Thread.currentThread().getName(), i));
                }
            }
        } catch (InterruptedException e) {
            System.out.println("线程中断异常");
        }
    }

}
