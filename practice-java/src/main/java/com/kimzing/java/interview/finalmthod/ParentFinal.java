package com.kimzing.java.interview.finalmthod;

import lombok.extern.java.Log;

/**
 * 父类，拥有final方法.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/8/22 23:18
 */
@Log
public class ParentFinal {


    public final void hello() {
        log.info("Hello World!");
    }

}
