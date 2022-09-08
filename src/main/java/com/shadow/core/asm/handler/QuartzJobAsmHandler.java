package com.shadow.core.asm.handler;

import com.shadow.utils.Constants;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.tree.*;

import java.util.Map;

public class QuartzJobAsmHandler extends AbstractAsmHandler {

    public QuartzJobAsmHandler(Map<String, String> args) {
        super(args);
        if (isDebug()) {
            System.out.println("ASM Quartz Job agent ...");
        }
    }

    public QuartzJobAsmHandler(String innerClassName, Map<String, String> args) {
        super(innerClassName, args);
        if (isDebug()) {
            System.out.println("ASM Quartz Job agent ...");
        }
    }

    @Override
    public void getMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;
        il.add(new VarInsnNode(Opcodes.ALOAD, 0));
        il.add(new FieldInsnNode(Opcodes.GETFIELD, getInnerClassName(), getArgs().get(Constants.IOC_FIELD_NAME), "Lorg/springframework/context/ApplicationContext;"));
        il.add(new VarInsnNode(Opcodes.ALOAD, 1));
        il.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, "org/springframework/context/ApplicationContext", "getBean", "(Ljava/lang/String;)Ljava/lang/Object;", true));
        il.add(new TypeInsnNode(Opcodes.CHECKCAST, "org/quartz/impl/triggers/CronTriggerImpl"));
        il.add(new VarInsnNode(Opcodes.ASTORE, 2));
        il.add(new TypeInsnNode(Opcodes.NEW, "org/quartz/JobKey"));
        il.add(new InsnNode(Opcodes.DUP));
        il.add(new VarInsnNode(Opcodes.ALOAD, 2));
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "org/quartz/impl/triggers/CronTriggerImpl", "getJobName", "()Ljava/lang/String;", false));
        il.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "org/quartz/JobKey", "<init>", "(Ljava/lang/String;)V", false));
        il.add(new VarInsnNode(Opcodes.ASTORE, 3));
        il.add(new VarInsnNode(Opcodes.ALOAD, 0));
        il.add(new FieldInsnNode(Opcodes.GETFIELD, getInnerClassName(), getArgs().get(Constants.IOC_FIELD_NAME), "Lorg/springframework/context/ApplicationContext;"));
        il.add(new LdcInsnNode(Type.getType("Lorg/springframework/scheduling/quartz/SchedulerFactoryBean;")));
        il.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, "org/springframework/context/ApplicationContext", "getBean", "(Ljava/lang/Class;)Ljava/lang/Object;", true));
        il.add(new TypeInsnNode(Opcodes.CHECKCAST, "org/springframework/scheduling/quartz/SchedulerFactoryBean"));
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "org/springframework/scheduling/quartz/SchedulerFactoryBean", "getScheduler", "()Lorg/quartz/Scheduler;", false));
        il.add(new VarInsnNode(Opcodes.ASTORE, 4));
        il.add(new TypeInsnNode(Opcodes.NEW, "org/quartz/JobDataMap"));
        il.add(new InsnNode(Opcodes.DUP));
        il.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "org/quartz/JobDataMap", "<init>", "()V", false));
        il.add(new VarInsnNode(Opcodes.ASTORE, 5));
        il.add(new VarInsnNode(Opcodes.ALOAD, 4));
        il.add(new VarInsnNode(Opcodes.ALOAD, 3));
        il.add(new VarInsnNode(Opcodes.ALOAD, 5));
        il.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, "org/quartz/Scheduler", "triggerJob", "(Lorg/quartz/JobKey;Lorg/quartz/JobDataMap;)V", true));
        il.add(new LdcInsnNode(Constants.SUCCESS));
        il.add(new InsnNode(Opcodes.ARETURN));
        methodNode.maxStack = 3;
        methodNode.maxLocals = 6;
    }
}
