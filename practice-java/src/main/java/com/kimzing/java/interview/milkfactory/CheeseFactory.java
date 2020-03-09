package com.kimzing.java.interview.milkfactory;

import com.kimzing.java.interview.milkfactory.consumer.CarConsumerThread;
import com.kimzing.java.interview.milkfactory.container.WareHouse;
import com.kimzing.java.interview.milkfactory.producer.CheeseProducerThread;
import com.kimzing.java.interview.milkfactory.producer.FermentationProducerThread;
import com.kimzing.java.interview.milkfactory.producer.MilkProducerThread;

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
        MilkProducerThread milkProducer = new MilkProducerThread();
        FermentationProducerThread fermentationProducer = new FermentationProducerThread();
        CheeseProducerThread cheeseProducer = new CheeseProducerThread(wareHouse, maxCheeseNum);
        startThread(milkProducer, "milk producer");
        startThread(fermentationProducer, "fermentation producer");
        startThread(cheeseProducer, "cheese producer");

        // 消费者
        CarConsumerThread carConsumer = new CarConsumerThread(wareHouse, carCapacity);
        startThread(carConsumer, "car consumer");
    }

    private static void startThread(Runnable runnable, String name) {
        new Thread(runnable, name).start();
    }



}
