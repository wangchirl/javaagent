package com.shadow.core.asm.handler;

import com.shadow.utils.*;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.tree.*;


public class QuartzJobAsmHandler extends AbstractAsmHandler {

    @Override
    public void setThreadLocalMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;
        // "org/quartz/JobDataMap"
        il.add(new TypeInsnNode(NEW, QuartzConstants.QUARTZ_JOB_DATA_MAP_TYPE.getInternalName()));
        il.add(new InsnNode(DUP));
        // "org/quartz/JobDataMap"、"<init>"、"()V"
        il.add(new MethodInsnNode(INVOKESPECIAL, QuartzConstants.QUARTZ_JOB_DATA_MAP_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_INIT, BaseConstants.V_, false));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_4));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_4));
        // "params"
        il.add(new LdcInsnNode(CommonConstants.CONST_PARAMS));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_2));
        // "org/quartz/JobDataMap"、"put"、"(Ljava/lang/String;Ljava/lang/String;)V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, QuartzConstants.QUARTZ_JOB_DATA_MAP_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_PUT, BaseConstants.V_SS, false));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_4));
        // "body"
        il.add(new LdcInsnNode(CommonConstants.CONST_BODY));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_3));
        // "org/quartz/JobDataMap"、"put"、"(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, QuartzConstants.QUARTZ_JOB_DATA_MAP_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_PUT, BaseConstants.O_SO, false));
        il.add(new InsnNode(POP));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_0));
        // "Lorg/springframework/context/ApplicationContext;"
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().getIocFieldName(), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor()));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_1));
        // "org/springframework/context/ApplicationContext"、"getBean"、"(Ljava/lang/String;)Ljava/lang/Object;"
        il.add(new MethodInsnNode(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_S, true));
        // "org/quartz/impl/triggers/CronTriggerImpl"
        il.add(new TypeInsnNode(CHECKCAST, QuartzConstants.QUARTZ_CRON_TRIGGER_IMPL_TYPE.getInternalName()));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_5));
        // "org/quartz/JobKey"
        il.add(new TypeInsnNode(NEW, QuartzConstants.QUARTZ_JOB_KEY_TYPE.getInternalName()));
        il.add(new InsnNode(DUP));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_5));
        // "org/quartz/impl/triggers/CronTriggerImpl"、"getJobName"、"()Ljava/lang/String;"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, QuartzConstants.QUARTZ_CRON_TRIGGER_IMPL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETJOBNAME, BaseConstants.S_, false));
        // "org/quartz/JobKey"、"<init>"、"(Ljava/lang/String;)V"
        il.add(new MethodInsnNode(INVOKESPECIAL, QuartzConstants.QUARTZ_JOB_KEY_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_INIT, BaseConstants.V_S, false));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_6));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_0));
        // "Lorg/springframework/context/ApplicationContext;"
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().getIocFieldName(), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor()));
        // "Lorg/springframework/scheduling/quartz/SchedulerFactoryBean;"
        il.add(new LdcInsnNode(Type.getType(SpringConstants.SPRING_QUARTZ_SCHEDULER_FACTORY_BEAN_TYPE.getDescriptor())));
        // "org/springframework/context/ApplicationContext"、"getBean"、"(Ljava/lang/Class;)Ljava/lang/Object;"
        il.add(new MethodInsnNode(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_C_, true));
        // "org/springframework/scheduling/quartz/SchedulerFactoryBean"
        il.add(new TypeInsnNode(CHECKCAST, SpringConstants.SPRING_QUARTZ_SCHEDULER_FACTORY_BEAN_TYPE.getInternalName()));
        // "org/springframework/scheduling/quartz/SchedulerFactoryBean"、"getScheduler"、"()Lorg/quartz/Scheduler;"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, SpringConstants.SPRING_QUARTZ_SCHEDULER_FACTORY_BEAN_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETSCHEDULER, QuartzConstants.SCHEDULER_, false));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_7));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_7));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_6));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_4));
        // "org/quartz/Scheduler"、"triggerJob"、"(Lorg/quartz/JobKey;Lorg/quartz/JobDataMap;)V"
        il.add(new MethodInsnNode(INVOKEINTERFACE, QuartzConstants.QUARTZ_SCHEDULER_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_TRIGGERJOB, QuartzConstants.V_JOB_KEY_JOB_DATA_MAP, true));
        il.add(new LdcInsnNode(QuartzConstants.QUARTZ_SUCCESS));
        il.add(new InsnNode(ARETURN));
        methodNode.maxStack = IndexConstants.INDEX_7;
        methodNode.maxLocals = IndexConstants.INDEX_8;
    }

    @Override
    public void setNormalMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_0));
        // "Lorg/springframework/context/ApplicationContext;"
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().getIocFieldName(), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor()));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_1));
        // "org/springframework/context/ApplicationContext"、"getBean"、"(Ljava/lang/String;)Ljava/lang/Object;"
        il.add(new MethodInsnNode(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_S, true));
        // "org/quartz/impl/triggers/CronTriggerImpl"
        il.add(new TypeInsnNode(CHECKCAST, QuartzConstants.QUARTZ_CRON_TRIGGER_IMPL_TYPE.getInternalName()));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_4));
        // "org/quartz/JobKey"
        il.add(new TypeInsnNode(NEW, QuartzConstants.QUARTZ_JOB_KEY_TYPE.getInternalName()));
        il.add(new InsnNode(DUP));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_4));
        // "org/quartz/impl/triggers/CronTriggerImpl"、"getJobName"、"()Ljava/lang/String;"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, QuartzConstants.QUARTZ_CRON_TRIGGER_IMPL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETJOBNAME, BaseConstants.S_, false));
        // "org/quartz/JobKey"、"<init>"、"(Ljava/lang/String;)V"
        il.add(new MethodInsnNode(INVOKESPECIAL, QuartzConstants.QUARTZ_JOB_KEY_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_INIT, BaseConstants.V_S, false));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_5));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_0));
        // "Lorg/springframework/context/ApplicationContext;"
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().getIocFieldName(), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor()));
        // "Lorg/springframework/scheduling/quartz/SchedulerFactoryBean;"
        il.add(new LdcInsnNode(Type.getType(SpringConstants.SPRING_QUARTZ_SCHEDULER_FACTORY_BEAN_TYPE.getDescriptor())));
        // "org/springframework/context/ApplicationContext"、"getBean"、"(Ljava/lang/Class;)Ljava/lang/Object;"
        il.add(new MethodInsnNode(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_C_, true));
        // "org/springframework/scheduling/quartz/SchedulerFactoryBean"
        il.add(new TypeInsnNode(CHECKCAST, SpringConstants.SPRING_QUARTZ_SCHEDULER_FACTORY_BEAN_TYPE.getInternalName()));
        // "org/springframework/scheduling/quartz/SchedulerFactoryBean"、"getScheduler"、"()Lorg/quartz/Scheduler;"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, SpringConstants.SPRING_QUARTZ_SCHEDULER_FACTORY_BEAN_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETSCHEDULER, QuartzConstants.SCHEDULER_, false));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_6));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_6));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_5));
        // "org/quartz/Scheduler"、"triggerJob"、"(Lorg/quartz/JobKey;)V"
        il.add(new MethodInsnNode(INVOKEINTERFACE, QuartzConstants.QUARTZ_SCHEDULER_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_TRIGGERJOB, QuartzConstants.V_JOB_KEY, true));
        il.add(new LdcInsnNode(QuartzConstants.QUARTZ_SUCCESS));
        il.add(new InsnNode(ARETURN));
        methodNode.maxStack = IndexConstants.INDEX_6;
        methodNode.maxLocals = IndexConstants.INDEX_7;
    }

    @Override
    protected void setCrudMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;
        il.add(new InsnNode(ICONST_0));
        il.add(new VarInsnNode(ISTORE, 4));
        il.add(new TypeInsnNode(NEW, "java/util/HashMap"));
        il.add(new InsnNode(DUP));
        il.add(new MethodInsnNode(INVOKESPECIAL, "java/util/HashMap", "<init>", "()V", false));
        il.add(new VarInsnNode(ASTORE, 5));
        il.add(new VarInsnNode(ALOAD, 2));
        il.add(new LdcInsnNode("@"));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/String", "split", "(Ljava/lang/String;)[Ljava/lang/String;", false));
        il.add(new VarInsnNode(ASTORE, 6));
        il.add(new VarInsnNode(ALOAD, 6));
        il.add(new InsnNode(ARRAYLENGTH));
        il.add(new InsnNode(ICONST_2));
        LabelNode labelNode0 = new LabelNode();
        il.add(new JumpInsnNode(IF_ICMPNE, labelNode0));
        il.add(new VarInsnNode(ALOAD, 6));
        il.add(new InsnNode(ICONST_0));
        il.add(new InsnNode(AALOAD));
        il.add(new VarInsnNode(ASTORE, 7));
        il.add(new VarInsnNode(ALOAD, 6));
        il.add(new InsnNode(ICONST_1));
        il.add(new InsnNode(AALOAD));
        il.add(new VarInsnNode(ASTORE, 8));
        il.add(new VarInsnNode(ALOAD, 0));
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().getIocFieldName(), "Lorg/springframework/context/ApplicationContext;"));
        il.add(new VarInsnNode(ALOAD, 7));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/springframework/context/ApplicationContext", "getBean", "(Ljava/lang/String;)Ljava/lang/Object;", true));
        il.add(new TypeInsnNode(CHECKCAST, "org/quartz/impl/triggers/CronTriggerImpl"));
        il.add(new VarInsnNode(ASTORE, 9));
        il.add(new VarInsnNode(ALOAD, 0));
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().getIocFieldName(), "Lorg/springframework/context/ApplicationContext;"));
        il.add(new LdcInsnNode(Type.getType("Lorg/springframework/scheduling/quartz/SchedulerFactoryBean;")));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/springframework/context/ApplicationContext", "getBean", "(Ljava/lang/Class;)Ljava/lang/Object;", true));
        il.add(new TypeInsnNode(CHECKCAST, "org/springframework/scheduling/quartz/SchedulerFactoryBean"));
        il.add(new VarInsnNode(ASTORE, 10));
        il.add(new VarInsnNode(ALOAD, 10));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "org/springframework/scheduling/quartz/SchedulerFactoryBean", "getScheduler", "()Lorg/quartz/Scheduler;", false));
        il.add(new VarInsnNode(ASTORE, 11));
        il.add(new VarInsnNode(ALOAD, 8));
        il.add(new VarInsnNode(ALOAD, 9));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "org/quartz/impl/triggers/CronTriggerImpl", "getJobName", "()Ljava/lang/String;", false));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false));
        il.add(new JumpInsnNode(IFEQ, labelNode0));
        il.add(new TypeInsnNode(NEW, "org/quartz/JobKey"));
        il.add(new InsnNode(DUP));
        il.add(new VarInsnNode(ALOAD, 9));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "org/quartz/impl/triggers/CronTriggerImpl", "getJobName", "()Ljava/lang/String;", false));
        il.add(new MethodInsnNode(INVOKESPECIAL, "org/quartz/JobKey", "<init>", "(Ljava/lang/String;)V", false));
        il.add(new VarInsnNode(ASTORE, 12));
        il.add(new VarInsnNode(ILOAD, 1));
        LabelNode labelNode1 = new LabelNode();
        LabelNode labelNode2 = new LabelNode();
        LabelNode labelNode3 = new LabelNode();
        LabelNode labelNode4 = new LabelNode();
        il.add(new TableSwitchInsnNode(0, 2, labelNode4, new LabelNode[] { labelNode1, labelNode2, labelNode3 }));
        il.add(labelNode1);
        il.add(new VarInsnNode(ALOAD, 11));
        il.add(new VarInsnNode(ALOAD, 12));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/quartz/Scheduler", "pauseJob", "(Lorg/quartz/JobKey;)V", true));
        il.add(new InsnNode(ICONST_1));
        il.add(new VarInsnNode(ISTORE, 4));
        il.add(new JumpInsnNode(GOTO, labelNode0));
        il.add(labelNode2);
        il.add(new VarInsnNode(ALOAD, 9));
        il.add(new VarInsnNode(ALOAD, 3));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "org/quartz/impl/triggers/CronTriggerImpl", "setCronExpression", "(Ljava/lang/String;)V", false));
        il.add(new VarInsnNode(ALOAD, 11));
        il.add(new VarInsnNode(ALOAD, 9));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "org/quartz/impl/triggers/CronTriggerImpl", "getKey", "()Lorg/quartz/TriggerKey;", false));
        il.add(new VarInsnNode(ALOAD, 9));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/quartz/Scheduler", "rescheduleJob", "(Lorg/quartz/TriggerKey;Lorg/quartz/Trigger;)Ljava/util/Date;", true));
        il.add(new InsnNode(POP));
        il.add(new InsnNode(ICONST_1));
        il.add(new VarInsnNode(ISTORE, 4));
        il.add(new JumpInsnNode(GOTO, labelNode0));
        il.add(labelNode3);
        il.add(new VarInsnNode(ALOAD, 11));
        il.add(new VarInsnNode(ALOAD, 12));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/quartz/Scheduler", "resumeJob", "(Lorg/quartz/JobKey;)V", true));
        il.add(new InsnNode(ICONST_1));
        il.add(new VarInsnNode(ISTORE, 4));
        il.add(new JumpInsnNode(GOTO, labelNode0));
        il.add(labelNode4);
        il.add(new VarInsnNode(ALOAD, 11));
        il.add(new MethodInsnNode(INVOKESTATIC, "org/quartz/impl/matchers/GroupMatcher", "anyGroup", "()Lorg/quartz/impl/matchers/GroupMatcher;", false));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/quartz/Scheduler", "getTriggerKeys", "(Lorg/quartz/impl/matchers/GroupMatcher;)Ljava/util/Set;", true));
        il.add(new VarInsnNode(ASTORE, 13));
        il.add(new VarInsnNode(ALOAD, 13));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/Set", "iterator", "()Ljava/util/Iterator;", true));
        il.add(new VarInsnNode(ASTORE, 14));
        LabelNode labelNode6 = new LabelNode();
        il.add(labelNode6);
        il.add(new VarInsnNode(ALOAD, 14));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true));
        LabelNode labelNode5 = new LabelNode();
        il.add(new JumpInsnNode(IFEQ, labelNode5));
        il.add(new VarInsnNode(ALOAD, 14));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true));
        il.add(new TypeInsnNode(CHECKCAST, "org/quartz/TriggerKey"));
        il.add(new VarInsnNode(ASTORE, 15));
        il.add(new VarInsnNode(ALOAD, 11));
        il.add(new VarInsnNode(ALOAD, 15));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/quartz/Scheduler", "getTrigger", "(Lorg/quartz/TriggerKey;)Lorg/quartz/Trigger;", true));
        il.add(new VarInsnNode(ASTORE, 16));
        il.add(new VarInsnNode(ALOAD, 16));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/quartz/Trigger", "getTriggerBuilder", "()Lorg/quartz/TriggerBuilder;", true));
        il.add(new VarInsnNode(ASTORE, 17));
        il.add(new LdcInsnNode(Type.getType("Lorg/quartz/TriggerBuilder;")));
        il.add(new LdcInsnNode("scheduleBuilder"));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/Class", "getDeclaredField", "(Ljava/lang/String;)Ljava/lang/reflect/Field;", false));
        il.add(new VarInsnNode(ASTORE, 18));
        il.add(new VarInsnNode(ALOAD, 18));
        il.add(new InsnNode(ICONST_1));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/reflect/Field", "setAccessible", "(Z)V", false));
        il.add(new VarInsnNode(ALOAD, 18));
        il.add(new VarInsnNode(ALOAD, 17));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/reflect/Field", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", false));
        il.add(new TypeInsnNode(CHECKCAST, "org/quartz/CronScheduleBuilder"));
        il.add(new VarInsnNode(ASTORE, 19));
        il.add(new LdcInsnNode(Type.getType("Lorg/quartz/CronScheduleBuilder;")));
        il.add(new LdcInsnNode("cronExpression"));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/Class", "getDeclaredField", "(Ljava/lang/String;)Ljava/lang/reflect/Field;", false));
        il.add(new VarInsnNode(ASTORE, 18));
        il.add(new VarInsnNode(ALOAD, 18));
        il.add(new InsnNode(ICONST_1));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/reflect/Field", "setAccessible", "(Z)V", false));
        il.add(new VarInsnNode(ALOAD, 18));
        il.add(new VarInsnNode(ALOAD, 19));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/reflect/Field", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", false));
        il.add(new TypeInsnNode(CHECKCAST, "org/quartz/CronExpression"));
        il.add(new VarInsnNode(ASTORE, 20));
        il.add(new VarInsnNode(ALOAD, 20));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "org/quartz/CronExpression", "getCronExpression", "()Ljava/lang/String;", false));
        il.add(new VarInsnNode(ASTORE, 3));
        il.add(new VarInsnNode(ALOAD, 5));
        il.add(new VarInsnNode(ALOAD, 16));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/quartz/Trigger", "getJobKey", "()Lorg/quartz/JobKey;", true));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "org/quartz/JobKey", "getName", "()Ljava/lang/String;", false));
        il.add(new TypeInsnNode(NEW, "java/lang/StringBuilder"));
        il.add(new InsnNode(DUP));
        il.add(new MethodInsnNode(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false));
        il.add(new VarInsnNode(ALOAD, 3));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false));
        il.add(new LdcInsnNode(" \u4efb\u52a1\u72b6\u6001\uff1a"));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false));
        il.add(new VarInsnNode(ALOAD, 11));
        il.add(new VarInsnNode(ALOAD, 15));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/quartz/Scheduler", "getTriggerState", "(Lorg/quartz/TriggerKey;)Lorg/quartz/Trigger$TriggerState;", true));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/Object;)Ljava/lang/StringBuilder;", false));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true));
        il.add(new InsnNode(POP));
        il.add(new JumpInsnNode(GOTO, labelNode6));
        il.add(labelNode5);
        il.add(new InsnNode(ICONST_0));
        il.add(new VarInsnNode(ISTORE, 4));
        il.add(labelNode0);
        il.add(new VarInsnNode(ILOAD, 4));
        LabelNode labelNode7 = new LabelNode();
        il.add(new JumpInsnNode(IFEQ, labelNode7));
        il.add(new LdcInsnNode("Success!"));
        il.add(new InsnNode(ARETURN));
        il.add(labelNode7);
        il.add(new VarInsnNode(ALOAD, 5));
        il.add(new InsnNode(ARETURN));
        methodNode.maxStack = 5;
        methodNode.maxLocals = 21;
    }
}
