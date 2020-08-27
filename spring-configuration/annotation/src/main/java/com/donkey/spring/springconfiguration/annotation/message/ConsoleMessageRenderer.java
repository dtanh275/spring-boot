package com.donkey.spring.springconfiguration.annotation.message;

import com.donkey.spring.springconfiguration.annotation.interfaces.MessageProvider;
import com.donkey.spring.springconfiguration.annotation.interfaces.MessageRenderer;

public class ConsoleMessageRenderer implements MessageRenderer {

    private MessageProvider provider;

    @Override
    public void setProvider(MessageProvider provider) {
        this.provider = provider;
    }

    @Override
    public String render() {
        return provider.getMessage();
    }
}
