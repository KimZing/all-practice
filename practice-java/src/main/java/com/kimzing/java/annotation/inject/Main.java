package com.kimzing.java.annotation.inject;

import com.kimzing.java.annotation.inject.factory.BeanFactory;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/9/28 19:49
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.init();
    }

}
