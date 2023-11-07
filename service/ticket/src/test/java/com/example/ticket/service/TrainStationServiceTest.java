package com.example.ticket.service;

import com.example.ticket.dto.resp.TrainStationQueryRespDTO;
import com.example.ticket.entity.Route;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @create 2023/10/15 15:15
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TrainStationServiceTest {

    @Resource
    private TrainStationService trainStationService;


    @Test
    public void listTrainStationQuery(){
        List<TrainStationQueryRespDTO> trainStationQueryRespDTOS = trainStationService.listTrainStationQuery(String.valueOf(1));
        System.out.println(trainStationQueryRespDTOS);
    }

    @Test
    public void listTrainStationRoute(){
        List<Route> routeDTOS = trainStationService.listTrainStationRoute(String.valueOf(1), "济南西", "宁波");
        System.out.println(routeDTOS);
//        trainStationService.listTrainStationRoute(1, "")
    }

    @Test
    public void listTakeoutTrainStationRoute(){
        List<Route> routes = trainStationService.listTakeoutTrainStationRoute(String.valueOf(1), "济南西", "南京南");
        System.out.println(routes);
    }

    @Test
    public void listAllStation(){
//        List<StationQueryRespDTO> stationQueryRespDTOS = trainStationService.listAllStation();
//        System.out.println(stationQueryRespDTOS);
    }
}
