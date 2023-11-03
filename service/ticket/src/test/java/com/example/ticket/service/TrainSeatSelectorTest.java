package com.example.ticket.service;

import com.example.ticket.common.enums.SeatSelectStrategyEnum;
import com.example.ticket.common.enums.VehicleSeatTypeEnum;
import com.example.ticket.dto.domain.PurchaseTicketPassengerDetailDTO;
import com.example.ticket.dto.domain.SelectSeatDTO;
import com.example.ticket.dto.req.PurchaseTicketReqDTO;
import com.example.ticket.dto.resp.SeatDistributeRespDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrainSeatSelectorTest {

    @Resource
    private TrainSeatSelector trainSeatSelector;

    @Test
    public void distributeSeats(){

        String startStation = "北京";
        String endStation = "德州";
        Long trainId = 3L;
        Integer seatSelectStrategyType = SeatSelectStrategyEnum.DEFAULT.getType();
        Integer seatType = VehicleSeatTypeEnum.BUSINESS_CLASS.getCode();

        List<PurchaseTicketPassengerDetailDTO> passengerSeatDetails = new ArrayList<>();
        PurchaseTicketPassengerDetailDTO rr1 = PurchaseTicketPassengerDetailDTO.builder()
                .seatType(seatType)
                .passengerId("211231000").build();
        PurchaseTicketPassengerDetailDTO rr2 = PurchaseTicketPassengerDetailDTO.builder()
                .seatType(seatType)
                .passengerId("211231001").build();
        PurchaseTicketPassengerDetailDTO rr3 = PurchaseTicketPassengerDetailDTO.builder()
                .seatType(seatType)
                .passengerId("211231002").build();
        PurchaseTicketPassengerDetailDTO rr4 = PurchaseTicketPassengerDetailDTO.builder()
                .seatType(seatType)
                .passengerId("211231003").build();
        passengerSeatDetails.add(rr1);
        passengerSeatDetails.add(rr2);
        passengerSeatDetails.add(rr3);
        passengerSeatDetails.add(rr4);

        PurchaseTicketReqDTO purchaseTicketReqDTO = PurchaseTicketReqDTO.builder()
                .chooseSeats(null)
                .passengers(passengerSeatDetails)
                .seatSelectStrategyType(seatSelectStrategyType)
                .trainId(trainId)
                .departure(startStation)
                .arrival(endStation).build();

        SelectSeatDTO requestParam = SelectSeatDTO.builder()
                .requestParam(purchaseTicketReqDTO)
                .price(7500)
                .passengerSeatDetails(passengerSeatDetails)
                .seatSelectStrategyType(seatSelectStrategyType)
                .seatType(seatType).build();

        List<SeatDistributeRespDTO> seatDistributeRespDTOS = trainSeatSelector.distributeSeats(requestParam);
        System.out.println(seatDistributeRespDTOS);

    }
}
