package com.example.ticket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ticket.entity.Seat;
import org.apache.ibatis.annotations.Mapper;

/**
 * @create 2023/9/25 21:42
 */
@Mapper
public interface SeatMapper extends BaseMapper<Seat> {
}
