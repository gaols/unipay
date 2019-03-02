package com.github.gaols.unipay.api;

import java.util.Map;

public interface RefundNotifyCallback {

    void onRefundSuccess(String outRefundNo, Map<String, String> notifyParas);

    /**
     * @return true if the notification has already handled, false otherwise.
     */
    boolean isNotifyHandled();

}
