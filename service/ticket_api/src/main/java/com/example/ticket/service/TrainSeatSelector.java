package com.example.ticket.service;

import com.example.ticket.dto.domain.PurchaseTicketPassengerDetailDTO;
import com.example.ticket.dto.domain.SelectSeatDTO;
import com.example.ticket.dto.req.PurchaseTicketReqDTO;
import com.example.ticket.dto.req.TrainPurchaseTicketRespDTO;

import java.util.List;

/**
 * @create 2023/10/2 14:02
 * 列车坐席选择器接口
 */
public interface TrainSeatSelector {
//    List<Integer> loadRemainingSeatsCount(SelectSeatDTO requestParam);
    List<TrainPurchaseTicketRespDTO> distributeSeats(SelectSeatDTO requestParam);

    List<TrainPurchaseTicketRespDTO> selectSeats(SelectSeatDTO requestParam, List<String> trainCarriageList, List<Integer> trainStationCarriageRemainingTicket);
    List<TrainPurchaseTicketRespDTO> selectComplexSeats(SelectSeatDTO requestParam, List<String> trainCarriageList, List<Integer> trainStationCarriageRemainingTicket);

}
