package com.shadow.core.javassist.loadtime;

import com.shadow.core.AbstractTransformer;
import com.shadow.core.javassist.handler.*;
import com.shadow.utils.CommonConstants;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Map;
import java.util.ServiceLoader;

public class JavassistTransformer extends AbstractTransformer implements ClassFileTransformer {

    /**
     * current handle
     */
    public AbstractJavassistHandler handler;

    public JavassistTransformer(Map<String, String> resolveArgs, CommonConstants.ScheduleTypeEnum scheduleTypeEnum) {
        super(resolveArgs);
        // SPI
        ServiceLoader<IJavassistHandler> handlers = ServiceLoader.load(IJavassistHandler.class);
        for (IJavassistHandler handler : handlers) {
            ((AbstractJavassistHandler) handler).setArgs(resolveArgs);
            if (scheduleTypeEnum.name().equalsIgnoreCase(handler.getClass().getSimpleName().replace(CommonConstants.JAVASSIST_HANDLER_NAME_SUFFIX, ""))) {
                this.handler = (AbstractJavassistHandler) handler;
                break;
            }
        }
    }

    /**
     * @param loader              加载当前 class 的类加载器
     * @param className           当前被加载类的 inner class name
     * @param classBeingRedefined 被 transform 过的 Class，一般情况下为 null
     * @param protectionDomain    被定义或重新定义的类的保护域
     * @param classfileBuffer     当前类的字节码信息
     */
    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        // load assigned class
        if (className.equals(getInnerClassName())) {
            return this.handler.handle(getClassName());
        }
        return null;
    }
}
