package com.example.ticket;

import com.example.ticket.dto.req.TicketPageQueryReqDTO;
import com.example.ticket.dto.resp.TicketPageQueryRespDTO;
import com.example.ticket.service.SeatService;
import com.example.ticket.service.TicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;


@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest
{
    @Autowired
    private SeatService service;

    @Autowired
    private TicketService ticketService;

    @Test
    public void testLoadSeatQuantityByTrainType(){
        Integer stringStringMap = service.loadSeatQuantityBySeatType("3",0, "北京", "德州");
        System.out.println(stringStringMap);
    }

    @Test
    public void test(){
        Map<Integer, Integer> stringStringMap = service.loadAllSeatQuantity("3", "北京", "杭州");
        System.out.println(stringStringMap);
    }

    @Test
    public void testTicketService(){
        TicketPageQueryReqDTO requestParam = TicketPageQueryReqDTO.builder()
                .fromStation("VNP").toStation("HGH")
//                .departureDate(calendar.getTime())
                .build();
        TicketPageQueryRespDTO ticketPageQueryRespDTO = ticketService.pageListTicketQuery(requestParam);
        System.out.println(ticketPageQueryRespDTO);
    }
}
