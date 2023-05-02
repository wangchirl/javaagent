package com.shadow.agent;

import com.shadow.common.RequestArgsVO;
import com.shadow.core.asm.dynamic.AsmTransformer;
import com.shadow.core.asm.handler.*;
import com.shadow.core.buddy.dynamic.BuddyTransformer;
import com.shadow.core.buddy.handler.AbstractBuddyHandler;
import com.shadow.core.buddy.handler.IBuddyHandler;
import com.shadow.core.javassist.dynamic.JavassistTransformer;
import com.shadow.core.javassist.handler.*;
import com.shadow.utils.CommonConstants;
import com.shadow.utils.ParamResolveUtils;
import jdk.internal.org.objectweb.asm.tree.MethodNode;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.agent.builder.ResettableClassFileTransformer;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.util.*;

public class DynamicAgent extends BaseAgent {

    public static void agentmain(String agentArgs, Instrumentation inst) throws Exception {
        // modify method body
        // 1、resolve args
        RequestArgsVO resolveArgs = ParamResolveUtils.resolveArgs(agentArgs);
        // 2、check necessary args
        if (resolveArgs.getOriginJobType() != null &&
                resolveArgs.getCtlClass() != null &&
                resolveArgs.getJobType() != null) {
            CommonConstants.JobTypeEnum originJobTypeEnum = CommonConstants.getByJobTypeName(resolveArgs.getOriginJobType());
            CommonConstants.JobTypeEnum jobTypeEnum = CommonConstants.getByJobTypeName(resolveArgs.getJobType());
            if (originJobTypeEnum != null && jobTypeEnum != null) {
                // transformer
                ClassFileTransformer transformer = null;
                CommonConstants.ProxyTypeEnum proxyTypeEnum = CommonConstants.getByProxyTypeName(resolveArgs.getProxyType());
                switch (proxyTypeEnum) {
                    case ASM:
                        // handle ASM default args
                        MethodNode[] methodNodes = handleAsmDefaultArgs(resolveArgs, originJobTypeEnum, jobTypeEnum);
                        transformer = new AsmTransformer(methodNodes[0], methodNodes[1]);
                        break;
                    case BUDDY:
                        // FIXME 暂不支持
                        throw new RuntimeException("暂不支持");
                        // AbstractBuddyHandler handler = handleBuddyDefaultArgs(resolveArgs, originJobTypeEnum, jobTypeEnum);
                        // transformer = new BuddyTransformer().handle(handler, inst);
                        // break;
                    case JAVASSIST:
                    default:
                        // handle Javassist default args
                        handleJavassistDefaultArgs(resolveArgs, originJobTypeEnum, jobTypeEnum);
                        transformer = new JavassistTransformer();
                        break;
                }
                String ctlClass = resolveArgs.getCtlClass();
                try {
                    // add retransform transformer , buddy has been added in handle method
                    inst.addTransformer(transformer, true);
                    Map<String, Class> map = new HashMap<>();
                    for (Class aClass : inst.getAllLoadedClasses()) {
                        map.put(aClass.getName(), aClass);
                    }
                    if (map.containsKey(ctlClass)) {
                        Class<?> targetClass = map.get(ctlClass);
                        inst.retransformClasses(targetClass);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    if (proxyTypeEnum == CommonConstants.ProxyTypeEnum.BUDDY) {
                        ((ResettableClassFileTransformer) transformer).reset(inst, AgentBuilder.RedefinitionStrategy.RETRANSFORMATION);
                    }
                    inst.removeTransformer(transformer);
                }
            }
        }
    }

    private static MethodNode[] handleAsmDefaultArgs(RequestArgsVO resolveArgs,
                                                     CommonConstants.JobTypeEnum originJobTypeEnum,
                                                     CommonConstants.JobTypeEnum jobTypeEnum) {
        // 1、common args
        handleCommonDefaultArgs(resolveArgs);
        // SPI
        Map<String, AbstractAsmHandler> handlerMap = new HashMap<>();
        ServiceLoader<IAsmHandler> handlers = ServiceLoader.load(IAsmHandler.class);
        for (IAsmHandler handler : handlers) {
            ((AbstractAsmHandler) handler).initInnerClassName();
            handlerMap.put(handler.getClass().getSimpleName().replace(CommonConstants.ASM_HANDLER_NAME_SUFFIX, "").toUpperCase(), (AbstractAsmHandler) handler);
        }
        // 2、find origin handle for method name
        AbstractAsmHandler originHandler = handlerMap.get(originJobTypeEnum.name());
        // if args not found method name, set default
        if (resolveArgs.getMethodName() == null) {
            resolveArgs.setMethodName(originHandler.getClass().getSimpleName().toLowerCase());
        }
        // 3、find current handle for method body
        for (IAsmHandler handler : handlers) {
            // reset args for set CommonConstants.METHOD_NAME
            ((AbstractAsmHandler) handler).setArgs(resolveArgs);
        }
        AbstractAsmHandler currentHandler = handlerMap.get(jobTypeEnum.name());
        MethodNode runMethodNode = currentHandler.getAndSetClassMethod(CommonConstants.ASM_API_VERSION);
        MethodNode crudMethodNode = currentHandler.getAndSetCrudClassMethod(CommonConstants.ASM_API_VERSION);
        return new MethodNode[]{runMethodNode, crudMethodNode};
    }

    private static AbstractBuddyHandler handleBuddyDefaultArgs(RequestArgsVO resolveArgs,
                                                               CommonConstants.JobTypeEnum originJobTypeEnum,
                                                               CommonConstants.JobTypeEnum jobTypeEnum) {
        // 1、common args
        handleCommonDefaultArgs(resolveArgs);
        // SPI
        Map<String, AbstractBuddyHandler> handlerMap = new HashMap<>();
        ServiceLoader<IBuddyHandler> handlers = ServiceLoader.load(IBuddyHandler.class);
        for (IBuddyHandler handler : handlers) {
            ((AbstractBuddyHandler) handler).initInnerClassName();
            handlerMap.put(handler.getClass().getSimpleName().replace(CommonConstants.BYTEBUDDY_HANDLER_NAME_SUFFIX, "").toUpperCase(), (AbstractBuddyHandler) handler);
        }
        // 2、find origin handle for method name
        AbstractBuddyHandler originHandler = handlerMap.get(originJobTypeEnum.name());
        // if args not found method name, set default
        if (resolveArgs.getMethodName() == null) {
            resolveArgs.setMethodName(originHandler.getClass().getSimpleName().toLowerCase());
        }
        // 3、find current handle for method body
        for (IBuddyHandler handler : handlers) {
            // reset args for set CommonConstants.METHOD_NAME
            ((AbstractBuddyHandler) handler).setArgs(resolveArgs);
        }
        AbstractBuddyHandler handler = handlerMap.get(jobTypeEnum.name());
        handler.initOriginHandler(originHandler);
        return handler;
    }

    private static void handleJavassistDefaultArgs(RequestArgsVO resolveArgs,
                                                   CommonConstants.JobTypeEnum originJobTypeEnum,
                                                   CommonConstants.JobTypeEnum jobTypeEnum) {
        // 1、common args
        handleCommonDefaultArgs(resolveArgs);
        // SPI
        Map<String, AbstractJavassistHandler> handlerMap = new HashMap<>();
        ServiceLoader<IJavassistHandler> handlers = ServiceLoader.load(IJavassistHandler.class);
        for (IJavassistHandler handler : handlers) {
            handlerMap.put(handler.getClass().getSimpleName().replace(CommonConstants.JAVASSIST_HANDLER_NAME_SUFFIX, "").toUpperCase(), (AbstractJavassistHandler) handler);
        }
        // 2、find origin handle for method name
        AbstractJavassistHandler originHandler = handlerMap.get(originJobTypeEnum.name());
        // if args not found method name, set default
        if (resolveArgs.getMethodName() == null) {
            resolveArgs.setMethodName(originHandler.getClass().getSimpleName().toLowerCase());
        }
        // 3、find current handle for method body
        for (IJavassistHandler handler : handlers) {
            // reset args for set CommonConstants.METHOD_NAME
            ((AbstractJavassistHandler) handler).setArgs(resolveArgs);
        }
        AbstractJavassistHandler currentHandler = handlerMap.get(jobTypeEnum.name());
        resolveArgs.setMethodBody(currentHandler.getMethodBody().get()); // run 方法
        resolveArgs.setCrudMethodBody(currentHandler.getCrudMethodBody().get()); // crud 方法
        currentHandler.setArgs(resolveArgs);
    }
}
