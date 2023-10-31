package com.example.toolkit;

import com.example.ticket.toolkit.SeatCalculateUtil;
import org.junit.Test;

import java.util.List;

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
}
