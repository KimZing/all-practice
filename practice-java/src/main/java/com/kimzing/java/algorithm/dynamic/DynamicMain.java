package com.kimzing.java.algorithm.dynamic;

import lombok.extern.java.Log;

import java.util.HashMap;

/**
 * @author KimZing - kimzing@163.com
 * @date 2017/10/18 上午1:25
 * @desc 动态规划算法.
 */
@Log
public class DynamicMain {

    public static void main(String[] args) {
        //使用递归
        long start1 = System.nanoTime();
        log.info("递归算法：" + getCountByRecursion(25) + ";耗时:" + (System.nanoTime() - start1));
        //递归优化
        long start2 = System.nanoTime();
        log.info("递归优化：" + getCountByRecursionMore(25) + ";耗时:" + (System.nanoTime() - start2));
        //迭代
        long start3 = System.nanoTime();
        log.info("迭代：" + getCountByIteration(25) + ";耗时:" + (System.nanoTime() - start3));

    }

    public static int getCountByRecursion(int x) {
        if (x < 1) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }
        if (x == 2) {
            return 2;
        }
        return getCountByRecursion(x - 1) + getCountByRecursion(x - 2);
    }

    private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    public static int getCountByRecursionMore(int x) {
        if (x < 1) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }
        if (x == 2) {
            return 2;
        }
        //如果map包含该值，直接返回
        if (map.containsKey(x)) {
            return map.get(x);
        }
        //不包含就继续计算
        int value = getCountByRecursionMore(x - 1) + getCountByRecursionMore(x - 2);
        map.put(x, value);
        return value;
    }

    public static int getCountByIteration(int x) {
        if (x < 1) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }
        if (x == 2) {
            return 2;
        }
        int a = 1;
        int b = 2;
        int temp = 0;
        for (int i = 3; i <= x; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }
        return temp;
    }

}
