package com.example.ticket.service;

import com.example.ticket.dto.resp.TrainStationRelationRespDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrainStationRelationServiceTest {

    @Autowired
    private TrainStationRelationService trainStationRelationService;

    @Test
    public void queryTrainStationPrice(){
        TrainStationRelationRespDTO trainStationRelationRespDTO = trainStationRelationService.queryTrainStatinRelation("3", "北京南", "济南西");
        System.out.println(trainStationRelationRespDTO);
    }
}
