package com.kimzing.java.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型示例.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/3/27 11:49
 */
public class GenericDemo {

    public static void main(String[] args) {
        // test1();
        // test2();
        test3();
    }

    public static void test3 () {
        List<String> stringList = getExpectStringListWithType();
        stringList.stream().forEach(s -> System.out.println(s));
    }

    public static void test2() {
        List stringList = getExpectStringListNoType();
        stringList.stream()
                .map(s -> {
                    if (s instanceof String) {
                        return ((String) s);
                    }
                    return null;
                })
                .filter(s -> s != null)
                .forEach(s -> System.out.println(s));
    }

    public static void test1() {
        List stringList = getExpectStringListNoType();
        stringList.stream()
                // 强转为String类型，因为期望的就是只放String
                .map(s -> ((String) s))
                // 编译器不会报错，但是运行期因为含有非期望元素，会报错。
                .forEach(s -> System.out.println(s));
    }

    public static List getExpectStringListNoType() {
        ArrayList stringList = new ArrayList();
        stringList.add("Hello");
        stringList.add("World");
        // 参数为object类型，也就是说可以添加任何类型，无法阻止其他人添加一个int的元素
        stringList.add(123);
        return stringList;
    }

    public static List<String> getExpectStringListWithType() {
        ArrayList<String> stringList = new ArrayList();
        stringList.add("Hello");
        stringList.add("World");
        // 编译器会进行检查，编译报错
        // stringList.add(123);
        return stringList;
    }
}
