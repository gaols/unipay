package com.alipay.impl.liuyangkly.model.result;

import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.impl.liuyangkly.model.TradeStatus;

/**
 * Created by liuyangkly on 15/8/27.
 */
public class AlipayF2FPrecreateResult extends TradeResult {
    private AlipayTradePrecreateResponse response;

    public AlipayF2FPrecreateResult(AlipayTradePrecreateResponse response) {
        this.response = response;
    }

    public void setResponse(AlipayTradePrecreateResponse response) {
        this.response = response;
    }

    public AlipayTradePrecreateResponse getResponse() {
        return response;
    }

    @Override
    public boolean isTradeSuccess() {
        return response != null && TradeStatus.SUCCESS.equals(tradeStatus);
    }
}
