package com.github.gaols.unipay.api;

import com.github.gaols.unipay.alipay.AlipayPayNotifyParser;
import com.github.gaols.unipay.wxpay.WxPayNotifyParser;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gaols
 */
public class PayNotifyParserFactory {
    /**
     * 根据支付类型，获取异步通知的处理器。
     *
     * @param type 支付类型
     * @return 异步支付通知处理器
     */
    public static PayNotifyParser getNotifyParser(PayType type, HttpServletRequest request) {
        switch (type) {
            case wx:
                return new WxPayNotifyParser(request);
            case alipay:
                return new AlipayPayNotifyParser(request);
        }

        throw new RuntimeException("no such type:" + type);
    }
}
