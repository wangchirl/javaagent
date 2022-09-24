package com.shadow.core.buddy.dynamic;


import com.shadow.core.AbstractTransformer;
import com.shadow.core.buddy.handler.AbstractBuddyHandler;
import com.shadow.utils.CommonConstants;
import com.shadow.utils.FileUtils;
import jdk.internal.org.objectweb.asm.ClassReader;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.agent.builder.ResettableClassFileTransformer;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.ClassWriter;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.util.Arrays;
import java.util.Map;

import static net.bytebuddy.matcher.ElementMatchers.named;


public class BuddyTransformer extends AbstractTransformer {

    public BuddyTransformer(Map<String, String> resolveArgs) {
        super(resolveArgs);
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
