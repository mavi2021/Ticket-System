package com.example.ticket.service;

import com.example.ticket.dto.domain.PurchaseTicketPassengerDetailDTO;
import com.example.ticket.dto.domain.SelectSeatDTO;
import com.example.ticket.dto.req.PurchaseTicketReqDTO;
import com.example.ticket.dto.resp.TrainStationPriceRespDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrainStationPriceServiceTest {

    @Resource
    private TrainStationPriceService trainStationPriceService;

    @Test
    public void queryTrainStationPrice(){
        PurchaseTicketReqDTO requestParam = PurchaseTicketReqDTO.builder()
                .trainId("1")
                .departure("北京南")
                .arrival("济南西").build();
        List<PurchaseTicketPassengerDetailDTO> passengerSeatDetails = new ArrayList<>();
        SelectSeatDTO selectSeatDTO = SelectSeatDTO.builder()
                .seatType(0)
                .requestParam(requestParam)
                .passengerSeatDetails(passengerSeatDetails).build();
        TrainStationPriceRespDTO trainStationPriceRespDTO = trainStationPriceService.queryTrainStationPrice("1", "0",  "北京南", "济南西");
        System.out.println(trainStationPriceRespDTO);

    }

}
