package com.kimzing.java.algorithm.selection;

import lombok.extern.java.Log;

/**
 * @author KimZing - kimzing@163.com
 * @date 2017/12/20 下午12:53
 * @desc 排序基础工具.
 */
@Log
public class SwapUtil {


    public static void println(int[] arr) {
        for (int i : arr) {
            log.info(i + "");
        }
    }


    public static int[] getArr() {
        return new int[]{5, 2, 45, 1, 33};
    }

    public static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }
}
