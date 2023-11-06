package com.example.ticket.context;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    @Getter
    private static ApplicationContext APPLICATIONCONTEXT;

    public static <T> T getBean(Class<T> clazz){
        return APPLICATIONCONTEXT.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.APPLICATIONCONTEXT = applicationContext;
    }

    public static <T> Map<String, T> getBeanOfType(Class<T> clazz){
        return APPLICATIONCONTEXT.getBeansOfType(clazz);
    }

}
