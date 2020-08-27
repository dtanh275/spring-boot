package com.donkey.spring.springconfiguration.annotation.interfaces;

public interface MessageRenderer {
    void setProvider(MessageProvider provider);

    String render();
}
