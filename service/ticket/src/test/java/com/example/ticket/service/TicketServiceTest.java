package com.example.ticket.service;

import com.example.ticket.common.enums.SeatSelectStrategyEnum;
import com.example.ticket.common.enums.VehicleSeatTypeEnum;
import com.example.ticket.dto.domain.PurchaseTicketPassengerDetailDTO;
import com.example.ticket.dto.remote.PayInfoRespDTO;
import com.example.ticket.dto.req.PurchaseTicketReqDTO;
import com.example.ticket.dto.req.TicketPageQueryReqDTO;
import com.example.ticket.dto.req.TrainPurchaseTicketRespDTO;
import com.example.ticket.dto.resp.TicketPageQueryRespDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TicketServiceTest {

    @Resource
    private TicketService ticketService;

    @Test
    public void pageListTicketQuery(){
        TicketPageQueryReqDTO requestParam = TicketPageQueryReqDTO.builder()
                .fromStation("VNP").toStation("HGH")
//                .departureDate(calendar.getTime())
                .build();
        TicketPageQueryRespDTO ticketPageQueryRespDTO = ticketService.pageListTicketQuery(requestParam);
        System.out.println(ticketPageQueryRespDTO);
    }

    @Test
    public void getPayInfo(){
        PayInfoRespDTO payInfo = ticketService.getPayInfo("1123123");
        System.out.println(payInfo);
    }

    @Test
    public void obtainActualPurchaseResult(){
        String startStation = "北京";
        String endStation = "德州";
        String  trainId = "3";
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

        PurchaseTicketReqDTO requestParam = PurchaseTicketReqDTO.builder()
                .trainId(trainId)
                .passengers(passengerSeatDetails)
                .chooseSeats(null)
                .departure(startStation)
                .arrival(endStation)
                .seatSelectStrategyType(seatSelectStrategyType).build();
        List<TrainPurchaseTicketRespDTO> trainPurchaseTicketRespDTOS = ticketService.obtainActualPurchaseResult(requestParam);
        System.out.println(trainPurchaseTicketRespDTOS);
    }
}
