package com.github.gaols.unipay.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MathUtils {

    public static String centsToYuan(int cents) {
        BigDecimal v = BigDecimal.valueOf(cents).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
        NumberFormat format = new DecimalFormat("#.##");
        return format.format(v);
    }

}
