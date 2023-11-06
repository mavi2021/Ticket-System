package com.example.ticket.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ticket.dto.resp.StationQueryRespDTO;
import com.example.ticket.entity.Station;
import com.example.ticket.mapper.StationMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationServiceImpl extends ServiceImpl<StationMapper, Station> implements StationService {

    @Override
    public List<StationQueryRespDTO> listAllStation() {
        List<Station> trainStationList = baseMapper.selectList(Wrappers.emptyWrapper());
        return trainStationList.stream().map(each -> BeanUtil.copyProperties(each, StationQueryRespDTO.class)).collect(Collectors.toList());
    }

}
