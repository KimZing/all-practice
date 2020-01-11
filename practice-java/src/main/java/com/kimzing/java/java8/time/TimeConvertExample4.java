package com.kimzing.java.java8.time;

import lombok.extern.java.Log;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 与旧API的转换.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/9/25 19:21
 */
@Log
public class TimeConvertExample4 {

    /**
     * 主方法
     * @param args
     */
    public static void main(String[] args) {
        // Date转换为新的API
        Date date = new Date();
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.of(ZoneId.SHORT_IDS.get("PST")));

        // LocalDateTime转换为Date
        Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

        // Calendar转换为Instant
        Instant time = Calendar.getInstance().toInstant();

        // TimeZone 转换为 ZoneId
        ZoneId defaultZone = TimeZone.getDefault().toZoneId();
        log.info(defaultZone + "");

    }

}
