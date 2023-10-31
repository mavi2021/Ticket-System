package com.example.ticket.executor;

import com.example.ticket.dto.domain.SelectSeatDTO;
import com.example.ticket.dto.resp.SeatDistributeRespDTO;
import com.example.ticket.factory.SeatSelectStrategyFactory;
import com.example.ticket.strategy.SeatSelectStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SeatSelectStrategyExecutor implements AbstractSeatSelectStrategyExecutor{

    private final SeatSelectStrategyFactory seatSelectStrategyFactory;

    @Override
    public List<SeatDistributeRespDTO> selectSeats(SelectSeatDTO requestParam, List<String> trainCarriageList, List<Integer> trainStationCarriageRemainingTicket) {
        SeatSelectStrategy seatSelectStrategy = seatSelectStrategyFactory.getSeatSelectStrategy(requestParam.getSeatSelectStrategyType());
        return seatSelectStrategy.selectSeats(requestParam, trainCarriageList, trainStationCarriageRemainingTicket);
    }
}
