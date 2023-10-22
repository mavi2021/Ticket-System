package com.example.ticket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ticket.entity.TrainStation;
import org.apache.ibatis.annotations.Mapper;

/**
 * @create 2023/9/24 9:54
 * 车站持久层
 */
@Mapper
public interface TrainStationMapper extends BaseMapper<TrainStation> {
}
