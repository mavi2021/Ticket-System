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

/**
 * @create 2023/10/27 22:23
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TrainStationPriceServiceTest {

    @Resource
    private TrainStationPriceService trainStationPriceService;

    @Test
    public void queryTrainStationPrice(){
        PurchaseTicketReqDTO requestParam = PurchaseTicketReqDTO.builder()
                .trainId(1L)
                .departure("北京南")
                .arrival("济南西").build();
        List<PurchaseTicketPassengerDetailDTO> passengerSeatDetails = new ArrayList<>();
        SelectSeatDTO selectSeatDTO = SelectSeatDTO.builder()
                .seatType(0)
                .requestParam(requestParam)
                .passengerSeatDetails(passengerSeatDetails).build();
        TrainStationPriceRespDTO trainStationPriceRespDTO = trainStationPriceService.queryTrainStationPrice(selectSeatDTO);
        System.out.println(trainStationPriceRespDTO);

    }

}
