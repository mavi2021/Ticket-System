package com.example.ticket.strategy;

import cn.hutool.core.map.MapUtil;
import com.example.ticket.algorithm.SeatSelectAlgorithm;
import com.example.ticket.dto.domain.SelectSeatDTO;
import com.example.ticket.dto.resp.SeatDistributeRespDTO;
import com.example.ticket.service.SeatService;
import com.example.ticket.toolkit.SeatCalculateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DefaultSeatSelectStrategy extends AbstractSeatSelectStrategy {

    private final SeatService seatService;

    @Override
    public List<SeatDistributeRespDTO> selectSeats(SelectSeatDTO requestParam, List<String> trainCarriageList, List<Integer> trainStationCarriageRemainingTicket) {
        System.out.println("DefaultSeatSelectStrategy");
        Map<String, List<List<Integer>>> selectedSeatMap = MapUtil.newHashMap();
        Map<String, int[][]> carriagesNumberSeatsMap = MapUtil.newHashMap();
        Map<String, Integer> seatStockNumMap = MapUtil.newHashMap();
        int seatsNum = requestParam.getPassengerSeatDetails().size();
        for (String carriageNum : trainCarriageList) {
            List<String> availableSeat = seatService.listAvailableSeat(String.valueOf(requestParam.getRequestParam().getTrainId()), carriageNum, requestParam.getSeatType(), requestParam.getRequestParam().getDeparture(), requestParam.getRequestParam().getArrival());
            int[][] seatLayout = SeatCalculateUtil.convertToSeatLayout(availableSeat);
            selectedSeatMap = SeatSelectAlgorithm.selectConsecutiveSeatsOfSameCarriage(seatsNum, seatLayout, carriageNum, requestParam.getSeatType());
            if(!selectedSeatMap.isEmpty()){
                break;
            }
            carriagesNumberSeatsMap.putIfAbsent(carriageNum, seatLayout);
            seatStockNumMap.putIfAbsent(carriageNum, availableSeat.size());
        }

        if(selectedSeatMap.isEmpty()){
            selectedSeatMap = SeatSelectAlgorithm.selectNonConsecutiveSeatsOfSameCarriage(seatsNum, carriagesNumberSeatsMap);
        }
        if (selectedSeatMap.isEmpty()){
            selectedSeatMap = SeatSelectAlgorithm.selectNonConsecutiveSeatsOfAllCarriages(seatsNum, carriagesNumberSeatsMap, seatStockNumMap);
        }
        return SeatCalculateUtil.convertToActualSeatSelectResp(requestParam.getPassengerSeatDetails(), selectedSeatMap);

    }
}
