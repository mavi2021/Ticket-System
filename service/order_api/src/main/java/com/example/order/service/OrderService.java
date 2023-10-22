package com.example.order.service;

import com.example.order.dto.domain.OrderStatusReversalDTO;
import com.example.order.dto.req.CancelTicketOrderReqDTO;
import com.example.order.dto.req.TicketOrderCreateReqDTO;
import com.example.order.dto.req.TicketOrderSelfPageQueryReqDTO;
import com.example.order.dto.resp.TicketOrderDetailRespDTO;
import com.example.order.dto.req.TicketOrderPageQueryReqDTO;
import com.example.order.dto.resp.PageResponse;
import com.example.order.dto.resp.TicketOrderDetailSelfRespDTO;
import com.example.order.entity.OrderItemPassenger;
import com.example.order.mq.event.PayResultCallbackOrderEvent;

/**
 * @create 2023/7/11 10:28
 */
public interface OrderService {
    /**
     * 跟据订单号查询车票订单
     *
     * @param orderSn 订单号
     * @return 订单详情
     */
    TicketOrderDetailRespDTO queryTicketOrderByOrderSn(String orderSn);

    /**
     * 根据用户ID分页查询车票订单
     *
     * @param requestParam 跟据用户 ID 分页查询对象
     * @return 订单分页详情
     */
    PageResponse<TicketOrderDetailRespDTO> pageTicketOrder(TicketOrderPageQueryReqDTO requestParam);

    /**
     * 创建火车票订单
     *
     * @param requestParam 商品订单入参
     * @return 订单号
     */
    String createTicketOrder(TicketOrderCreateReqDTO requestParam);

    /**
     * 关闭火车票订单
     *
     * @param requestParam 关闭火车票订单入参
     */
    boolean closeTickOrder(CancelTicketOrderReqDTO requestParam);

    /**
     * 取消火车票订单
     *
     * @param requestParam 取消火车票订单入参
     */
    boolean cancelTickOrder(CancelTicketOrderReqDTO requestParam);

    /**
     * 订单状态反转
     *
     * @param requestParam 请求参数
     */
    void statusReversal(OrderStatusReversalDTO requestParam);

    /**
     * 支付结果回调订单
     *
     * @param requestParam 请求参数
     */
    void payCallbackOrder(PayResultCallbackOrderEvent requestParam);

    /**
     * 分页查询本人车票订单
     *
     * @param requestParam 请求参数
     * @return 本人车票订单集合
     */
    PageResponse<TicketOrderDetailSelfRespDTO> pageSelfTicketOrder(TicketOrderSelfPageQueryReqDTO requestParam);

    /**
     * 根据订单和身份证查询车票订单详情
     *
     * @param orderItemPassenger 请求参数
     * @return 本人车票订单集合
     */
    TicketOrderDetailSelfRespDTO getOrderDetailsByIdCard(OrderItemPassenger orderItemPassenger);

}
