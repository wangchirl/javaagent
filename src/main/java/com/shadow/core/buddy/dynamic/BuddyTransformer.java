package com.shadow.core.buddy.dynamic;


import com.shadow.core.AbstractTransformer;
import com.shadow.core.buddy.handler.AbstractBuddyHandler;
import com.shadow.utils.CommonConstants;
import com.shadow.utils.FileUtils;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.dynamic.DynamicType;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.util.Map;

import static net.bytebuddy.matcher.ElementMatchers.named;


public class BuddyTransformer extends AbstractTransformer {

    public BuddyTransformer(Map<String, String> resolveArgs) {
        super(resolveArgs);
    }

    @Override
    protected boolean handlerMatched(Class<?> handler) {
        return getJobType().name().equalsIgnoreCase(handler.getSimpleName().replace(CommonConstants.BYTEBUDDY_HANDLER_NAME_SUFFIX, ""));
    }

    public ClassFileTransformer handle(AbstractBuddyHandler handler, Instrumentation inst) {
        getArgs().forEach((k,v) ->{
            System.out.println(k + " = " + v);
        });
        return new AgentBuilder.Default()
                .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
                .with(AgentBuilder.TypeStrategy.Default.REDEFINE)
                .type(named(getArgs().get(CommonConstants.CONTROLLER_CLASS)))
                .transform((builder, typeDescription, classLoader, module) -> {
                    DynamicType.Builder<?> newBuilder = builder.visit(handler);
                    System.out.println("hahaha");
                    if (isDebug()) {
                        FileUtils.writeBytes(getInnerClassName() + CommonConstants.CLASS_SUFFIX, newBuilder.make().getBytes());
                    }
                    return newBuilder;
                }).installOn(inst);
    }
}
