package com.donkey.spring.di.injection.collection;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

@Service("injectCollection")
public class CollectionInjection {

    @Autowired
    private List<NameHolder> allMember;

    @Autowired
    @Qualifier("allGirls")
    private List<NameHolder> allGirls;

    @Resource(name = "allBoys")
    private List<NameHolder> allBoys;

    public static void main(String... args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(CollectionConfig.class);

        CollectionInjection instance = (CollectionInjection) ctx.getBean("injectCollection");
        instance.displayInfo();
    }

    public void displayInfo() {
        System.out.println("All members:\n");
        allMember.forEach(obj -> System.out.println("Value: " + obj));

        System.out.println("\nAll girls:\n");
        allGirls.forEach(obj -> System.out.println("Value: " + obj));


        System.out.println("\nAll boys:\n");
        allBoys.forEach(obj -> System.out.println("Value: " + obj));
    }

}
