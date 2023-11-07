package com.example.ticket.common.constant;

import java.security.PublicKey;

/**
 * @date 2023/11/7 16:56
 */
public final class RedisKeyConstant {

    /**
     * 列车购买令牌桶，Key Prefix + 列车ID
     */
    public static final String TICKET_AVAILABILITY_TOKEN_BUCKET = "index12306-ticket-service:ticket_availability_token_bucket:";
    /**
     * 列车购买令牌桶 lua脚本地址
     */
    public static final String LUA_TICKET_AVAILABILITY_TOKEN_BUCKET_PATH = "lua/ticket_availability_token_bucket.lua";

}
