package com.donkey.spring.di.injection.injection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.donkey.spring.di.injection.injection")
public class SpringInjectionConfig {

    @Bean("message")
    public String message() {
        return "Hello, constructor injection";
    }

    @Bean("setterMessage")
    public String setterMessage() {
        return "Hello, setter injection";
    }

    @Bean("fieldMessage")
    public String fieldMessage() {
        return "Hello, filed injection";
    }
}
