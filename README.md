# 可能是最简单的扫码支付sdk

网站需要同时接入支付宝扫码支付和微信扫码支付？微信支付和支付宝扫码支付接口不同，写起来有没有很坑？本sdk的目标是统一这两家的扫码
支付接口，让接入变得简单。

## 简单之道

**10行代码**就生成了二维码内容（字符串），将这个字符串传到网站页面，使用一个二维码js库就可以生成
一个支付二维码。

> 不知道二维码js库？[戳这里看看！](https://github.com/davidshimjs/qrcodejs)

### 1.支付宝支付
```java
UnipayService service = UniPayServiceFactory.getUnipayService(PayType.alipay); // 使用微信支付使用PayType.wx

Order order = new Order();
order.setSubject("腾讯充值中心-企鹅币充值"); // 商品
order.setOutTradeNo("Q12345678923"); // 订单号
order.setTotalFee(100);  // 支付金额，单位为分

OrderContext context = new OrderContext();
context.setNotifyUrl("http://www.youdomain/xyz/pay/notify/alipay");

// 如果是微信支付，那么使用MchInfo.create(PayType.wx, "wx.properties")
PushOrderResult result = service.unifyOrder(context, order, MchInfo.create(PayType.alipay, "zfb_test.properties"));
if (result.isOk()) {
    // 将这个字符串传到网页上，使用一个二维码生成js库生成二维码就可以了。
    String qrcodeContent = result.getQrCodeContent();
}
```

> 统一接口，用起来是不是方便，而且只需要短短的10来行代码！是很真的吗？先看完魔鬼细节再说吧！

## 魔鬼细节

## 最佳实践

## 使用说明
