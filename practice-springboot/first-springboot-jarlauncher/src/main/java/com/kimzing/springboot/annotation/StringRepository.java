package com.kimzing.springboot.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 字符仓储.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/2/7 09:41
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface StringRepository {

    @AliasFor(annotation = Component.class)
    String value() default "";

}
