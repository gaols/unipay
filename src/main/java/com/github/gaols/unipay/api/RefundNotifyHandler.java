package com.github.gaols.unipay.api;

import javax.servlet.http.HttpServletRequest;

public interface RefundNotifyHandler {

    String handle(HttpServletRequest request, MchInfo mchInfo, RefundNotifyCallback callback);
}
