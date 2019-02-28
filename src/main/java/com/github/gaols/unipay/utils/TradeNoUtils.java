package com.github.gaols.unipay.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TradeNoUtils {

    public static String genUniqueNo(String prefix) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return prefix + sdf.format(new Date()) + getPositiveRandom(4);
    }

    private static String getPositiveRandom(int count) {
        StringBuilder sb = new StringBuilder();
        String str = "0123456789";
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num) + ""), "");
        }
        return sb.toString();
    }

    public synchronized static String genOrderNo(String prefix) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return prefix + sdf.format(new Date()) + getPositiveRandom(2);
    }

    public synchronized static String genOrderNo2(String prefix) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
        return prefix + sdf.format(new Date()) + RandomStringUtils.randomAlphanumeric(4);
    }

    public synchronized static String genOrderNo3(String prefix) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
        return prefix + sdf.format(new Date()) + RandomStringUtils.randomAlphanumeric(3);
    }
}
