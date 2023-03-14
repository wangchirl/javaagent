package com.shadow.core.asm.loadtime;

import com.shadow.core.AbstractTransformer;
import com.shadow.core.asm.handler.*;
import com.shadow.utils.CommonConstants;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Map;

public class AsmTransformer extends AbstractTransformer implements ClassFileTransformer {

    public AsmTransformer(Map<String, String> resolveArgs) {
        super(resolveArgs, IAsmHandler.class);
    }

    @Override
    protected boolean handlerMatched(Class<?> handler) {
        return getJobType().name().equalsIgnoreCase(handler.getSimpleName().replace(CommonConstants.ASM_HANDLER_NAME_SUFFIX, ""));
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        // load assigned class
        if (className.equals(getInnerClassName())) {
            return ((AbstractAsmHandler) getHandler(loader)).handle(classfileBuffer);
        }
        return null;
    }
}
