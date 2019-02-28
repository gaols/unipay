package com.github.gaols.unipay.api;

import com.github.gaols.unipay.alipay.AlipayUnipayService;
import com.github.gaols.unipay.wxpay.WxUnipayService;

/**
 * @author gaols
 */
public class UniPayServiceFactory {

    public static UnipayService getUnipayService(PayType type) {
        switch (type) {
            case wx:
                return WxUnipayService.create();
            case alipay:
                return AlipayUnipayService.create();
        }

        throw new IllegalArgumentException("Unknown pay type:" + type);
    }

    public static UnipayService getUnipayService(String type) {
        return getUnipayService(PayType.valueOf(type));
    }
}
