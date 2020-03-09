package com.kimzing.java.interview.milkfactory;

import com.kimzing.java.interview.milkfactory.consumer.CarConsumer;
import com.kimzing.java.interview.milkfactory.container.WareHouse;
import com.kimzing.java.interview.milkfactory.producer.CheeseProducer;

/**
 * 奶酪工厂.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/3/9 17:19
 */
public class CheeseFactory {


    public static void main(String[] args) {
        // 准备
        int carCapacity = 100;
        int wareHouseCapacity = 1000;
        int maxCheeseNum = 10_0000;
        WareHouse wareHouse = new WareHouse(wareHouseCapacity);

        // 生产
        CheeseProducer cheeseProducer = new CheeseProducer(wareHouse, maxCheeseNum);
        startThread(cheeseProducer, "cheese producer");

        // 消费者
        CarConsumer carConsumer = new CarConsumer(wareHouse, carCapacity);
        startThread(carConsumer, "car consumer");
    }

    private static void startThread(Runnable runnable, String name) {
        new Thread(runnable, name).start();
    }



}
