package com.shadow.core;

import com.shadow.utils.Constants;

import java.util.Map;
import java.util.function.Supplier;

public abstract class AbstractHandler {

    /**
     * 记录 javaagent 传递的参数信息
     */
    private Map<String, String> args;

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

    public String getThreadLocalInnerClassName() {
        return threadLocalInnerClassName;
    }

    public String getThreadLocalFieldName() {
        return threadLocalFieldName;
    }

    public AbstractHandler(Map<String, String> args) {
        this.args = args;
        this.threadLocalFieldName = getArgs().get(Constants.THREADLOCAL_FIELD_NAME);
        this.threadLocalInnerClassName = getArgs().get(Constants.THREADLOCAL_CLASS) == null ? null : getArgs().get(Constants.THREADLOCAL_CLASS).replaceAll(Constants.DOT, Constants.BIAS);
    }

    /**
     * agent method name
     *
     * @return {@link java.lang.String}
     */
    protected Supplier<String> getMethodName() {
        return () -> this.args.get(Constants.METHOD_NAME) == null ? this.getClass().getSimpleName().toLowerCase() : this.args.get(Constants.METHOD_NAME);
    }

    /**
     * 是否 debug 模式
     */
    protected boolean isDebug() {
        return Boolean.parseBoolean(this.args.get(Constants.DEBUG));
    }

}
