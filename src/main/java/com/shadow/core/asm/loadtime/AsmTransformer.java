package com.shadow.core.asm.loadtime;

import com.shadow.core.AbstractTransformer;
import com.shadow.core.asm.handler.*;
import com.shadow.core.common.ProxyClassLoader;
import com.shadow.utils.CommonConstants;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Map;
import java.util.ServiceLoader;

public class AsmTransformer extends AbstractTransformer implements ClassFileTransformer {

    /**
     * current handle
     */
    private String handlerClassName;

    public AsmTransformer(Map<String, String> resolveArgs, CommonConstants.ScheduleTypeEnum scheduleTypeEnum) {
        super(resolveArgs);
        // SPI
        ServiceLoader<IAsmHandler> handlers = ServiceLoader.load(IAsmHandler.class);
        for (IAsmHandler handler : handlers) {
            if (scheduleTypeEnum.name().equalsIgnoreCase(handler.getClass().getSimpleName().replace(CommonConstants.ASM_HANDLER_NAME_SUFFIX, ""))) {
                this.handlerClassName = handler.getClass().getName();
                break;
            }
        }
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className.equals(getInnerClassName())) {
            try {
                ProxyClassLoader classLoader = new ProxyClassLoader(loader);
                Class<?> handlerClass = classLoader.loadClass(this.handlerClassName);
                AbstractAsmHandler handler = (AbstractAsmHandler) handlerClass.newInstance();
                handler.setArgs(getArgs());
                return handler.handle(classfileBuffer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
