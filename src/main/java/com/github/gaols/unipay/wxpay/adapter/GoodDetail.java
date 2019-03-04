package com.github.gaols.unipay.wxpay.adapter;

/*
 * 具体可以参考：https://pay.weixin.qq.com/wiki/doc/api/danpin.php?chapter=9_102&index=2
 * "goods_id": "商品编码",
 * "wxpay_goods_id": "1001",
 * "goods_name": "",
 * "quantity": 1,
 * "price": 528800
 */
public class GoodDetail {

    /**
     * 商品编码，String(32)，由半角的大小写字母、数字、中划线、下划线中的一种或几种组成。
     */
    private String goodsId;
    /**
     * 微信侧商品编码，非必填，String(32)，微信支付定义的统一商品编号（没有可不传）。
     */
    private String wxpayGoodsId;
    /**
     * 商品名称，非必填，String(256)。
     */
    private String goodsName;
    /**
     * 用户购买的数量。
     */
    private int quantity;
    /**
     * 单位为：分。如果商户有优惠，需传输商户优惠后的单价(例如：用户对一笔100元的订单使用了商场发的纸质优惠券100-50，则活动商品的单价应为原单价-50。
     */
    private long price;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getWxpayGoodsId() {
        return wxpayGoodsId;
    }

    public void setWxpayGoodsId(String wxpayGoodsId) {
        this.wxpayGoodsId = wxpayGoodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
