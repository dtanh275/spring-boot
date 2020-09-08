package com.donkey.spring.di.javaconfiguration.annotated;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.donkey.spring.di.javaconfiguration.annotated")
public class SpringAnnotationConfig {
}
