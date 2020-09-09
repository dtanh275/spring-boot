package com.donkey.spring.di.injection.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("setterRenderer")
public class SetterInjectionRenderer {

    private String message;

    @Autowired
    @Qualifier("setterMessage")
    public void setMessage(String message) {
        this.message = message;
    }

    public String render() {
        return message;
    }

}
