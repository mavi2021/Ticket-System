package com.example.ticket.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ticket.common.enums.RegionQueryTypeEnum;
import com.example.ticket.dto.req.RegionStationQueryReqDTO;
import com.example.ticket.dto.resp.RegionStationQueryRespDTO;
import com.example.ticket.entity.Region;
import com.example.ticket.mapper.RegionMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @create 2023/10/16 14:17
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements RegionService{

    @Override
    public List<RegionStationQueryRespDTO> listRegionStation(RegionStationQueryReqDTO requestParam) {
        LambdaQueryWrapper<Region> regionQueryWrapper = RegionQueryTypeEnum.getWrapperByType(String.valueOf(requestParam.getQueryType()));
        List<Region> regionList = baseMapper.selectList(regionQueryWrapper);
        return regionList.stream().map(each-> BeanUtil.copyProperties(each, RegionStationQueryRespDTO.class)).collect(Collectors.toList());
    }
}
