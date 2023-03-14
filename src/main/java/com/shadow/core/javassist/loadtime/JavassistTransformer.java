package com.shadow.core.javassist.loadtime;

import com.shadow.core.AbstractTransformer;
import com.shadow.core.javassist.handler.AbstractJavassistHandler;
import com.shadow.core.javassist.handler.IJavassistHandler;
import com.shadow.utils.CommonConstants;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Map;

public class JavassistTransformer extends AbstractTransformer implements ClassFileTransformer {

    public JavassistTransformer(Map<String, String> resolveArgs) {
        super(resolveArgs, IJavassistHandler.class);
    }

    @Override
    protected boolean handlerMatched(Class<?> handler) {
        return getJobType().name().equalsIgnoreCase(handler.getSimpleName().replace(CommonConstants.JAVASSIST_HANDLER_NAME_SUFFIX, ""));
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        // load assigned class
        if (className.equals(getInnerClassName())) {
            return ((AbstractJavassistHandler) getHandler(loader)).handle(getClassName());
        }
        return null;
    }
}
