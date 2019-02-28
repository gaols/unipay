package com.github.gaols.unipay.api;

import javax.servlet.http.HttpServletRequest;

public interface NotifyHandler {
    /**
     * 返回给微信或者支付宝的处理结果。
     *
     * @param handleResult true if successfully handled, false otherwise.
     */
    String generateResult(boolean handleResult);

    PayNotifyParser getPayNotifyParser(HttpServletRequest request);
}
