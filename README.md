# 扫码支付sdk

网站需要同时接入支付宝扫码支付和微信扫码支付？微信支付和支付宝扫码支付接口不同，写起来有没有很坑？本sdk的目标是统一这两家的扫码
支付接口，让接入变得简单。

## 使用方法

### 引入依赖

```xml
<dependency>
    <groupId>com.github.gaols.unipay</groupId>
    <artifactId>unipay-pc</artifactId>
    <version>1.0.3</version>
</dependency>
```

### 代码示例

```java
UnipayService service = UniPayServiceFactory.getUnipayService(PayType.alipay); // 微信支付使用PayType.wx

Order order = new Order();
order.setSubject("腾讯充值中心-企鹅币充值"); // 商品
order.setOutTradeNo("Q12345678923"); // 订单号
order.setTotalFee(100);  // 支付金额，单位为**分**

OrderContext context = new OrderContext();
context.setNotifyUrl("http://www.youdomain/pay/notify/callback"); // 接收支付回调的url

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

## 配置细节

`MchInfo.create(...)`是为了简化配置而写的工具类，内部实现会根据支付方式的不同自动创建不同的账户配置实例：

* 支付宝对应的是：[AlipayMchInfo](https://github.com/gaols/unipay/blob/master/src/main/java/com/github/gaols/unipay/alipay/AlipayMchInfo.java)；
* 微信对应的是：[WxpayMchInfo](https://github.com/gaols/unipay/blob/master/src/main/java/com/github/gaols/unipay/wxpay/WxpayMchInfo.java)。

`MchInfo.create(...)`第二个参数是用来接收收款账户的配置信息。支付宝和微信需要的配置文件是不同，为了简便起见，推荐直接拷贝本项目目录下
*sample*子目录下的配置文件，将里面的配置信息改成实际的值，放到项目的**src/main/resources**目录下。

> `MchInfo.create(...)`工具类只是为了简化配置，如有必要，可以根据支付类型的不同手动实例化不同的配置实例。

## 处理支付回调

支付回调是支付宝或者微信在用户支付成功后，给商户服务器异步推送的支付通知，告知商户支付结果。商户应以支付结果为依据，正确的处理业务逻辑，同时
按照支付宝和微信要求的回复格式进行应答。由于网络原因或者其他未知原因，支付通知**可能会多次推送**，商户应该能够正确处理。

这里有几点需要注意：

* 商户服务器应该能够正确的处理重复通知；
* 应该按照支付宝和微信要求的格式正确应答；
* 为了防止虚假支付通知，应该能够校验支付通知的真伪。

为了屏蔽处理的复杂性，本项目同样提供了工具类让开发者专注于业务逻辑的开发：

```java
@RequestMapping("/pay/notify")
@Controller
class NotifyController {
    @RequestMapping("/callback")
    @ResponseBody
    public String handleNotify(HttpServletRequest request) {
        PayNotifyHandler h = NotifyHandlerFactory.getNotifyHandler(PayType.wx); // 如果是支付宝支付回调使用PayType.alipay
        return h.handle(request, mchInfo, new PayNotifyCallback() {
            void onPaySuccess(String outTradeNo, Map<String, String> notifyParas) {
                // 这里处理支付成功的业务逻辑，能够进入这里也表明支付校验已经通过。
            }
            boolean isNotifyHandled(String outTradeNo) {
                // 这里处理重复通知，如果已经处理过了，返回true，否则返回false。
                // 如果这里返回了true，那么onPaySuccess不会执行。
            }
        });
    }
}
```

## 依赖说明

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
    <version>2.8.27</version>
</dependency>

<!-- 或者 -->
<dependency>
    <groupId>com.github.binarywang</groupId>
    <artifactId>weixin-java-pay</artifactId>
    <version>3.4.0</version>
</dependency>
```
