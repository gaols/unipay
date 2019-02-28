package com.github.gaols.unipay.core;

import com.github.gaols.unipay.api.*;

/**
 * 默认实现是抛出异常，以提示API使用者使用正确的实现。
 */
public class DefaultUnifyOrderService implements UnipayService {

    private final String payType;

    @Override
    public PushOrderResult unifyOrder(OrderContext context, Order order, MchInfo mchInfo) {
        throw new IllegalStateException("unknown pay type:" + this.payType);
    }

    @Override
    public TradeStatus queryOrderStatus(String outTradeNo, MchInfo mchInfo) {
        throw new IllegalStateException("unknown out trade no");
    }

    @Override
    public void cancelOrder(String outTradeNo, MchInfo mchInfo) {
        throw new IllegalStateException("unknown out trade no");
    }

    public DefaultUnifyOrderService(String payType) {
        this.payType = payType;
    }
}
