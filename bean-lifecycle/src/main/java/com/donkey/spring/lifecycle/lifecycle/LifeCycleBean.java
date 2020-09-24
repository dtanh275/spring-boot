package com.donkey.spring.lifecycle.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class LifeCycleBean implements BeanNameAware, BeanClassLoaderAware,
                                      ApplicationContextAware, InitializingBean, DisposableBean {
    @Override
    public void setBeanName(String name) {
        System.out.println(String.format("--- setBeanName([%s]) executed ---", name));
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("--- setBeanClassLoader() executed ---");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("--- setApplicationContext() executed ---");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("--- @PostConstruct executed ---");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("--- afterPropertiesSet() executed ---");
    }

    public void initMethod() {
        System.out.println("--- init-method executed ---");
    }

    public void annotationInitMethod() {
        System.out.println("--- annotationInitMethod() executed ---");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("--- @PreDestroy executed ---");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("--- destroy executed ---");
    }

    public void destroyMethod() {
        System.out.println("--- destroy-method executed ---");
    }

    public void annotationDestroyMethod() {
        System.out.println("--- annotationDestroyMethod() executed ---");
    }

}
