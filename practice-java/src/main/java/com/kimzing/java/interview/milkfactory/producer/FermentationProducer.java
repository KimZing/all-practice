package com.kimzing.java.interview.milkfactory.producer;

/**
 * 发酵剂生产线程.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/3/9 17:19
 */
public class FermentationProducer implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("生产了一份添加剂");
        }
    }

}
