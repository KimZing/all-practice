package com.kimzing.java.interview.finalmthod;

/**
 * 测试子类能否使用父类的final方法.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/8/22 23:20
 */
public class Child extends ParentFinal {

    public static void main(String[] args) {
        Child child = new Child();
        child.hello();
    }

}
