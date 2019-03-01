package com.github.gaols.unipay.core;

import com.github.gaols.unipay.api.UnipayService;
import com.github.gaols.unipay.wxpay.adapter.WxPayDefaultAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WxVendor {

    private static final Logger logger = LoggerFactory.getLogger(WxVendor.class);

    private UnipayService proxy;
    private static final WxVendor vendor = new WxVendor();

    public static UnipayService getProxy() {
        return vendor.proxy;
    }

    private void tryProxy(String proxyClass, String proxyAdapterClass) {
        if (proxy == null) {
            try {
                Class.forName(proxyClass);
                proxy = (UnipayService) Class.forName(proxyAdapterClass).newInstance();
                logger.info("{} detected", proxyClass);
            } catch (ClassNotFoundException e) {
                logger.info("{} not in classpath", proxyClass);
            } catch (IllegalAccessException | InstantiationException e) {
                logger.error("instantiate proxy error", e);
            }
        }
    }

    private WxVendor() {
        tryProxy("weixin.popular.bean.paymch.Unifiedorder", "com.github.gaols.unipay.wxpay.adapter.WeixinPopularAdapter");
        tryProxy("com.github.binarywang.wxpay.service.WxPayService", "com.github.gaols.unipay.wxpay.adapter.WxJavaPayAdapter");
        if (proxy == null) {
            proxy = new WxPayDefaultAdapter();
        }
    }

}
