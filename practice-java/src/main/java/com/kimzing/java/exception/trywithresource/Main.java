package com.kimzing.java.exception.trywithresource;

import lombok.extern.java.Log;

/**
 * @author KimZing - kimzing@163.com
 * @date 2017/10/16 下午5:30
 * @desc 主方法.
 */
@Log
public class Main {

    public static void main(String[] args) {
        try (Computor computor = new Computor()) {
            log.info("computor is working");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
