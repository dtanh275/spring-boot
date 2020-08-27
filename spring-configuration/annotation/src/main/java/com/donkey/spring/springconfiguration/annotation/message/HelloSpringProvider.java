package com.donkey.spring.springconfiguration.annotation.message;

import com.donkey.spring.springconfiguration.annotation.interfaces.MessageProvider;

public class HelloSpringProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Hello, Spring Annotation!!!";
    }
}
