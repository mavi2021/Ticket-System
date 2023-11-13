package com.example.ticket.common.constant;

/**
 * @date 2023/11/13 18:55
 */
public final class RocketMQConstant {

    /**
     * Canal 监听数据库余票变更 Topic Key
     */
    public static final String CANAL_COMMON_SYNC_TOPIC_KEY = "index12306_canal_ticket-service_common-sync_topic";

    /**
     * Canal 监听数据库余票变更业务消费者组 Key
     */
    public static final String CANAL_COMMON_SYNC_CG_KEY = "index12306_canal_ticket-service_common-sync_cg";

}
