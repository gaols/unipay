package com.github.gaols.unipay.api;

/**
 * @author gaols
 */
public enum TradeStatus {
//    蚂蚁金服交易状态：
//    WAIT_BUYER_PAY
//    TRADE_CLOSED
//    TRADE_SUCCESS
//    TRADE_FINISHED

//    微信交易状态：
//    SUCCESS—支付成功
//    REFUND—转入退款
//    NOTPAY—未支付
//    CLOSED—已关闭
//    REVOKED—已撤销（刷卡支付）
//    USERPAYING--用户支付中
//    PAYERROR--支付失败(其他原因，如银行返回失败)

    WAIT_BUYER_PAY,
    SUCCESS,
    CLOSED,
    USERPAYING,
    FINISHED,
    PAYERROR,
    REVOKED,
    REFUND,
    UNKNOWN
}
