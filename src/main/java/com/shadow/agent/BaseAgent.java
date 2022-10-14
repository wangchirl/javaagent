package com.shadow.agent;

import com.shadow.utils.CommonConstants;

import java.util.Map;

class BaseAgent {

    static void handleCommonDefaultArgs(Map<String, String> resolveArgs) {
        // 1、DEBUG
        resolveArgs.putIfAbsent(CommonConstants.DEBUG, "false");
        // 2、CRUD 相关
        resolveArgs.putIfAbsent(CommonConstants.TASK_CRUD, "true");
        resolveArgs.computeIfAbsent(CommonConstants.SIMPLE_JOB_IOC_FIELD_NAME, k -> CommonConstants.DEFAULT_SIMPLE_JOB_IOC_FIELD_NAME);
        // 3、HTTP_REQUEST_URI
        if (resolveArgs.get(CommonConstants.HTTP_REQUEST_PREFIX_URI) != null) {
            resolveArgs.put(CommonConstants.HTTP_REQUEST_PREFIX_URI, resolveArgs.get(CommonConstants.HTTP_REQUEST_PREFIX_URI) + CommonConstants.PATH_SUFFIX);
        } else {
            resolveArgs.put(CommonConstants.HTTP_REQUEST_PREFIX_URI, CommonConstants.DEFAULT_HTTP_PATH_PREFIX + CommonConstants.PATH_SUFFIX);
        }
        // 4、IOC_FIELD_NAME
        resolveArgs.computeIfAbsent(CommonConstants.IOC_FIELD_NAME, k -> CommonConstants.DEFAULT_IOC_FIELD_VALUE);
    }

}
