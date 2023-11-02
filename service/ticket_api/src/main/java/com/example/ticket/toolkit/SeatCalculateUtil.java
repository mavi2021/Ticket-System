package com.example.ticket.toolkit;

import cn.hutool.core.collection.ListUtil;
import com.example.ticket.common.enums.VehicleSeatLayoutEnum;
import com.example.ticket.dto.domain.PurchaseTicketPassengerDetailDTO;
import com.example.ticket.dto.resp.SeatDistributeRespDTO;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @create 2023/10/30 16:34
 */
public final class SeatCalculateUtil {

    public static List<List<Integer>> getConsecutiveSeats(int seatsNum, int[][] seatLayout){
        int row = seatLayout.length;
        int col = seatLayout[0].length;
        if(seatsNum <= 0){
            return ListUtil.empty();
        }
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
                if(res.size() >= seatsNum){
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

    public static int[][] convertToSeatLayout(Integer seatType, List<String> availableSeat) {
        int row = VehicleSeatLayoutEnum.getRowByCode(seatType);
        int col = VehicleSeatLayoutEnum.getColByCode(seatType);
        int[][] seatLayout = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int seatRowPos = i + 1;
                int seatColumnPos = j + 1;
                seatLayout[i][j] = availableSeat.contains(convertToActualSeatNumber(seatType, seatRowPos, seatColumnPos)) ? 0 : 1;
            }
        }
        return seatLayout;
    }

    public static String convertToActualSeatNumber(Integer seatType, Integer seatRowPos, Integer seatColumnPos){
        return String.format("%02d", seatRowPos)+VehicleSeatLayoutEnum.getSeatSymbolBySeatPos(seatType, seatColumnPos);
    }

    public static List<SeatDistributeRespDTO> convertToActualSeatSelectResp(Integer seatType, List<PurchaseTicketPassengerDetailDTO> passengerSeatDetails, Map<String, List<List<Integer>>> selectedSeatMap) {
        LinkedList<String> actualSelectedSeatNumberList = new LinkedList<>();
        selectedSeatMap.forEach((key, value)->{
            List<String> seatNumbersList = value.stream().map(each -> key + "-" + convertToActualSeatNumber(seatType, each.get(0), each.get(1))).collect(Collectors.toList());
            actualSelectedSeatNumberList.addAll(seatNumbersList);
        });
        System.out.println(actualSelectedSeatNumberList);
//        passengerSeatDetails.stream().map(each->{
//
//            SeatDistributeRespDTO.builder()
//                    .passengerId()
//                    .seatType()
//                    .carriageNumber()
//                    .seatNumber()
//                    .price().build();
//        });
        return null;
    }
}
