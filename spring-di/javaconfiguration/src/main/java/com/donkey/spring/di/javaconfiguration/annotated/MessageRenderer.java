package com.donkey.spring.di.javaconfiguration.annotated;

public interface MessageRenderer {
    void setProvider(MessageProvider provider);

    String render();
}
