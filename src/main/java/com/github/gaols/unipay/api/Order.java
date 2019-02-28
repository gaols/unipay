package com.github.gaols.unipay.api;

import java.util.List;

/**
 * The abstraction for an order.
 *
 * @author gaols
 */
public class Order {
    /**
     * 订单编号。
     */
    private String outTradeNo;
    /**
     * 订单标题（例如：Iphone 6S 16G）
     */
    private String subject;
    /**
     * 订单子项，可不填。
     */
    private List<LineItem> lineItemList;
    /**
     * 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"，仅针对支付宝支付。
     */
    private String body;
    /**
     * 订单总金额，单位（分）。
     */
    private long totalFee;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    /**
     * 微信支付的body参数，应该使用该函数。
     * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2, body字段的格式要求。
     * subject的示例：腾讯充值中心-QQ会员充值
     */
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<LineItem> getLineItemList() {
        return lineItemList;
    }

    public void setLineItemList(List<LineItem> lineItemList) {
        this.lineItemList = lineItemList;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(long totalFee) {
        this.totalFee = totalFee;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("----------------------------------------------------\n");
        sb.append(String.format("%10s: %s%n", "outTradeNo", outTradeNo));
        sb.append(String.format("%10s: %s%n", "subject", subject));
        sb.append(String.format("%10s: %s%n", "body", body));
        sb.append(String.format("%10s: %s%n", "totalFee", totalFee));
        if (lineItemList != null && !lineItemList.isEmpty()) {
            sb.append("----------------------------------------------------\n");
            sb.append("Line items:\n");
            for (LineItem item : lineItemList) {
                sb.append(item.toString()).append("\n");
            }
        }
        sb.append("----------------------------------------------------\n");
        return sb.toString();
    }
}
