package com.kimzing.java.classloader;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2018-12-31 14:16
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        MyClassLoader classLoader = new MyClassLoader("/Users/kimzing/Desktop/", "Kim");
        Class<?> kim = classLoader.loadClass("Kim");
        kim.newInstance();

        // 查看classloader的parent
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());
        System.out.println(classLoader.getParent().getParent().getParent());

        // Class.forName 与 classloader.loadClass区别
        Class.forName("com.kimzing.classloader.Robot");
        /*
        ClassLoader loader = Robot.class.getClassLoader();
        loader.loadClass("com.kimzing.classloader.Robot");
        */
    }

}
