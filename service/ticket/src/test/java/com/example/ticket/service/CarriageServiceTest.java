package com.example.ticket.service;

import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @create 2023/10/16 10:51
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@RequiredArgsConstructor
public class CarriageServiceTest {

    @Autowired
    private ApplicationContext applicationContext;

    static {

    }

    @Test
    public void listCarriageNumber(){
        CarriageService carriageService = applicationContext.getBean(CarriageService.class);
        List<String> strings = carriageService.listCarriageNumber("1", 1);
        System.out.println(strings);
    }
}
