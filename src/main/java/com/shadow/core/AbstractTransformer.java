package com.shadow.core;

import com.shadow.core.common.ProxyClassLoader;
import com.shadow.utils.CommonConstants;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Set;

public abstract class AbstractTransformer {

    /**
     * current handle class name
     */
    private String handlerClassName;

    /**
     * current job type
     */
    private CommonConstants.JobTypeEnum jobType;

    /**
     * proxy controller class name
     * {@code java.lang.String}
     */
    private String className;

    /**
     * proxy controller inner class name
     * {@code java/lang/String}
     */
    private String innerClassName;

    /**
     * agent request args
     */
    private Map<String, String> args;

    protected CommonConstants.JobTypeEnum getJobType() {
        return jobType;
    }

    public String getInnerClassName() {
        return innerClassName;
    }

    public String getClassName() {
        return className;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public AbstractTransformer(Map<String, String> resolveArgs) {
        this.args = resolveArgs;
        this.className = resolveArgs.get(CommonConstants.CONTROLLER_CLASS);
        this.innerClassName = this.className.replaceAll(CommonConstants.REG_DOT, CommonConstants.BIAS);
        this.jobType = CommonConstants.getByJobTypeName(resolveArgs.get(CommonConstants.JOB_TYPE));
    }

    public AbstractTransformer(Map<String, String> resolveArgs, Class clazz) {
        this(resolveArgs);
        String clazzName = clazz.getName();
        Reflections reflections = new Reflections(clazzName, clazzName.substring(0, clazzName.lastIndexOf(CommonConstants.DOT)));
        Set<Class<?>> handlers = reflections.getSubTypesOf(clazz);
        for (Class<?> handler : handlers) {
            if (!Modifier.isAbstract(handler.getModifiers()) && handlerMatched(handler)) {
                handlerClassName = handler.getName();
                break;
            }
        }
    }

    /**
     * match handler prefix
     */
    protected abstract boolean handlerMatched(Class<?> handler);

    protected AbstractHandler getHandler(ClassLoader loader) {
        try {
            ProxyClassLoader classLoader = new ProxyClassLoader(loader);
            Class<?> handlerClass = classLoader.loadClass(this.handlerClassName);
            AbstractHandler handler = (AbstractHandler) handlerClass.newInstance();
            handler.setArgs(getArgs());
            return handler;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 是否 debug 模式
     */
    public boolean isDebug() {
        return Boolean.parseBoolean(getArgs().get(CommonConstants.DEBUG));
    }

}
