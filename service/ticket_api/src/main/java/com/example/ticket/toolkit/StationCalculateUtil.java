package com.example.ticket.toolkit;

import cn.hutool.core.util.StrUtil;
import com.example.ticket.entity.Route;

import java.util.ArrayList;
import java.util.List;

/**
 * @create 2023/10/15 16:41
 */
public class StationCalculateUtil {
    public static List<Route> throughStation(List<String> stations, String startStation, String endStation) {
        int startIndex = -1;
        int endIndex = -1;
        for (int i = 0; i < stations.size(); i++) {
            if(StrUtil.equals(stations.get(i), startStation)){
                startIndex = i;
            }
            if(StrUtil.equals(stations.get(i), endStation)){
                endIndex = i;
            }
        }
        List<Route> routes = new ArrayList<>();
        if (startIndex == -1 || endIndex == -1){
            return routes;
        }
        for (int i = startIndex; i < endIndex; i++) {
            for (int j = i+1; j <= endIndex ; j++) {
                Route subRoute = Route.builder().startStation(stations.get(i)).endStation(stations.get(j)).build();
                routes.add(subRoute);
            }
        }
        return routes;
    }

    public static List<Route> takeoutStation(List<String> stations, String startStation, String endStation) {
        int startIndex = -1;
        int endIndex = -1;
        for (int i = 0; i < stations.size(); i++) {
            if(StrUtil.equals(stations.get(i), startStation)){
                startIndex = i;
            }
            if(StrUtil.equals(stations.get(i), endStation)){
                endIndex = i;
            }
        }
        List<Route> takeoutStationList = new ArrayList<>();
        if (startIndex == -1 || endIndex == -1){
            return takeoutStationList;
        }
        for (int i = 0; i < startIndex; i++) {
            for (int j = startIndex + 1; j < stations.size(); j++) {
                Route insertStation = Route.builder().startStation(stations.get(i)).endStation(stations.get(j)).build();
                takeoutStationList.add(insertStation);
            }
        }
        for (int i = startIndex; i < endIndex; i++) {
            for (int j = i + 1; j < stations.size(); j++) {
                Route insertStation = Route.builder().startStation(stations.get(i)).endStation(stations.get(j)).build();
                takeoutStationList.add(insertStation);
            }
        }
        return takeoutStationList;
    }
}