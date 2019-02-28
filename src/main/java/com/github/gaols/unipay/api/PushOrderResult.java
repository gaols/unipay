package com.github.gaols.unipay.api;


import com.github.gaols.unipay.core.PushOrderStatus;

import java.util.HashMap;
import java.util.Map;

public class PushOrderResult {
    private PushOrderStatus pushOrderStatus;
    private Map<String, Object> response = new HashMap<>();

    public PushOrderStatus getPushOrderStatus() {
        return pushOrderStatus;
    }

    public void setPushOrderStatus(PushOrderStatus pushOrderStatus) {
        this.pushOrderStatus = pushOrderStatus;
    }

    public Map<String, Object> getResponse() {
        return response;
    }

    public boolean isOk() {
        return PushOrderStatus.SUCCESS == this.pushOrderStatus;
    }

    public String getQrCodeContent() {
        return (String) response.get("qr_code_url");
    }

    public String getOutTradeNo() {
        return (String) response.get("out_trade_no");
    }

    public void setResponse(Map<String, Object> response) {
        this.response = response;
    }
}
