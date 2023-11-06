package com.example.ticket.context;

import com.example.ticket.service.SeatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationContextHolderTest {

    @Test
    public void getApplicationContext(){
        ApplicationContext applicationContext = ApplicationContextHolder.getAPPLICATIONCONTEXT();
        System.out.println(applicationContext);
    }

    @Test
    public void getBean(){
        SeatService bean = ApplicationContextHolder.getBean(SeatService.class);
        System.out.println(bean);
        SeatService bean2 = ApplicationContextHolder2.getBean(SeatService.class);
        System.out.println(bean2);
    }
}
