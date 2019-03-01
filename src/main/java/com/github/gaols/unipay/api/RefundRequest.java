package com.github.gaols.unipay.api;

import org.apache.commons.lang3.StringUtils;

public class RefundRequest {

    private final PayType payType;
    /**
     * 必填
     */
    private String outTradeNo;
    /**
     * 微信退款必须，支付宝不是必填。
     * https://docs.open.alipay.com/api_1/alipay.trade.refund
     * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_4
     * mapping: tansaction_id = trade_no
     */
    private String transactionId;
    /**
     * 退款金额。
     */
    private int refundFee;
    /**
     * 订单总金额，微信退款必填。
     */
    private int totalFee;
    /**
     * 退款原因，支付宝退款必填。
     */
    private String refundReason;
    /**
     * 退款货币类型，需与支付一致，或者不填。
     */
    private String feeType;
    /**
     * 异步接收微信支付退款结果通知的回调地址。
     */
    private String notifyUrl;
    /**
     * 支付宝：标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
     * 微信：商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     * 对于支付宝来说，如果部分退款，则必填。
     */
    private String outRequestNo;

    public RefundRequest(PayType payType) {
        this.payType = payType;
    }

    public PayType getPayType() {
        return payType;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public RefundRequest outTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public RefundRequest transactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public int getRefundFee() {
        return refundFee;
    }

    public RefundRequest refundFee(int refundFee) {
        this.refundFee = refundFee;
        return this;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public RefundRequest totalFee(int totalFee) {
        this.totalFee = totalFee;
        return this;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public RefundRequest refundReason(String refundReason) {
        this.refundReason = refundReason;
        return this;
    }

    public String getFeeType() {
        return feeType;
    }

    public RefundRequest feeType(String feeType) {
        this.feeType = feeType;
        return this;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public RefundRequest notifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }

    public String getOutRequestNo() {
        return outRequestNo;
    }

    public RefundRequest outRequestNo(String outRequestNo) {
        this.outRequestNo = outRequestNo;
        return this;
    }

    public void validate() {
        if (StringUtils.isBlank(outTradeNo)) {
            throw new IllegalArgumentException("outTradeNo required");
        }
        if (refundFee <= 0) {
            throw new IllegalArgumentException("refundFee required");
        }
        if (StringUtils.isBlank(notifyUrl)) {
            throw new IllegalArgumentException("notifyUrl required");
        }

        if (PayType.wx == this.payType) {
            if (StringUtils.isBlank(transactionId)) {
                throw new IllegalArgumentException("transactionId required");
            }
            if (totalFee <= 0) {
                throw new IllegalArgumentException("totalFee required");
            }
        } else if (PayType.alipay == this.payType) {
            if (StringUtils.isBlank(refundReason)) {
                throw new IllegalArgumentException("refundReason required");
            }
            if (totalFee > 0 && refundFee < totalFee && StringUtils.isBlank(outRequestNo)) {
                throw new IllegalArgumentException("outRequestNo required when partial refund");
            }
        }
    }
}
