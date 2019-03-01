package com.github.gaols.unipay.api;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class PayNotifyBaseHandler implements PayNotifyHandler {

    @Override
    public String handle(HttpServletRequest request, MchInfo mchInfo, PayNotifyCallback callback) {
        PayNotifyParser parser = getPayNotifyParser(request);
        Map<String, String> parasMap = parser.getNotifyParasMap();
        String outTradeNo = parasMap.get("out_trade_no");
        if (callback.isNotifyHandled()) {
            return generateResult(true);
        }

        if (parser.isSignValid(mchInfo) && parser.isSuccess()) {
            callback.onPaySuccess(outTradeNo, parasMap);
            return generateResult(true);
        }

        return generateResult(false);
    }

}
