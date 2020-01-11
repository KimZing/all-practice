package com.kimzing.java.java8.time;

import lombok.extern.java.Log;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;

/**
 * 时间类操作.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/9/25 17:59
 */
@Log
public class LocalDateTimeOperatorExample2 {

    public static void main(String[] args) {

       LocalDateTime today = LocalDateTime.now();

       // 获取当前年，并判断是否是闰年
       log.info("Year " + today.getYear() + " is Leap Year? " + today.toLocalDate().isLeapYear());

       // 比较两个日期的先后
       log.info("Today is before 01/01/2015? " + today.isBefore(LocalDateTime.of(2015, 1, 1, 11, 30)));

       // 时间加减
       log.info("10 days after today will be " + today.plusDays(10));
       log.info("3 weeks after today will be " + today.plusWeeks(3));
       log.info("20 months after today will be " + today.plusMonths(20));
       log.info("10 days before today will be " + today.minusDays(10));
       log.info("3 weeks before today will be " + today.minusWeeks(3));
       log.info("20 months before today will be " + today.minusMonths(20));

       // 查询日期的特定点
       log.info("First date of this month= " + today.with(TemporalAdjusters.firstDayOfMonth()));
       LocalDate lastDayOfYear = today.toLocalDate().with(TemporalAdjusters.lastDayOfYear());
       log.info("Last date of this year= " + lastDayOfYear);

       // 两个日期间的间隔
       Period period = today.toLocalDate().until(lastDayOfYear);
       log.info("Period Format= " + period);
    }

}
