package com.example;

import com.example.order.dto.domain.OrderStatusReversalDTO;
import com.example.order.dto.req.*;
import com.example.order.dto.resp.PageResponse;
import com.example.order.dto.resp.TicketOrderDetailRespDTO;
import com.example.order.dto.resp.TicketOrderDetailSelfRespDTO;
import com.example.order.entity.OrderItemPassenger;
import com.example.order.enums.OrderItemStatusEnum;
import com.example.order.enums.OrderStatusEnum;
import com.example.order.mq.event.PayResultCallbackOrderEvent;
import com.example.order.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @create 2023/10/20 17:11
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;


    @Test
    public void queryTicketOrderByOrderSn(){
        TicketOrderDetailRespDTO ticketOrderDetailRespDTO = orderService.queryTicketOrderByOrderSn("1111");
        System.out.println(ticketOrderDetailRespDTO);
    }

    @Test
    public void pageTicketOrder(){
        TicketOrderPageQueryReqDTO ticketOrderPageQueryReqDTO = TicketOrderPageQueryReqDTO.builder()
                .userId("123")
                .statusType(1)
                .build();
        ticketOrderPageQueryReqDTO.setCurrent(1L);
        ticketOrderPageQueryReqDTO.setSize(1L);
        PageResponse<TicketOrderDetailRespDTO> respDTOPageResponse = orderService.pageTicketOrder(ticketOrderPageQueryReqDTO);
        System.out.println(respDTOPageResponse);
    }

    @Test
    public void createTicketOrder(){
        TicketOrderItemCreateReqDTO ticketOrderItemCreateReqDTO = TicketOrderItemCreateReqDTO.builder()
                .carriageNumber("1")
                .seatNumber("05F")
                .build();
        List<TicketOrderItemCreateReqDTO> tt = Collections.singletonList(ticketOrderItemCreateReqDTO);
        TicketOrderCreateReqDTO ticketOrderCreateReqDTO = TicketOrderCreateReqDTO.builder()
                .departure("北京")
                .arrival("南京")
                .userId(11111L)
                .ticketOrderItems(tt)
                .build();
        orderService.createTicketOrder(ticketOrderCreateReqDTO);
    }

    @Test
    public void cancelTickOrder(){
        CancelTicketOrderReqDTO cancelTicketOrderReqDTO = CancelTicketOrderReqDTO.builder()
                .orderSn("239cf261-281e-4583-a557-65a18d78ffab")
                .build();
        orderService.cancelTickOrder(cancelTicketOrderReqDTO);
    }

    @Test
    public void statusReversal(){
        OrderStatusReversalDTO orderStatusReversalDTO = OrderStatusReversalDTO.builder()
                .orderSn("239cf261-281e-4583-a557-65a18d78ffab")
                .orderStatus(OrderStatusEnum.PENDING_PAYMENT.getStatus())
                .orderItemStatus(OrderItemStatusEnum.PENDING_PAYMENT.getStatus())
                .build();
        orderService.statusReversal(orderStatusReversalDTO);
    }


    @Test
    public void payCallbackOrder(){
        PayResultCallbackOrderEvent payResultCallbackOrderEvent = PayResultCallbackOrderEvent.builder()
                .orderSn("239cf261-281e-4583-a557-65a18d78ffab")
                .channel(1)
                .gmtPayment(new Date()).build();
        orderService.payCallbackOrder(payResultCallbackOrderEvent);


    }

    @Test
    public void pageSelfTicketOrder(){
        TicketOrderSelfPageQueryReqDTO ticketOrderSelfPageQueryReqDTO = TicketOrderSelfPageQueryReqDTO.builder()
                .idCard("388877")
                .idType(1)
                .build();
        ticketOrderSelfPageQueryReqDTO.setCurrent(1L);
        ticketOrderSelfPageQueryReqDTO.setSize(10L);
        PageResponse<TicketOrderDetailSelfRespDTO> ticketOrderDetailSelfRespDTOPageResponse = orderService.pageSelfTicketOrder(ticketOrderSelfPageQueryReqDTO);
        System.out.println(ticketOrderDetailSelfRespDTOPageResponse);
    }

    @Test
    public void getOrderDetailsByIdCard(){
        OrderItemPassenger orderItemPassenger = OrderItemPassenger.builder()
                .idCard("388877")
                .idType(1)
                .orderSn("239cf261-281e-4583-a557-65a18d78ffab").build();
        TicketOrderDetailSelfRespDTO orderDetailsByIdCard = orderService.getOrderDetailsByIdCard(orderItemPassenger);
        System.out.println(orderDetailsByIdCard);
    }




}
