package com.donkey.spring.di.injection.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("defaultRenderer")
public class DefaultMessageRenderer {

    private String message;

    @Autowired
    public DefaultMessageRenderer(@Value("Hello, default value") String message) {
        this.message = message;
    }

    public String render() {
        return message;
    }

}
