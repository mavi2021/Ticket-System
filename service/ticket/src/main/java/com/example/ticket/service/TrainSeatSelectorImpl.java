package com.example.ticket.service;

import com.example.common.exception.ServiceException;
import com.example.ticket.dto.domain.SelectSeatDTO;
import com.example.ticket.dto.req.TrainPurchaseTicketRespDTO;
import com.example.ticket.dto.resp.SeatDistributeRespDTO;
import com.example.ticket.dto.resp.TrainStationPriceRespDTO;
import com.example.ticket.executor.SeatSelectStrategyExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @create 2023/10/2 13:57
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class TrainSeatSelectorImpl implements TrainSeatSelector{

    private final SeatService seatService;
    private final SeatSelectStrategyExecutor seatSelectStrategyExecutor;

    @Override
    public List<SeatDistributeRespDTO> distributeSeats(SelectSeatDTO requestParam) {
        int passengerNumbers = requestParam.getPassengerSeatDetails().size();
        Long trainId = requestParam.getRequestParam().getTrainId();
        Integer seatType = requestParam.getSeatType();
        String startStation = requestParam.getRequestParam().getDeparture();
        String endStation = requestParam.getRequestParam().getArrival();
        List<String> trainCarriageList = seatService.listUsableCarriages(
                trainId, seatType, startStation, endStation);
        List<Integer> trainStationCarriageRemainingTicket  = seatService.selectRemainingSeats(
                trainId, startStation, endStation, trainCarriageList);
        int remainingSeatsCount = trainStationCarriageRemainingTicket.stream()
                .mapToInt(Integer::intValue).sum();

        if(remainingSeatsCount < passengerNumbers){
            throw new ServiceException("站点余票不足，请尝试更换座位类型或选择其它站点");
        }
        return seatSelectStrategyExecutor.selectSeats(requestParam, trainCarriageList, trainStationCarriageRemainingTicket);
    }

}
