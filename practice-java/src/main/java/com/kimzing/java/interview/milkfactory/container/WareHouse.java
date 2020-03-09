package com.kimzing.java.interview.milkfactory.container;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 冷库存储.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/3/9 17:21
 */
public class WareHouse {

    // 使用阻塞队列充当存储角色
    private ArrayBlockingQueue<Integer> cheeses;

    public WareHouse(int capacity) {
        cheeses = new ArrayBlockingQueue(capacity);
    }

    /**
     * 放入奶酪
     */
    public void put(Integer i) throws InterruptedException {
        cheeses.put(i);
        // System.out.println(String.format("当前存储奶酪:%d", cheeses.size()));
    }

    /**
     * 取走奶酪
     */
    public void take() throws InterruptedException {
        cheeses.take();
        // System.out.println(String.format("当前剩余奶酪:%d", cheeses.size()));
    }

    /**
     * 当前仓库的奶酪数量
     * @return
     */
    public int size() {
        return cheeses.size();
    }

}
