package com.digcredit.core.fsm.config;

/**
 * Order states
 * Created by Administrator on 2018/12/28 0028.
 */
public enum OrderStates {
    /**
     * 待支付
     */
    WAIT_PAYMENT,

    /**
     * 待发货
     */
    WAIT_DELIVER,

    /**
     * 待收货
     */
    WAIT_RECEIVE,

    /**
     * 订单结束
     */
    FINISH
}
