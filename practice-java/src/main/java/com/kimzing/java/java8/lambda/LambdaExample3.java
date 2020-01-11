package com.kimzing.java.java8.lambda;

/**
 * 自定义函数接口的完整示例.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/9/26 16:07
 */
public class LambdaExample3 {

    @FunctionalInterface
    interface Predicate <T> {
        boolean test(T t);
    }
    /**
     * 执⾏Predicate判断
     * @param age 年龄
     * @param predicate Predicate函数式接⼝
     * @return 返回布尔类型结果
     */
    public static boolean doPredicate(int age, Predicate<Integer> predicate) {
        return predicate.test(age);
    }

    public static void main(String[] args) {
        boolean isAdult = doPredicate(20, x -> x >= 18);
        System.out.println(isAdult);
    }
}
