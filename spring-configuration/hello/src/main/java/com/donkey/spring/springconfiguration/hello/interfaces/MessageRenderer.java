package com.donkey.spring.springconfiguration.hello.interfaces;

public interface MessageRenderer {
    void setProvider(MessageProvider provider);

    String render();
}
