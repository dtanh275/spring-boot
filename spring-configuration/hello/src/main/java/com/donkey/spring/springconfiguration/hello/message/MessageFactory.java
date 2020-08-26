package com.donkey.spring.springconfiguration.hello.message;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MessageFactory {
    private static MessageFactory instance;

    private Properties properties;

    private Map<String, Object> beans;

    public synchronized static MessageFactory getInstance() {
        if (instance == null) {
            instance = new MessageFactory();
        }
        return instance;
    }

    public <T> T getBean(String key, Class<T> clazz) {
        return clazz.cast(beans.get(key));
    }

    private MessageFactory() {
        properties = new Properties();
        beans = new HashMap<>();
        try {
            properties.load(getClass().getResourceAsStream("/ms.properties"));

            properties.stringPropertyNames().stream()
                      .filter(s -> s.startsWith("class"))
                      .forEach(this::constructInstance);

            properties.stringPropertyNames().stream()
                      .filter(s -> s.startsWith("ref-"))
                      .forEach(this::handleMethod);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void constructInstance(String key) {
        try {
            beans.put(key, Class.forName(properties.getProperty(key)).getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleMethod(String key) {
        try {
            String[] meta = key.split("-");
            Object refObj = beans.get(properties.getProperty(key));
            Object baseObj = beans.get(meta[1]);

            Method method = baseObj.getClass().getMethod(meta[2], refObj.getClass().getInterfaces()[0]);
            method.invoke(baseObj, refObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
