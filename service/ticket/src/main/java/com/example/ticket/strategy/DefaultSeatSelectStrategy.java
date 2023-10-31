package com.example.ticket.strategy;

import com.example.ticket.dto.domain.SelectSeatDTO;
import com.example.ticket.dto.resp.SeatDistributeRespDTO;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DefaultSeatSelectStrategy extends AbstractSeatSelectStrategy{

    @Override
    public List<SeatDistributeRespDTO> selectSeats(SelectSeatDTO requestParam, List<String> trainCarriageList, List<Integer> trainStationCarriageRemainingTicket) {
        System.out.println("DefaultSeatSelectStrategy");
        return null;
    }

}
