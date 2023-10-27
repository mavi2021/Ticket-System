package com.example.order.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.order.dto.req.*;
import com.example.order.entity.OrderItemPassenger;
import com.example.order.enums.OrderItemStatusEnum;
import com.example.order.enums.OrderStatusEnum;
import com.example.order.enums.OrderStatusTypeEnum;
import com.example.order.mapper.OrderMapper;
import com.example.order.dto.domain.OrderStatusReversalDTO;
import com.example.order.dto.resp.PageResponse;
import com.example.order.dto.resp.TicketOrderDetailRespDTO;
import com.example.order.dto.resp.TicketOrderDetailSelfRespDTO;
import com.example.order.dto.resp.TicketOrderPassengerDetailRespDTO;
import com.example.order.entity.Order;
import com.example.order.entity.OrderItem;
import com.example.order.mq.event.PayResultCallbackOrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * @create 2023/7/11 10:40
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final OrderItemService orderItemService;
    private final OrderItemPassengerService orderItemPassengerService;
    @Override
    public TicketOrderDetailRespDTO queryTicketOrderByOrderSn(String orderSn) {
        LambdaQueryWrapper<Order> orderLambdaQueryWrapper = Wrappers.lambdaQuery(Order.class).eq(Order::getOrderSn, orderSn);
        Order order = baseMapper.selectOne(orderLambdaQueryWrapper);
        List<OrderItem> orderItemList = orderItemService.selectOrderItemList(orderSn);
        List<TicketOrderPassengerDetailRespDTO> passengerDetails = BeanUtil.copyToList(orderItemList, TicketOrderPassengerDetailRespDTO.class);
        TicketOrderDetailRespDTO ticketOrderDetailRespDTO = BeanUtil.copyProperties(order, TicketOrderDetailRespDTO.class);
        ticketOrderDetailRespDTO.setPassengerDetails(passengerDetails);
        return ticketOrderDetailRespDTO;
    }

    @Override
    public PageResponse<TicketOrderDetailRespDTO> pageTicketOrder(TicketOrderPageQueryReqDTO requestParam) {
        IPage<Order> page = new Page<>(requestParam.getCurrent(), requestParam.getSize());
        LambdaQueryWrapper<Order> orderLambdaQueryWrapper = Wrappers.lambdaQuery(Order.class)
                .eq(Order::getUserId, requestParam.getUserId())
                .in(Order::getStatus, OrderStatusTypeEnum.getPaymentListByType(requestParam.getStatusType()))
                .orderByDesc(Order::getOrderTime);
        IPage<Order> orderIPage = baseMapper.selectPage(page, orderLambdaQueryWrapper);

        List<TicketOrderDetailRespDTO> ticketOrderDetailRespDTOS = orderIPage.getRecords().stream().map(each -> {
            TicketOrderDetailRespDTO ticketOrderDetailRespDTO = BeanUtil.copyProperties(each, TicketOrderDetailRespDTO.class);
            List<OrderItem> orderItemList = orderItemService.selectOrderItemList(each.getOrderSn());
            List<TicketOrderPassengerDetailRespDTO> passengerDetails = BeanUtil.copyToList(orderItemList, TicketOrderPassengerDetailRespDTO.class);
            ticketOrderDetailRespDTO.setPassengerDetails(passengerDetails);
            return ticketOrderDetailRespDTO;
        }).collect(Collectors.toList());

        return PageResponse.<TicketOrderDetailRespDTO>builder()
                .current(orderIPage.getCurrent())
                .size(orderIPage.getSize())
                .total(orderIPage.getTotal())
                .records(ticketOrderDetailRespDTOS)
                .build();
    }

    @Override
    @Transactional
    public String createTicketOrder(TicketOrderCreateReqDTO requestParam) {
        // 暂时使用UUID
        String orderSn =UUID.randomUUID().toString();
        Order order = BeanUtil.copyProperties(requestParam, Order.class);
        order.setOrderSn(orderSn);
        order.setStatus(OrderStatusEnum.PENDING_PAYMENT.getStatus());
        baseMapper.insert(order);

        List<TicketOrderItemCreateReqDTO> ticketOrderItemList = requestParam.getTicketOrderItems();
        List<OrderItem> orderItemList = BeanUtil.copyToList(ticketOrderItemList, OrderItem.class);
        ArrayList<OrderItemPassenger> orderItemPassengerList = new ArrayList<>();
        orderItemList.forEach(each->{
            each.setTrainId(requestParam.getTrainId());
            each.setOrderSn(orderSn);
            each.setUserId(String.valueOf(requestParam.getUserId()));
            each.setUsername(requestParam.getUsername());
            each.setStatus(OrderStatusEnum.PENDING_PAYMENT.getStatus());
            OrderItemPassenger orderItemPassenger = OrderItemPassenger.builder().orderSn(orderSn).idType(each.getIdType()).idCard(each.getIdCard()).build();
            orderItemPassengerList.add(orderItemPassenger);
        });
        orderItemService.saveBatch(orderItemList);
        orderItemPassengerService.saveBatch(orderItemPassengerList);
        return orderSn;
    }

    @Override
    public boolean closeTickOrder(CancelTicketOrderReqDTO requestParam) {
        String orderSn = requestParam.getOrderSn();
        LambdaUpdateWrapper<Order> orderLambdaUpdateWrapper = Wrappers.lambdaUpdate(Order.class).eq(Order::getOrderSn, orderSn);
        Order updateOrder = Order.builder().status(OrderStatusEnum.CLOSED.getStatus()).build();
        baseMapper.update(updateOrder, orderLambdaUpdateWrapper);

        LambdaUpdateWrapper<OrderItem> orderItemLambdaUpdateWrapper = Wrappers.lambdaUpdate(OrderItem.class).eq(OrderItem::getOrderSn, orderSn);
        OrderItem updateOrderItem = OrderItem.builder().status(OrderItemStatusEnum.CLOSED.getStatus()).build();
        orderItemService.update(updateOrderItem, orderItemLambdaUpdateWrapper);

        return true;
    }

    @Override
    @Transactional
    public boolean cancelTickOrder(CancelTicketOrderReqDTO requestParam) {
        String orderSn = requestParam.getOrderSn();
        LambdaUpdateWrapper<Order> orderLambdaUpdateWrapper = Wrappers.lambdaUpdate(Order.class).eq(Order::getOrderSn, orderSn);
        Order updateOrder = Order.builder().status(OrderStatusEnum.CLOSED.getStatus()).build();
        baseMapper.update(updateOrder, orderLambdaUpdateWrapper);

        LambdaUpdateWrapper<OrderItem> orderItemLambdaUpdateWrapper = Wrappers.lambdaUpdate(OrderItem.class).eq(OrderItem::getOrderSn, orderSn);
        OrderItem updateOrderItem = OrderItem.builder().status(OrderItemStatusEnum.CLOSED.getStatus()).build();
        orderItemService.update(updateOrderItem, orderItemLambdaUpdateWrapper);

        return true;
    }

    @Override
    public void statusReversal(OrderStatusReversalDTO requestParam) {
        String orderSn = requestParam.getOrderSn();
        LambdaUpdateWrapper<Order> orderLambdaUpdateWrapper = Wrappers.lambdaUpdate(Order.class)
                .eq(Order::getOrderSn, orderSn);
        Order updateOrder = Order.builder().status(requestParam.getOrderStatus()).build();
        baseMapper.update(updateOrder, orderLambdaUpdateWrapper);

        LambdaUpdateWrapper<OrderItem> orderItemLambdaUpdateWrapper = Wrappers.lambdaUpdate(OrderItem.class)
                .eq(OrderItem::getOrderSn, orderSn);
        OrderItem updateOrderItem = OrderItem.builder().status(requestParam.getOrderItemStatus()).build();
        orderItemService.update(updateOrderItem, orderItemLambdaUpdateWrapper);

    }

    @Override
    public void payCallbackOrder(PayResultCallbackOrderEvent requestParam) {
        Order updateOrder = Order.builder()
                .payType(requestParam.getChannel())
                .payTime(requestParam.getGmtPayment()).build();
        LambdaUpdateWrapper<Order> orderLambdaUpdateWrapper = Wrappers.lambdaUpdate(Order.class)
                .eq(Order::getOrderSn, requestParam.getOrderSn());
        baseMapper.update(updateOrder, orderLambdaUpdateWrapper);
    }

    @Override
    public PageResponse<TicketOrderDetailSelfRespDTO> pageSelfTicketOrder(TicketOrderSelfPageQueryReqDTO requestParam) {
        LambdaQueryWrapper<OrderItemPassenger> orderItemPassengerLambdaQueryWrapper = Wrappers.lambdaQuery(OrderItemPassenger.class)
                .eq(OrderItemPassenger::getIdType, requestParam.getIdType())
                .eq(OrderItemPassenger::getIdCard, requestParam.getIdCard());
        IPage<OrderItemPassenger> selfOrdersPage = new Page<>(requestParam.getCurrent(), requestParam.getSize());
        IPage<OrderItemPassenger> selfOrdersIPage = orderItemPassengerService.page(selfOrdersPage, orderItemPassengerLambdaQueryWrapper);
        List<TicketOrderDetailSelfRespDTO> ticketOrderDetailSelfRespDTOS = selfOrdersIPage.getRecords().stream().map(this::getOrderDetailsByIdCard).collect(Collectors.toList());

        return PageResponse.<TicketOrderDetailSelfRespDTO>builder()
                .current(selfOrdersIPage.getCurrent())
                .size(selfOrdersIPage.getSize())
                .total(selfOrdersIPage.getTotal())
                .records(ticketOrderDetailSelfRespDTOS)
                .build();
    }

    @Override
    public TicketOrderDetailSelfRespDTO getOrderDetailsByIdCard(OrderItemPassenger orderItemPassenger) {
        String orderSn = orderItemPassenger.getOrderSn();
        LambdaQueryWrapper<Order> orderLambdaQueryWrapper = Wrappers.lambdaQuery(Order.class)
                .eq(Order::getOrderSn, orderSn);
        Order order = baseMapper.selectOne(orderLambdaQueryWrapper);

        LambdaQueryWrapper<OrderItem> orderItemLambdaQueryWrapper = Wrappers.lambdaQuery(OrderItem.class)
                .eq(OrderItem::getOrderSn, orderSn)
                .eq(OrderItem::getIdType, orderItemPassenger.getIdType())
                .eq(OrderItem::getIdCard, orderItemPassenger.getIdCard());
        OrderItem orderItem = orderItemService.getOne(orderItemLambdaQueryWrapper);

        TicketOrderDetailSelfRespDTO ticketOrderDetailSelfRespDTO = BeanUtil.copyProperties(order, TicketOrderDetailSelfRespDTO.class);
        BeanUtil.copyProperties(orderItem, ticketOrderDetailSelfRespDTO, CopyOptions.create().ignoreNullValue());
        return ticketOrderDetailSelfRespDTO;
    }
}
