package com.shadow.core;

import com.shadow.common.RequestArgsVO;
import com.shadow.utils.CommonConstants;
import com.shadow.utils.ParamResolveUtils;

import java.util.function.Supplier;

public abstract class AbstractHandler {

    /**
     * agent request args
     */
    private RequestArgsVO args;

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

    /**
     * inner class name
     */
    private String innerClassName;

    public String getInnerClassName() {
        return innerClassName;
    }

    public void initInnerClassName() {
        this.innerClassName = getArgs().getCtlClass().replaceAll(CommonConstants.REG_DOT, CommonConstants.BIAS);
    }

    public RequestArgsVO getArgs() {
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

    public AbstractHandler() {
        this.args = ParamResolveUtils.REQUEST_ARGS_VO_THREAD_LOCAL.get();
        init();
    }

    public void setArgs(RequestArgsVO args) {
        this.args = args;
        init();
    }

    private void init() {
        this.threadLocalFieldName = getArgs().getTlFieldName();
        this.threadLocalClassName = getArgs().getTlClass();
        this.threadLocalInnerClassName = this.threadLocalClassName == null ? null : this.threadLocalClassName.replaceAll(CommonConstants.REG_DOT, CommonConstants.BIAS);
        this.initInnerClassName();
    }

    /**
     * agent method name
     *
     * @return {@link java.lang.String}
     */
    protected Supplier<String> getMethodName() {
        return () -> this.args.getMethodName() == null ? this.getClass().getSimpleName().toLowerCase() : this.args.getMethodName();
    }

    /**
     * 是否 debug 模式
     */
    protected boolean isDebug() {
        return this.args.getDebug();
    }

}
