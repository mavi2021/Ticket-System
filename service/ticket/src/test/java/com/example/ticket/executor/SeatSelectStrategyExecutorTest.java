package com.example.ticket.executor;

import com.example.ticket.common.enums.SelectStrategyEnum;
import com.example.ticket.dto.domain.SelectSeatDTO;
import com.example.ticket.dto.resp.SeatDistributeRespDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SeatSelectStrategyExecutorTest {

    @Resource
    private SeatSelectStrategyExecutor seatSelectStrategyExecutor;

    @Test
    public void selectSeats(){
        SelectSeatDTO selectSeatDTO = SelectSeatDTO.builder()
                .seatSelectStrategyType(SelectStrategyEnum.DESIGNATED.getType()).build();
        List<String> trainCarriageList = new ArrayList<>();
        List<Integer> trainStationCarriageRemainingTicket = new ArrayList<>();
        List<SeatDistributeRespDTO> seatDistributeRespDTOS = seatSelectStrategyExecutor.selectSeats(selectSeatDTO, trainCarriageList, trainStationCarriageRemainingTicket);
        System.out.println(seatDistributeRespDTOS);
    }


}
