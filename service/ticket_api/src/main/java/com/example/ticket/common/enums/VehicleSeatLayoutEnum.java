package com.example.ticket.common.enums;

import cn.hutool.core.collection.ListUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public enum VehicleSeatLayoutEnum {

    /**
     * 商务座
     */
    BUSINESS_CLASS(0, "BUSINESS_CLASS", 8, 3, 3, ListUtil.of("A","C","F")),

    /**
     * 一等座
     */
    FIRST_CLASS(1, "FIRST_CLASS", 13, 4, 4, ListUtil.of("A","B","C","F")),

    /**
     * 二等座
     */
    SECOND_CLASS(2, "SECOND_CLASS", 17, 5, 5, ListUtil.of("A","B","C","D","F")),

    /**
     * 二等包座
     */
    SECOND_CLASS_CABIN_SEAT(3, "SECOND_CLASS_CABIN_SEAT", 8, 3, 3, ListUtil.of("A","C","F")),

    /**
     * 一等卧
     */
    FIRST_SLEEPER(4, "FIRST_SLEEPER", 20, 2, 2, ListUtil.of("A","F")),

    /**
     * 二等卧
     */
    SECOND_SLEEPER(5, "SECOND_SLEEPER", 20, 3, 2, ListUtil.of("A","F")),

    /**
     * 软卧
     */
    SOFT_SLEEPER(6, "SOFT_SLEEPER", 18, 2, 2, ListUtil.of("A","F")),

    /**
     * 硬卧
     */
    HARD_SLEEPER(7, "HARD_SLEEPER", 22, 3, 3, ListUtil.of("A","C","F")),

    /**
     * 硬座
     */
    HARD_SEAT(8, "HARD_SEAT", 23, 5, 5, ListUtil.of("A","B","C","D","F")),

    /**
     * 高级软卧
     */
    DELUXE_SOFT_SLEEPER(9, "DELUXE_SOFT_SLEEPER", null, null, null, null),

    /**
     * 动卧
     */
    DINING_CAR_SLEEPER(10, "DINING_CAR_SLEEPER", null, null, null, null),

    /**
     * 软座
     */
    SOFT_SEAT(11, "SOFT_SEAT", null, null, null, null),

    /**
     * 特等座
     */
    FIRST_CLASS_SEAT(12, "FIRST_CLASS_SEAT", null, null, null, null),

    /**
     * 无座
     */
    NO_SEAT_SLEEPER(13, "NO_SEAT_SLEEPER", 0, 0, Integer.MAX_VALUE, null),

    /**
     * 其他
     */
    OTHER(14, "OTHER", null, null, null, null);

    @Getter
    private final Integer code;

    @Getter
    private final String name;

    @Getter
    private final Integer row;

    @Getter
    private final Integer column;

    @Getter
    private final Integer maxConsecutiveSeatsCapacity;

    @Getter
    private final List<String> seatSymbols;

    public static VehicleSeatLayoutEnum getVehicleSeatLayoutEnumByCode(Integer vehicleCode){
        return Arrays.stream(values())
                .filter(each-> Objects.equals(each.getCode(), vehicleCode))
                .findFirst()
                .orElse(null);
    }

    public static Integer getMaxConsecutiveSeatsCapacityByCode(Integer vehicleCode){
        return Arrays.stream(values())
                .filter(each->Objects.equals(each.getCode(), vehicleCode))
                .findFirst()
                .map(VehicleSeatLayoutEnum::getMaxConsecutiveSeatsCapacity)
                .orElse(0);
    }

    public static String getSeatSymbolBySeatPos(Integer vehicleCode, Integer seatColumnPos){
        return Arrays.stream(values())
                .filter(each->Objects.equals(each.getCode(), vehicleCode))
                .findFirst()
                .map(each->each.getSeatSymbols().get(seatColumnPos-1))
                .orElse(null);
    }

    public static Integer getRowByCode(Integer vehicleCode){
        return Arrays.stream(values())
                .filter(each->Objects.equals(each.getCode(), vehicleCode))
                .findFirst()
                .map(VehicleSeatLayoutEnum::getRow)
                .orElse(0);
    }

    public static Integer getColByCode(Integer vehicleCode){
        return Arrays.stream(values())
                .filter(each->Objects.equals(each.getCode(), vehicleCode))
                .findFirst()
                .map(VehicleSeatLayoutEnum::getColumn)
                .orElse(0);
    }

}
