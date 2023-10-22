package com.example.ticket.service;

import com.example.ticket.dto.req.RegionStationQueryReqDTO;
import com.example.ticket.dto.resp.RegionStationQueryRespDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @create 2023/10/16 15:30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RegionServiceTest {

    @Resource
    private RegionService regionService;

    @Test
    public void listRegionStation(){

        RegionStationQueryReqDTO build = RegionStationQueryReqDTO.builder()
                .queryType(0)
                .build();
        List<RegionStationQueryRespDTO> regionStationQueryRespDTOS = regionService.listRegionStation(build);
        System.out.println(regionStationQueryRespDTOS);
    }
}
