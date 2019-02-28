package com.github.gaols.unipay.wxpay;

import java.util.UUID;

/**
 * @author gaols
 */
public class NonceStr {
    public static String gen() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
