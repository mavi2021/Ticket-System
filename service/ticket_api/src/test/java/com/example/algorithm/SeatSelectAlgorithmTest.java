package com.example.algorithm;

import cn.hutool.core.map.MapUtil;
import com.example.ticket.algorithm.SeatSelectAlgorithm;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatSelectAlgorithmTest {

    @Test
    public void selectConsecutiveSeatsOfSameCarriage(){
        int[][] seatsLayout = {{1,0,1},{1,0,1},{0,0,1}};
        Map<String, List<List<Integer>>> integerListMap = SeatSelectAlgorithm.selectConsecutiveSeatsOfSameCarriage(2, seatsLayout, "01", 0);
        System.out.println(integerListMap);
        System.out.println(Arrays.deepToString(seatsLayout));
    }

    @Test
    public void selectNonConsecutiveSeatsOfSameCarriage(){
        int[][] seatLayout1 = {{1,0,1},{0,1,1},{1,1,1}};
        int[][] seatLayout2 = {{1,0,0},{0,0,0},{0,0,1}};
        HashMap<String, int[][]> objectObjectHashMap = MapUtil.newHashMap();
        objectObjectHashMap.put("01", seatLayout1);
        objectObjectHashMap.put("02", seatLayout2);
        Map<String, List<List<Integer>>> integerListMap = SeatSelectAlgorithm.selectNonConsecutiveSeatsOfSameCarriage(3, objectObjectHashMap);
        System.out.println(integerListMap);
    }

    @Test
    public void selectNonConsecutiveSeatsOfAllCarriages(){
        int[][] seatLayout1 = {{1,0,1},{1,1,1},{1,1,1}};
        int[][] seatLayout2 = {{1,1,1},{1,0,0},{1,1,1}};
        int[][] seatLayout3 = {{0,1,1},{0,0,1},{0,0,0}};
        Map<String, int[][]> stringMap = MapUtil.ofEntries(MapUtil.entry("01", seatLayout1), MapUtil.entry("02", seatLayout2), MapUtil.entry("03",seatLayout3));
        Map<String, Integer> rr = new HashMap<String, Integer>(){{put("01",1); put("02", 2); put("03", 6);}};
        Map<String, List<List<Integer>>> stringListMap = SeatSelectAlgorithm.selectNonConsecutiveSeatsOfAllCarriages(3, stringMap, rr);
        System.out.println(stringListMap);
    }
}
