package com.kimzing.java.java8.stream;

import lombok.val;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/11/25 13:20
 */
public class MapAndFlatMapDemo {

    public static void main(String[] args) {
        // MARK map用来将流中的每个元素的某个属性转换为一个集合
        List<Student> studentList = getStudents("一");
        List<String> collect = studentList.stream().map(s -> s.getName()).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);

        // MARK flatMap可以将外层元素对应的流进行合并
        List<Student> studentList1 = getStudents("一");
        List<Student> studentList2 = getStudents("二");
        List<List<Student>> gradeList = new ArrayList<>();
        gradeList.add(studentList1);
        gradeList.add(studentList2);

        val hello = new Student("小金1", 18, "", 99L);

        gradeList.stream().flatMap(s -> s.stream()).forEach(System.out::println);

    }

    private static List<Student> getStudents(String grade) {
        Student student1 = new Student("小金1", 18, grade, 99L);
        Student student2 = new Student("小金2", 19, grade, 100L);
        Student student3 = new Student("小金3", 120, grade, 101L);
        return Arrays.asList(student1, student2, student3);
    }

}
