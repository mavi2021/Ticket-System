package com.example.ticket.tokenbucket;

/**
 * @date 2023/11/7 16:11
 */
public interface AbstractTokenBucket<T>{

    boolean takeTokenFromBucket(T requestParam);

}
