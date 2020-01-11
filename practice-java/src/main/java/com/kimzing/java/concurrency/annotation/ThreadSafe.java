package com.kimzing.java.concurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 线程安全标志，被标注的类<b>是线程安全</b>的.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018-08-07 02:02
 */
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.SOURCE)
public @interface ThreadSafe {

    String value() default "";

}
