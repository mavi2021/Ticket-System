package com.example.enums;

import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.ticket.common.enums.RegionQueryTypeEnum;
import com.example.ticket.entity.Region;
import org.junit.Test;

/**
 * @create 2023/10/16 15:18
 */
public class RegionQueryTypeEnumTest {
    @Test
    public void getWrapperByType(){
        LambdaQueryWrapper<Region> wrapperByType = RegionQueryTypeEnum.getWrapperByType("1");
        LambdaQueryWrapper<Region> in = Wrappers.lambdaQuery(Region.class).in(Region::getInitial, ListUtil.of("A", "B", "C", "D", "E")).eq(Region::getName, "zz");
        MergeSegments expression = wrapperByType.getExpression();
//        System.out.println(expression.getNormal());
        System.out.println(in.getExpression().getNormal());

    }
}
