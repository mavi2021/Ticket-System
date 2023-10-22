package com.example.ticket.service;

import com.example.ticket.dto.req.TicketPageQueryReqDTO;
import com.example.ticket.dto.resp.TicketPageQueryRespDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @create 2023/10/19 20:58
 */
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
}
