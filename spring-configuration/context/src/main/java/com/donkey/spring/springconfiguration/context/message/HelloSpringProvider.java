package com.donkey.spring.springconfiguration.context.message;

import com.donkey.spring.springconfiguration.context.interfaces.MessageProvider;

public class HelloSpringProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Hello, Spring Context!!!";
    }
}
