package com.kimzing.java.thread.create.decorate;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-06 10:21
 */
public class TimerWay {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时任务");
            }
        }, 1000, 1000);
    }

}
