package com.kimzing.springboot;

import com.kimzing.springboot.configuration.WebConfiguration;
import org.springframework.boot.SpringApplication;

/**
 * 在2.x版本已经发生变化
 * @SpringBootConfiguration
 * @EnableAutoConfiguration
 * @ComponentScan(excludeFilters = {
 *                @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
 *        		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
 */
// @SpringBootApplication   //并非一定要是启动类，可以放在其它类上
// @EnableAutoConfiguration
// @SpringBootConfiguration
// @ComponentScan
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebConfiguration.class, args);

	}


}
