package com.kimzing.java.generic.generalgeneric;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/3/27 16:41
 */
public class AnimalGenericDemo<T> {

    public static List<? extends Animal> getList1(List<? extends Animal> animals) {
        // 上界限定时，无法对其进行修改
        // animals.add(new Cat());
        return animals;
    }

    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog());
        List<? extends Animal> list1 = getList1(dogs);
        // 站在使用者的角度，肯定想转换回原来的类型，而如果添加了Cat，当然会报错了。
        list1.stream().forEach(d -> System.out.println(((Dog) d)));

        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal());
        List<? super Dog> list2 = getList2(animals);
        // 取出的是Object类型
        Object object = list2.get(0);
        // 站在使用者的角度，想转换回原来的类型Animal,
        //我们在内部添加了Dog的子类Samoyed, Samoyed同样也是Animal的子类，所以转换时不会报错的。
        list2.stream().forEach(d -> System.out.println(((Animal) d)));

    }

    public static List<? super Dog> getList2(List<? super Dog> animals) {
        animals.add(new Samoyed());
        return animals;
    }


}
