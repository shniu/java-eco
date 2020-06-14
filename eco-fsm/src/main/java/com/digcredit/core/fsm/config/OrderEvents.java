package com.digcredit.core.fsm.config;

/**
 * Order event
 * Created by Administrator on 2018/12/28 0028.
 */
public enum OrderEvents {
    /**
     * 支付
     */
    PAYED,

    /**
     * 发货
     */
    DELIVERY,

    /**
     * 确认收货
     */
    RECEIVED
}
