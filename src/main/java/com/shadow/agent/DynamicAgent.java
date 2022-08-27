package com.shadow.agent;

import com.shadow.core.javassist.dynamic.JavassistTransformer;
import com.shadow.core.javassist.loadtime.*;
import com.shadow.utils.Constants;
import com.shadow.utils.ParamResolveUtils;

import java.lang.instrument.Instrumentation;
import java.util.Map;

public class DynamicAgent {

    public static void agentmain(String agentArgs, Instrumentation inst) {
        // 修改方法体
        // 1、解析参数
        Map<String, String> resolveArgs = ParamResolveUtils.resolveArgs(agentArgs);

        Class<?> agentClass = DynamicAgent.class;
        System.out.println("Agent-Class: " + agentClass.getName());
        System.out.println("agentArgs: " + agentArgs);
        System.out.println("Instrumentation: " + inst.getClass().getName());
        System.out.println("ClassLoader: " + agentClass.getClassLoader());
        System.out.println("Thread Id: " + Thread.currentThread().getName() + "@" +
                Thread.currentThread().getId() + "(" + Thread.currentThread().isDaemon() + ")"
        );
        System.out.println("Can-Redefine-Classes: " + inst.isRedefineClassesSupported());
        System.out.println("Can-Retransform-Classes: " + inst.isRetransformClassesSupported());
        System.out.println("Can-Set-Native-Method-Prefix: " + inst.isNativeMethodPrefixSupported());
        System.out.println("========= ========= =========");

        // 2、必要参数有时才处理
        if (resolveArgs.get(Constants.ORIGIN_JOB_TYPE) != null &&
                resolveArgs.get(Constants.CONTROLLER_CLASS) != null &&
                resolveArgs.get(Constants.JOB_TYPE) != null &&
                !resolveArgs.get(Constants.ORIGIN_JOB_TYPE).equals(resolveArgs.get(Constants.JOB_TYPE))) {
            Constants.ScheduleTypeEnum originScheduleTypeEnum = Constants.getByName(resolveArgs.get(Constants.ORIGIN_JOB_TYPE));
            Constants.ScheduleTypeEnum scheduleTypeEnum = Constants.getByName(resolveArgs.get(Constants.JOB_TYPE));
            if (originScheduleTypeEnum != null && scheduleTypeEnum != null) {
                // set default args
                handleDefaultArgs(resolveArgs, originScheduleTypeEnum, scheduleTypeEnum);
                // transformer
                JavassistTransformer transformer = new JavassistTransformer(resolveArgs);
                String ctlClass = resolveArgs.get(Constants.CONTROLLER_CLASS);
                try {
                    // add retransform transformer
                    inst.addTransformer(transformer, true);
                    Class<?> targetClass = Class.forName(ctlClass);
                    inst.retransformClasses(targetClass);
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    inst.removeTransformer(transformer);
                }
            }
        }
    }

    private static void handleDefaultArgs(Map<String, String> resolveArgs,
                                          Constants.ScheduleTypeEnum originScheduleTypeEnum,
                                          Constants.ScheduleTypeEnum scheduleTypeEnum) {
        // 1、DEBUG
        resolveArgs.putIfAbsent(Constants.DEBUG, "false");
        // 2、IOC_FIELD_NAME
        resolveArgs.computeIfAbsent(Constants.IOC_FIELD_NAME, k -> Constants.DEFAULT_IOC_FIELD_VALUE);
        // 3、find origin handle for method name
        AbstractJavassistHandler originHandler = getAbstractJavassistHandler(resolveArgs, originScheduleTypeEnum);
        // if args not found method name, set default
        if (resolveArgs.get(Constants.METHOD_NAME) == null) {
            resolveArgs.put(Constants.METHOD_NAME, originHandler.getClass().getSimpleName().toLowerCase());
        }
        // 4、find current handle for method body
        AbstractJavassistHandler currentHandler = getAbstractJavassistHandler(resolveArgs, scheduleTypeEnum);
        resolveArgs.put(Constants.METHOD_BODY, currentHandler.getMethodBody().get());
    }

    private static AbstractJavassistHandler getAbstractJavassistHandler(Map<String, String> resolveArgs, Constants.ScheduleTypeEnum scheduleTypeEnum) {
        AbstractJavassistHandler handler = null;
        switch (scheduleTypeEnum) {
            case XXL:
                handler = new XxlJobJavassistHandler(resolveArgs);
                break;
            case QUARTZ:
                handler = new QuartzJobJavassistHandler(resolveArgs);
                break;
            case SPRING:
                handler = new SpringJobJavassistHandler(resolveArgs);
                break;
            case SIMPLE:
                handler = new SimpleJobJavassistHandler(resolveArgs);
                break;
            default:
                break;
        }
        return handler;
    }
}
