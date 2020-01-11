package com.kimzing.java.java8.time;

import java.time.*;

/**
 * 创建示例.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/9/25 17:56
 */
public class LocalDateTimeCreateExample1 {

    public static void main(String[] args) {

        //获取当前时间
        LocalDateTime today = LocalDateTime.now();

        //使用LocalDate and LocalTime 创建LocalDateTime
        today = LocalDateTime.of(LocalDate.now(), LocalTime.now());

        //通过of方法参数创建
        LocalDateTime specificDate = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30);

        //不正确的参数会抛出异常
        //Exception in thread "main" java.time.DateTimeException: Invalid value for HourOfDay (valid values 0 - 23): 25
        LocalDateTime illegalDate = LocalDateTime.of(2014, Month.FEBRUARY, 28, 25, 1, 1);

        //获取"Asia/Kolkata",加尔哥达
        LocalDateTime todayKolkata = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));

        //通过时间戳获取时间
        LocalDateTime dateFromBase = LocalDateTime.ofEpochSecond(10000, 0, ZoneOffset.UTC);
    }

}
