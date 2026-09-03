package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    private static final ApplicationContext context = new ClassPathXmlApplicationContext(
            "ApplicationContext.xml");

    public static ApplicationContext getContext() {
        return context;
    }

}
