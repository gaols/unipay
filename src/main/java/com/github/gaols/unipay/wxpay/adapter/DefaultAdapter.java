package com.github.gaols.unipay.wxpay.adapter;

import com.github.gaols.unipay.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultAdapter implements UnipayService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultAdapter.class);

    @Override
    public PushOrderResult unifyOrder(OrderContext context, Order order, MchInfo mchInfo) {
        throw new UnsupportedOperationException(msg());
    }

    @Override
    public TradeStatus queryOrderStatus(String outTradeNo, MchInfo mchInfo) {
        throw new UnsupportedOperationException(msg());
    }

    @Override
    public void cancelOrder(String outTradeNo, MchInfo mchInfo) {
        throw new UnsupportedOperationException(msg());
    }

    private String msg() {
        return "To support weixin pay, you can use either weixin-popular or WxJava";
    }

}
