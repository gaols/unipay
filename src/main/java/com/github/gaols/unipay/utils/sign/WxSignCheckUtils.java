package com.github.gaols.unipay.utils.sign;

import com.github.gaols.unipay.core.WxVendor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class WxSignCheckUtils {

    public static boolean signCheck(Map<String, String> paras, String signType, String mchKey) {
        Method checkSignMethod = null;

        if (WxVendor.TYPE_weixin_popular.equals(WxVendor.vendorType)) {
            try {
                Class<?> clazz = Class.forName("weixin.popular.util.SignatureUtil");
                checkSignMethod = clazz.getMethod("validateSign", Map.class, String.class, String.class);
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                throw new RuntimeException("incompatible version of weixin-popular", e);
            }
        } else if (WxVendor.TYPE_WxJava.equals(WxVendor.vendorType)) {
            try {
                Class<?> clazz = Class.forName("com.github.binarywang.wxpay.util.SignUtils");
                checkSignMethod = clazz.getMethod("checkSign", Map.class, String.class, String.class);
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                throw new RuntimeException("incompatible version of WxJava", e);
            }
        }

        if (checkSignMethod == null) {
            throw new RuntimeException("checkSignMethod not found");
        }

        try {
            return (boolean) checkSignMethod.invoke(null, paras, signType, mchKey);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
