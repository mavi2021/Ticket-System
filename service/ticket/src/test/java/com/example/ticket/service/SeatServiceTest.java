package com.example.ticket.service;

import com.example.ticket.dto.req.TrainPurchaseTicketRespDTO;
import com.example.ticket.dto.resp.SeatTypeCountRespDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SeatServiceTest {

    @Autowired
    private SeatService seatService;

    @Test
    public void querySeatID(){
        String s = seatService.querySeatId("3", "北京", "德州", "01", "01A");
        System.out.println(s);
    }

    @Test
    public void loadAllSeatQuantity(){
        Map<Integer, Integer> integerIntegerMap = seatService.loadAllSeatQuantity("3", "北京", "德州");
        System.out.println(integerIntegerMap);
    }

    @Test
    public void loadSeatQuantityBySeatType(){
        Integer integer = seatService.loadSeatQuantityBySeatType("3", 1, "北京", "德州");
        System.out.println(integer);
    }

    @Test
    public void listAvailableSeat(){
        List<String> strings = seatService.listAvailableSeat("3", "01", 0, "北京", "德州");
        System.out.println(strings);
    }

    @Test
    public void listUsableCarriages(){
        List<String> strings = seatService.listUsableCarriages("3", 1, "北京", "德州");
        System.out.println(strings);
    }

    @Test
    public void selectRemainingSeats(){
        List<Integer> integers = seatService.selectRemainingSeats("3", "北京", "德州", Arrays.asList("07", "08", "09", "10"));
        System.out.println(integers);
    }

    @Test
    public void lockSeat(){
        TrainPurchaseTicketRespDTO build1 = TrainPurchaseTicketRespDTO.builder()
                .carriageNumber("03")
                .seatNumber("05A")
                .build();
        TrainPurchaseTicketRespDTO build2 = TrainPurchaseTicketRespDTO.builder()
                .carriageNumber("03")
                .seatNumber("05C")
                .build();
        List<TrainPurchaseTicketRespDTO> purchaseTicketRespDTOS = Arrays.asList(build1, build2);
        seatService.lockSeat("3", "德州", "南京", purchaseTicketRespDTOS);
    }

    @Test
    public void unlock(){
        TrainPurchaseTicketRespDTO build1 = TrainPurchaseTicketRespDTO.builder()
                .carriageNumber("03")
                .seatNumber("05A")
                .build();
        TrainPurchaseTicketRespDTO build2 = TrainPurchaseTicketRespDTO.builder()
                .carriageNumber("03")
                .seatNumber("05C")
                .build();
        List<TrainPurchaseTicketRespDTO> purchaseTicketRespDTOS = Arrays.asList(build1, build2);
        seatService.unlock("3", "德州", "南京", purchaseTicketRespDTOS);
    }

    @Test
    public void listSeatTypeCount(){
        List<SeatTypeCountRespDTO> seatTypeCountRespDTOS = seatService.listSeatTypeCount("3", "北京", "德州");
        System.out.println(seatTypeCountRespDTOS);
    }

}
