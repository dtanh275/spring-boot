package com.donkey.spring.di.javaconfiguration.annotated;

import org.springframework.stereotype.Component;

@Component("provider")
public class HelloSpringProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Hello, Spring Annotation!!!";
    }
}
