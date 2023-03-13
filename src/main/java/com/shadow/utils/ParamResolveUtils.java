package com.shadow.utils;

import java.util.HashMap;
import java.util.Map;

public class ParamResolveUtils {

    private ParamResolveUtils() {

    }

    /**
     * 解析请求参数
     *
     * @param agentArgs 请求参数，格式：k1=v1&k2=v2&k3=v3
     * @return {@link Map}
     */
    public static Map<String, String> resolveArgs(String agentArgs) {
        Map<String, String> map = new HashMap<>();
        if (agentArgs != null && agentArgs.length() > 0) {
            String[] kvs = agentArgs.split(CommonConstants.COMM);
            for (String kv : kvs) {
                String[] data = kv.split(CommonConstants.EQUAL);
                if (data.length == 2) {
                    map.put(data[0], data[1]);
                }
            }
        }
        return map;
    }
}
