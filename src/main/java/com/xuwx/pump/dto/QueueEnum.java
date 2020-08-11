package com.xuwx.pump.dto;

import lombok.Getter;

/**
 *@ClassName QueueEnum
 *@Description 消息队列枚举配置
 *@Author wx.Xu
 *Date 2020/8/4 15:12
 *Version 1.0
 */
@Getter
public enum QueueEnum {

    /**
     * 消息通知队列
     */
    QUEUE_ORDER_CANCEL("pump.order.direct","pump.order.cancel", "pump.order.cancel"),

    /**
     * 消息通知ttl队列
      */
    QUEUE_TTL_ORDER_CANCEL("pump.order.direct.ttl", "pump.order.cancel.ttl", "pump.order.cancel.ttl");

    /**
     * 交换名称
     */
    private String exchange;

    /**
     * 队列名称
     */
    private String name;

    /**
     * 路由键
     */
    private String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
