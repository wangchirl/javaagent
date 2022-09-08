package com.shadow.core;

import com.shadow.utils.Constants;

import java.util.Map;
import java.util.function.Supplier;

public abstract class AbstractHandler implements IHandler{

    /**
     * 记录 javaagent 传递的参数信息
     */
    private Map<String, String> args;

    public Map<String, String> getArgs() {
        return this.args;
    }

    public AbstractHandler(Map<String, String> args) {
        this.args = args;
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
