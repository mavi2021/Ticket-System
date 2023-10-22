package com.example.ticket.common.enums;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.common.enums.FlagEnum;
import com.example.ticket.entity.Region;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * @create 2023/10/16 14:23
 */
@RequiredArgsConstructor
public enum RegionQueryTypeEnum {
    HOT("0", Wrappers.lambdaQuery(Region.class).eq(Region::getPopularFlag, FlagEnum.TRUE.getCode())),
    A_E("1", Wrappers.lambdaQuery(Region.class).in(Region::getInitial, ListUtil.of("A", "B", "C", "D", "E"))),
    F_J("2", Wrappers.lambdaQuery(Region.class).in(Region::getInitial, ListUtil.of("F", "G", "H", "I", "J"))),
    K_O("3", Wrappers.lambdaQuery(Region.class).in(Region::getInitial, ListUtil.of("K", "L", "M", "N", "O"))),
    P_T("4", Wrappers.lambdaQuery(Region.class).in(Region::getInitial, ListUtil.of("P", "Q", "R", "S", "T"))),
    U_Z("5", Wrappers.lambdaQuery(Region.class).in(Region::getInitial, ListUtil.of("U", "V", "W", "X", "Y", "Z")));

    @Getter
    private final String queryType;
    @Getter
    private final LambdaQueryWrapper<Region> queryWrapper;

    public static LambdaQueryWrapper<Region> getWrapperByType(String type){
        return Arrays.stream(RegionQueryTypeEnum.values()).filter(each -> StrUtil.equals(each.getQueryType(), type)).findFirst().map(RegionQueryTypeEnum::getQueryWrapper).orElse(null);
    }

}
