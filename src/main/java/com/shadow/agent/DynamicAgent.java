package com.shadow.agent;

import com.shadow.core.asm.dynamic.AsmTransformer;
import com.shadow.core.asm.handler.*;
import com.shadow.core.javassist.dynamic.JavassistTransformer;
import com.shadow.core.javassist.handler.*;
import com.shadow.utils.CommonConstants;
import com.shadow.utils.ParamResolveUtils;
import jdk.internal.org.objectweb.asm.tree.MethodNode;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class DynamicAgent extends BaseAgent {

    public static void agentmain(String agentArgs, Instrumentation inst) {
        // 修改方法体
        // 1、解析参数
        Map<String, String> resolveArgs = ParamResolveUtils.resolveArgs(agentArgs);
        // 2、必要参数有时才处理
        if (resolveArgs.get(CommonConstants.ORIGIN_JOB_TYPE) != null &&
                resolveArgs.get(CommonConstants.CONTROLLER_CLASS) != null &&
                resolveArgs.get(CommonConstants.JOB_TYPE) != null) {
            CommonConstants.ScheduleTypeEnum originScheduleTypeEnum = CommonConstants.getByJobTypeName(resolveArgs.get(CommonConstants.ORIGIN_JOB_TYPE));
            CommonConstants.ScheduleTypeEnum scheduleTypeEnum = CommonConstants.getByJobTypeName(resolveArgs.get(CommonConstants.JOB_TYPE));
            if (originScheduleTypeEnum != null && scheduleTypeEnum != null) {
                // transformer
                ClassFileTransformer transformer;
                switch (CommonConstants.getByProxyTypeName(resolveArgs.get(CommonConstants.PROXY_TYPE))) {
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
                String ctlClass = resolveArgs.get(CommonConstants.CONTROLLER_CLASS);
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
                                                   CommonConstants.ScheduleTypeEnum originScheduleTypeEnum,
                                                   CommonConstants.ScheduleTypeEnum scheduleTypeEnum) {
        // 1、公共默认参数处理
        handleCommonDefaultArgs(resolveArgs);
        // SPI
        Map<String, AbstractAsmHandler> handlerMap = new HashMap<>();
        ServiceLoader<IAsmHandler> handlers = ServiceLoader.load(IAsmHandler.class);
        for (IAsmHandler handler : handlers) {
            ((AbstractAsmHandler) handler).setArgs(resolveArgs);
            ((AbstractAsmHandler) handler).initInnerClassName();
            handlerMap.put(handler.getClass().getSimpleName().replace(CommonConstants.ASM_HANDLER_NAME_SUFFIX, "").toUpperCase(), (AbstractAsmHandler) handler);
        }
        // 2、find origin handle for method name
        AbstractAsmHandler originHandler = handlerMap.get(originScheduleTypeEnum.name());
        // if args not found method name, set default
        if (resolveArgs.get(CommonConstants.METHOD_NAME) == null) {
            resolveArgs.put(CommonConstants.METHOD_NAME, originHandler.getClass().getSimpleName().toLowerCase());
        }
        // 3、find current handle for method body
        for (IAsmHandler handler : handlers) {
            // reset args for set CommonConstants.METHOD_NAME
            ((AbstractAsmHandler) handler).setArgs(resolveArgs);
        }
        AbstractAsmHandler currentHandler = handlerMap.get(scheduleTypeEnum.name());
        return currentHandler.getAndSetClassMethod(CommonConstants.ASM_API_VERSION);
    }

    private static void handleJavassistDefaultArgs(Map<String, String> resolveArgs,
                                                   CommonConstants.ScheduleTypeEnum originScheduleTypeEnum,
                                                   CommonConstants.ScheduleTypeEnum scheduleTypeEnum) {
        // 1、公共默认参数处理
        handleCommonDefaultArgs(resolveArgs);
        // SPI
        Map<String, AbstractJavassistHandler> handlerMap = new HashMap<>();
        ServiceLoader<IJavassistHandler> handlers = ServiceLoader.load(IJavassistHandler.class);
        for (IJavassistHandler handler : handlers) {
            ((AbstractJavassistHandler) handler).setArgs(resolveArgs);
            handlerMap.put(handler.getClass().getSimpleName().replace(CommonConstants.JAVASSIST_HANDLER_NAME_SUFFIX, "").toUpperCase(), (AbstractJavassistHandler) handler);
        }
        // 2、find origin handle for method name
        AbstractJavassistHandler originHandler = handlerMap.get(originScheduleTypeEnum.name());
        // if args not found method name, set default
        if (resolveArgs.get(CommonConstants.METHOD_NAME) == null) {
            resolveArgs.put(CommonConstants.METHOD_NAME, originHandler.getClass().getSimpleName().toLowerCase());
        }
        // 3、find current handle for method body
        for (IJavassistHandler handler : handlers) {
            // reset args for set CommonConstants.METHOD_NAME
            ((AbstractJavassistHandler) handler).setArgs(resolveArgs);
        }
        AbstractJavassistHandler currentHandler = handlerMap.get(scheduleTypeEnum.name());
        resolveArgs.put(CommonConstants.METHOD_BODY, currentHandler.getMethodBody().get());
    }
}
