package com.github.gaols.unipay.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author gaols
 */
public class ParaUtils {
    /**
     * 抽取请求搜有请求参数的第一个值，注意是第一个，也就是说如果参数有多个值，也只会取第一个值。
     *
     * @param map The original parameters map.
     * @return the para map which map every para to the very first value.
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getParasMap(Map map) {
        Map<String, String> parasMap = new HashMap<>();
        Set set = map.entrySet();
        for (Object v : set) {
            Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) v;
            String[] value = entry.getValue();
            parasMap.put(entry.getKey(), value != null && value.length > 0 ? value[0] : null);
        }
        return parasMap;
    }

    public static String formatParas(Map<String, String> parasMap, String header) {
        Set<Map.Entry<String, String>> set = parasMap.entrySet();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s%n", header));
        sb.append("-----------------------------------------------\n");
        for (Map.Entry<String, String> entry : set) {
            sb.append(String.format("%30s:  %s%n", entry.getKey(), entry.getValue()));
        }
        sb.append("-----------------------------------------------\n");
        return sb.toString();
    }
}
