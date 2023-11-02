package com.example.ticket.factory;

import com.example.ticket.common.enums.SeatSelectStrategyEnum;
import com.example.ticket.dto.domain.SelectSeatDTO;
import com.example.ticket.dto.resp.SeatDistributeRespDTO;
import com.example.ticket.strategy.SeatSelectStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SeatSelectStrategyFactoryTest {

    @Autowired
    private SeatSelectStrategyFactory seatSelectStrategyFactory;

    @Test
    public void getSeatSelectStrategy(){
        SeatSelectStrategy seatSelectStrategy = seatSelectStrategyFactory.getSeatSelectStrategy(SeatSelectStrategyEnum.DESIGNATED.getType());
        SelectSeatDTO r1 = null;
        List<String> r2 = null;
        List<Integer> r3 = null;
        List<SeatDistributeRespDTO> seatDistributeRespDTOS = seatSelectStrategy.selectSeats(r1, r2, r3);
        System.out.println(seatDistributeRespDTOS);
    }
}
