package com.donkey.spring.springconfiguration.context.interfaces;

public interface MessageRenderer {
    void setProvider(MessageProvider provider);

    String render();
}
