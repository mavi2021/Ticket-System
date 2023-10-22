package com.example.ticket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ticket.entity.Ticket;
import org.apache.ibatis.annotations.Mapper;

/**
 * @create 2023/9/24 8:28
 * 车票持久层
 */
@Mapper
public interface TicketMapper extends BaseMapper<Ticket> {
}
