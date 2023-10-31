package com.example.ticket.algorithm;

import cn.hutool.core.map.MapUtil;
import com.example.ticket.common.enums.VehicleSeatLayoutEnum;
import com.example.ticket.toolkit.SeatCalculateUtil;

import java.util.*;

/**
 * @create 2023/10/31 21:24
 */
public final class SeatSelectAlgorithm {

    public static Map<String, List<List<Integer>>> selectConsecutiveSeatsOfSameCarriage(int seatsNum, int[][] seatsLayout, String carriageNum, int seatType){
        int[][] seatsLayoutCopy = Arrays.stream(seatsLayout).map(int[]::clone).toArray(int[][]::new);
        int maxConsecutiveSeatsCapacity = VehicleSeatLayoutEnum.getMaxConsecutiveSeatsCapacityByCode(seatType);
        List<List<Integer>> selectedSeats = new ArrayList<>();
        while (seatsNum > 0){
            int selectedSeatsNum = Math.min(seatsNum, maxConsecutiveSeatsCapacity);
            List<List<Integer>> consecutiveSeats = SeatCalculateUtil.getConsecutiveSeats(selectedSeatsNum, seatsLayoutCopy);
            selectedSeats.addAll(consecutiveSeats);
            seatsNum -= selectedSeatsNum;
            updateSeatLayout(seatsLayoutCopy, consecutiveSeats);
        }
        if(selectedSeats.isEmpty()){
            return MapUtil.empty();
        }
        return new HashMap<String, List<List<Integer>>>(){{put(carriageNum, selectedSeats);}};
    }

    public static Map<String, List<List<Integer>>> selectNonConsecutiveSeatsOfSameCarriage(int seatsNum, Map<String, int[][]> carriagesNumberSeatsMap){
        for (Map.Entry<String, int[][]> entry : carriagesNumberSeatsMap.entrySet()) {
            String carriageNum = entry.getKey();
            int[][] seatsLayout = entry.getValue();
            List<List<Integer>> nonConsecutiveSeats = SeatCalculateUtil.getNonConsecutiveSeats(seatsNum, seatsLayout);
            if(!nonConsecutiveSeats.isEmpty()){
                return MapUtil.ofEntries(MapUtil.entry(carriageNum, nonConsecutiveSeats));
            }
        }
        return MapUtil.empty();
    }

    public static Map<String, List<List<Integer>>> selectNonConsecutiveSeatsOfAllCarriages(int seatsNum, Map<String, int[][]> carriagesNumberSeatsMap, Map<String, Integer> seatStockNumMap) {
        HashMap<String, List<List<Integer>>> selectedSeatMap = MapUtil.newHashMap();
        for (Map.Entry<String , int[][]> entry : carriagesNumberSeatsMap.entrySet()) {
            if(seatsNum <= 0){
                break;
            }
            String  carriageNum = entry.getKey();
            int[][] seatsLayout = entry.getValue();
            int seatStock = seatStockNumMap.getOrDefault(carriageNum, 0);
            int selectedSeatsNum = Math.min(seatsNum, seatStock);
            List<List<Integer>> nonConsecutiveSeats = SeatCalculateUtil.getNonConsecutiveSeats(selectedSeatsNum, seatsLayout);
            if(!nonConsecutiveSeats.isEmpty()){
                selectedSeatMap.putIfAbsent(carriageNum, nonConsecutiveSeats);
                seatsNum -= selectedSeatsNum;
            }
        }
        return selectedSeatMap;
    }


    private static void updateSeatLayout(int[][] seatsLayout, List<List<Integer>> selectedSeats) {
        selectedSeats.forEach(each->{
            int x = each.get(0) - 1;
            int y = each.get(1) - 1;
            seatsLayout[x][y] = 1;
        });
    }



    public static void main(String[] args) {
        int[][] seatsLayout = {{1,1,0},{1,0,1},{0,0,1}};
        List<List<Integer>> selectedSeats = new ArrayList<>();
        selectedSeats.add(Arrays.asList(2,2));
        updateSeatLayout(seatsLayout, selectedSeats);
        System.out.println(Arrays.deepToString(seatsLayout));
    }

}
