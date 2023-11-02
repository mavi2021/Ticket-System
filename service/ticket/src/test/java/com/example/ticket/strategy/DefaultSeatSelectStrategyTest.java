package com.example.ticket.strategy;

import com.example.ticket.common.enums.VehicleSeatTypeEnum;
import com.example.ticket.dto.domain.SelectSeatDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DefaultSeatSelectStrategyTest {

    @Resource
    private DefaultSeatSelectStrategy defaultSeatSelectStrategy;

    @Test
    public void selectSeats(){

//        SelectSeatDTO requestParam = SelectSeatDTO.builder()
//                .seatType(VehicleSeatTypeEnum.BUSINESS_CLASS.getCode())
//                .seatSelectStrategyType(SeatE)
//                .price()
//                .passengerSeatDetails()
//                .requestParam()
//                .build();
//        List<String> trainCarriageList;
//        List<Integer> trainStationCarriageRemainingTicket;
//        defaultSeatSelectStrategy.selectSeats(requestParam, trainCarriageList, trainStationCarriageRemainingTicket);
    }
}
