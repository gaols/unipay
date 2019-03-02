package com.github.gaols.unipay.api;

import com.github.gaols.unipay.wxpay.WxMchInfo;

import javax.servlet.http.HttpServletRequest;

public class WxRefundNotifyHandler implements RefundNotifyHandler {

    @Override
    public String handle(HttpServletRequest request, MchInfo mchInfo, RefundNotifyCallback callback) {
        WxRefundNotifyParser parser = new WxRefundNotifyParser(request, ((WxMchInfo) mchInfo).getMchKey());
        if (parser.isSuccess()) {
            if (callback.isNotifyHandled(parser.getOutRefundNo())) {
                return WxNotifyResponse.success("OK");
            }

            callback.onRefundSuccess(parser.getOutRefundNo(), parser.getNotifyParasMap());
            return WxNotifyResponse.success("OK");
        }

        return WxNotifyResponse.fail("FAIL");
    }

}
