package com.github.gaols.unipay.api;

import java.util.Map;

public interface PayNotifyCallback {

    void onPaySuccess(String outTradeNo, Map<String, String> notifyParas);

    /**
     * @return true if the notification has already handled, false otherwise.
     */
    boolean isNotifyHandled(String outTradeNo);
}
