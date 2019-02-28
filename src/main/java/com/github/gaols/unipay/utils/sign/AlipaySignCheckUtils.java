package com.github.gaols.unipay.utils.sign;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.github.gaols.unipay.alipay.AlipayMchInfo;
import com.github.gaols.unipay.api.MchInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class AlipaySignCheckUtils {

    private static final Logger logger = LoggerFactory.getLogger(AlipaySignCheckUtils.class);

    public static boolean rsaSignCheck(Map<String, String> paras, MchInfo mchInfo) {
        try {
            AlipayMchInfo info = (AlipayMchInfo) mchInfo;
            return AlipaySignature.rsaCheckV1(paras, info.getAlipayPublicKey(), "UTF-8", info.getSignType());
        } catch (AlipayApiException e) {
            logger.error("sign check failed", e);
            throw new RuntimeException(e);
        }
    }
}
