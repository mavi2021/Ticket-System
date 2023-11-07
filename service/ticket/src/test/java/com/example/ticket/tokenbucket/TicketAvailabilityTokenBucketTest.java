package com.example.ticket.tokenbucket;

import cn.hutool.core.collection.ListUtil;
import com.example.ticket.common.enums.SeatSelectStrategyEnum;
import com.example.ticket.common.enums.VehicleSeatTypeEnum;
import com.example.ticket.dto.domain.PurchaseTicketPassengerDetailDTO;
import com.example.ticket.dto.req.PurchaseTicketReqDTO;
import io.netty.channel.SelectStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @date 2023/11/8 21:17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TicketAvailabilityTokenBucketTest {

    @Resource
    private TicketAvailabilityTokenBucket ticketAvailabilityTokenBucket;

    @Test
    public void takeTokenFromBucket(){
        Integer seatType = VehicleSeatTypeEnum.BUSINESS_CLASS.getCode();
        PurchaseTicketPassengerDetailDTO rr1 = PurchaseTicketPassengerDetailDTO.builder()
                .seatType(seatType)
                .passengerId("211231000").build();
        PurchaseTicketPassengerDetailDTO rr2 = PurchaseTicketPassengerDetailDTO.builder()
                .seatType(seatType)
                .passengerId("211231001").build();
        PurchaseTicketPassengerDetailDTO rr3 = PurchaseTicketPassengerDetailDTO.builder()
                .seatType(seatType)
                .passengerId("211231002").build();
        PurchaseTicketPassengerDetailDTO rr4 = PurchaseTicketPassengerDetailDTO.builder()
                .seatType(seatType)
                .passengerId("211231003").build();
        List<PurchaseTicketPassengerDetailDTO> passengerSeatDetails = new ArrayList<>(ListUtil.of(rr1, rr2, rr3, rr4));
        PurchaseTicketReqDTO rr = PurchaseTicketReqDTO.builder()
                .trainId("3")
                .departure("北京")
                .arrival("德州")
                .seatSelectStrategyType(SeatSelectStrategyEnum.DEFAULT.getType())
                .passengers(passengerSeatDetails).build();
        boolean b = ticketAvailabilityTokenBucket.takeTokenFromBucket(rr);
        System.out.println(b);
    }
}
