package com.example.ticket.service;

import com.example.ticket.dto.req.RegionStationQueryReqDTO;
import com.example.ticket.dto.resp.RegionStationQueryRespDTO;

import java.util.List;

/**
 * @create 2023/10/16 14:18
 */
public interface RegionService {

    /**
     * 查询车站&城市站点集合信息
     *
     * @param requestParam 车站&站点查询参数
     * @return 车站&站点返回数据集合
     */
    List<RegionStationQueryRespDTO> listRegionStation(RegionStationQueryReqDTO requestParam);

}
