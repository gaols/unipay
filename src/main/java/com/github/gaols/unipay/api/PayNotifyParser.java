package com.github.gaols.unipay.api;

import java.util.Map;

/**
 * 处理支付异步通知。
 *
 * @author gaols
 */
public interface PayNotifyParser {
    /**
     * 支付异步通知是否成功。
     *
     * @return true if paid success, false otherwise.
     */
    boolean isSuccess();

    /**
     * 验签是否成功。
     *
     * @return true if sign check passed, false otherwise.
     */
    boolean isSignValid(MchInfo mchInfo);

    /**
     * 获取异步通知的所有参数。
     */
    Map<String, String> getNotifyParasMap();
}
