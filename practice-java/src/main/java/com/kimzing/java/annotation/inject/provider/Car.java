package com.kimzing.java.annotation.inject.provider;

import com.kimzing.java.annotation.inject.annotation.Service;
import lombok.Data;
import lombok.extern.java.Log;

/**
 * 汽车.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/9/28 19:43
 */
@Data
@Log
@Service(name = "bmw")
public class Car {

    public void start() {
        log.info("车已经启动");
    }

}
