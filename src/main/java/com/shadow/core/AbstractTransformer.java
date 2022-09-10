package com.shadow.core;

import com.shadow.utils.CommonConstants;

import java.util.Map;

public abstract class AbstractTransformer {

    /**
     * class name
     * {@code java.lang.String}
     */
    private String className;

    /**
     * inner class name
     * {@code java/lang/String}
     */
    private String innerClassName;

    /**
     * 代理参数
     */
    private Map<String, String> args;

    public String getClassName() {
        return className;
    }

    public String getInnerClassName() {
        return innerClassName;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public AbstractTransformer(Map<String, String> resolveArgs) {
        this.args = resolveArgs;
        this.className = getArgs().get(CommonConstants.CONTROLLER_CLASS);
        this.innerClassName = getClassName().replaceAll(CommonConstants.DOT, CommonConstants.BIAS);
    }

    /**
     * 是否 debug 模式
     */
    public boolean isDebug() {
        return Boolean.parseBoolean(getArgs().get(CommonConstants.DEBUG));
    }

}
