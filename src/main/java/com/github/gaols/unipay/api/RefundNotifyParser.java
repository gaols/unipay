package com.github.gaols.unipay.api;

import java.util.Map;

public interface RefundNotifyParser {

    /**
     * 退款是否成功。
     *
     * @return true if refund success, false otherwise.
     */
    boolean isSuccess();

    /**
     * 获取异步通知的所有参数。
     */
    Map<String, String> getNotifyParasMap();
}
