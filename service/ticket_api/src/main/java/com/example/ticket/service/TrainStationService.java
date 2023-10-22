package com.example.ticket.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ticket.dto.req.RegionStationQueryReqDTO;
import com.example.ticket.dto.resp.RegionStationQueryRespDTO;
import com.example.ticket.dto.resp.StationQueryRespDTO;
import com.example.ticket.dto.resp.TrainStationQueryRespDTO;
import com.example.ticket.entity.Route;
import com.example.ticket.entity.TrainStation;

import java.util.List;

/**
 * @create 2023/9/24 9:54
 */
public interface TrainStationService extends IService<TrainStation> {

    /**
     * 根据列车 ID 查询站点信息
     *
     * @param trainId 列车 ID
     * @return 列车经停站信息
     */
    List<TrainStationQueryRespDTO> listTrainStationQuery(String trainId);

    /**
     * 计算列车站点路线关系
     * 获取开始站点和目的站点及中间站点信息
     *
     * @param trainId   列车 ID
     * @param departure 出发站
     * @param arrival   到达站
     * @return 列车站点路线关系信息
     */
    List<Route> listTrainStationRoute(String trainId, String departure, String arrival);

    /**
     * 获取需列车站点扣减路线关系
     * 获取开始站点和目的站点、中间站点以及关联站点信息
     *
     * @param trainId   列车 ID
     * @param departure 出发站
     * @param arrival   到达站
     * @return 需扣减列车站点路线关系信息
     */
    List<Route> listTakeoutTrainStationRoute(String trainId, String departure, String arrival);
}
