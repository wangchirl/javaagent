package com.shadow.core;

import com.shadow.utils.CommonConstants;

import java.util.Map;
import java.util.function.Supplier;

public abstract class AbstractHandler {

    /**
     * 记录 javaagent 传递的参数信息
     */
    private Map<String, String> args;

    /**
     * ThreadLocal class name
     */
    private String threadLocalClassName;

    /**
     * ThreadLocal inner class name
     */
    private String threadLocalInnerClassName;

    /**
     * ThreadLocal class field name
     */
    private String threadLocalFieldName;

    public Map<String, String> getArgs() {
        return this.args;
    }

    protected String getThreadLocalClassName() {
        return threadLocalClassName;
    }

    protected String getThreadLocalInnerClassName() {
        return threadLocalInnerClassName;
    }

    public String getThreadLocalFieldName() {
        return threadLocalFieldName;
    }

    public void setArgs(Map<String, String> args) {
        this.args = args;
        this.threadLocalFieldName = getArgs().get(CommonConstants.THREADLOCAL_FIELD_NAME);
        this.threadLocalClassName = getArgs().get(CommonConstants.THREADLOCAL_CLASS_NAME);
        this.threadLocalInnerClassName = this.threadLocalClassName == null ? null : this.threadLocalClassName.replaceAll(CommonConstants.DOT, CommonConstants.BIAS);
        init();
    }

    protected void init() {
        // hook method
    }

    /**
     * agent method name
     *
     * @return {@link java.lang.String}
     */
    protected Supplier<String> getMethodName() {
        return () -> this.args.get(CommonConstants.METHOD_NAME) == null ? this.getClass().getSimpleName().toLowerCase() : this.args.get(CommonConstants.METHOD_NAME);
    }

    /**
     * 是否 debug 模式
     */
    protected boolean isDebug() {
        return Boolean.parseBoolean(this.args.get(CommonConstants.DEBUG));
    }

}
