package com.github.gaols.unipay.api;

import com.github.gaols.unipay.alipay.AlipayNotifyHandler;
import com.github.gaols.unipay.wxpay.WxNotifyHandler;

/**
 * @author gaols
 */
public class NotifyHandlerFactory {

    public static NotifyHandler getNotifyHandler(PayType type) {
        switch (type) {
            case wx:
                return new WxNotifyHandler();
            case alipay:
                return new AlipayNotifyHandler();
        }

        throw new IllegalArgumentException("unsupported pay type: " + type);
    }

    public static NotifyHandler getNotifyHandler(String type) {
        if (Constants.PAY_TYPE_WX.equals(type)) {
            return new WxNotifyHandler();
        } else if (Constants.PAY_TYPE_ALIPAY.equals(type)) {
            return new AlipayNotifyHandler();
        }

        throw new IllegalArgumentException("unsupported pay type: " + type);
    }
}
