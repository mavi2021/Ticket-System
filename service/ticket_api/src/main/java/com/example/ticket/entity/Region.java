package com.example.ticket.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.bases.BaseEntity;
import lombok.Builder;
import lombok.Data;

/**
 * @create 2023/10/16 14:28
 */
@Data
@Builder
@TableName("t_region")
public class Region extends BaseEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 地区名称
     */
    private String name;

    /**
     * 地区全名
     */
    private String fullName;

    /**
     * 地区编码
     */
    private String code;

    /**
     * 地区首字母
     */
    private String initial;

    /**
     * 拼音
     */
    private String spell;

    /**
     * 热门标识
     */
    private Integer popularFlag;
}

