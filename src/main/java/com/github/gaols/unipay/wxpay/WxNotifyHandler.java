package com.github.gaols.unipay.wxpay;

import com.github.gaols.unipay.api.PayNotifyBaseHandler;
import com.github.gaols.unipay.api.PayNotifyParser;
import com.github.gaols.unipay.api.WxNotifyResponse;

import javax.servlet.http.HttpServletRequest;

public class WxNotifyHandler extends PayNotifyBaseHandler {

    @Override
    public String generateResult(boolean handleResult) {
        return handleResult ? WxNotifyResponse.success("OK") : WxNotifyResponse.fail("OK");
    }

    @Override
    public PayNotifyParser getPayNotifyParser(HttpServletRequest request) {
        return new WxPayNotifyParser(request);
    }
}
