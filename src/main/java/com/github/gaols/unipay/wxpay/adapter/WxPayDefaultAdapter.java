package com.github.gaols.unipay.wxpay.adapter;

import com.github.gaols.unipay.api.*;

import java.util.Map;

public class WxPayDefaultAdapter implements UnipayService {

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

    @Override
    public boolean checkSign(Map<String, String> params, String signType, String mchKey) {
        throw new UnsupportedOperationException(msg());
    }

    private String msg() {
        return "To support weixin pay, you can use either weixin-popular or WxJava";
    }

}
