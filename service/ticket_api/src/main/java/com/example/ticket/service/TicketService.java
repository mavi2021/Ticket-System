package com.example.ticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ticket.dto.remote.PayInfoRespDTO;
import com.example.ticket.dto.req.*;
import com.example.ticket.dto.resp.CheckTicketRespDTO;
import com.example.ticket.dto.resp.TicketPageQueryRespDTO;
import com.example.ticket.dto.resp.TicketPurchaseRespDTO;
import com.example.ticket.entity.Ticket;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 车票接口
 *
 */
public interface TicketService extends IService<Ticket> {

    /**
     * 根据条件分页查询车票
     *
     * @param requestParam 分页查询车票请求参数
     * @return 查询车票返回结果
     */
    TicketPageQueryRespDTO pageListTicketQuery(TicketPageQueryReqDTO requestParam);

    /**
     * 购买车票
     *
     * @param requestParam 车票购买请求参数
     * @return 订单号
     */
    TicketPurchaseRespDTO purchaseTickets(@RequestBody PurchaseTicketReqDTO requestParam);


    /**
     * 执行购买车票
     * 被对应购票版本号接口调用 {@link TicketService#purchaseTickets(PurchaseTicketReqDTO)} }
     *
     * @param requestParam 车票购买请求参数
     * @return 订单号
     */
    TicketPurchaseRespDTO executePurchaseTickets(@RequestBody PurchaseTicketReqDTO requestParam);


    /**
     * 获取真实购票结果
     *
     * @param requestParam 车票购买请求参数
     * @return 真实购票结果
     */
    List<TrainPurchaseTicketRespDTO> obtainActualPurchaseResult(PurchaseTicketReqDTO requestParam);

    /**
     * 支付单详情查询
     *
     * @param orderSn 订单号
     * @return 支付单详情
     */
    PayInfoRespDTO getPayInfo(String orderSn);

    /**
     * 取消车票订单
     *
     * @param requestParam 取消车票订单入参
     */
    void cancelTicketOrder(CancelTicketOrderReqDTO requestParam);

    /**
     * 公共支付接口
     *
     * @param requestParam 支付请求参数
     *
     */
    void commonTicketPay(PayTicketReqDTO requestParam);

    /**
     * 公共退款接口
     *
     * @param requestParam 退款请求参数
     *
     */
    void commonTicketRefund(RefundTicketReqDTO requestParam);

    /**
     * 凭身份证进站
     * @param requestParam 检票请求参数
     */
    CheckTicketRespDTO checkTicketByIdCard(CheckTicketReqDTO requestParam);

}
