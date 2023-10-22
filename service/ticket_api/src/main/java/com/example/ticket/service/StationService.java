package com.example.ticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ticket.dto.resp.StationQueryRespDTO;
import com.example.ticket.entity.Station;

import java.util.List;

/**
 * @create 2023/9/24 10:33
 */
public interface StationService extends IService<Station> {
    /**
     * 查询所有车站&城市站点集合信息
     *
     * @return 车站返回数据集合
     */
    List<StationQueryRespDTO> listAllStation();

}
