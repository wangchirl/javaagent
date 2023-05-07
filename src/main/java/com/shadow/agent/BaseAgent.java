package com.shadow.agent;

import com.shadow.common.RequestArgsVO;
import com.shadow.utils.CommonConstants;


class BaseAgent {

    static void handleCommonDefaultArgs(RequestArgsVO argsBean) {
        // 1、SIMPLE JOB IOC
        if (argsBean.getCrudFieldName() == null) {
            argsBean.setCrudFieldName(CommonConstants.DEFAULT_SIMPLE_JOB_IOC_FIELD_NAME);
        }
        // 2、HTTP_REQUEST_URI
        if (argsBean.getHttpUri() != null) {
            argsBean.setHttpUri(argsBean.getHttpUri() + CommonConstants.PATH_SUFFIX);
        } else {
            argsBean.setHttpUri(CommonConstants.DEFAULT_HTTP_PATH_PREFIX + CommonConstants.PATH_SUFFIX);
        }
        // 3、IOC_FIELD_NAME
        if (argsBean.getIocFieldName() == null) {
            argsBean.setIocFieldName(CommonConstants.DEFAULT_IOC_FIELD_VALUE);
        }
    }

}
