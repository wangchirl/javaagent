package com.shadow.core.asm.handler;

import com.shadow.utils.Constants;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

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

    /**
     * 0: new           #2                  // class org/quartz/JobDataMap
     * 3: dup
     * 4: invokespecial #3                  // Method org/quartz/JobDataMap."<init>":()V
     * 7: astore        4
     * 9: aload         4
     * 11: ldc           #4                  // String params
     * 13: aload_2
     * 14: invokevirtual #5                  // Method org/quartz/JobDataMap.put:(Ljava/lang/String;Ljava/lang/String;)V
     * 17: aload         4
     * 19: ldc           #6                  // String body
     * 21: aload_3
     * 22: invokevirtual #7                  // Method org/quartz/JobDataMap.put:(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;
     * 25: pop
     * 26: aload_0
     * 27: getfield      #8                  // Field applicationContext:Lorg/springframework/context/ApplicationContext;
     * 30: aload_1
     * 31: invokeinterface #9,  2            // InterfaceMethod org/springframework/context/ApplicationContext.getBean:(Ljava/lang/String;)Ljava/lang/Object;
     * 36: checkcast     #10                 // class org/quartz/impl/triggers/CronTriggerImpl
     * 39: astore        5
     * 41: new           #11                 // class org/quartz/JobKey
     * 44: dup
     * 45: aload         5
     * 47: invokevirtual #12                 // Method org/quartz/impl/triggers/CronTriggerImpl.getJobName:()Ljava/lang/String;
     * 50: invokespecial #13                 // Method org/quartz/JobKey."<init>":(Ljava/lang/String;)V
     * 53: astore        6
     * 55: aload_0
     * 56: getfield      #8                  // Field applicationContext:Lorg/springframework/context/ApplicationContext;
     * 59: ldc           #14                 // class org/springframework/scheduling/quartz/SchedulerFactoryBean
     * 61: invokeinterface #15,  2           // InterfaceMethod org/springframework/context/ApplicationContext.getBean:(Ljava/lang/Class;)Ljava/lang/Object;
     * 66: checkcast     #14                 // class org/springframework/scheduling/quartz/SchedulerFactoryBean
     * 69: invokevirtual #16                 // Method org/springframework/scheduling/quartz/SchedulerFactoryBean.getScheduler:()Lorg/quartz/Scheduler;
     * 72: astore        7
     * 74: aload         7
     * 76: aload         6
     * 78: aload         4
     * 80: invokeinterface #17,  3           // InterfaceMethod org/quartz/Scheduler.triggerJob:(Lorg/quartz/JobKey;Lorg/quartz/JobDataMap;)V
     * 85: ldc           #18                 // String Execute Quartz Job Successful !
     * 87: areturn
     */
    @Override
    public void getThreadLocalMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;
        il.add(new TypeInsnNode(NEW, "org/quartz/JobDataMap"));
        il.add(new InsnNode(DUP));
        il.add(new MethodInsnNode(INVOKESPECIAL, "org/quartz/JobDataMap", "<init>", "()V", false));
        il.add(new VarInsnNode(ASTORE, 4));
        il.add(new VarInsnNode(ALOAD, 4));
        il.add(new LdcInsnNode("params"));
        il.add(new VarInsnNode(ALOAD, 2));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "org/quartz/JobDataMap", "put", "(Ljava/lang/String;Ljava/lang/String;)V", false));
        il.add(new VarInsnNode(ALOAD, 4));
        il.add(new LdcInsnNode("body"));
        il.add(new VarInsnNode(ALOAD, 3));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "org/quartz/JobDataMap", "put", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", false));
        il.add(new InsnNode(POP));
        il.add(new VarInsnNode(ALOAD, 0));
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().get(Constants.IOC_FIELD_NAME), "Lorg/springframework/context/ApplicationContext;"));
        il.add(new VarInsnNode(ALOAD, 1));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/springframework/context/ApplicationContext", "getBean", "(Ljava/lang/String;)Ljava/lang/Object;", true));
        il.add(new TypeInsnNode(CHECKCAST, "org/quartz/impl/triggers/CronTriggerImpl"));
        il.add(new VarInsnNode(ASTORE, 5));
        il.add(new TypeInsnNode(NEW, "org/quartz/JobKey"));
        il.add(new InsnNode(DUP));
        il.add(new VarInsnNode(ALOAD, 5));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "org/quartz/impl/triggers/CronTriggerImpl", "getJobName", "()Ljava/lang/String;", false));
        il.add(new MethodInsnNode(INVOKESPECIAL, "org/quartz/JobKey", "<init>", "(Ljava/lang/String;)V", false));
        il.add(new VarInsnNode(ASTORE, 6));
        il.add(new VarInsnNode(ALOAD, 0));
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().get(Constants.IOC_FIELD_NAME), "Lorg/springframework/context/ApplicationContext;"));
        il.add(new LdcInsnNode(Type.getType("Lorg/springframework/scheduling/quartz/SchedulerFactoryBean;")));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/springframework/context/ApplicationContext", "getBean", "(Ljava/lang/Class;)Ljava/lang/Object;", true));
        il.add(new TypeInsnNode(CHECKCAST, "org/springframework/scheduling/quartz/SchedulerFactoryBean"));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "org/springframework/scheduling/quartz/SchedulerFactoryBean", "getScheduler", "()Lorg/quartz/Scheduler;", false));
        il.add(new VarInsnNode(ASTORE, 7));
        il.add(new VarInsnNode(ALOAD, 7));
        il.add(new VarInsnNode(ALOAD, 6));
        il.add(new VarInsnNode(ALOAD, 4));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/quartz/Scheduler", "triggerJob", "(Lorg/quartz/JobKey;Lorg/quartz/JobDataMap;)V", true));
        il.add(new LdcInsnNode(Constants.QUARTZ_SUCCESS));
        il.add(new InsnNode(ARETURN));
        methodNode.maxStack = 7;
        methodNode.maxLocals = 8;
    }

    /**
     * 0: aload_0
     * 1: getfield      #8                  // Field applicationContext:Lorg/springframework/context/ApplicationContext;
     * 4: aload_1
     * 5: invokeinterface #9,  2            // InterfaceMethod org/springframework/context/ApplicationContext.getBean:(Ljava/lang/String;)Ljava/lang/Object;
     * 10: checkcast     #10                 // class org/quartz/impl/triggers/CronTriggerImpl
     * 13: astore        4
     * 15: new           #11                 // class org/quartz/JobKey
     * 18: dup
     * 19: aload         4
     * 21: invokevirtual #12                 // Method org/quartz/impl/triggers/CronTriggerImpl.getJobName:()Ljava/lang/String;
     * 24: invokespecial #13                 // Method org/quartz/JobKey."<init>":(Ljava/lang/String;)V
     * 27: astore        5
     * 29: aload_0
     * 30: getfield      #8                  // Field applicationContext:Lorg/springframework/context/ApplicationContext;
     * 33: ldc           #14                 // class org/springframework/scheduling/quartz/SchedulerFactoryBean
     * 35: invokeinterface #15,  2           // InterfaceMethod org/springframework/context/ApplicationContext.getBean:(Ljava/lang/Class;)Ljava/lang/Object;
     * 40: checkcast     #14                 // class org/springframework/scheduling/quartz/SchedulerFactoryBean
     * 43: invokevirtual #16                 // Method org/springframework/scheduling/quartz/SchedulerFactoryBean.getScheduler:()Lorg/quartz/Scheduler;
     * 46: astore        6
     * 48: aload         6
     * 50: aload         5
     * 52: invokeinterface #19,  2           // InterfaceMethod org/quartz/Scheduler.triggerJob:(Lorg/quartz/JobKey;)V
     * 57: ldc           #18                 // String Execute Quartz Job Successful !
     * 59: areturn
     */
    @Override
    public void getNormalMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;
        il.add(new VarInsnNode(ALOAD, 0));
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().get(Constants.IOC_FIELD_NAME), "Lorg/springframework/context/ApplicationContext;"));
        il.add(new VarInsnNode(ALOAD, 1));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/springframework/context/ApplicationContext", "getBean", "(Ljava/lang/String;)Ljava/lang/Object;", true));
        il.add(new TypeInsnNode(CHECKCAST, "org/quartz/impl/triggers/CronTriggerImpl"));
        il.add(new VarInsnNode(ASTORE, 4));
        il.add(new TypeInsnNode(NEW, "org/quartz/JobKey"));
        il.add(new InsnNode(DUP));
        il.add(new VarInsnNode(ALOAD, 4));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "org/quartz/impl/triggers/CronTriggerImpl", "getJobName", "()Ljava/lang/String;", false));
        il.add(new MethodInsnNode(INVOKESPECIAL, "org/quartz/JobKey", "<init>", "(Ljava/lang/String;)V", false));
        il.add(new VarInsnNode(ASTORE, 5));
        il.add(new VarInsnNode(ALOAD, 0));
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().get(Constants.IOC_FIELD_NAME), "Lorg/springframework/context/ApplicationContext;"));
        il.add(new LdcInsnNode(Type.getType("Lorg/springframework/scheduling/quartz/SchedulerFactoryBean;")));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/springframework/context/ApplicationContext", "getBean", "(Ljava/lang/Class;)Ljava/lang/Object;", true));
        il.add(new TypeInsnNode(CHECKCAST, "org/springframework/scheduling/quartz/SchedulerFactoryBean"));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "org/springframework/scheduling/quartz/SchedulerFactoryBean", "getScheduler", "()Lorg/quartz/Scheduler;", false));
        il.add(new VarInsnNode(ASTORE, 6));
        il.add(new VarInsnNode(ALOAD, 6));
        il.add(new VarInsnNode(ALOAD, 5));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/quartz/Scheduler", "triggerJob", "(Lorg/quartz/JobKey;)V", true));
        il.add(new LdcInsnNode(Constants.QUARTZ_SUCCESS));
        il.add(new InsnNode(ARETURN));
        methodNode.maxStack = 6;
        methodNode.maxLocals = 7;
    }
}
