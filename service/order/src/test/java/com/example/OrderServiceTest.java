package com.example;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.order.dto.domain.OrderStatusReversalDTO;
import com.example.order.dto.req.CancelTicketOrderReqDTO;
import com.example.order.dto.req.TicketOrderCreateReqDTO;
import com.example.order.dto.req.TicketOrderItemCreateReqDTO;
import com.example.order.dto.req.TicketOrderPageQueryReqDTO;
import com.example.order.dto.resp.PageResponse;
import com.example.order.dto.resp.TicketOrderDetailRespDTO;
import com.example.order.entity.Order;
import com.example.order.enums.OrderItemStatusEnum;
import com.example.order.enums.OrderStatusEnum;
import com.example.order.mapper.OrderMapper;
import com.example.order.service.OrderItemService;
import com.example.order.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @create 2023/10/20 17:11
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTest {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderService orderService;

    @Resource
    private OrderMapper orderMapper;

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
    public void testPageList(){
        LambdaQueryWrapper<Order> orderLambdaQueryWrapper = Wrappers.lambdaQuery(Order.class).eq(Order::getUserId, "123");
        IPage<Order> page = new Page<>(0L, 2L);
        IPage<Order> orderIPage = orderMapper.selectPage(page, orderLambdaQueryWrapper);
        System.out.println(orderIPage);
    }





}
