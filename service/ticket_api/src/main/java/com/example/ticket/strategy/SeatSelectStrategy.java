package com.example.ticket.strategy;

import com.example.ticket.dto.domain.SelectSeatDTO;
import com.example.ticket.dto.resp.SeatDistributeRespDTO;

import java.util.List;

public interface SeatSelectStrategy {

    List<SeatDistributeRespDTO> selectSeats(SelectSeatDTO requestParam, List<String> trainCarriageList, List<Integer> trainStationCarriageRemainingTicket);

}
