package com.github.gaols.unipay.alipay;

import com.github.gaols.unipay.api.NotifyHandler;
import com.github.gaols.unipay.api.PayNotifyParser;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gaols
 */
public class AlipayNotifyHandler implements NotifyHandler {
    @Override
    public String generateResult(boolean handleResult) {
        return handleResult ? "SUCCESS" : "FAIL";
    }

    @Override
    public PayNotifyParser getPayNotifyParser(HttpServletRequest request) {
        return new AlipayPayNotifyParser(request);
    }
}
