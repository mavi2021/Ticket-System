package com.example.toolkit;

import com.example.ticket.entity.Route;
import com.example.ticket.toolkit.StationCalculateUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @create 2023/10/15 16:54
 */
public class StationCalculateUtilTest {

    @Test
    public void throughStation(){
        ArrayList<String> stations = new ArrayList<>(Arrays.asList("北京南", "济南西", "南京南", "杭州东", "宁波"));
        List<Route> routes = StationCalculateUtil.throughStation(stations, "西南", "宁波");
        System.out.println(routes);
    }

    @Test
    public void takeoutStation(){
        ArrayList<String> stations = new ArrayList<>(Arrays.asList("北京南", "济南西", "南京南", "杭州东", "宁波"));
        List<Route> routes = StationCalculateUtil.takeoutStation(stations, "杭州东", "宁波");
        System.out.println(routes);
    }
}
