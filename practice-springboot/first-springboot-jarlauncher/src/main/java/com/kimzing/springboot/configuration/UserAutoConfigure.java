package com.kimzing.springboot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * User自动装配.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/2/6 22:29
 */
@Configuration
@Import(UserConfiguration.class)
public class UserAutoConfigure {
}
