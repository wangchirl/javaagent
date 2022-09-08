package com.shadow.agent;

import com.shadow.core.asm.dynamic.AsmTransformer;
import com.shadow.core.asm.handler.*;
import com.shadow.core.javassist.dynamic.JavassistTransformer;
import com.shadow.core.javassist.handler.*;
import com.shadow.utils.Constants;
import com.shadow.utils.ParamResolveUtils;
import jdk.internal.org.objectweb.asm.tree.MethodNode;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.util.Map;

public class DynamicAgent extends BaseAgent {

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
                resolveArgs.get(Constants.JOB_TYPE) != null) {
            Constants.ScheduleTypeEnum originScheduleTypeEnum = Constants.getByJobTypeName(resolveArgs.get(Constants.ORIGIN_JOB_TYPE));
            Constants.ScheduleTypeEnum scheduleTypeEnum = Constants.getByJobTypeName(resolveArgs.get(Constants.JOB_TYPE));
            if (originScheduleTypeEnum != null && scheduleTypeEnum != null) {
                // transformer
                ClassFileTransformer transformer;
                switch (Constants.getByProxyTypeName(resolveArgs.get(Constants.PROXY_TYPE))) {
                    case ASM:
                        // handle ASM default args
                        MethodNode methodNode = handleAsmDefaultArgs(resolveArgs, originScheduleTypeEnum, scheduleTypeEnum);
                        transformer = new AsmTransformer(resolveArgs, methodNode);
                        break;
                    case BUDDY:
                        // TODO
                        throw new RuntimeException("暂不支持的操作");
                    case JAVASSIST:
                    default:
                        // handle Javassist default args
                        handleJavassistDefaultArgs(resolveArgs, originScheduleTypeEnum, scheduleTypeEnum);
                        transformer = new JavassistTransformer(resolveArgs);
                        break;
                }
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

    private static MethodNode handleAsmDefaultArgs(Map<String, String> resolveArgs,
                                                   Constants.ScheduleTypeEnum originScheduleTypeEnum,
                                                   Constants.ScheduleTypeEnum scheduleTypeEnum) {
        // 1、公共默认参数处理
        handleCommonDefaultArgs(resolveArgs);
        // 2、find origin handle for method name
        AbstractAsmHandler originHandler = getAbstractAsmHandler(resolveArgs, originScheduleTypeEnum);
        // if args not found method name, set default
        if (resolveArgs.get(Constants.METHOD_NAME) == null) {
            resolveArgs.put(Constants.METHOD_NAME, originHandler.getClass().getSimpleName().toLowerCase());
        }
        // 3、find current handle for method body
        AbstractAsmHandler currentHandler = getAbstractAsmHandler(resolveArgs, scheduleTypeEnum);
        MethodNode methodNode = currentHandler.getAndSetClassMethod(Constants.ASM_API_VERSION);
        currentHandler.getMethodBody(methodNode);
        return methodNode;
    }

    private static void handleJavassistDefaultArgs(Map<String, String> resolveArgs,
                                                   Constants.ScheduleTypeEnum originScheduleTypeEnum,
                                                   Constants.ScheduleTypeEnum scheduleTypeEnum) {
        // 1、公共默认参数处理
        handleCommonDefaultArgs(resolveArgs);
        // 2、find origin handle for method name
        AbstractJavassistHandler originHandler = getAbstractJavassistHandler(resolveArgs, originScheduleTypeEnum);
        // if args not found method name, set default
        if (resolveArgs.get(Constants.METHOD_NAME) == null) {
            resolveArgs.put(Constants.METHOD_NAME, originHandler.getClass().getSimpleName().toLowerCase());
        }
        // 3、find current handle for method body
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

    private static AbstractAsmHandler getAbstractAsmHandler(Map<String, String> resolveArgs, Constants.ScheduleTypeEnum scheduleTypeEnum) {
        AbstractAsmHandler handler = null;
        switch (scheduleTypeEnum) {
            case XXL:
                handler = new XxlJobAsmHandler(resolveArgs);
                break;
            case QUARTZ:
                handler = new QuartzJobAsmHandler(resolveArgs);
                break;
            case SPRING:
                handler = new SpringJobAsmHandler(resolveArgs);
                break;
            case SIMPLE:
                handler = new SimpleJobAsmHandler(resolveArgs);
                break;
            default:
                break;
        }
        return handler;
    }
}
