package com.kimzing.springboot.configuration;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/2/6 21:56
 */
// @SpringBootApplication
@EnableAutoConfiguration  // 与springbootapplication注解还是有区别的  在Bean类型上会有所区别
public class WebConfiguration {

    @Bean
    public RouterFunction<ServerResponse> hello() {
        return route(GET("/hello"), request -> ok().body(Mono.just("Hello !"), String.class));
    }

    // 验证容器类型
    @Bean
    public ApplicationRunner runner(WebServerApplicationContext applicationContext) {
        // 验证在@SpringBootApplication-> cglib提升和@EnableAutoConfiguration-> 普通bean 情况下的bean的类型
        WebConfiguration bean = applicationContext.getBean(WebConfiguration.class);

        // 验证自动装配
        System.out.println(applicationContext.getBean(UserConfiguration.User.class).name);
        return args -> System.out.println(applicationContext.getClass().getName());
    }

    // 另一种方式验证容器类型
    @EventListener
    public void checkWebServerApplicationType(WebServerInitializedEvent event) {
        System.out.println(event.getApplicationContext().getClass().getName());
    }

}
