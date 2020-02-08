package com.kimzing.springboot.configuration;

import org.springframework.context.annotation.Bean;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/2/6 22:32
 */
public class UserConfiguration {

    @Bean
    public User user() {
        return new User();
    }


    static class User {

        public String name = "kim";

        public User() {

        }
    }

}
