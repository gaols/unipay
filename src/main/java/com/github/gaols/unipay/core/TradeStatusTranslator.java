package com.github.gaols.unipay.core;

import com.alipay.impl.liuyangkly.model.TradeStatus;

public class TradeStatusTranslator {
    public static PushOrderStatus translateAlipayPushOrderStatus(TradeStatus status) {
        PushOrderStatus retStatus;
        switch (status) {
            case SUCCESS:
                retStatus = PushOrderStatus.SUCCESS;
                break;
            case FAILED:
                retStatus = PushOrderStatus.FAILED;
                break;
            default:
                retStatus = PushOrderStatus.UNKNOWN;
                break;
        }
        return retStatus;
    }

    public static com.github.gaols.unipay.api.TradeStatus translateAlipayTradeStatus(TradeStatus status) {
        com.github.gaols.unipay.api.TradeStatus retStatus;
        switch (status) {
            case SUCCESS:
                retStatus = com.github.gaols.unipay.api.TradeStatus.SUCCESS;
                break;
            case FAILED:
                retStatus = com.github.gaols.unipay.api.TradeStatus.PAYERROR;
                break;
            default:
                retStatus = com.github.gaols.unipay.api.TradeStatus.UNKNOWN;
                break;
        }
        return retStatus;
    }

    public static com.github.gaols.unipay.api.TradeStatus translateWxTradeStatus(String status) {
        com.github.gaols.unipay.api.TradeStatus retStatus = com.github.gaols.unipay.api.TradeStatus.UNKNOWN;
        if ("SUCCESS".equals(status)) {
            retStatus = com.github.gaols.unipay.api.TradeStatus.SUCCESS;
        } else if ("REFUND".equals(status)) {
            retStatus = com.github.gaols.unipay.api.TradeStatus.REFUND;
        } else if ("NOTPAY".equals(status)) {
            retStatus = com.github.gaols.unipay.api.TradeStatus.WAIT_BUYER_PAY;
        } else if ("CLOSED".equals(status)) {
            retStatus = com.github.gaols.unipay.api.TradeStatus.CLOSED;
        } else if ("REVOKED".equals(status)) {
            retStatus = com.github.gaols.unipay.api.TradeStatus.REVOKED;
        } else if ("USERPAYING".equals(status)) {
            retStatus = com.github.gaols.unipay.api.TradeStatus.USERPAYING;
        } else if ("PAYERROR".equals(status)) {
            retStatus = com.github.gaols.unipay.api.TradeStatus.PAYERROR;
        }
        return retStatus;
    }
}
