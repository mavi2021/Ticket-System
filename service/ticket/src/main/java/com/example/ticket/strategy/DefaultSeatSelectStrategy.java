package com.example.ticket.strategy;

import com.example.ticket.dto.domain.SelectSeatDTO;
import com.example.ticket.dto.resp.SeatDistributeRespDTO;
import com.example.ticket.service.SeatService;
import com.example.ticket.toolkit.SeatCalculateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultSeatSelectStrategy extends AbstractSeatSelectStrategy{

    private final SeatService seatService;

    @Override
    public List<SeatDistributeRespDTO> selectSeats(SelectSeatDTO requestParam, List<String> trainCarriageList, List<Integer> trainStationCarriageRemainingTicket) {
        System.out.println("DefaultSeatSelectStrategy");
//        SeatCalculateUtil.getConsecutiveSeats()
//        seatService.listAvailableSeat(requestParam.getRequestParam().getTrainId(), requestParam);
        return null;
    }

}
