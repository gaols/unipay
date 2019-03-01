package com.github.gaols.unipay.api;

import com.alipay.api.domain.TradeFundBill;

import java.util.Date;
import java.util.List;

public class AlipayRefundResult extends RefundResult {

    private String buyerLogonId;
    private String buyerUserId;
    private String fundChange;
    private Date gmtRefundPay;
    private String openId;
    private String outTradeNo;
    private String presentRefundBuyerAmount;
    private String presentRefundDiscountAmount;
    private String presentRefundMdiscountAmount;
    private String refundCurrency;
    private List<TradeFundBill> refundDetailItemList;
    private String refundFee;
    private String sendBackFee;
    private String storeName;
    private String tradeNo;

    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId;
    }

    public String getBuyerUserId() {
        return buyerUserId;
    }

    public void setBuyerUserId(String buyerUserId) {
        this.buyerUserId = buyerUserId;
    }

    public String getFundChange() {
        return fundChange;
    }

    public void setFundChange(String fundChange) {
        this.fundChange = fundChange;
    }

    public Date getGmtRefundPay() {
        return gmtRefundPay;
    }

    public void setGmtRefundPay(Date gmtRefundPay) {
        this.gmtRefundPay = gmtRefundPay;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getPresentRefundBuyerAmount() {
        return presentRefundBuyerAmount;
    }

    public void setPresentRefundBuyerAmount(String presentRefundBuyerAmount) {
        this.presentRefundBuyerAmount = presentRefundBuyerAmount;
    }

    public String getPresentRefundDiscountAmount() {
        return presentRefundDiscountAmount;
    }

    public void setPresentRefundDiscountAmount(String presentRefundDiscountAmount) {
        this.presentRefundDiscountAmount = presentRefundDiscountAmount;
    }

    public String getPresentRefundMdiscountAmount() {
        return presentRefundMdiscountAmount;
    }

    public void setPresentRefundMdiscountAmount(String presentRefundMdiscountAmount) {
        this.presentRefundMdiscountAmount = presentRefundMdiscountAmount;
    }

    public String getRefundCurrency() {
        return refundCurrency;
    }

    public void setRefundCurrency(String refundCurrency) {
        this.refundCurrency = refundCurrency;
    }

    public List<TradeFundBill> getRefundDetailItemList() {
        return refundDetailItemList;
    }

    public void setRefundDetailItemList(List<TradeFundBill> refundDetailItemList) {
        this.refundDetailItemList = refundDetailItemList;
    }

    public String getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;
    }

    public String getSendBackFee() {
        return sendBackFee;
    }

    public void setSendBackFee(String sendBackFee) {
        this.sendBackFee = sendBackFee;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }
}
