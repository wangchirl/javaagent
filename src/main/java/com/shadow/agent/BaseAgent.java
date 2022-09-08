package com.shadow.agent;

import com.shadow.utils.Constants;

import java.util.Map;

class BaseAgent {

    static void handleCommonDefaultArgs(Map<String, String> resolveArgs) {
        // 1、DEBUG
        resolveArgs.putIfAbsent(Constants.DEBUG, "false");
        // 2、CRUD 相关
        resolveArgs.putIfAbsent(Constants.TASK_CRUD, "false");
        resolveArgs.computeIfAbsent(Constants.SIMPLE_JOB_IOC_FIELD_NAME, k -> Constants.DEFAULT_SIMPLE_JOB_IOC_FIELD_NAME);
        // 3、HTTP_REQUEST_URI
        if (resolveArgs.get(Constants.HTTP_REQUEST_PREFIX_URI) != null) {
            resolveArgs.put(Constants.HTTP_REQUEST_PREFIX_URI, resolveArgs.get(Constants.HTTP_REQUEST_PREFIX_URI) + Constants.PATH_SUFFIX);
        } else {
            resolveArgs.put(Constants.HTTP_REQUEST_PREFIX_URI, Constants.DEFAULT_HTTP_PATH_PREFIX + Constants.PATH_SUFFIX);
        }
        // 4、IOC_FIELD_NAME
        resolveArgs.computeIfAbsent(Constants.IOC_FIELD_NAME, k -> Constants.DEFAULT_IOC_FIELD_VALUE);
    }

}
