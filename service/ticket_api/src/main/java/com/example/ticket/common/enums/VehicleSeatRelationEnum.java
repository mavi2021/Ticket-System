package com.example.ticket.common.enums;

import cn.hutool.core.collection.ListUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;

/**
 * @create 2023/9/26 12:03
 */
@RequiredArgsConstructor
public enum VehicleSeatRelationEnum {
    HIGH_SPEED_RAIN(0, ListUtil.of(0, 1, 2)),
    BULLET(1, ListUtil.of(3, 4, 5, 13)),
    REGULAR_TRAIN(2, ListUtil.of(6, 7, 8, 13));

    @Getter
    private final Integer trainType;

    @Getter
    private final List<Integer> seatTypes;

    public static List getSeatTypesByType(Integer type){
        return Arrays.stream(VehicleSeatRelationEnum.values())
                .filter(each -> Objects.equals(each.getTrainType(), type))
                .findFirst()
                .map(VehicleSeatRelationEnum::getSeatTypes)
                .orElse(Collections.EMPTY_LIST);
    }


}
