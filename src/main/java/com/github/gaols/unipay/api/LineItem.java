package com.github.gaols.unipay.api;

/**
 * The entity counterpart of alipay is GoodsDetail.
 *
 * @author gaols
 */
public class LineItem {
    private String goodsId;
    /**
     * vendorGoodsId can be alipay or wx Goods id
     */
    private String vendorGoodsId;
    private String goodsName;
    private int quantity;
    private long price;
    private String goodsCategory;
    private String body;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getVendorGoodsId() {
        return vendorGoodsId;
    }

    public void setVendorGoodsId(String vendorGoodsId) {
        this.vendorGoodsId = vendorGoodsId;
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

    public String getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(String goodsCategory) {
        this.goodsCategory = goodsCategory;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return String.format("%s *%d/price:%d/%s", goodsName, quantity, price, body);
    }
}
