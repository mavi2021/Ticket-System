package com.example.ticket.service;

import com.example.ticket.dto.domain.PurchaseTicketPassengerDetailDTO;
import com.example.ticket.dto.domain.SelectSeatDTO;
import com.example.ticket.dto.req.PurchaseTicketReqDTO;
import com.example.ticket.dto.req.TrainPurchaseTicketRespDTO;
import com.example.ticket.dto.resp.SeatDistributeRespDTO;

import java.util.List;

/**
 * @create 2023/10/2 14:02
 * 列车坐席选择器接口
 */
public interface TrainSeatSelector {

    List<SeatDistributeRespDTO> distributeSeats(SelectSeatDTO requestParam);

}
