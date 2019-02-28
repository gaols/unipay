package com.github.gaols.unipay.api;

import org.junit.Assert;
import org.junit.Test;

public class UniPayServiceFactoryTest {

    @Test
    public void testUnifyOrder() {
        UnipayService service = UniPayServiceFactory.getUnipayService(PayType.alipay);

        Order order = new Order();
        order.setSubject("腾讯充值中心-企鹅币充值"); // 商品
        order.setOutTradeNo("Q12345678923"); // 订单号
        order.setTotalFee(100);  // 支付金额，单位为分

        OrderContext context = new OrderContext();
        context.setNotifyUrl("http://www.youdomain/xyz/pay/notify/alipay");

        PushOrderResult result = service.unifyOrder(context, order, MchInfo.create(PayType.alipay, "zfb_test.properties"));
        if (result.isOk()) {
            String qrcodeContent = result.getQrCodeContent();
            Assert.assertNotNull(qrcodeContent);
            System.out.println("code: " + qrcodeContent);
            return;
        }

        Assert.fail();
    }

}
