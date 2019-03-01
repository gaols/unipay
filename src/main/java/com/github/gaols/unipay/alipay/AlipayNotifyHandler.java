package com.github.gaols.unipay.alipay;

import com.github.gaols.unipay.api.PayNotifyBaseHandler;
import com.github.gaols.unipay.api.PayNotifyParser;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gaols
 */
public class AlipayNotifyHandler extends PayNotifyBaseHandler {
    @Override
    public String generateResult(boolean handleResult) {
        return handleResult ? "SUCCESS" : "FAIL";
    }

    @Override
    public PayNotifyParser getPayNotifyParser(HttpServletRequest request) {
        return new AlipayPayNotifyParser(request);
    }
}
