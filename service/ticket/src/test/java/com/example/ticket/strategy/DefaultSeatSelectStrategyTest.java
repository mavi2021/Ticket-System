package com.example.ticket.strategy;

import com.example.ticket.common.enums.SeatSelectStrategyEnum;
import com.example.ticket.common.enums.VehicleSeatTypeEnum;
import com.example.ticket.dto.domain.PurchaseTicketPassengerDetailDTO;
import com.example.ticket.dto.domain.SelectSeatDTO;
import com.example.ticket.dto.req.PurchaseTicketReqDTO;
import com.example.ticket.dto.resp.SeatDistributeRespDTO;
import com.example.ticket.service.SeatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DefaultSeatSelectStrategyTest {

    @Resource
    private DefaultSeatSelectStrategy defaultSeatSelectStrategy;

    @Resource
    private SeatService seatService;

    @Test
    public void selectSeats(){

        String startStation = "北京";
        String endStation = "德州";
        Long trainId = 3L;
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
                .seatSelectStrategyType(SeatSelectStrategyEnum.DEFAULT.getType())
                .trainId(trainId)
                .departure(startStation)
                .arrival(endStation).build();

        SelectSeatDTO requestParam = SelectSeatDTO.builder()
                .seatType(seatType)
                .seatSelectStrategyType(SeatSelectStrategyEnum.DEFAULT.getType())
                .price(7500)
                .passengerSeatDetails(passengerSeatDetails)
                .requestParam(purchaseTicketReqDTO)
                .build();

        List<String> trainCarriageList = seatService.listUsableCarriages(
                trainId, seatType, startStation, endStation);
        List<Integer> trainStationCarriageRemainingTicket  = seatService.selectRemainingSeats(
                trainId, startStation, endStation, trainCarriageList);
        List<SeatDistributeRespDTO> seatDistributeRespDTOS = defaultSeatSelectStrategy.selectSeats(requestParam, trainCarriageList, trainStationCarriageRemainingTicket);
        System.out.println(seatDistributeRespDTOS);
    }
}
