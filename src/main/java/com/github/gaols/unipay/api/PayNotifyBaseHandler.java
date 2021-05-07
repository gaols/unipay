package com.github.gaols.unipay.api;

import com.github.gaols.unipay.core.Locker;
import com.github.gaols.unipay.core.SimpleLocker;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class PayNotifyBaseHandler implements PayNotifyHandler {

    private static Locker locker = new SimpleLocker();

    @Override
    public String handle(HttpServletRequest request, MchInfo mchInfo, PayNotifyCallback callback) {
        String lockName = String.valueOf(System.currentTimeMillis());
        PayNotifyParser parser = getPayNotifyParser(request);
        Map<String, String> parasMap = parser.getNotifyParasMap();
        String outTradeNo = parasMap.get("out_trade_no");
        if (StringUtils.isNotBlank(outTradeNo)) {
            lockName = outTradeNo;
        }
        try {
            locker.lock(lockName);
            if (callback.isNotifyHandled(outTradeNo)) {
                return generateResult(true);
            }

            if (parser.isSignValid(mchInfo) && parser.isSuccess()) {
                callback.onPaySuccess(outTradeNo, parasMap);
                return generateResult(true);
            }

            return generateResult(false);
        } finally {
            locker.release(lockName);
        }
    }

}
