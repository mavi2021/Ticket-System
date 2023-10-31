package com.example.ticket.toolkit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @create 2023/10/30 16:34
 */
public class SeatCalculateUtil {

    public static List<List<Integer>> getConsecutiveSeats(int seatsNum, int[][] seatLayout){
        int row = seatLayout.length;
        int col = seatLayout[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col - seatsNum + 1; j++) {
                List<List<Integer>> res = nextMaxConsecutiveSeatsNum(seatLayout, i, j, seatsNum);
                if(seatsNum == res.size()){
                    return res;
                }
            }
        }
        return new ArrayList<>();
    }

    public static List<List<Integer>> getNonConsecutiveSeats(int seatsNum, int[][] seatLayout) {
        int row = seatLayout.length;
        int col = seatLayout[0].length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(res.size() == seatsNum){
                    break;
                }
                if(seatLayout[i][j] == 0){
                    res.add(Arrays.asList(i+1, j+1));
                }
            }
        }
        if(res.size() < seatsNum){
            return new ArrayList<>();
        }
        return res;
    }

    public static List<List<Integer>> nextMaxConsecutiveSeatsNum(int[][] seatLayout, int x, int y, int seatsNum) {
        int col = seatLayout[0].length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = y; i < col; i++) {
            if(res.size() == seatsNum){
                break;
            }
            if(seatLayout[x][i] == 0){
                res.add(Arrays.asList(x+1, i+1));
            }else {
                break;
            }
        }
        return res;
    }

}