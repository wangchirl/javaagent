package com.shadow.core.buddy.dynamic;


import com.shadow.core.AbstractTransformer;
import com.shadow.core.buddy.handler.AbstractBuddyHandler;
import com.shadow.utils.CommonConstants;
import com.shadow.utils.FileUtils;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.dynamic.DynamicType;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.named;


public class BuddyTransformer extends AbstractTransformer {

    public BuddyTransformer() {
        super();
    }

    @Override
    protected boolean handlerMatched(Class<?> handler) {
        return getJobType().name().equalsIgnoreCase(handler.getSimpleName().replace(CommonConstants.BYTEBUDDY_HANDLER_NAME_SUFFIX, ""));
    }

    public ClassFileTransformer handle(AbstractBuddyHandler handler, Instrumentation inst) {
        return new AgentBuilder.Default()
                .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
                .with(AgentBuilder.TypeStrategy.Default.REDEFINE)
                .type(named(getArgs().getCtlClass()))
                .transform((builder, typeDescription, classLoader, module) -> {
                    DynamicType.Builder<?> newBuilder = builder.visit(handler);
                    if (isDebug()) {
                        FileUtils.writeBytes(getInnerClassName() + CommonConstants.CLASS_SUFFIX, newBuilder.make().getBytes());
                    }
                    return newBuilder;
                }).installOn(inst);
    }
}
