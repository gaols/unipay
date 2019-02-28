package com.alipay.impl.liuyangkly.utils;

import java.math.BigDecimal;

/**
 * Created by liuyangkly on 15/6/27.
 * 杂物工具类
 */
public class Utils {

    private Utils() {
        // No instances.
    }

    public static String toAmount(long amount) {
        return new BigDecimal(amount).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
