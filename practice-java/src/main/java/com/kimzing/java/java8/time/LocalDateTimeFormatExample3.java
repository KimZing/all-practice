package com.kimzing.java.java8.time;

import lombok.extern.java.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 格式化.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/9/25 18:59
 */
@Log
public class LocalDateTimeFormatExample3 {

    public static void main(String[] args) {
        //格式化日期
        LocalDateTime now = LocalDateTime.now();
        String formatTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd mm:ss"));
        log.info(formatTime);

        //通过指定格式解析日期
        LocalDateTime dateTime = LocalDateTime.parse("2018-10-10 12:12", DateTimeFormatter.ofPattern("yyyy-MM-dd mm:ss"));
    }

}
