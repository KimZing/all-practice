package com.kimzing.java.algorithm.selection;

/**
 * @author KimZing - kimzing@163.com
 * @date 2017/12/20 下午12:57
 * @desc 选择排序.
 * 思路：
 * 每次找出最大或者最小值进行循环排序
 */
public class SelectionSort {

    public static void main(String[] args) {
        //获取数组
        int[] arr = SwapUtil.getArr();
        //循环n - 1次
        for (int i = 0; i < arr.length - 1; i++) {
            /*
            每一趟循环比较时，min用于存放较小元素的数组下标，
            这样当前批次比较完毕最终存放的就是此趟内最小的元
            素的下标，避免每次遇到较小元素都要进行交换。
            */
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            //如果min发生变化，则进行交换
            if (min != i) {
                SwapUtil.swap(arr, min, i);
            }
        }
        SwapUtil.println(arr);
    }

}
