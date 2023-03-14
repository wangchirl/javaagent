package com.shadow.core.buddy.loadtime;

import com.shadow.core.AbstractTransformer;
import com.shadow.core.buddy.handler.AbstractBuddyHandler;
import com.shadow.core.buddy.handler.IBuddyHandler;
import com.shadow.utils.CommonConstants;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.util.Map;

public class BuddyTransformer extends AbstractTransformer {

    public BuddyTransformer(Map<String, String> resolveArgs) {
        super(resolveArgs, IBuddyHandler.class);
    }

    @Override
    protected boolean handlerMatched(Class<?> handler) {
        return getJobType().name().equalsIgnoreCase(handler.getSimpleName().replace(CommonConstants.BYTEBUDDY_HANDLER_NAME_SUFFIX, ""));
    }

    public ClassFileTransformer handle(Instrumentation inst) {
        return ((AbstractBuddyHandler) getHandler(this.getClass().getClassLoader())).handle(inst);
    }

}
