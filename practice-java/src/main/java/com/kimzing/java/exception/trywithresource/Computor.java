package com.kimzing.java.exception.trywithresource;

import lombok.extern.java.Log;

/**
 * @author KimZing - kimzing@163.com
 * @date 2017/10/16 下午5:31
 * @desc 电脑.
 */
@Log
public class Computor implements AutoCloseable {


    @Override
    public void close() throws Exception {
        log.info("computor is closing!");
    }

}
