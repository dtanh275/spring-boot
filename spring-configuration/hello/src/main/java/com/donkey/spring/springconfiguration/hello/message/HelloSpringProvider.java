package com.donkey.spring.springconfiguration.hello.message;

import com.donkey.spring.springconfiguration.hello.interfaces.MessageProvider;

public class HelloSpringProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Hello, Spring!!!";
    }
}
