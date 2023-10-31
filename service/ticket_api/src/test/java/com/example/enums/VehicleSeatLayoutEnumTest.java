package com.example.enums;

import com.example.ticket.common.enums.VehicleSeatLayoutEnum;
import org.junit.Test;

public class VehicleSeatLayoutEnumTest {

    @Test
    public void getVehicleSeatLayoutEnumByCode(){
        VehicleSeatLayoutEnum vehicleSeatLayoutEnumByCode = VehicleSeatLayoutEnum.getVehicleSeatLayoutEnumByCode(VehicleSeatLayoutEnum.BUSINESS_CLASS.getCode());
        System.out.println(vehicleSeatLayoutEnumByCode.getRow()+" "+vehicleSeatLayoutEnumByCode.getColumn());
    }

    @Test
    public void getMaxConsecutiveSeatsCapacityByCode(){
        Integer maxConsecutiveSeatsCapacityByCode = VehicleSeatLayoutEnum.getMaxConsecutiveSeatsCapacityByCode(VehicleSeatLayoutEnum.BUSINESS_CLASS.getCode());
        System.out.println(maxConsecutiveSeatsCapacityByCode);
    }
}
