package com.example.common.enums;

/**
 * @create 2023/10/16 14:43
 */
public enum FlagEnum {
    FALSE(0),
    TRUE(1);

    private Integer code;

    public Integer getCode(){
        return code;
    }
    FlagEnum(Integer initCode){
        this.code = initCode;
    }
}
