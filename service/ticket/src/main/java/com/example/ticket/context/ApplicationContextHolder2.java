package com.example.ticket.context;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class ApplicationContextHolder2{

    private static ApplicationContext CONTEXT;

    @Resource
    private ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> clazz){
        return CONTEXT.getBean(clazz);
    }

    @PostConstruct
    void init(){
        CONTEXT = applicationContext;
    }
}
