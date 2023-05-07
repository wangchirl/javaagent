package com.shadow.core;

import com.shadow.common.ProxyClassLoader;
import com.shadow.common.RequestArgsVO;
import com.shadow.utils.CommonConstants;
import com.shadow.utils.ParamResolveUtils;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
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
     * proxy controller inner class name
     * {@code java/lang/String}
     */
    private String innerClassName;

    /**
     * agent request args
     */
    private RequestArgsVO args;

    protected CommonConstants.JobTypeEnum getJobType() {
        return jobType;
    }

    public String getInnerClassName() {
        return innerClassName;
    }

    protected String getClassName() {
        return args.getCtlClass();
    }

    public RequestArgsVO getArgs() {
        return args;
    }

    public AbstractTransformer() {
        this.args = ParamResolveUtils.REQUEST_ARGS_VO_THREAD_LOCAL.get();
        this.innerClassName = this.args.getCtlClass().replaceAll(CommonConstants.REG_DOT, CommonConstants.BIAS);
        this.jobType = CommonConstants.getByJobTypeName(this.args.getJobType());
    }

    public AbstractTransformer(Class clazz) {
        this();
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
            return(AbstractHandler) handlerClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 是否 debug 模式
     */
    protected boolean isDebug() {
        return this.args.getDebug();
    }

}
