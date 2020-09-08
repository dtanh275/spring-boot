package com.donkey.spring.di.javaconfiguration.annotated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("renderer")
public class ConsoleMessageRenderer implements MessageRenderer {

    private MessageProvider provider;

    @Override
    @Autowired
    public void setProvider(MessageProvider provider) {
        this.provider = provider;
    }

    @Override
    public String render() {
        return provider.getMessage();
    }
}
