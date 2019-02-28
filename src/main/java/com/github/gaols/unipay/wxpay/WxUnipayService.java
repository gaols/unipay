package com.github.gaols.unipay.wxpay;

import com.github.gaols.unipay.api.*;
import com.github.gaols.unipay.core.WxVendor;
import com.github.gaols.unipay.wxpay.adapter.DefaultAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author gaols
 */
public class WxUnipayService implements UnipayService {

    private static final Logger logger = LoggerFactory.getLogger(WxUnipayService.class);
    private static final WxUnipayService service = new WxUnipayService();

    private UnipayService proxy;

    @Override
    public PushOrderResult unifyOrder(OrderContext context, Order order, MchInfo mchInfo) {
        logger.error("Unify order START: " + order.toString());
        return proxy.unifyOrder(context, order, mchInfo);
    }

    @Override
    public TradeStatus queryOrderStatus(String outTradeNo, MchInfo mchInfo) {
        return proxy.queryOrderStatus(outTradeNo, mchInfo);
    }

    /**
     * 微信在下单五分钟之内是不允许撤销的。
     *
     * @param outTradeNo 订单编号
     */
    @Override
    public void cancelOrder(String outTradeNo, MchInfo mchInfo) {
        proxy.cancelOrder(outTradeNo, mchInfo);
    }

    @Override
    public boolean checkSign(Map<String, String> params, String signType, String mchKey) {
        return false;
    }

    private WxUnipayService() {
        tryProxy("weixin.popular.bean.paymch.Unifiedorder", "com.github.gaols.unipay.wxpay.adapter.WeixinPopularAdapter", WxVendor.TYPE_weixin_popular);
        tryProxy("com.github.binarywang.wxpay.service.WxPayService", "com.github.gaols.unipay.wxpay.adapter.WxJavaPayAdapter", WxVendor.TYPE_WxJava);
        if (proxy == null) {
            proxy = new DefaultAdapter();
        }
    }

    private void tryProxy(String clazz, String proxy, String vendorType) {
        if (this.proxy == null) {
            try {
                Class.forName(clazz);
                this.proxy = (UnipayService) Class.forName(proxy).newInstance();
                WxVendor.vendorType = vendorType;
                logger.info("{} detected", clazz);
            } catch (ClassNotFoundException e) {
                logger.info("{} not in classpath", clazz);
            } catch (IllegalAccessException | InstantiationException e) {
                logger.error("instantiate proxy error", e);
            }
        }
    }

    public static WxUnipayService create() {
        return service;
    }
}
