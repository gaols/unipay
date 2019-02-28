package com.alipay.impl.liuyangkly.model.result;

import com.alipay.impl.liuyangkly.model.TradeStatus;

public abstract class TradeResult implements Result {

    protected TradeStatus tradeStatus;

    public void setTradeStatus(TradeStatus tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public TradeStatus getTradeStatus() {
        return tradeStatus;
    }

}
