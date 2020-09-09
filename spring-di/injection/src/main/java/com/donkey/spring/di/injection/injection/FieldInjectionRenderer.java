package com.donkey.spring.di.injection.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("fieldRenderer")
public class FieldInjectionRenderer {
    @Autowired
    @Qualifier("fieldMessage")
    private String message;

    public String render() {
        return message;
    }

}
