package com.example.ticket.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
public enum VehicleSeatLayoutEnum {

    // 布局暂时全部设为 2 2 2
    /**
     * 商务座
     */
    BUSINESS_CLASS(0, "BUSINESS_CLASS", 2, 2, 2),

    /**
     * 一等座
     */
    FIRST_CLASS(1, "FIRST_CLASS", 2, 2, 2),

    /**
     * 二等座
     */
    SECOND_CLASS(2, "SECOND_CLASS", 2, 2, 2),

    /**
     * 二等包座
     */
    SECOND_CLASS_CABIN_SEAT(3, "SECOND_CLASS_CABIN_SEAT", 2, 2, 2),

    /**
     * 一等卧
     */
    FIRST_SLEEPER(4, "FIRST_SLEEPER", 2, 2, 2),

    /**
     * 二等卧
     */
    SECOND_SLEEPER(5, "SECOND_SLEEPER", 2, 2, 2),

    /**
     * 软卧
     */
    SOFT_SLEEPER(6, "SOFT_SLEEPER", 2, 2, 2),

    /**
     * 硬卧
     */
    HARD_SLEEPER(7, "HARD_SLEEPER", 2, 2, 2),

    /**
     * 硬座
     */
    HARD_SEAT(8, "HARD_SEAT", 2, 2, 2),

    /**
     * 高级软卧
     */
    DELUXE_SOFT_SLEEPER(9, "DELUXE_SOFT_SLEEPER", 2, 2, 2),

    /**
     * 动卧
     */
    DINING_CAR_SLEEPER(10, "DINING_CAR_SLEEPER", 2, 2, 2),

    /**
     * 软座
     */
    SOFT_SEAT(11, "SOFT_SEAT", 2, 2, 2),

    /**
     * 特等座
     */
    FIRST_CLASS_SEAT(12, "FIRST_CLASS_SEAT", 2, 2, 2),

    /**
     * 无座
     */
    NO_SEAT_SLEEPER(13, "NO_SEAT_SLEEPER", 0, 0, Integer.MAX_VALUE),

    /**
     * 其他
     */
    OTHER(14, "OTHER", 2, 2, 2);

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
}
