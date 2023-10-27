package com.example.ticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ticket.dto.domain.SelectSeatDTO;
import com.example.ticket.dto.resp.TrainStationPriceRespDTO;
import com.example.ticket.entity.TrainStationPrice;

/**
 * @create 2023/9/25 11:04
 */
public interface TrainStationPriceService extends IService<TrainStationPrice> {

    /**
     * 查询当前座位价格
     * @param selectSeatDTO
     * @return
     */
    TrainStationPriceRespDTO queryTrainStationPrice(SelectSeatDTO selectSeatDTO);
}