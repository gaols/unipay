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

    public void setResponse(Map<String, Object> response) {
        this.response = response;
    }
}
