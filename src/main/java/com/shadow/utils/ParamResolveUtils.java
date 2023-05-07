package com.shadow.utils;

import com.shadow.common.RequestArgsVO;

import java.util.HashMap;
import java.util.Map;

public class ParamResolveUtils {

    public static final ThreadLocal<RequestArgsVO> REQUEST_ARGS_VO_THREAD_LOCAL = new ThreadLocal<>();

    private ParamResolveUtils() {

    }

    /**
     * 解析请求参数
     *
     * @param agentArgs 请求参数，格式：k1=v1,k2=v2,k3=v3
     * @return {@link RequestArgsVO}
     */
    public static RequestArgsVO resolveArgs(String agentArgs) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (agentArgs != null && agentArgs.length() > 0) {
            String[] kvs = agentArgs.split(CommonConstants.COMM);
            for (String kv : kvs) {
                String[] data = kv.split(CommonConstants.EQUAL);
                if (data.length == 2) {
                    map.put(data[0], data[1]);
                }
            }
        }
        RequestArgsVO requestArgsVO = BeanUtils.mapToBean(map, RequestArgsVO.class);
        // 默认值设置
        if (requestArgsVO.getDebug() == null) {
            requestArgsVO.setDebug(false);
        }
        if (requestArgsVO.getLogger() == null) {
            requestArgsVO.setLogger(false);
        }
        if (requestArgsVO.getCrud() == null) {
            requestArgsVO.setCrud(true);
        }
        // thread local 存储对象
        REQUEST_ARGS_VO_THREAD_LOCAL.set(requestArgsVO);
        return requestArgsVO;
    }
}
