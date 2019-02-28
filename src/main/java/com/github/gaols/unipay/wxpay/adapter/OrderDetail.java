package com.github.gaols.unipay.wxpay.adapter;

import java.util.List;

/**
 * 具体可以参考：https://pay.weixin.qq.com/wiki/doc/api/danpin.php?chapter=9_102&index=2
 * 微信统一下单中的detail字段。如果要遵循规范，那么该字段应为json格式。
 */
public class OrderDetail {
    /**
     * 订单原价。
     * 1.商户侧一张小票订单可能被分多次支付，订单原价用于记录整张小票的交易金额。
     * 2.当订单原价与支付金额不相等，则不享受优惠。
     * 3.该字段主要用于防止同一张小票分多次支付，以享受多次优惠的情况，正常支付订单不必上传此参数。
     */
    private int costPrice;
    /**
     * 商品小票ID，String(32)，非必填。
     */
    private String receiptId;
    /**
     * 单品列表，单品信息，使用Json数组格式提交。
     */
    private List<GoodDetail> goodDetails;

    public long getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(int costPrice) {
        this.costPrice = costPrice;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public List<GoodDetail> getGoodDetails() {
        return goodDetails;
    }

    public void setGoodDetails(List<GoodDetail> goodDetails) {
        this.goodDetails = goodDetails;
    }
}
