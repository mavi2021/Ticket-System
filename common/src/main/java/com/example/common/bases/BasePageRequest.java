package com.example.common.bases;

import lombok.Getter;
import lombok.Setter;

/**
 * @create 2023/10/20 13:30
 */
public class BasePageRequest {

    /**
     * 当前页
     */
    @Setter
    @Getter
    private Long current = 1L;

    /**
     * 每页显示条数
     */
    @Setter
    @Getter
    private Long size = 10L;
}
