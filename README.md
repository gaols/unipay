# 可能是最简单的扫码支付sdk

网站需要同时接入支付宝扫码支付和微信扫码支付？微信支付和支付宝扫码支付接口不同，写起来有没有很坑？本sdk的目标是统一这两家的扫码
支付接口，让接入变得简单。

## 简单之道

### 支付宝支付
```java
UnipayService service = UniPayServiceFactory.getUnipayService(PayType.alipay); // 使用微信支付使用PayType.wx

Order order = new Order();
order.setSubject("腾讯充值中心-企鹅币充值"); // 商品
order.setOutTradeNo("Q12345678923"); // 订单号
order.setTotalFee(100);  // 支付金额，单位为分

OrderContext context = new OrderContext();
context.setNotifyUrl("http://www.youdomain/xyz/pay/notify/alipay"); // 接收支付回调的url

// 如果是微信支付，那么使用MchInfo.create(PayType.wx, "wx.properties")
PushOrderResult result = service.unifyOrder(context, order, MchInfo.create(PayType.alipay, "zfb_test.properties"));
if (result.isOk()) {
    // 将这个字符串传到网页上，使用一个二维码生成js库生成二维码就可以了。
    String qrcodeContent = result.getQrCodeContent();
}
```

**10行代码**就生成了二维码内容（字符串），将这个字符串传到网站页面，使用一个二维码js库就可以生成
一个支付二维码。

> 不知道二维码js库？[戳这里看看！](https://github.com/davidshimjs/qrcodejs)
> 统一接口，用起来是不是方便，而且只需要短短的10来行代码！是很真的吗？先看完魔鬼细节再说吧！

### 魔鬼细节

1. `MchInfo.create(PayType.alipay, "zfb_test.properties")`有什么特别？

zfb_test.properties是什么？这个简单，当然是支付宝账户的配置信息。那么这个配置文件应该放哪？当然可以放在项目中的任何地方，只要你的代码可以
访问即可，但为了方便，推荐放在`src/main/resources`目录下，`MchInfo.create(...)`会根据支付方式方式的不同自动创建不同的账户配置实例：
支付宝对应的`AlipayMchInfo`,微信对应的是`WxMchInfo`。如果不嫌麻烦，当然可以根据不同的支付类型，手动实例化`AlipayMchInfo`和`WxMchInfo`。

还有一个需要强调的是，`MchInfo.create(...)`对配置文件的具体格式是有要求的。这个很容易理解，配置文件不可能随便写。那么配置文件究竟怎么写？
直接拷贝本项目目录sample子目录下的配置文件，然后将里面的配置信息改成实际的值即可。

## 使用说明

如果要支持支付宝支付，需要引入`alipay-sdk-java`。

```xml
<dependency>
    <groupId>com.alipay.sdk</groupId>
    <artifactId>alipay-sdk-java</artifactId>
    <version>3.6.0.ALL</version>
</dependency>
```

如果要支持微信支付，需要引入`weixin-java-pay`或者`weixin-popular`。二选一即可！

```xml
<dependency>
    <groupId>com.github.liyiorg</groupId>
    <artifactId>weixin-popular</artifactId>
    <version>2.8.10</version>
</dependency>

<!-- 或者 -->
<dependency>
    <groupId>com.github.binarywang</groupId>
    <artifactId>weixin-java-pay</artifactId>
    <version>3.2.0</version>
</dependency>
```
