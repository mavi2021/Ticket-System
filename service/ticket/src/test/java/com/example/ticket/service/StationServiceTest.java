package com.example.ticket.service;

import com.example.ticket.dto.resp.StationQueryRespDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @create 2023/10/16 13:21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StationServiceTest {

    @Autowired
    private ApplicationContext applicationContext;

    private StationService stationService;

    @PostConstruct
    public void init(){
        stationService = applicationContext.getBean(StationService.class);
    }


    @Test
    public void listAllStation(){
        List<StationQueryRespDTO> stationQueryRespDTOS = stationService.listAllStation();
        System.out.println(stationQueryRespDTOS);
    }
}
