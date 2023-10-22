package com.example.user.dto.resp;

import lombok.Builder;
import lombok.Data;

/**
 * @create 2023/10/15 1:26
 */
@Data
@Builder
public class UserDeletionRespDTO {

    /**
     * 证件类型
     */
    private Integer idType;

    /**
     * 证件号
     */
    private String idCard;
}
