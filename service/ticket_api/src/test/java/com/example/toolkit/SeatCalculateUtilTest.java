package com.example.toolkit;

import cn.hutool.core.collection.ListUtil;
import com.example.ticket.dto.domain.PurchaseTicketPassengerDetailDTO;
import com.example.ticket.dto.resp.SeatDistributeRespDTO;
import com.example.ticket.toolkit.SeatCalculateUtil;
import org.junit.Test;

import java.util.*;

/**
 * @create 2023/10/31 20:16
 */
public class SeatCalculateUtilTest {

    @Test
    public void getConsecutiveSeats(){
        int[][] seatLayout = {
                {1, 1, 0, 1},
                {1, 0, 0, 1},
                {1, 0, 0, 0},
                {0, 0, 0, 0}
        };
        List<List<Integer>> consecutiveSeats = SeatCalculateUtil.getConsecutiveSeats(3, seatLayout);
        System.out.println(consecutiveSeats);
    }

    @Test
    public void getNonConsecutiveSeats(){
        int[][] seatLayout = {
                {1, 1, 0, 1},
                {1, 0, 0, 1},
                {1, 0, 0, 0},
                {0, 0, 0, 0}
        };
        List<List<Integer>> nonConsecutiveSeats = SeatCalculateUtil.getNonConsecutiveSeats(12, seatLayout);
        System.out.println(nonConsecutiveSeats);
    }

    @Test
    public void nextMaxConsecutiveSeatsNum(){
        int[][] seatLayout = {
                {1, 0, 0, 1},
                {1, 1, 1, 0},
                {1, 1, 1, 0},
                {0, 0, 0, 0}
        };
        List<List<Integer>> lists = SeatCalculateUtil.nextMaxConsecutiveSeatsNum(seatLayout, 0, 1, 3);
        System.out.println(lists);
    }

    @Test
    public void convertToSeatLayout(){
        int[][] ints = SeatCalculateUtil.convertToSeatLayout(0, ListUtil.of("01A", "02F", "03C"));
        System.out.println(Arrays.deepToString(ints));
    }

    @Test
    public void convertToActualSeatNumber(){
        String s = SeatCalculateUtil.convertToActualSeatNumber(0, 11, 3);
        System.out.println(s);
    }

    @Test
    public void convertToActualSeatSelectResp(){
        List<PurchaseTicketPassengerDetailDTO> passengerSeatDetails = new ArrayList<>();
        passengerSeatDetails.add(PurchaseTicketPassengerDetailDTO.builder()
                .passengerId("211231000")
                .seatType(1).build());
        passengerSeatDetails.add(PurchaseTicketPassengerDetailDTO.builder()
                .passengerId("211231001")
                .seatType(1).build());
        passengerSeatDetails.add(PurchaseTicketPassengerDetailDTO.builder()
                .passengerId("211231002")
                .seatType(1).build());
        passengerSeatDetails.add(PurchaseTicketPassengerDetailDTO.builder()
                .passengerId("211231003")
                .seatType(1).build());
        Map<String, List<List<Integer>>> selectedSeatMap = new HashMap<>();
        selectedSeatMap.put("01",ListUtil.of(ListUtil.of(1, 1), ListUtil.of(2,3)));
        selectedSeatMap.put("02",ListUtil.of(ListUtil.of(1, 1), ListUtil.of(1,3)));
        List<SeatDistributeRespDTO> seatDistributeRespDTOS = SeatCalculateUtil.convertToActualSeatSelectResp(1, 7500, passengerSeatDetails, selectedSeatMap);
        System.out.println(seatDistributeRespDTOS);
    }

}
