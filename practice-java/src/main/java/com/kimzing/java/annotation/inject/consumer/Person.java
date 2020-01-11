package com.kimzing.java.annotation.inject.consumer;

import com.kimzing.java.annotation.inject.annotation.Autowire;
import com.kimzing.java.annotation.inject.annotation.Service;
import com.kimzing.java.annotation.inject.provider.Car;
import lombok.extern.java.Log;

/**
 * 人.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/9/28 19:45
 */
@Log
@Service(name = "kim")
public class Person {

    @Autowire(name = "bmw")
    private Car car;


    public void drive() {
        car.start();
        log.info("开车中");
    }
}
